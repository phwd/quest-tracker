package X;

/* renamed from: X.1hS  reason: invalid class name */
public class AnonymousClass1hS {
    public final long A00;
    public final long A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01);
        sb.append("/");
        sb.append(this.A00);
        return sb.toString();
    }

    public AnonymousClass1hS(long j, long j2) {
        if (j2 == 0) {
            this.A00 = 1;
            return;
        }
        this.A01 = j;
        this.A00 = j2;
    }
}
