package defpackage;

import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import org.chromium.base.ContextUtils;

/* renamed from: X  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class X {

    /* renamed from: a  reason: collision with root package name */
    public Boolean f9183a;
    public Boolean b;
    public C1322Vq0 c;
    public V d;

    public void a(W w) {
        c().b(w);
        w.l(d());
    }

    public final AccessibilityManager b() {
        return (AccessibilityManager) ContextUtils.getApplicationContext().getSystemService("accessibility");
    }

    public final C1322Vq0 c() {
        if (this.c == null) {
            this.c = new C1322Vq0();
        }
        return this.c;
    }

    public abstract boolean d();

    public final void e() {
        this.d = new V(this, null);
        AccessibilityManager b2 = b();
        b2.addAccessibilityStateChangeListener(this.d);
        b2.addTouchExplorationStateChangeListener(this.d);
    }

    public void f(W w) {
        c().c(w);
    }

    public void g() {
        boolean d2 = d();
        this.f9183a = null;
        this.b = null;
        if (d2 != d()) {
            boolean d3 = d();
            Iterator it = c().iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((W) uq0.next()).l(d3);
                } else {
                    return;
                }
            }
        }
    }
}
