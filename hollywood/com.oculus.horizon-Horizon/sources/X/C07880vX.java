package X;

/* renamed from: X.0vX  reason: invalid class name and case insensitive filesystem */
public final class C07880vX {
    public final int A00;
    public final int A01;
    public final C07880vX[] A02;

    public C07880vX() {
        this.A02 = new C07880vX[256];
        this.A00 = 0;
        this.A01 = 0;
    }

    public C07880vX(int i, int i2) {
        this.A02 = null;
        this.A00 = i;
        int i3 = i2 & 7;
        this.A01 = i3 == 0 ? 8 : i3;
    }
}
