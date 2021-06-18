package org.example.Sensor;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.common.base.CharMatcher;

import java.util.ArrayList;


public class ConnectionEKG implements SensorObservable {
//her hentes data

    private SerialPort serialPort;

    public ConnectionEKG() {
        //Klasse til at håndtere serielporte, filtrere data, og returnere dem.

        SerialPort[] porte = SerialPort.getCommPorts();

        int EKGPort = -1;        //finder port hvor temperaur sensor er sat til
        for (int n = 0; n < porte.length; n++) {
            SerialPort port = porte[n];
            if (port.getPortDescription().equals("USBSER000"))
                //USB-Serial Controller D er navnet på sensor, som der søges efter ved port gennemgang
                EKGPort = n;
        }

        if (EKGPort != -1) {
            SerialPort port = porte[EKGPort];

            port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 10, 0); // port tjekk
            port.openPort();
            port.setBaudRate(38400);
            //højere baudrate fungerer ikke
            port.setNumDataBits(8);
            port.setNumStopBits(1);
            port.setParity(SerialPort.NO_PARITY);
            port.addDataListener(new SerialPortDataListener() {
                //https://github.com/cbudtz/EKGMonitorObserver/blob/master/src/main/java/Main.java

                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                  //  if(serialPortEvent.getEventType()!= SerialPort.LISTENING_EVENT_DATA_AVAILABLE){
                       // System.out.println(port.bytesAvailable());
                       // return;
                    //}
                //byte[] buffer = new byte[port.bytesAvailable()];
                  //  int antalByteLæst = port.readBytes(buffer, buffer.length);
                    //System.out.print(new String(buffer));
                }
            });

           // port.addDataListener();
            serialPort = port;
        }
    }

    public ArrayList<Integer> getDataArrayList() {
        //Når denne her kaldes
        for (String string :splittedData){
            //kør det splittede data igennem
            dataArrayList.add(Integer.parseInt(string));
            //konverter hver plads fra array til int, og tilføj til arrayList
        }

        return dataArrayList;
    }

    private ArrayList<Integer> dataArrayList = new ArrayList<>();
    private String[] splittedData;
    private String input = "";

    private String readFromPort(){
        byte[] buffer = new byte[serialPort.bytesAvailable()];
        int antalByteLaest = serialPort.readBytes(buffer, buffer.length);
        input = new String(buffer, 0, antalByteLaest);
        return input;
    }

    public String[] getSplittedData(){

        String materiale = readFromPort();

        String[] splittet = materiale.split("\\s+");
        //
        for (String indholdISPlittet : splittet){
            indholdISPlittet= CharMatcher.inRange('0', '9').or(CharMatcher.whitespace()).retainFrom(indholdISPlittet);

        }
    splittedData = splittet;
        //fungerer lidt som tidligere men er mere samlet.
        return splittedData;
    }


    public String readData() {
        byte[] buffer = new byte[serialPort.bytesAvailable()];
        int antalByteLæst = serialPort.readBytes(buffer, buffer.length);
        return new String(buffer, 0, antalByteLæst);
    }


    ArrayList<SensorObserver> observers = new ArrayList<>();
    @Override
    public void registerObserver(SensorObserver sensorObserver) {
        observers.add(sensorObserver);
        //hvilken klasse skal gøres afhængig af materialet herfra?
        //skal kaldes udefra, men ikke nædvendigvis portdatafilter
//hvis en anden klasse bruger denne her, skal fx. nyMålingController registrere sig selv


    }

    @Override
    public void run() {

        while(true){

           for(SensorObserver x :observers){
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

