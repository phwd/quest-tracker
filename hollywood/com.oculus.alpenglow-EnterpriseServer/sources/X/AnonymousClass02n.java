package X;

/* renamed from: X.02n  reason: invalid class name */
public class AnonymousClass02n implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.app.AppCompatDelegateImpl$2";
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    public AnonymousClass02n(LayoutInflater$Factory2C04430et r1) {
        this.A00 = r1;
    }

    public final void run() {
        LayoutInflater$Factory2C04430et r2 = this.A00;
        if ((r2.A04 & 1) != 0) {
            r2.A0e(0);
        }
        if ((r2.A04 & 4096) != 0) {
            r2.A0e(108);
        }
        r2.A0U = false;
        r2.A04 = 0;
    }
}
