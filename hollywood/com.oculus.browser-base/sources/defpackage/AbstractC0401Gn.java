package defpackage;

import android.os.Build;
import org.chromium.base.ContextUtils;

/* renamed from: Gn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0401Gn {

    /* renamed from: a  reason: collision with root package name */
    public static final C0462Hn f8109a;

    static {
        if (Build.VERSION.SDK_INT < 26) {
            f8109a = new C0462Hn(false, null, null, -1);
        } else {
            f8109a = new C0462Hn(true, NU0.f8549a, new C0340Fn(new C0771Mp0(ContextUtils.getApplicationContext()), AbstractC2250dr.f9812a, ContextUtils.getApplicationContext().getResources()), 2);
        }
    }
}
