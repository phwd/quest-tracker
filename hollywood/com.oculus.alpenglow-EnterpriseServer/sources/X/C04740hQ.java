package X;

/* renamed from: X.0hQ  reason: invalid class name and case insensitive filesystem */
public final class C04740hQ {
    public final int A00;
    public final int A01;
    public final C04740hQ[] A02;

    public C04740hQ() {
        this.A02 = new C04740hQ[256];
        this.A00 = 0;
        this.A01 = 0;
    }

    public C04740hQ(int i, int i2) {
        this.A02 = null;
        this.A00 = i;
        int i3 = i2 & 7;
        this.A01 = i3 == 0 ? 8 : i3;
    }
}
