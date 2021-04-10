package com.oculus.vrapi;

import android.content.Context;
import com.oculus.trackingserviceclient.IConnectedRemote;
import com.oculus.trackingserviceclient.TrackingServiceClient;

public class ConnectedRemote implements IConnectedRemote {
    public final long DeviceIdentifier;
    public final int ModelId;

    public ConnectedRemote(Context context, long deviceIdentifier, int touchMaxX, int touchMaxY, int modelId) {
        this.DeviceIdentifier = deviceIdentifier;
        this.ModelId = modelId;
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemote
    public void destroy() {
        TrackingServiceClient.nativeSetRemoteDisconnected(this.DeviceIdentifier);
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemote
    public void onTouch(int touchState, int touchX, int touchY) {
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemote
    public void onButtonDownEvent(int buttonData) {
    }
}
