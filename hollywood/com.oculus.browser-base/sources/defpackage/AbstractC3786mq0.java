package defpackage;

import android.content.Context;
import org.chromium.base.ContextUtils;

/* renamed from: mq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3786mq0 {
    public static AbstractC3615lq0 a(boolean z, String str) {
        return b(z, str, null, null);
    }

    public static AbstractC3615lq0 b(boolean z, String str, String str2, C0832Np0 np0) {
        Context applicationContext = ContextUtils.getApplicationContext();
        C0340Fn fn = new C0340Fn(new C0771Mp0(applicationContext), AbstractC2250dr.f9812a, applicationContext.getResources());
        if (z) {
            return new C3620ls(applicationContext, str, fn, np0);
        }
        return new C3791ms(applicationContext, str, fn, np0);
    }
}
