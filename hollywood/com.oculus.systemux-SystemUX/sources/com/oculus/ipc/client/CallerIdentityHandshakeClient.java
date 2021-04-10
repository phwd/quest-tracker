package com.oculus.ipc.client;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.ipc.common.Constants;
import java.util.Locale;

public class CallerIdentityHandshakeClient {
    public static void addToBundle(Bundle bundle, int i) {
        bundle.putBinder(Constants.EXTRA_KEY_HANDSHAKE_BINDER, new CustomBinder(i));
    }

    private static class CustomBinder extends Binder {
        private int mTargetUid;

        public CustomBinder(int i) {
            this.mTargetUid = i;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                throw new RemoteException("Unexpected code was received: " + i);
            } else if (Binder.getCallingUid() == this.mTargetUid) {
                parcel.readStrongBinder().transact(1, Parcel.obtain(), null, 0);
                return true;
            } else {
                throw new RemoteException(String.format(Locale.US, "Handshake transaction is not from expected uid [expected: %d, from: %d]", Integer.valueOf(this.mTargetUid), Integer.valueOf(Binder.getCallingUid())));
            }
        }
    }
}
