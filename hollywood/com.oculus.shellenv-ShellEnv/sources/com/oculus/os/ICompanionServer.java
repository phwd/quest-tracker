package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface ICompanionServer extends IInterface {

    public static class Default implements ICompanionServer {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public void claimDevice(String str) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public String getLegacyNuxOtaState() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public boolean getLegacyPreOtaComplete() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public String getUserId() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public void performAntiPiracyKillSwitchAction() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ICompanionServer
        public void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) {
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

        public static ICompanionServer getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(ICompanionServer iCompanionServer) {
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

    String getLegacyNuxOtaState();

    boolean getLegacyPreOtaComplete();

    String getUserId();

    void performAntiPiracyKillSwitchAction();

    void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback);
}
