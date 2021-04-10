package defpackage;

import java.util.Iterator;

/* renamed from: z61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5880z61 {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f11721a = new C1322Vq0();
    public AbstractC0124Ca1 b;
    public AbstractC0612Ka1 c;
    public AbstractC5783ya1 d;
    public RK e;
    public int f;
    public boolean g;

    public void a(AbstractC5710y61 y61) {
        this.f11721a.b(y61);
        AbstractC0124Ca1 ca1 = this.b;
        if (ca1 != null) {
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
            if (ea1.h) {
                y61.b(ea1.c.d().getCount(), ((AbstractC0246Ea1) this.b).r());
            }
        }
    }

    public final void b() {
        AbstractC0124Ca1 ca1 = this.b;
        if (((AbstractC0246Ea1) ca1).h) {
            int count = ((AbstractC0246Ea1) ca1).c.d().getCount();
            boolean r = ((AbstractC0246Ea1) this.b).r();
            if (this.f != count || this.g != r) {
                this.f = count;
                this.g = r;
                Iterator it = this.f11721a.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((AbstractC5710y61) uq0.next()).b(count, r);
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
