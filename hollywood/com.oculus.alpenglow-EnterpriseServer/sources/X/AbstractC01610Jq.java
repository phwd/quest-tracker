package X;

import java.io.IOException;

/* renamed from: X.0Jq  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01610Jq extends AnonymousClass0aI implements AbstractC06310mX {
    public static final long serialVersionUID = -3581199092426900829L;
    public volatile transient String A00;

    public abstract String A0O();

    @Override // X.AnonymousClass0mE
    public final String A02() {
        return A0O();
    }

    @Override // X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C05910ld {
        r2.A0S(A02());
    }

    @Override // X.AbstractC06310mX
    public final void A7i(AbstractC02640aV r1, AnonymousClass0a8 r2, AnonymousClass0o6 r3) throws IOException, C05910ld {
        r3.A03(this, r1);
        A7h(r1, r2);
        r3.A06(this, r1);
    }

    public AbstractC01610Jq(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
    }
}
