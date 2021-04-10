package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: fZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC2539fZ extends Binder implements AbstractC2710gZ {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f9928a = 0;

    public static AbstractC2710gZ c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("org.chromium.components.payments.IPaymentDetailsUpdateServiceCallback");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC2710gZ)) {
            return new C2368eZ(iBinder);
        }
        return (AbstractC2710gZ) queryLocalInterface;
    }
}
