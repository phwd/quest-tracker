package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface IDownloadService extends IInterface {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public abstract class Stub extends Binder implements IDownloadService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IDownloadService asInterface(IBinder iBinder) {
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

    String enqueueDownload(RemoteResource remoteResource, String str, IntentSender intentSender, Bundle bundle);

    String enqueueSizeOf(RemoteResource remoteResource, IntentSender intentSender);

    void enqueueTaggedDownload(String str, RemoteResource remoteResource, String str2, IntentSender intentSender, Bundle bundle);

    void enqueueTaggedSizeOf(String str, RemoteResource remoteResource, IntentSender intentSender);
}
