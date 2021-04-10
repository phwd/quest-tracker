package com.oculus.vrdesktop;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public interface IVrDesktopService extends IInterface {

    public static class Default implements IVrDesktopService {
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IVrDesktopService
        public IContainer startActivity(IBinder iBinder, Surface surface, int i, int i2, int i3, Intent intent) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public Default() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IVrDesktopService {
        public static IVrDesktopService asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public static IVrDesktopService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IVrDesktopService iVrDesktopService) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public Stub() {
            throw new RuntimeException("Stub!");
        }
    }

    IContainer startActivity(IBinder iBinder, Surface surface, int i, int i2, int i3, Intent intent) throws RemoteException;
}
