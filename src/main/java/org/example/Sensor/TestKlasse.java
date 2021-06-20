package org.example.Sensor;

import java.lang.Object.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.google.common.base.CharMatcher;
import org.example.Database.DBConn;
import org.example.Database.MeasurementDTO;
import org.example.NyMaalingController;

public class TestKlasse { // brygt til test af filtre til den rå data fra Arduino

    private static Connection connection;

    public static String buffer = "";
    private static int[] tempLib2 = new int[100];
    public static int[] dataLib = new int[100];

    public static void main(String[] args) throws Exception {

        //DBConn DBConnklasse = new DBConn();
       // Connection conn = DBConnklasse.getConnectionobject(user, password);

       // MeasurementDTO cm = new MeasurementDTO(conn);
    //    ConnectionEKG connEKG = new ConnectionEKG();
       // PortDataFilter portDataFilter = new PortDataFilter();
        //opret port - fint.

        //String[] data = portDataFilter.dataString();
        //String[] data = connEKG.getSplittedData();
       // Thread thread = new Thread(connEKG);
        //NyMaalingController nmc = new NyMaalingController();
      //  thread.start();

    //    for (String indhold :data){
           // System.out.println(indhold);
      //  }

       // int cpr = 161198;
        //cm.InsertIntoMeasurementsArray(cpr, data);

        // med String[]
       /* String[] dataStr = new String[150];
        while (true) {
            try {
                for (int s = 0; s < dataStr.length; s++) {

                    String data = connEKG.readData();
                    //System.out.println("data: " + data);

                    if (data != null) {
                        buffer = buffer + data;
                    }
                    String[] tempLib = buffer.split("\\s+");
                    dataStr[s] = tempLib[s];

                    System.out.println("t: " + dataStr[s]);
                    System.out.println("s:" + s);


                }
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        }*/

        // med int[]
        /*while(true){

        try{

            for (int s = 0; s < 10; s++) {


                String data = connEKG.readData();
             //   System.out.println("data: "+ data);


                // De forskellige bider data bliver sæt sammen til en streng

                if (data != null) {
                    buffer = buffer + data;

                    buffer = CharMatcher.inRange('0','9').or(CharMatcher.whitespace()).retainFrom(buffer); //find ny metode der ikke samler cifre
//lånt fra https://guava.dev/releases/21.0/api/docs/com/google/common/base/CharMatcher.html

                }
                //System.out.println("buffer: " + buffer);
                if (buffer != null) {


                        String[] tempLib = buffer.split("\\s*");
                        //https://stackoverflow.com/questions/13750716/what-does-regular-expression-s-s-do
                        //bruges til at undgå whitespaces.
                    //https://stackoverflow.com/questions/41953388/java-split-and-trim-in-one-shot trim

                    try{
                        tempLib2[s] = Integer.parseInt(tempLib[s]);
                    }catch (NumberFormatException ex){
                        //split igen

                      //  ex.printStackTrace();
                        System.out.println(tempLib2[s]);
                      //  System.out.println("prut");
                    }

                        //fortolk og konverter vores data.
                    if(tempLib2[s]>4096){
                        tempLib2[s] = -1;
                        System.out.println(s + " value out of bounds");

                    }
                    dataLib[s]=tempLib2[s];



                   // System.out.println("t: " + dataLib[s]);
                 //   System.out.println(s);
                }



            }

            Thread.sleep(200);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        }*/

        //vi bruger en for/tælle løkke til noget, der skal køre kontinuerligt -
        //hvad har vi indtil videre?

    }
}


