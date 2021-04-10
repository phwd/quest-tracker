package X;

import javax.annotation.Nullable;

/* renamed from: X.0Hr  reason: invalid class name */
public class AnonymousClass0Hr {
    public final long A00;
    public final long A01;
    @Nullable
    public final String A02;

    public AnonymousClass0Hr(long j, long j2, @Nullable String str) {
        String str2;
        if (j < 0) {
            str2 = "minDelayMs < 0";
        } else if (j2 < 0) {
            str2 = "maxDelayMs < 0";
        } else if (j <= j2) {
            this.A01 = j;
            this.A00 = j2;
            this.A02 = str;
            return;
        } else {
            StringBuilder sb = new StringBuilder("minDelay=");
            sb.append(j);
            sb.append("; maxDelay=");
            sb.append(j2);
            str2 = sb.toString();
        }
        throw new IllegalArgumentException(str2);
    }
}
