package com.oculus.ipc.service;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.ipc.common.Constants;

public class CallerIdentityHandshakeServer {
    private int mCallingUid = -1;

    public int getCallingUid(Bundle bundle) throws RemoteException {
        IBinder binder = bundle.getBinder(Constants.EXTRA_KEY_HANDSHAKE_BINDER);
        if (binder != null) {
            HandshakeBinder handshakeBinder = new HandshakeBinder();
            Parcel obtain = Parcel.obtain();
            obtain.writeStrongBinder(handshakeBinder);
            binder.transact(1, obtain, null, 0);
            int i = this.mCallingUid;
            if (i != -1) {
                return i;
            }
            throw new RemoteException("HandshakeBinder did not receive a response from the client.");
        }
        throw new RemoteException("Bundle is missing handshake binder!");
    }

    private class HandshakeBinder extends Binder {
        private HandshakeBinder() {
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (CallerIdentityHandshakeServer.this.mCallingUid != -1) {
                throw new RemoteException("HandshakeBinder should only receive one transaction!");
            } else if (i == 1) {
                CallerIdentityHandshakeServer.this.mCallingUid = Binder.getCallingUid();
                return true;
            } else {
                throw new RemoteException("Unexpected code was received: " + i);
            }
        }
    }
}
