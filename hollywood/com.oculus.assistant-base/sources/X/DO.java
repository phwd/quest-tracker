package X;

public final class DO extends ThreadLocal {
    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        return new DP();
    }
}
