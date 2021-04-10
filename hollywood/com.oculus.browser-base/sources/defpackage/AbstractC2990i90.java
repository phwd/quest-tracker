package defpackage;

import java.util.Iterator;

/* renamed from: i90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2990i90 implements AbstractC2819h90 {
    public final C1322Vq0 F = new C1322Vq0();

    public void m(int i, int i2) {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2648g90) uq0.next()).e(this, i, i2);
            } else {
                return;
            }
        }
    }

    public void n(int i, int i2, Object obj) {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2648g90) uq0.next()).l(this, i, i2, obj);
            } else {
                return;
            }
        }
    }

    public void o(int i, int i2) {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2648g90) uq0.next()).f(this, i, i2);
            } else {
                return;
            }
        }
    }

    public void p(int i, int i2) {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2648g90) uq0.next()).a(this, i, i2);
            } else {
                return;
            }
        }
    }
}
