package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IRemoteWipeCallback extends IInterface {

    public static class Default implements IRemoteWipeCallback {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public void onFailure(String str) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public void onSuccess() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IRemoteWipeCallback {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IRemoteWipeCallback asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public static IRemoteWipeCallback getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IRemoteWipeCallback iRemoteWipeCallback) {
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

    void onFailure(String str);

    void onSuccess();
}
