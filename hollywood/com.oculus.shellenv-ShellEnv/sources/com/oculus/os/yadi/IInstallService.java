package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IInstallService extends IInterface {

    public static class Default implements IInstallService {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IInstallService
        public String enqueueInstall(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IInstallService
        public void enqueueTaggedInstall(String str, RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IInstallService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IInstallService asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public static IInstallService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IInstallService iInstallService) {
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
