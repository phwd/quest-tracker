package X;

public final class Ep {
    public static Ed A00;

    public static synchronized Ed A00() {
        Ed ed;
        synchronized (Ep.class) {
            ed = A00;
            if (ed == null) {
                ed = new Ed(BX.A00().getAssets());
                A00 = ed;
            }
        }
        return ed;
    }
}
