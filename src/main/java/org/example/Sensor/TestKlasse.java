package org.example.Sensor;

import java.lang.Object.*;
import java.io.IOException;
//import com.google.common.base.CharMatcher;
import com.google.common.base.CharMatcher;

public class TestKlasse {

    public static String buffer = "";
    public static int[] dataLib = new int[5000];

    public static void main(String[] args) throws Exception {

        ConnectionEKG connEKG = new ConnectionEKG();


        for (int s = 0; s < 5000; s++) {
         String data = connEKG.readData();
            System.out.println(data);


            // De forskellige bider data bliver sæt sammen til en streng
            //String var = connEKG.readData();
            if (data != null) {
                buffer = buffer + data;
                //buffer = CharMatcher.ascii().retainFrom(buffer);

            }
            if (buffer != null) {

                //buffer = buffer.replaceAll("[ ]", "t");
                //String[] tempLib = buffer.split("t");
                String[] tempLib = buffer.split("\\s+");
                //buffer = CharMatcher.javaDigit().retainFrom(buffer);


                dataLib[s] = Integer.parseInt(tempLib[s]);

                System.out.println("t: " + dataLib[s]);
                System.out.println(s);
            }


        }

    }
}

