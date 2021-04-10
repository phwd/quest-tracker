package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPostprocService extends IInterface {
    float getBacklightDutyCycle() throws RemoteException;

    void getPanelChromaticities(double[] dArr) throws RemoteException;

    void getPanelColorCorrection(float[] fArr) throws RemoteException;

    void setBacklightDutyCycle(float f) throws RemoteException;

    void setBlackLevel(float f) throws RemoteException;

    void setPanelColorCorrection(float[] fArr, int i) throws RemoteException;

    public static class Default implements IPostprocService {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void setBlackLevel(float newBlackLevel) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void getPanelColorCorrection(float[] payload) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void setPanelColorCorrection(float[] payload, int side) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public float getBacklightDutyCycle() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void setBacklightDutyCycle(float backlightDC) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void getPanelChromaticities(double[] chromaticities) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IPostprocService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IPostprocService asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IPostprocService impl) {
            throw new RuntimeException("Stub!");
        }

        public static IPostprocService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
