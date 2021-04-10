package X;

/* renamed from: X.yw  reason: case insensitive filesystem */
public final class C1410yw implements AbstractC0238Ma {
    public final /* synthetic */ AbstractC0444Yo A00;

    public C1410yw(AbstractC0444Yo yo) {
        this.A00 = yo;
    }

    @Override // X.AbstractC0238Ma
    public final void A43() {
        AbstractC0444Yo yo = this.A00;
        if (yo != null) {
            yo.A44();
        }
    }

    @Override // X.AbstractC0238Ma
    public final void onError(Exception exc) {
        C0514bB.A02(exc, "ex");
        AbstractC0444Yo yo = this.A00;
        if (yo != null) {
            yo.A45(exc);
        }
    }

    @Override // X.AbstractC0238Ma
    public final void onStart() {
        AbstractC0444Yo yo = this.A00;
        if (yo != null) {
            yo.A4M();
        }
    }
}
