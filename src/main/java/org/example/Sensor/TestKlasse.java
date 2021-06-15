package org.example.Sensor;

import java.lang.Object.*;
import java.io.IOException;
import com.google.common.base.CharMatcher;

public class TestKlasse {

    public static String buffer = "";
    public static int[] dataLib = new int[100];

    public static void main(String[] args) throws Exception {

        ConnectionEKG connEKG = new ConnectionEKG();
//opret port - fint.

        while(true){

        try{

            for (int s = 0; s < 100; s++) {


                String data = connEKG.readData();
                System.out.println("data: "+ data);


                // De forskellige bider data bliver sæt sammen til en streng

                if (data != null) {
                    buffer = buffer + data;

                    buffer = CharMatcher.inRange('0','9').or(CharMatcher.whitespace()).retainFrom(buffer);
//lånt fra https://guava.dev/releases/21.0/api/docs/com/google/common/base/CharMatcher.html

                }
                System.out.println("buffer: " + buffer);
                if (buffer != null) {

                    String[] tempLib = buffer.split("\\s*");
                    //https://stackoverflow.com/questions/13750716/what-does-regular-expression-s-s-do
                    //bruges til at undgå whitespaces.

                    dataLib[s] = Integer.parseInt(tempLib[s]);
                    //fortolk og konverter vores data.

                    System.out.println("t: " + dataLib[s]);
                    System.out.println(s);
                }



            }

            Thread.sleep(5);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        }

        //vi bruger en for/tælle løkke til noget, der skal køre kontinuerligt -
        //hvad har vi indtil videre?



    }
}

