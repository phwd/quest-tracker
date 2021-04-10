package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IInstallService extends IInterface {
    String enqueueInstall(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) throws RemoteException;

    void enqueueTaggedInstall(String str, RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) throws RemoteException;

    public static class Default implements IInstallService {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IInstallService
        public String enqueueInstall(RemoteApp appRequest, RemoteResource[] resourceRequests, IntentSender statusIntent) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IInstallService
        public void enqueueTaggedInstall(String requestTag, RemoteApp appRequest, RemoteResource[] resourceRequests, IntentSender statusIntent) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IInstallService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IInstallService asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IInstallService impl) {
            throw new RuntimeException("Stub!");
        }

        public static IInstallService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
