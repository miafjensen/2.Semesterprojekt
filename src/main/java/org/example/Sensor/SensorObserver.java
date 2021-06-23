package org.example.Sensor;

public interface SensorObserver {
    // observerer ConnectionEKG, hvor data fra sensor kommer ind
    public void notify(EKGConn EKGConn); // bruges til at fortælle når der er data fra connectionEKG

}
