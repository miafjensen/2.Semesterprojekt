package org.example.Sensor;


import com.fazecast.jSerialComm.SerialPort;

public class VisSensor {

    public static void main(String[] args) throws Exception {
        System.out.println("Viser sensorer");

        // Læs https://fazecast.github.io/jSerialComm/javadoc/com/fazecast/jSerialComm/SerialPort.html
        // for dokumentation af SerialPort klassen
        System.out.println("SerialPort.getVersion() = " + SerialPort.getVersion());

        SerialPort[] porte = SerialPort.getCommPorts();
        System.out.println("Antal serielle porte: " + porte.length);

        for (int n=0; n< porte.length; n++) {
            SerialPort port = porte[n];
            System.out.println("port nummer "+n+ " er " + port.getPortDescription());
        }

        if (porte.length>0) {
            SerialPort port = porte[0];
            port.openPort();
            port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 10000, 0);

            port.setBaudRate(38400);
            port.setNumDataBits(8);
            port.setNumStopBits(1);
            port.setParity(SerialPort.NO_PARITY);

            System.out.println("port.isOpen() = " + port.isOpen());

            for (int i=0; i<50; i++) {
                String strengFraPort = hentStrengFraPort(port);
                System.out.println(i +  " læst fra port: " + strengFraPort.substring(2,6));
                System.out.println(strengFraPort);
            }
        }

        System.out.println("Slut");
    }

    /**
     * Læser en streng fra en port
     * @param port porten, der skal læses fra. Porten skal være åben
     * @return en streng læst fra porten. Strengen kan være tom. Returnerer null hvis der ikke kunne læses fra porten
     */
    private static String hentStrengFraPort(SerialPort port) {
        byte[] buffer = new byte[1024];
        int antalByteHentet = port.readBytes(buffer, buffer.length);
        System.out.println("Læst " + antalByteHentet + " byte fra porten.");
        if (antalByteHentet==-1) return null;
        String tekst = new String(buffer, 0, antalByteHentet);
        return tekst;
    }
}


