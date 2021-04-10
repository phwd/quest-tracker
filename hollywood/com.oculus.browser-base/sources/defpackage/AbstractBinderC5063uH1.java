package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: uH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC5063uH1 extends Binder implements AbstractC2161dH1, IInterface {
    public static AbstractC2161dH1 c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
        if (queryLocalInterface instanceof AbstractC2161dH1) {
            return (AbstractC2161dH1) queryLocalInterface;
        }
        return new LH1(iBinder);
    }
}
