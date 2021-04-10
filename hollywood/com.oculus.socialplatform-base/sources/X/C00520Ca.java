package X;

/* renamed from: X.0Ca  reason: invalid class name and case insensitive filesystem */
public final class C00520Ca extends AnonymousClass0Sy {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0Og _property;

    @Override // X.AbstractC03600nz
    public final AbstractC03600nz<Object> A01(Class<?> cls) {
        if (cls == this._scope) {
            return this;
        }
        return new C00520Ca(cls, this._property);
    }

    @Override // X.AnonymousClass0iW, X.AbstractC03600nz
    public final Object A03(Object obj) {
        try {
            return this._property.A02(obj);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(AnonymousClass006.A0B("Problem accessing property '", this._property.A06.getValue(), "': ", e2.getMessage()), e2);
        }
    }

    public C00520Ca(Class<?> cls, AnonymousClass0Og r2) {
        super(cls);
        this._property = r2;
    }

    @Override // X.AbstractC03600nz
    public final C03590ny A00(Object obj) {
        return new C03590ny(getClass(), this._scope, obj);
    }

    @Override // X.AnonymousClass0iW, X.AbstractC03600nz
    public final boolean A04(AbstractC03600nz<?> r4) {
        if (r4.getClass() != getClass()) {
            return false;
        }
        C00520Ca r42 = (C00520Ca) r4;
        if (r42.A02() == this._scope && r42._property == this._property) {
            return true;
        }
        return false;
    }
}
