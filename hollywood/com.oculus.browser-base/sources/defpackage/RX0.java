package defpackage;

import android.content.Context;
import android.content.Intent;

/* renamed from: RX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class RX0 {
    public static void a(Context context, Intent intent) {
        P21 f0 = P21.f0();
        try {
            context.startActivity(intent);
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
