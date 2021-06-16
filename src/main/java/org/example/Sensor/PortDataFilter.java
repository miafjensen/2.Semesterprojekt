package org.example.Sensor;

import com.google.common.base.CharMatcher;

import java.sql.Array;

public class PortDataFilter {


    public static String buffer = "";

    public int[] dataFilter() {
        int[] dataLib = new int[50];

        ConnectionEKG connEKG = new ConnectionEKG();

        while (true) {

            try{

                for (int s = 0; s < dataLib.length ; s++) {


                    String data = connEKG.readData();
                    System.out.println("data: " + data);


                    // De forskellige bider data bliver sæt sammen til en streng

                    if (data != null) {
                        buffer = buffer + data;
                        buffer = CharMatcher.inRange('0', '9').or(CharMatcher.whitespace()).retainFrom(buffer);
                        // beholder kun tegn der matcher 0-9 og mellemrum, og sorterer alt andet fra
                        //lånt fra https://guava.dev/releases/21.0/api/docs/com/google/common/base/CharMatcher.html

                    }
                    System.out.println("buffer: " + buffer);
                    if (buffer != null) {

                        String[] tempLib = buffer.split("\\s+");
                        //https://stackoverflow.com/questions/13750716/what-does-regular-expression-s-s-do
                        //bruges til at undgå whitespaces.

                        dataLib[s] = Integer.parseInt(tempLib[s]);
                        //fortolk og konverter vores data.

                        System.out.println("t: " + dataLib[s]);
                        System.out.println(s);
                    }
                }

                Thread.sleep(5);
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            return dataLib;
        }
    }
}
