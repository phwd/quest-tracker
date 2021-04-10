package defpackage;

import android.os.IBinder;
import android.os.IInterface;

/* renamed from: D0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class D0 extends AbstractBinderC2658gC1 implements AbstractC4757sY {
    public static AbstractC4757sY d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if (queryLocalInterface instanceof AbstractC4757sY) {
            return (AbstractC4757sY) queryLocalInterface;
        }
        return new C4587rY(iBinder);
    }
}
