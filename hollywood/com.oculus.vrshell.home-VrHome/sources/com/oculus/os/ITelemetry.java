package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITelemetry extends IInterface {
    void recordEvent(String str, String str2) throws RemoteException;

    public static class Default implements ITelemetry {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ITelemetry
        public void recordEvent(String name, String content) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements ITelemetry {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static ITelemetry asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(ITelemetry impl) {
            throw new RuntimeException("Stub!");
        }

        public static ITelemetry getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
