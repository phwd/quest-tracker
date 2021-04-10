package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteWipeCallback extends IInterface {
    void onFailure(String str) throws RemoteException;

    void onSuccess() throws RemoteException;

    public static class Default implements IRemoteWipeCallback {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public void onSuccess() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IRemoteWipeCallback
        public void onFailure(String failureReason) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IRemoteWipeCallback {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IRemoteWipeCallback asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IRemoteWipeCallback impl) {
            throw new RuntimeException("Stub!");
        }

        public static IRemoteWipeCallback getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
