package X;

/* renamed from: X.0AV  reason: invalid class name */
public class AnonymousClass0AV implements Runnable {
    public static final String __redex_internal_original_name = "androidx.lifecycle.LiveData$1";
    public final /* synthetic */ AnonymousClass0AX A00;

    public AnonymousClass0AV(AnonymousClass0AX r1) {
        this.A00 = r1;
    }

    public final void run() {
        Object obj;
        AnonymousClass0AX r3 = this.A00;
        synchronized (r3.A05) {
            obj = r3.A07;
            r3.A07 = AnonymousClass0AX.A09;
        }
        r3.A02(obj);
    }
}
