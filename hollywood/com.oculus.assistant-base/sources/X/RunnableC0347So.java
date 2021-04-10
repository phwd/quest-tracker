package X;

/* renamed from: X.So  reason: case insensitive filesystem */
public final class RunnableC0347So implements Runnable {
    public static final String __redex_internal_original_name = "com.google.android.gms.tasks.zzj";
    public final /* synthetic */ Sk A00;
    public final /* synthetic */ C1106st A01;

    public RunnableC0347So(C1106st stVar, Sk sk) {
        this.A01 = stVar;
        this.A00 = sk;
    }

    public final void run() {
        C1106st stVar = this.A01;
        synchronized (stVar.A01) {
            AbstractC0346Si si = stVar.A00;
            if (si != null) {
                si.A3v(this.A00);
            }
        }
    }
}
