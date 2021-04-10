package com.oculus.trackingserviceclient;

import android.content.Context;

public interface IConnectedRemoteFactory {
    IConnectedRemote createConnectedRemote(Context context, long j, int i, int i2, int i3);
}
