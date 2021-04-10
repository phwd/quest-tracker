package X;

public final class Ab {
    public static final Ab A01 = new Ab(new AZ());
    public final Throwable A00;

    public Ab(Throwable th) {
        if (th != null) {
            this.A00 = th;
            return;
        }
        throw null;
    }
}
