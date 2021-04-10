package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface ICompanionServer extends IInterface {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public abstract class Stub extends Binder implements ICompanionServer {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static ICompanionServer asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            throw new RuntimeException("Stub!");
        }
    }

    void claimDevice(String str);

    String getUserId();

    void performAntiPiracyKillSwitchAction();

    void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback);
}
