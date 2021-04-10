package com.oculus.ipc.client;

import X.AnonymousClass006;
import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Locale;

public class CallerIdentityHandshakeClient {

    public static class CustomBinder extends Binder {
        public int mTargetUid;

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str;
            if (i == 1) {
                int callingUid = Binder.getCallingUid();
                int i3 = this.mTargetUid;
                if (callingUid == i3) {
                    parcel.readStrongBinder().transact(1, Parcel.obtain(), null, 0);
                    return true;
                }
                str = String.format(Locale.US, "Handshake transaction is not from expected uid [expected: %d, from: %d]", Integer.valueOf(i3), Integer.valueOf(Binder.getCallingUid()));
            } else {
                str = AnonymousClass006.A01("Unexpected code was received: ", i);
            }
            throw new RemoteException(str);
        }

        public CustomBinder(int i) {
            this.mTargetUid = i;
        }
    }
}
