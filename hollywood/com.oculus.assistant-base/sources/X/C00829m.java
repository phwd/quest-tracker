package X;

/* renamed from: X.9m  reason: invalid class name and case insensitive filesystem */
public final class C00829m {
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("'edit_distance': ");
        sb.append(this.A01);
        sb.append(",");
        sb.append("'inserts': ");
        sb.append(this.A02);
        sb.append(",");
        sb.append("'deletions': ");
        sb.append(this.A00);
        sb.append(",");
        sb.append("'replacements': ");
        sb.append(this.A03);
        sb.append("}");
        return sb.toString();
    }

    public C00829m(int i, int i2, int i3, int i4) {
        this.A01 = i;
        this.A02 = i2;
        this.A00 = i3;
        this.A03 = i4;
    }
}
