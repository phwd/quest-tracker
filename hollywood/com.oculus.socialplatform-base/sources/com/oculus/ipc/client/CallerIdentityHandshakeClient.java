package com.oculus.ipc.client;

import X.AnonymousClass006;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.ipc.common.Constants;
import java.util.Locale;

public class CallerIdentityHandshakeClient {

    public static class CustomBinder extends Binder {
        public int mTargetUid;

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                int callingUid = Binder.getCallingUid();
                int i3 = this.mTargetUid;
                if (callingUid == i3) {
                    parcel.readStrongBinder().transact(1, Parcel.obtain(), null, 0);
                    return true;
                }
                throw new RemoteException(String.format(Locale.US, "Handshake transaction is not from expected uid [expected: %d, from: %d]", Integer.valueOf(i3), Integer.valueOf(Binder.getCallingUid())));
            }
            throw new RemoteException(AnonymousClass006.A03("Unexpected code was received: ", i));
        }

        public CustomBinder(int i) {
            this.mTargetUid = i;
        }
    }

    public static void addToBundle(Bundle bundle, int i) {
        bundle.putBinder(Constants.EXTRA_KEY_HANDSHAKE_BINDER, new CustomBinder(i));
    }
}
