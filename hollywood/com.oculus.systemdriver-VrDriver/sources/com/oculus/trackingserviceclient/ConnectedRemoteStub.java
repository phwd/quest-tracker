package com.oculus.trackingserviceclient;

import android.util.Log;

/* compiled from: IConnectedRemote */
class ConnectedRemoteStub implements IConnectedRemote {
    ConnectedRemoteStub() {
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemote
    public void destroy() {
        Log.d("IConnectedRemote", "ConnectedRemoteStub::destroy");
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemote
    public void onTouch(int touchState, int touchX, int touchY) {
        Log.d("IConnectedRemote", "ConnectedRemoteStub::onTouch");
    }

    @Override // com.oculus.trackingserviceclient.IConnectedRemote
    public void onButtonDownEvent(int buttonData) {
        Log.d("IConnectedRemote", "ConnectedRemoteStub::onButtonDownEvent");
    }
}
