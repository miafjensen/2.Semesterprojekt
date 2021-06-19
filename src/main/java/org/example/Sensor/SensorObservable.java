package org.example.Sensor;

public interface SensorObservable extends Runnable {
    //bliver observeret og registreret af SensorObserver
    public void registerObserver(SensorObserver sensorObserver);
}
