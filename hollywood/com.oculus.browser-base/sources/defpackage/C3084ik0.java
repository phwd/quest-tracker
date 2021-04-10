package defpackage;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/* renamed from: ik0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3084ik0 {

    /* renamed from: a  reason: collision with root package name */
    public final Queue f10159a = new ArrayDeque();
    public final Map b = new HashMap();
    public AbstractC4452qk0 c;
    public C2253ds d;
    public final C2399ej1 e = new C2399ej1(new RunnableC2060ck0(this));

    public final void a() {
        if (!this.f10159a.isEmpty()) {
            if (this.c == null && !this.e.b()) {
                AbstractC4452qk0 qk0 = (AbstractC4452qk0) this.f10159a.element();
                this.c = qk0;
                C2253ds dsVar = this.d;
                qk0.getClass();
                RunnableC2572fk0 fk0 = new RunnableC2572fk0(qk0);
                dsVar.e = dsVar.c.G.q();
                C0638Kj0 kj0 = dsVar.b;
                kj0.F.setVisibility(0);
                kj0.b();
                AbstractC0124Ca1 ca1 = dsVar.h;
                if (C1786b61.e(ca1 != null ? ((AbstractC0246Ea1) ca1).j() : null) == 2 || AbstractC2571fk.a(dsVar.c)) {
                    fk0.run();
                } else {
                    dsVar.f.F = fk0;
                }
            } else if (this.c != null && this.e.b()) {
                ((WW0) this.c).b(false, new RunnableC2743gk0(this));
            }
        }
    }
}
