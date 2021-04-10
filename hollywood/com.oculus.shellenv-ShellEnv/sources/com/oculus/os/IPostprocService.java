package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IPostprocService extends IInterface {

    public static class Default implements IPostprocService {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public float getBacklightDutyCycle() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void getPanelChromaticities(double[] dArr) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void getPanelColorCorrection(float[] fArr) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void setBacklightDutyCycle(float f) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void setBlackLevel(float f) {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.os.IPostprocService
        public void setPanelColorCorrection(float[] fArr, int i) {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IPostprocService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IPostprocService asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public static IPostprocService getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IPostprocService iPostprocService) {
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

    float getBacklightDutyCycle();

    void getPanelChromaticities(double[] dArr);

    void getPanelColorCorrection(float[] fArr);

    void setBacklightDutyCycle(float f);

    void setBlackLevel(float f);

    void setPanelColorCorrection(float[] fArr, int i);
}
