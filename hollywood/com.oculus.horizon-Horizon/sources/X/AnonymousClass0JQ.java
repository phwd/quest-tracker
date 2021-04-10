package X;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.0JQ  reason: invalid class name */
public abstract class AnonymousClass0JQ implements AbstractC06950qN {
    public static final ThreadLocal<byte[]> A03 = new AnonymousClass0HS();
    public static final ThreadLocal<char[]> A04 = new AnonymousClass0HT();
    public final AnonymousClass0GB A00;
    public final Object A01;
    @Nullable
    public volatile AnonymousClass0GD A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{lockKey=");
        sb.append(this.A01);
        sb.append(";hasLock=");
        sb.append(false);
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass0JQ(AnonymousClass0GB r1, Object obj) {
        this.A00 = r1;
        this.A01 = obj;
    }
}
