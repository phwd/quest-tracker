package X;

/* renamed from: X.e2  reason: case insensitive filesystem */
public abstract class AbstractC0657e2 implements eq {
    public static final ThreadLocal A03 = new C00507d();
    public static final ThreadLocal A04 = new AnonymousClass7e();
    public final AnonymousClass6Z A00;
    public final Object A01;
    public volatile AbstractC00346b A02;

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

    public AbstractC0657e2(AnonymousClass6Z r1, Object obj) {
        this.A00 = r1;
        this.A01 = obj;
    }
}
