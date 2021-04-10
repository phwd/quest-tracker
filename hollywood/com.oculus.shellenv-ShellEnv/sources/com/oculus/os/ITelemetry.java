package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface ITelemetry extends IInterface {

    public static class Default implements ITelemetry {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.ITelemetry
        public void recordEvent(String str, String str2) {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements ITelemetry {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static ITelemetry asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public static ITelemetry getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(ITelemetry iTelemetry) {
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

    void recordEvent(String str, String str2);
}
