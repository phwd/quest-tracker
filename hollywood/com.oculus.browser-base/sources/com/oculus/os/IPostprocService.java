package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface IPostprocService extends IInterface {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public abstract class Stub extends Binder implements IPostprocService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IPostprocService asInterface(IBinder iBinder) {
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
