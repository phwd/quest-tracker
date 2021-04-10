package com.oculus.trackingserviceclient;

public interface ITrackingServiceConnection {

    public interface IDisconnectCallback {
        void onDisconnect();
    }

    void close();

    int getTrackingSocketFd();

    boolean onClientConnect();

    void onClientDisconnect();
}
