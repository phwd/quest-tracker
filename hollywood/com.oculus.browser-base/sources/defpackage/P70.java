package defpackage;

import java.util.HashSet;
import java.util.Set;

/* renamed from: P70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P70 implements AbstractC1641aI0 {
    public final AbstractC1821bI0 F;
    public final Object G;
    public final O70 H;
    public final AbstractC2435ev1 I;

    /* renamed from: J  reason: collision with root package name */
    public final YH0 f8670J;
    public boolean K;
    public Object L;
    public final Set M;

    public P70(AbstractC1821bI0 bi0, Object obj, O70 o70, AbstractC2435ev1 ev1, YH0 yh0) {
        HashSet hashSet = new HashSet();
        this.M = hashSet;
        this.F = bi0;
        this.G = obj;
        this.H = o70;
        this.I = ev1;
        this.f8670J = yh0;
        hashSet.addAll(bi0.a());
        ev1.a(new N70(this));
        bi0.f9530a.b(this);
    }

    public static P70 a(UH0 uh0, QH0 qh0, AbstractC2435ev1 ev1, YH0 yh0) {
        return new P70(uh0, qh0, new O70(qh0), ev1, yh0);
    }

    @Override // defpackage.AbstractC1641aI0
    public void b(AbstractC1821bI0 bi0, Object obj) {
        this.M.add(obj);
        O70 o70 = this.H;
        if (!((UH0) this.F).h(o70.f8601a) && obj != this.G) {
            return;
        }
        if (this.L != null) {
            c();
        } else if (!this.K) {
            this.K = true;
            this.I.b();
        }
    }

    public final void c() {
        boolean z = false;
        for (Object obj : this.M) {
            if (obj == this.G) {
                z = true;
            } else {
                this.f8670J.a(this.F, this.L, obj);
            }
        }
        if (z) {
            this.f8670J.a(this.F, this.L, this.G);
        }
        this.M.clear();
    }
}
