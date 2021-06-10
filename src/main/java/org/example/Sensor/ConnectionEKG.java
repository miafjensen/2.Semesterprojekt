package org.example.Sensor;

import com.fazecast.jSerialComm.SerialPort;

public class ConnectionEKG {


    private SerialPort serialPort;

    public ConnectionEKG() {

        SerialPort[] porte = SerialPort.getCommPorts();

        int temperaturPort = -1;        //finder port hvor temperaur sensor er sat til
        for (int n = 0; n < porte.length; n++) {
            SerialPort port = porte[n];
            if (port.getPortDescription().equals("USBSER000"))
                //USB-Serial Controller D er navnet på sensor, som der søges efter ved port gennemgang
                temperaturPort = n;
        }

        if (temperaturPort != -1) {
            SerialPort port = porte[temperaturPort];
            port.openPort();
            port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 2500, 0); // port tjekkes for data hvert 2,5 ssek

            port.setBaudRate(38400);
            port.setNumDataBits(8);
            port.setNumStopBits(1);
            port.setParity(SerialPort.NO_PARITY);

            serialPort = port;
        }
    }

    public double aflæsTemperatur() {       // den metode som kaldes i projektet
        byte[] buffer = new byte[1024];
        int antalByteLæst = serialPort.readBytes(buffer, buffer.length);
        if (antalByteLæst == -1) return -1.0;
        String aflæstStreng = new String(buffer, 0, antalByteLæst);
        double grader = Double.parseDouble(aflæstStreng.substring(2, 6));
        //temperaturen aflæse i modtagne bytes og konvertere værdien til double
        return grader;
    }

}

