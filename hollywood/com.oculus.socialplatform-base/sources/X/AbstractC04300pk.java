package X;

import java.io.IOException;

/* renamed from: X.0pk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04300pk {
    public AbstractC02190iF A01(AnonymousClass0HU r2) {
        return null;
    }

    public AnonymousClass0Ox A02() {
        return null;
    }

    public AnonymousClass0Ow A03() {
        return null;
    }

    public AnonymousClass0Ow A04() {
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

    public AbstractC01170Rz[] A0M(AnonymousClass0HU r2) {
        return null;
    }

    public Object A05(AbstractC02210iH r4) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), "; no default creator found"));
    }

    public Object A06(AbstractC02210iH r4, double d) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Floating-point number (double)"));
    }

    public Object A07(AbstractC02210iH r4, int i) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Integer number (int)"));
    }

    public Object A08(AbstractC02210iH r4, long j) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Integer number (long)"));
    }

    public Object A09(AbstractC02210iH r4, Object obj) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " using delegate"));
    }

    public Object A0A(AbstractC02210iH r4, String str) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from String value"));
    }

    public Object A0B(AbstractC02210iH r4, boolean z) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " from Boolean value"));
    }

    public Object A0C(AbstractC02210iH r4, Object[] objArr) throws IOException, C03620oC {
        throw new C02180iD(AnonymousClass006.A09("Can not instantiate value of type ", A0D(), " with arguments"));
    }

    public boolean A0K() {
        if (A03() != null) {
            return true;
        }
        return false;
    }
}
