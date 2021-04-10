package defpackage;

import android.graphics.Typeface;

/* renamed from: fm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2577fm extends AbstractC0931Pf1 {

    /* renamed from: a  reason: collision with root package name */
    public final Typeface f9948a;
    public final C0295Ev b;
    public boolean c;

    public C2577fm(C0295Ev ev, Typeface typeface) {
        this.f9948a = typeface;
        this.b = ev;
    }

    @Override // defpackage.AbstractC0931Pf1
    public void a(int i) {
        c(this.f9948a);
    }

    @Override // defpackage.AbstractC0931Pf1
    public void b(Typeface typeface, boolean z) {
        c(typeface);
    }

    public final void c(Typeface typeface) {
        if (!this.c) {
            C0356Fv fv = this.b.f7985a;
            C2577fm fmVar = fv.v;
            boolean z = true;
            if (fmVar != null) {
                fmVar.c = true;
            }
            if (fv.s != typeface) {
                fv.s = typeface;
            } else {
                z = false;
            }
            if (z) {
                fv.j();
            }
        }
    }
}
