package defpackage;

import java.util.Objects;

/* renamed from: q41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4343q41 extends AbstractC5771yV {

    /* renamed from: a  reason: collision with root package name */
    public int f11114a;
    public float b;
    public float c;
    public final /* synthetic */ AbstractC4854t41 d;

    public C4343q41(AbstractC4854t41 t41) {
        this.d = t41;
    }

    @Override // defpackage.AbstractC5601xV
    public void a(int i, int i2) {
        Objects.requireNonNull((B10) this.d);
        if (B10.P && AbstractC4854t41.a(this.d)) {
            g(i, i2);
            this.f11114a = i;
            this.d.I = 1;
        }
    }

    @Override // defpackage.AbstractC5601xV, defpackage.AbstractC5431wV
    public void b(int i, int i2) {
        Objects.requireNonNull((B10) this.d);
        if (B10.P && AbstractC4854t41.a(this.d)) {
            g(i, i2);
            this.d.I = 2;
        }
    }

    @Override // defpackage.AbstractC5601xV
    public void e(int i, int i2) {
        AbstractC4854t41 t41 = this.d;
        boolean z = true;
        if (t41.I == 1) {
            t41.I = 0;
            h(i, i2);
            AbstractC4854t41 t412 = this.d;
            boolean z2 = ((float) i) < ((float) t412.M) * 0.5f;
            boolean z3 = t412.getTranslationY() < ((float) t412.M) * 0.5f;
            if (!z2 && !z3) {
                z = false;
            }
            t412.c(z);
        }
    }

    @Override // defpackage.AbstractC5601xV, defpackage.AbstractC5431wV
    public void f(int i, int i2) {
        AbstractC4854t41 t41 = this.d;
        if (t41.I == 2) {
            boolean z = false;
            t41.I = 0;
            h(i, i2);
            boolean z2 = i > this.f11114a;
            float f = this.b;
            AbstractC4854t41 t412 = this.d;
            boolean z3 = f < ((float) t412.M);
            float translationY = t412.getTranslationY();
            AbstractC4854t41 t413 = this.d;
            int i3 = t413.M;
            boolean z4 = 1.0f - (translationY / ((float) i3)) > (z3 ? 0.9f : 0.2f);
            boolean z5 = ((float) i) < ((float) i3) * 0.5f;
            if ((!z2 && z4) || z5) {
                z = true;
            }
            t413.c(z);
        }
    }

    public final void g(int i, int i2) {
        AbstractC4854t41 t41 = this.d;
        t41.L = i;
        this.c = (float) i2;
        this.b = t41.getTranslationY();
    }

    public final void h(int i, int i2) {
        AbstractC4854t41 t41 = this.d;
        float b2 = AbstractC4089od0.b(this.b + (((float) i2) - this.c) + ((float) (i - t41.L)), (float) t41.M, 0.0f);
        if (b2 <= 0.0f) {
            g(i, i2);
        }
        this.d.setTranslationY(b2);
    }
}
