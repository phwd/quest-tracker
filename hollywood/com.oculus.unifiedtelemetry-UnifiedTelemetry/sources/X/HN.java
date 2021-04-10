package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HN {
    public final long A00;
    public final long A01;
    public final long A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{single=(");
        sb.append(this.A02);
        sb.append(",");
        sb.append(this.A01);
        sb.append("), batch=(");
        sb.append(0L);
        sb.append(",");
        sb.append(this.A00);
        sb.append(")}");
        return sb.toString();
    }

    public HN(long j, long j2, long j3) {
        this.A02 = j;
        this.A01 = j2;
        this.A00 = j3;
    }
}
