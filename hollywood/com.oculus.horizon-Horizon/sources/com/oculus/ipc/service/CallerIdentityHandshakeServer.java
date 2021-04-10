package com.oculus.ipc.service;

import X.AnonymousClass006;
import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

public class CallerIdentityHandshakeServer {
    public int mCallingUid = -1;

    public class HandshakeBinder extends Binder {
        public HandshakeBinder() {
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str;
            CallerIdentityHandshakeServer callerIdentityHandshakeServer = CallerIdentityHandshakeServer.this;
            if (callerIdentityHandshakeServer.mCallingUid != -1) {
                str = "HandshakeBinder should only receive one transaction!";
            } else if (i == 1) {
                callerIdentityHandshakeServer.mCallingUid = Binder.getCallingUid();
                return true;
            } else {
                str = AnonymousClass006.A01("Unexpected code was received: ", i);
            }
            throw new RemoteException(str);
        }
    }
}
