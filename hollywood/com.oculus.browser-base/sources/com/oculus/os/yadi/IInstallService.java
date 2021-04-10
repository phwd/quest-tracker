package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface IInstallService extends IInterface {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public abstract class Stub extends Binder implements IInstallService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IInstallService asInterface(IBinder iBinder) {
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

    String enqueueInstall(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender);

    void enqueueTaggedInstall(String str, RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender);
}
