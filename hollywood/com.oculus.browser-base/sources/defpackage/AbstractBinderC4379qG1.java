package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: qG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC4379qG1 extends Binder implements AbstractC2328eG1, IInterface {
    public static AbstractC2328eG1 c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        if (queryLocalInterface instanceof AbstractC2328eG1) {
            return (AbstractC2328eG1) queryLocalInterface;
        }
        return new FG1(iBinder);
    }
}
