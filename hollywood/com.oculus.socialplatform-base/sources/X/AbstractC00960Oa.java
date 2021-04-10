package X;

import java.io.IOException;

/* renamed from: X.0Oa  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00960Oa extends AbstractC02190iF implements AnonymousClass0p5 {
    public static final long serialVersionUID = -3581199092426900829L;
    public volatile transient String A00;

    public abstract String A0O();

    @Override // X.AbstractC03910om
    public final String A02() {
        return A0O();
    }

    @Override // X.AnonymousClass0p5
    public final void A9c(AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C03620oC {
        r2.A0U(A02());
    }

    @Override // X.AnonymousClass0p5
    public final void A9d(AbstractC02300iS r1, AbstractC02120i3 r2, AbstractC04550qd r3) throws IOException, C03620oC {
        r3.A03(this, r1);
        A9c(r1, r2);
        r3.A06(this, r1);
    }

    public AbstractC00960Oa(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
    }
}
