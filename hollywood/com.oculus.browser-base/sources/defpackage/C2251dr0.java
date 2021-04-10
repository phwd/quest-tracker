package defpackage;

import android.content.Context;
import com.oculus.browser.components.OculusUser;

/* renamed from: dr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2251dr0 extends AbstractC0500Ie {
    public final /* synthetic */ Context i;

    public C2251dr0(Context context) {
        this.i = context;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Context context = this.i;
        Object obj = OculusUser.b;
        synchronized (obj) {
            if (OculusUser.f9719a == null) {
                OculusUser oculusUser = new OculusUser(context);
                oculusUser.a();
                OculusUser.f9719a = oculusUser;
                AbstractC1220Ua0.d("OculusUser", "Successfully initialized OculusUser", new Object[0]);
                obj.notify();
            }
        }
        OculusUser oculusUser2 = OculusUser.f9719a;
        return null;
    }
}
