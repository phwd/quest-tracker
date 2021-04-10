package com.oculus.vrdesktop;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public interface IVrDesktopService extends IInterface {
    IContainer startActivity(IBinder iBinder, Surface surface, int i, int i2, int i3, Intent intent) throws RemoteException;

    public static class Default implements IVrDesktopService {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IVrDesktopService
        public IContainer startActivity(IBinder parentActivityToken, Surface surface, int width, int height, int density, Intent intent) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IVrDesktopService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IVrDesktopService asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IVrDesktopService impl) {
            throw new RuntimeException("Stub!");
        }

        public static IVrDesktopService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
