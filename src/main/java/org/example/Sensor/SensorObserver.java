package org.example.Sensor;

public interface SensorObserver {
    // observerer ConnectionEKG, hvor data fra sensor kommer ind
    public void notify(ConnectionEKG connectionEKG);

}
