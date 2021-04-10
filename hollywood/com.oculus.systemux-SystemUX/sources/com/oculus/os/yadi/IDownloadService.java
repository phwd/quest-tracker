package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDownloadService extends IInterface {
    String enqueueDownload(RemoteResource remoteResource, String str, IntentSender intentSender, Bundle bundle) throws RemoteException;

    String enqueueSizeOf(RemoteResource remoteResource, IntentSender intentSender) throws RemoteException;

    void enqueueTaggedDownload(String str, RemoteResource remoteResource, String str2, IntentSender intentSender, Bundle bundle) throws RemoteException;

    void enqueueTaggedSizeOf(String str, RemoteResource remoteResource, IntentSender intentSender) throws RemoteException;

    public static class Default implements IDownloadService {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public String enqueueDownload(RemoteResource remoteResource, String str, IntentSender intentSender, Bundle bundle) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public String enqueueSizeOf(RemoteResource remoteResource, IntentSender intentSender) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public void enqueueTaggedDownload(String str, RemoteResource remoteResource, String str2, IntentSender intentSender, Bundle bundle) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public void enqueueTaggedSizeOf(String str, RemoteResource remoteResource, IntentSender intentSender) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IDownloadService {
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
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IDownloadService iDownloadService) {
            throw new RuntimeException("Stub!");
        }

        public static IDownloadService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
