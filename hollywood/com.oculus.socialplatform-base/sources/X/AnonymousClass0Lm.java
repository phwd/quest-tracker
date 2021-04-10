package X;

/* renamed from: X.0Lm  reason: invalid class name */
public class AnonymousClass0Lm implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.crudolib.prefs.LightSharedPreferencesImpl$2";
    public final /* synthetic */ C03850of A00;
    public final /* synthetic */ Throwable A01;

    public AnonymousClass0Lm(C03850of r1, Throwable th) {
        this.A00 = r1;
        this.A01 = th;
    }

    public final void run() {
        this.A00.A00 = this.A01;
        throw new NullPointerException("onSharedPreferenceChanged");
    }
}
