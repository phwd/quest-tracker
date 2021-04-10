package X;

/* renamed from: X.1u  reason: invalid class name */
public final class AnonymousClass1u extends S2 {
    public static final AnonymousClass1u A01 = new AnonymousClass1u(null);
    public final Object A00;

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=SUCCESS, result=[");
        sb.append(this.A00);
        sb.append("]]");
        return sb.toString();
    }

    public AnonymousClass1u(Object obj) {
        this.A00 = obj;
    }

    @Override // java.util.concurrent.Future, X.S2
    public final Object get() {
        return this.A00;
    }
}
