package org.example.Sensor;

public class TestKlasse {

    public static String buffer = "";
    public static int[] dataLib = new int[5000];

    public static void main(String[] args) throws Exception {

        ConnectionEKG connEKG = new ConnectionEKG();


        for(int s=0;s<5000;s++) {
            String data = connEKG.readData();
            //System.out.println(data);


            // De forskellige bider data bliver sæt sammen til en streng
            String var = connEKG.readData();
            if(var != null) {
                buffer = buffer + data;
            }

            String[] tempLib = buffer.split("t");

            //buffer = "";
            dataLib[s] = Integer.parseInt(tempLib[s]);
            System.out.println("t: " + dataLib[s]);


        }

    }
}

