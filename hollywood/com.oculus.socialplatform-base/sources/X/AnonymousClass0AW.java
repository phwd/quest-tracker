package X;

/* renamed from: X.0AW  reason: invalid class name */
public class AnonymousClass0AW implements Runnable {
    public static final String __redex_internal_original_name = "androidx.lifecycle.LiveData$1";
    public final /* synthetic */ AnonymousClass0AY A00;

    public AnonymousClass0AW(AnonymousClass0AY r1) {
        this.A00 = r1;
    }

    public final void run() {
        Object obj;
        AnonymousClass0AY r3 = this.A00;
        synchronized (r3.A05) {
            obj = r3.A07;
            r3.A07 = AnonymousClass0AY.A09;
        }
        AnonymousClass0AY.A01("setValue");
        r3.A01++;
        r3.A08 = obj;
        r3.A03(null);
    }
}
