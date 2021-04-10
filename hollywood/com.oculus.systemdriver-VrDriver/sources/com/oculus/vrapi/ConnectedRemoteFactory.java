package com.oculus.vrapi;

import android.content.Context;
import com.oculus.trackingserviceclient.IConnectedRemote;
import com.oculus.trackingserviceclient.IConnectedRemoteFactory;

/* compiled from: ConnectedRemote */
class ConnectedRemoteFactory implements IConnectedRemoteFactory {
    ConnectedRemoteFactory() {
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemoteFactory
    public IConnectedRemote createConnectedRemote(Context context, long deviceId, int touchMaxX, int touchMaxY, int modelId) {
        return new ConnectedRemote(context, deviceId, touchMaxX, touchMaxY, modelId);
    }
}
