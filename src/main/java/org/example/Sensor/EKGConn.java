package org.example.Sensor;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.common.base.CharMatcher;

import java.util.ArrayList;


public class EKGConn implements SensorObservable {
//her hentes data

    private SerialPort serialPort;
    private ArrayList<Integer> dataArrayList = new ArrayList<>();
    private String[] splitData;
    private String input = "";
    ArrayList<SensorObserver> observers = new ArrayList<>();

    public EKGConn() {
        //Klasse til at håndtere serielport, filtrere data, og returnere dem.

        SerialPort[] porte = SerialPort.getCommPorts();

        int EKGPort = -1;        //finder port hvor sensor er sat til
        for (int n = 0; n < porte.length; n++) {
            SerialPort port = porte[n];
            if (port.getPortDescription().equals("USBSER001")) {
                //USBSER001 er navnet på sensor tilsluttet Mias computer,
                // som der søges efter ved port gennemgang.
                EKGPort = n;
                System.out.println("port " + EKGPort + " fundet");
            }
            if (port.getPortDescription().equals("USBSER000")) {
                //USBSER001 er navnet på sensor tilsluttet Mias computer,
                // som der søges efter ved port gennemgang.
                EKGPort = n;
                System.out.println("port " + EKGPort + " fundet");
            }
        }

        if (EKGPort != -1) {
            SerialPort port = porte[EKGPort];

            port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,
                    10, 0); // port tjekes hvert 10. ms
            port.openPort();
            System.out.println("port åbnet");
            port.setBaudRate(38400);
            //denne baudrate fungerer bedst efter forsøg med højere rates
            port.setNumDataBits(8);
            port.setNumStopBits(1);
            port.setParity(SerialPort.NO_PARITY);
            port.addDataListener(new SerialPortDataListener() {
                //https://github.com/cbudtz/EKGMonitorObserver/blob/master/src/
                // main/java/Main.java
                @Override
                public int getListeningEvents() {

                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                }
            });
            serialPort = port;
        }
    }


    private String readFromPort() {
        byte[] buffer = new byte[serialPort.bytesAvailable()];
        int antalByteLaest = serialPort.readBytes(buffer, buffer.length);
        input = new String(buffer, 0, antalByteLaest);
        return input;
    }

    public String[] getSplitData() {

        String material = readFromPort();

        String[] splittet = material.split("\\s+");
        //bruges til at undgå whitespaces.
        //https://stackoverflow.com/questions/13750716/what-does-regular-expression-s-s-do

        for (String indholdISPlittet : splittet) {
            indholdISPlittet = CharMatcher.inRange('0', '9').
                    or(CharMatcher.whitespace()).retainFrom(indholdISPlittet);
            // beholder kun tegn der matcher 0-9 og mellemrum, og sorterer alt andet fra
            //lånt fra https://guava.dev/releases/21.0/api/docs/com/google/common/
            // base/CharMatcher.html
        }
        splitData = splittet;

        return splitData;
    }

    public ArrayList<Integer> getDataArrayList() {
        for (String string : splitData) {
            //kører det splittede data igennem
            dataArrayList.add(Integer.parseInt(string));
            //konverterer indhold i array til int, og tilføjes til arrayList plads for plads
        }
        return dataArrayList;
    }

    @Override
    public void registerObserver(SensorObserver sensorObserver) {
        //kaldes fra SensorObservable og gør denne klasse observerbar for SensorObserver
        observers.add(sensorObserver);
    }

    @Override
    public void run() {         //sørger for at loopet kører

        while (true) {          // kører uendeligt

            for (SensorObserver x : observers) {
                x.notify(this);
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

