package X;

public final class Ig {
    public static final Ig A05 = new Ig();
    public final C0920ov A00;
    public final C0923oy A01;
    public final C0930p5 A02;
    public final Cb A03;
    public final C0940pH A04;

    public Ig() {
        this.A03 = null;
        this.A04 = null;
        this.A02 = null;
        this.A00 = null;
        this.A01 = null;
    }

    public Ig(Cb cb, C0940pH pHVar) {
        this.A03 = cb;
        this.A04 = pHVar;
        this.A00 = new C0920ov(pHVar);
        this.A02 = new C0930p5(cb, pHVar);
        this.A01 = new C0923oy(pHVar);
    }
}
