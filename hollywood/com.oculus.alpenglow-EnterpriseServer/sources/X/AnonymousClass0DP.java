package X;

/* renamed from: X.0DP  reason: invalid class name */
public final class AnonymousClass0DP extends AnonymousClass0LQ {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0Jw _property;

    @Override // X.AnonymousClass0lR
    public final AnonymousClass0lR<Object> A01(Class<?> cls) {
        if (cls == this._scope) {
            return this;
        }
        return new AnonymousClass0DP(cls, this._property);
    }

    @Override // X.AnonymousClass0lR, X.AbstractC02680aZ
    public final Object A03(Object obj) {
        try {
            return this._property.A02(obj);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(AnonymousClass006.A08("Problem accessing property '", this._property.A06.getValue(), "': ", e2.getMessage()), e2);
        }
    }

    public AnonymousClass0DP(Class<?> cls, AnonymousClass0Jw r2) {
        super(cls);
        this._property = r2;
    }

    @Override // X.AnonymousClass0lR
    public final C05800lQ A00(Object obj) {
        return new C05800lQ(getClass(), this._scope, obj);
    }

    @Override // X.AnonymousClass0lR, X.AbstractC02680aZ
    public final boolean A04(AnonymousClass0lR<?> r4) {
        if (r4.getClass() != getClass()) {
            return false;
        }
        AnonymousClass0DP r42 = (AnonymousClass0DP) r4;
        if (r42.A02() == this._scope && r42._property == this._property) {
            return true;
        }
        return false;
    }
}
