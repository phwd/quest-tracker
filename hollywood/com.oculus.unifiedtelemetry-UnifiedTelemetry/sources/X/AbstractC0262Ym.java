package X;

import java.io.IOException;

/* renamed from: X.Ym  reason: case insensitive filesystem */
public abstract class AbstractC0262Ym {
    public AbstractC0224Wl A01(AnonymousClass8M r2) {
        return null;
    }

    public CC A02() {
        return null;
    }

    public CB A03() {
        return null;
    }

    public CB A04() {
        return null;
    }

    public abstract String A0D();

    public boolean A0E() {
        return false;
    }

    public boolean A0F() {
        return false;
    }

    public boolean A0G() {
        return false;
    }

    public boolean A0H() {
        return false;
    }

    public boolean A0I() {
        return false;
    }

    public boolean A0J() {
        return false;
    }

    public boolean A0L() {
        return false;
    }

    public AbstractC0073Cr[] A0M(AnonymousClass8M r2) {
        return null;
    }

    public Object A05(AbstractC0226Wn wn) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), "; no default creator found"));
    }

    public Object A06(AbstractC0226Wn wn, double d) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Floating-point number (double)"));
    }

    public Object A07(AbstractC0226Wn wn, int i) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Integer number (int)"));
    }

    public Object A08(AbstractC0226Wn wn, long j) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Integer number (long)"));
    }

    public Object A09(AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " using delegate"));
    }

    public Object A0A(AbstractC0226Wn wn, String str) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from String value"));
    }

    public Object A0B(AbstractC0226Wn wn, boolean z) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " from Boolean value"));
    }

    public Object A0C(AbstractC0226Wn wn, Object[] objArr) throws IOException, q0 {
        throw new C0223Wj(AnonymousClass06.A05("Can not instantiate value of type ", A0D(), " with arguments"));
    }

    public boolean A0K() {
        if (A03() != null) {
            return true;
        }
        return false;
    }
}
