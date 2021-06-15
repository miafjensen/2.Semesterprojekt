package org.example.Sensor;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class ConnectionEKG implements SensorObservable {
//her hentes data

    private SerialPort serialPort;

    public ConnectionEKG() {

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
                    if(serialPortEvent.getEventType()!= SerialPort.LISTENING_EVENT_DATA_AVAILABLE){
                        return;
                    }
                byte[] buffer = new byte[port.bytesAvailable()];
                    int antalByteLæst = port.readBytes(buffer, buffer.length);
                    System.out.print(new String(buffer));
                }
            });

           // port.addDataListener();
            serialPort = port;
        }
    }

    public String readData() {
        byte[] buffer = new byte[serialPort.bytesAvailable()];
        int antalByteLæst = serialPort.readBytes(buffer, buffer.length);
        return new String(buffer, 0, antalByteLæst);
    }


    @Override
    public void registerObserver(SensorObserver sensorObserver) {
        //hvilken klasse skal gøres afhængig af materialet herfra?
        //skal kaldes udefra, men ikke nædvendigvis portdatafilter

    }

    @Override
    public void run() {

    }
}

