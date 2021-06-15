package org.example.Sensor;

public interface SensorObservable extends Runnable {
    //Giv mig til noget du vil sørge for kan observeres.

    public void registerObserver(SensorObserver sensorObserver);


}
