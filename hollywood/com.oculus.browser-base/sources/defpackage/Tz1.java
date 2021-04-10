package defpackage;

import android.os.IBinder;
import android.os.IInterface;

/* renamed from: Tz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Tz1 implements AbstractC5722yA1 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC5722yA1 f9001a = new Tz1();

    @Override // defpackage.AbstractC5722yA1
    public final Object a(IBinder iBinder) {
        int i = AbstractBinderC3677mA1.f10403a;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.appupdate.protocol.IAppUpdateService");
        return queryLocalInterface instanceof AbstractC3848nA1 ? (AbstractC3848nA1) queryLocalInterface : new C4190pA1(iBinder);
    }
}
