package org.example.Sensor;

import com.fazecast.jSerialComm.SerialPort;
import jssc.SerialPortException;

public class ConnectionEKG {


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
            port.openPort();
            port.setComPortTimeouts(SerialPort.NO_PARITY, 0, 0); // port tjekk

            port.setBaudRate(38400);
            port.setNumDataBits(8);
            port.setNumStopBits(1);
            port.setParity(SerialPort.NO_PARITY);

            serialPort = port;
        }
    }

    public String readData() {
        byte[] buffer = new byte[1024];
        int antalByteLæst = serialPort.readBytes(buffer, buffer.length);
        String rawData = new String(buffer, 0, antalByteLæst);
        return rawData;
    }


}

