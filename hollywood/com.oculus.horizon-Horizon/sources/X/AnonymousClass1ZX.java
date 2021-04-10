package X;

/* renamed from: X.1ZX  reason: invalid class name */
public class AnonymousClass1ZX {
    public final int A00;
    public final String A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01);
        sb.append(" (pid ");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass1ZX(String str, int i) {
        this.A01 = str;
        this.A00 = i;
    }
}
