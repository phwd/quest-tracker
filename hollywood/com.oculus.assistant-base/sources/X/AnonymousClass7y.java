package X;

/* renamed from: X.7y  reason: invalid class name */
public final class AnonymousClass7y {
    public final long A00;
    public final long A01;
    public final String A02;

    public AnonymousClass7y(long j, long j2, String str) {
        if (j < 0) {
            throw new IllegalArgumentException("minDelayMs < 0");
        } else if (j2 < 0) {
            throw new IllegalArgumentException("maxDelayMs < 0");
        } else if (j <= j2) {
            this.A01 = j;
            this.A00 = j2;
            this.A02 = str;
        } else {
            StringBuilder sb = new StringBuilder("minDelay=");
            sb.append(j);
            sb.append("; maxDelay=");
            sb.append(j2);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
