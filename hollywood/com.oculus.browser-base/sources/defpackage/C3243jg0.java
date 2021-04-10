package defpackage;

import java.util.Iterator;

/* renamed from: jg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3243jg0 extends AbstractC0750Mg0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DialogC5460wg0 f10227a;

    public C3243jg0(DialogC5460wg0 wg0) {
        this.f10227a = wg0;
    }

    @Override // defpackage.AbstractC0750Mg0
    public void d(C3246jh0 jh0, C2392eh0 eh0) {
        this.f10227a.n();
    }

    @Override // defpackage.AbstractC0750Mg0
    public void e(C3246jh0 jh0, C2392eh0 eh0) {
        boolean z;
        C2222dh0 b;
        if (eh0 == this.f10227a.L && eh0.a() != null) {
            Iterator it = eh0.f9872a.b().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C2392eh0 eh02 = (C2392eh0) it.next();
                if (!this.f10227a.L.c().contains(eh02) && (b = this.f10227a.L.b(eh02)) != null && b.a() && !this.f10227a.N.contains(eh02)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (z) {
            this.f10227a.o();
            this.f10227a.m();
            return;
        }
        this.f10227a.n();
    }

    @Override // defpackage.AbstractC0750Mg0
    public void f(C3246jh0 jh0, C2392eh0 eh0) {
        this.f10227a.n();
    }

    @Override // defpackage.AbstractC0750Mg0
    public void g(C3246jh0 jh0, C2392eh0 eh0) {
        DialogC5460wg0 wg0 = this.f10227a;
        wg0.L = eh0;
        wg0.o();
        this.f10227a.m();
    }

    @Override // defpackage.AbstractC0750Mg0
    public void h(C3246jh0 jh0, C2392eh0 eh0) {
        this.f10227a.n();
    }

    @Override // defpackage.AbstractC0750Mg0
    public void j(C3246jh0 jh0, C2392eh0 eh0) {
        AbstractC3072ig0 ig0;
        int i = DialogC5460wg0.H;
        DialogC5460wg0 wg0 = this.f10227a;
        if (wg0.Z != eh0 && (ig0 = (AbstractC3072ig0) wg0.Y.get(eh0.c)) != null) {
            int i2 = ig0.Z.o;
            ig0.y(i2 == 0);
            ig0.b0.setProgress(i2);
        }
    }
}
