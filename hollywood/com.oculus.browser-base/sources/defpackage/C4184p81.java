package defpackage;

import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ContextUtils;

/* renamed from: p81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4184p81 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f11050a = new Object();
    public static C4184p81 b;
    public final AtomicInteger c;
    public PU0 d;

    public C4184p81(Context context) {
        AtomicInteger atomicInteger = new AtomicInteger();
        this.c = atomicInteger;
        PU0 pu0 = NU0.f8549a;
        this.d = pu0;
        atomicInteger.set(pu0.f("org.chromium.chrome.browser.tab.TabIdManager.NEXT_ID", 0));
    }

    public static C4184p81 a() {
        Context applicationContext = ContextUtils.getApplicationContext();
        synchronized (f11050a) {
            if (b == null) {
                b = new C4184p81(applicationContext);
            }
        }
        return b;
    }

    public final void b(int i) {
        int i2 = i - this.c.get();
        if (i2 >= 0) {
            this.c.addAndGet(i2);
            this.d.n("org.chromium.chrome.browser.tab.TabIdManager.NEXT_ID", this.c.get());
        }
    }
}
