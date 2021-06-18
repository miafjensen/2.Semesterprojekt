package org.example.Sensor;

public interface SensorObserver {

    public void notify(ConnectionEKG connectionEKG);
    //det giver man til en, som skal observere, fx en sensor
}
