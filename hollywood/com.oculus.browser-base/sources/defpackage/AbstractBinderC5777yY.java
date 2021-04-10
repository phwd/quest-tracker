package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* renamed from: yY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC5777yY extends Binder implements AbstractC5947zY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f11687a = 0;

    public static AbstractC5947zY c(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.customtabs.ICustomTabsCallback");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC5947zY)) {
            return new C5607xY(iBinder);
        }
        return (AbstractC5947zY) queryLocalInterface;
    }
}
