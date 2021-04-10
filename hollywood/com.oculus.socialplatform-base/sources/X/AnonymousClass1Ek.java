package X;

/* renamed from: X.1Ek  reason: invalid class name */
public class AnonymousClass1Ek {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public AnonymousClass1Ah A04;
    public AnonymousClass1Ah A05;

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChangeInfo{oldHolder=");
        sb.append(this.A05);
        sb.append(", newHolder=");
        sb.append(this.A04);
        sb.append(", fromX=");
        sb.append(this.A00);
        sb.append(", fromY=");
        sb.append(this.A01);
        sb.append(", toX=");
        sb.append(this.A02);
        sb.append(", toY=");
        sb.append(this.A03);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1Ek(AnonymousClass1Ah r1, AnonymousClass1Ah r2, int i, int i2, int i3, int i4) {
        this.A05 = r1;
        this.A04 = r2;
        this.A00 = i;
        this.A01 = i2;
        this.A02 = i3;
        this.A03 = i4;
    }
}
