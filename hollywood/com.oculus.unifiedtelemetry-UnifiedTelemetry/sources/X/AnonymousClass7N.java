package X;

/* renamed from: X.7N  reason: invalid class name */
public final class AnonymousClass7N extends C6 {
    public static final long serialVersionUID = 1;

    @Override // X.C6, X.V4, X.W8
    public final V4 A04(AbstractC0227Wo wo) {
        if (wo == this._property) {
            return this;
        }
        return new AnonymousClass7N(this, wo);
    }

    @Override // X.C6, X.V4, X.W8
    public final EnumC0463pg A03() {
        return EnumC0463pg.EXTERNAL_PROPERTY;
    }

    public AnonymousClass7N(AbstractC0224Wl wl, V3 v3, String str, boolean z, Class<?> cls) {
        super(wl, v3, str, z, cls);
    }

    public AnonymousClass7N(AnonymousClass7N r1, AbstractC0227Wo wo) {
        super(r1, wo);
    }
}
