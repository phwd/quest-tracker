package com.oculus.trackingserviceclient;

import android.content.Context;
import android.util.Log;

/* compiled from: IConnectedRemoteFactory */
class ConnectedRemoteStubFactory implements IConnectedRemoteFactory {
    ConnectedRemoteStubFactory() {
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemoteFactory
    public IConnectedRemote createConnectedRemote(Context context, long deviceId, int touchMaxX, int touchMaxY, int modelId) {
        Log.d("IConnectedRemote", "ConnectedRemoteStubFactory::createConnectedRemote");
        return new ConnectedRemoteStub();
    }
}
