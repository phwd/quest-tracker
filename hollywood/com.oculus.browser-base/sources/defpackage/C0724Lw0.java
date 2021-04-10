package defpackage;

import J.N;
import java.util.HashSet;

/* renamed from: Lw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0724Lw0 extends AbstractC2032cb {
    public final Object i = new Object();
    public final /* synthetic */ C0785Mw0 j;

    public C0724Lw0(C0785Mw0 mw0, C0603Jw0 jw0) {
        this.j = mw0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0047  */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 363
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0724Lw0.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r2 = (Void) obj;
        synchronized (this.i) {
            this.j.a();
        }
    }

    public final void m(C0359Fw0 fw0, HashSet hashSet) {
        if (!hashSet.contains(fw0)) {
            hashSet.add(fw0);
            if (fw0.f8052a != 0) {
                try {
                    synchronized (this.i) {
                        C0785Mw0 mw0 = this.j;
                        String str = fw0.d;
                        fw0.h = N.M1QiERX9(mw0.c, mw0, str, fw0.e, fw0.c, fw0.b, fw0.f, fw0.g, mw0.b.c(str), AbstractC4656rv1.a(mw0.d, 16.0f), new C0603Jw0(mw0, str));
                    }
                } catch (IllegalArgumentException e) {
                    StringBuilder i2 = AbstractC2531fV.i("Error inserting bookmark ");
                    i2.append(fw0.e);
                    AbstractC1220Ua0.f("PartnerBMReader", i2.toString(), e);
                }
                if (fw0.h == -1) {
                    AbstractC1220Ua0.a("PartnerBMReader", AbstractC2531fV.h(AbstractC2531fV.i("Error creating bookmark '"), fw0.e, "'."), new Object[0]);
                    return;
                }
            }
            if (fw0.c) {
                for (C0359Fw0 fw02 : fw0.j) {
                    if (fw02.i != fw0) {
                        AbstractC1220Ua0.f("PartnerBMReader", AbstractC2531fV.h(AbstractC2531fV.i("Hierarchy error in bookmark '"), fw0.e, "'. Skipping."), new Object[0]);
                    } else {
                        fw02.b = fw0.h;
                        m(fw02, hashSet);
                    }
                }
            }
        }
    }
}
