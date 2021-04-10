package X;

/* renamed from: X.1x4  reason: invalid class name */
public final class AnonymousClass1x4 extends AbstractC12411xQ {
    public final AnonymousClass1xU A00 = new AnonymousClass1xU();
    public final C12251x9 A01;
    public final AnonymousClass1x1 A02;
    public final C12251x9 A03;
    public volatile boolean A04;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.A04) {
            this.A04 = true;
            this.A03.dispose();
        }
    }

    public AnonymousClass1x4(AnonymousClass1x1 r3) {
        this.A02 = r3;
        C12251x9 r1 = new C12251x9();
        this.A01 = r1;
        C12251x9 r0 = new C12251x9();
        this.A03 = r0;
        r0.A1D(r1);
        this.A03.A1D(this.A00);
    }
}
