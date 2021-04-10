package defpackage;

/* renamed from: MW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MW0 extends ZR implements AbstractC1649aL0 {
    public final FW0 G;
    public final NW0 H;
    public final KW0 I;

    public MW0(C1794b90 b90, NW0 nw0, LW0 lw0) {
        KW0 kw0 = new KW0(lw0);
        this.H = nw0;
        this.I = kw0;
        this.G = b90;
        b90.F.b(this);
    }

    @Override // defpackage.AbstractC1649aL0
    public void b(Object obj, int i, Object obj2) {
        KW0 kw0 = this.I;
        Void r5 = (Void) obj2;
        kw0.f8370a.a(obj, ((AbstractC1965c90) this.G).G.get(i));
    }

    @Override // defpackage.AbstractC1649aL0
    public void c(Object obj) {
    }

    @Override // defpackage.AbstractC1649aL0
    public int getItemViewType(int i) {
        NW0 nw0 = this.H;
        if (nw0 == null) {
            return 0;
        }
        return nw0.a(((AbstractC1965c90) this.G).G.get(i));
    }

    @Override // defpackage.AbstractC1649aL0
    public int h() {
        return ((AbstractC1965c90) this.G).size();
    }
}
