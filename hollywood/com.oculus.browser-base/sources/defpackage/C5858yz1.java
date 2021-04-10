package defpackage;

import android.content.Context;

/* renamed from: yz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5858yz1 {

    /* renamed from: a  reason: collision with root package name */
    public static C5858yz1 f11715a = new C5858yz1();
    public C3969nu0 b = null;

    public static C3969nu0 a(Context context) {
        C3969nu0 nu0;
        C5858yz1 yz1 = f11715a;
        synchronized (yz1) {
            if (yz1.b == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                yz1.b = new C3969nu0(context);
            }
            nu0 = yz1.b;
        }
        return nu0;
    }
}
