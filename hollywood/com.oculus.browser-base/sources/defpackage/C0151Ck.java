package defpackage;

import android.os.Handler;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* renamed from: Ck  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0151Ck implements AbstractC0624Ke1 {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f7835a;
    public static boolean b;
    public final WeakHashMap c = new WeakHashMap();

    public static void f() {
        if (!f7835a) {
            f7835a = true;
            Objects.requireNonNull(C2076cp1.f9721a);
            PostTask.e.set(1, new C0151Ck());
        }
    }

    @Override // defpackage.AbstractC0624Ke1
    public BS0 a(C3070if1 if1) {
        return e(if1);
    }

    @Override // defpackage.AbstractC0624Ke1
    public void b(C3070if1 if1, Runnable runnable, long j) {
        e(if1).a(runnable, j);
    }

    @Override // defpackage.AbstractC0624Ke1
    public AbstractC2387ef1 c(C3070if1 if1) {
        return e(if1);
    }

    @Override // defpackage.AbstractC0624Ke1
    public boolean d(C3070if1 if1) {
        return e(if1).c();
    }

    public AbstractC5436wX0 e(C3070if1 if1) {
        C2076cp1 cp1;
        AbstractC5436wX0 wx0;
        synchronized (this.c) {
            WeakReference weakReference = (WeakReference) this.c.get(if1);
            if (weakReference != null && (wx0 = (AbstractC5436wX0) weakReference.get()) != null) {
                return wx0;
            }
            Handler b2 = ThreadUtils.b();
            boolean z = false;
            if (b) {
                C1905bp1 bp1 = C2076cp1.f9721a;
                if (if1.m == 1) {
                    byte b3 = if1.n[1];
                    cp1 = new C2076cp1();
                    cp1.g = b3;
                } else {
                    cp1 = null;
                }
                if (cp1 != null) {
                    if (cp1.g == 1) {
                        z = true;
                    }
                }
            }
            C5606xX0 xx0 = new C5606xX0(b2, if1, z);
            this.c.put(if1, new WeakReference(xx0));
            return xx0;
        }
    }
}
