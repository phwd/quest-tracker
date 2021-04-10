package defpackage;

import com.google.android.gms.internal.location.zzbf;

/* renamed from: oJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4046oJ1 extends AC1 {
    public final /* synthetic */ AbstractC0489Ia0 q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4046oJ1(YV yv, AbstractC0489Ia0 ia0) {
        super(yv);
        this.q = ia0;
    }

    @Override // defpackage.AbstractC4439qg
    public final void j(Z6 z6) {
        AbstractC0489Ia0 ia0 = this.q;
        String simpleName = AbstractC0489Ia0.class.getSimpleName();
        SE0.i(ia0, "Listener must not be null");
        SE0.i(simpleName, "Listener type must not be null");
        SE0.g(simpleName, "Listener type must not be empty");
        C5378w90 w90 = new C5378w90(ia0, simpleName);
        HC1 hc1 = new HC1(this);
        SD1 sd1 = ((C3176jE1) z6).F;
        sd1.f8885a.f8215a.c();
        SE0.i(w90, "Invalid null listener key");
        synchronized (sd1.d) {
            BinderC2493fE1 fe1 = (BinderC2493fE1) sd1.d.remove(w90);
            if (fe1 != null) {
                synchronized (fe1) {
                    fe1.b.b = null;
                }
                ((JD1) sd1.f8885a.a()).f(zzbf.x(fe1, hc1));
            }
        }
    }
}
