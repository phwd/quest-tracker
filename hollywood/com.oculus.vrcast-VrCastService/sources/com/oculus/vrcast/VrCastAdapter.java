package com.oculus.vrcast;

public interface VrCastAdapter {
    void connect(CastDevice castDevice);

    void disconnect(CastDevice castDevice);

    void startDiscovery();

    void stopDiscovery();
}
