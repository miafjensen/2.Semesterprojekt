package org.example.Sensor;

import com.google.common.base.CharMatcher;

public class PortDataFilter {

    public static String buffer = "";


    public  int[] dataFilter() {
         int[] dataLib = new int[5000];

        for (int s = 0; s < 5000; s++) {
            ConnectionEKG connEKG = new ConnectionEKG();
            String data = connEKG.readData();
            System.out.println(data);
            //int s = 0;


            // De forskellige bider data bliver sæt sammen til en streng
            //String var = connEKG.readData();
            if (data != null) {
                buffer = buffer + data;


            }
            if (buffer != null) {
                buffer = CharMatcher.inRange('0', '9').or(CharMatcher.whitespace()).retainFrom(buffer);
                String[] tempLib = buffer.split("\\s+");
                dataLib[s] = Integer.parseInt(tempLib[s]);

                System.out.println("t: " + dataLib[s]);
                System.out.println(s);

            }

        }
        return dataLib;
    }
}
