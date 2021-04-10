package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* renamed from: Q7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Q7 {
    public static int F = -100;
    public static final C5271va G = new C5271va(0);
    public static final Object H = new Object();

    public static void j(Q7 q7) {
        synchronized (H) {
            C5271va vaVar = G;
            Objects.requireNonNull(vaVar);
            C5101ua uaVar = new C5101ua(vaVar);
            while (uaVar.hasNext()) {
                Q7 q72 = (Q7) ((WeakReference) uaVar.next()).get();
                if (q72 == q7 || q72 == null) {
                    uaVar.remove();
                }
            }
        }
    }

    public static void o(int i) {
        if ((i == -1 || i == 0 || i == 1 || i == 2 || i == 3) && F != i) {
            F = i;
            synchronized (H) {
                C5271va vaVar = G;
                Objects.requireNonNull(vaVar);
                C5101ua uaVar = new C5101ua(vaVar);
                while (uaVar.hasNext()) {
                    Q7 q7 = (Q7) ((WeakReference) uaVar.next()).get();
                    if (q7 != null) {
                        q7.d();
                    }
                }
            }
        }
    }

    public abstract void c(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean d();

    public abstract void e();

    public abstract void f();

    public abstract void g(Bundle bundle);

    public abstract void h();

    public abstract void i();

    public abstract boolean k(int i);

    public abstract void l(int i);

    public abstract void m(View view);

    public abstract void n(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void p(CharSequence charSequence);
}
