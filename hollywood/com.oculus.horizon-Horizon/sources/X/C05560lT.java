package X;

import java.io.IOException;

/* renamed from: X.0lT  reason: invalid class name and case insensitive filesystem */
public final class C05560lT {
    public AbstractC05550lS A00;
    public Object A01;
    public int A02;
    public final AbstractC04020gg A03;
    public final C05530lQ A04;
    public final Object[] A05;
    public final AbstractC04100gp A06;

    public final void A00(C05480lF r3, String str, Object obj) {
        this.A00 = new C03880gL(this.A00, obj, r3, str);
    }

    public final void A01(AnonymousClass0HD r3, Object obj) {
        this.A00 = new C03860gJ(this.A00, obj, r3);
    }

    public final boolean A02(int i, Object obj) {
        this.A05[i] = obj;
        int i2 = this.A02 - 1;
        this.A02 = i2;
        if (i2 > 0) {
            return false;
        }
        return true;
    }

    public final boolean A03(String str) throws IOException {
        C05530lQ r1 = this.A04;
        if (r1 == null || !str.equals(r1.propertyName)) {
            return false;
        }
        this.A01 = r1.deserializer.A09(this.A06, this.A03);
        return true;
    }

    public C05560lT(AbstractC04100gp r2, AbstractC04020gg r3, int i, C05530lQ r5) {
        this.A06 = r2;
        this.A03 = r3;
        this.A02 = i;
        this.A04 = r5;
        this.A05 = new Object[i];
    }
}
