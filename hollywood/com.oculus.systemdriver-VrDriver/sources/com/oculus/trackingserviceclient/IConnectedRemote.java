package com.oculus.trackingserviceclient;

public interface IConnectedRemote {
    void destroy();

    void onButtonDownEvent(int i);

    void onTouch(int i, int i2, int i3);
}
