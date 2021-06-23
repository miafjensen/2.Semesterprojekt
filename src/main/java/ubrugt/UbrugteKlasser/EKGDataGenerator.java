package ubrugt.UbrugteKlasser;

import org.example.Sensor.SensorObservable;
import org.example.Sensor.SensorObserver;

public abstract class EKGDataGenerator implements SensorObservable {

    //private ArrayList<PortDataFilter>

    public void registerObserver(SensorObserver sensorObserver) {


    }

    @Override
    public void run() {

        while (true){


        }

        // kan hente array fra PortDataFilter kan laves i constructor oven over

        // lav if løkke, der registrerer om array er forskelligt fra 0 og har relevant mængde data,


    }
}
