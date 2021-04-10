package com.oculus.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IQuirksDb extends IInterface {

    public static class Default implements IQuirksDb {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IQuirksDb
        public Bundle getQuirkForProcess(int i, int i2) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IQuirksDb
        public void setQuirksForCaller(Bundle bundle) {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IQuirksDb {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IQuirksDb asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public static IQuirksDb getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IQuirksDb iQuirksDb) {
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

    Bundle getQuirkForProcess(int i, int i2);

    void setQuirksForCaller(Bundle bundle);
}
