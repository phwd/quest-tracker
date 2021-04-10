package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICompanionServer extends IInterface {
    void claimDevice(String str) throws RemoteException;

    String getLegacyNuxOtaState() throws RemoteException;

    boolean getLegacyPreOtaComplete() throws RemoteException;

    String getUserId() throws RemoteException;

    void performAntiPiracyKillSwitchAction() throws RemoteException;

    void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) throws RemoteException;

    public static class Default implements ICompanionServer {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public String getUserId() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public void claimDevice(String str) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public void performAntiPiracyKillSwitchAction() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public boolean getLegacyPreOtaComplete() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public String getLegacyNuxOtaState() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements ICompanionServer {
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
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(ICompanionServer iCompanionServer) {
            throw new RuntimeException("Stub!");
        }

        public static ICompanionServer getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
