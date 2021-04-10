package X;

/* renamed from: X.1ua  reason: invalid class name */
public class AnonymousClass1ua extends AbstractC12091uu implements AnonymousClass1uj<AbstractC12101uv> {
    public final AnonymousClass1uY<AbstractC12101uv> A00;

    @Override // X.AnonymousClass1uj
    public final void A9y(AnonymousClass0AS r1) {
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1uj
    public final void A1I(AbstractC12101uv r1) {
        r1.addOnPropertyChangedCallback(this);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass1uj
    public final void A9B(AbstractC12101uv r1) {
        r1.removeOnPropertyChangedCallback(this);
    }

    @Override // X.AbstractC12091uu
    public final void onPropertyChanged(AbstractC12101uv r4, int i) {
        AnonymousClass1uY<AbstractC12101uv> r2 = this.A00;
        AnonymousClass1uW r1 = (AnonymousClass1uW) r2.get();
        if (r1 == null) {
            r2.A00();
        } else if (r2.A00 == r4) {
            r1.handleFieldChange(r2.A02, r4, i);
        }
    }

    public AnonymousClass1ua(AnonymousClass1uW r2, int i) {
        this.A00 = new AnonymousClass1uY<>(r2, i, this);
    }
}
