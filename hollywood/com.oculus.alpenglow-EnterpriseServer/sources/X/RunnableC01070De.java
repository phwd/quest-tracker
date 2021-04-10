package X;

/* renamed from: X.0De  reason: invalid class name and case insensitive filesystem */
public class RunnableC01070De implements Runnable {
    public static final String __redex_internal_original_name = "androidx.lifecycle.LiveData$1";
    public final /* synthetic */ AnonymousClass0Dg A00;

    public RunnableC01070De(AnonymousClass0Dg r1) {
        this.A00 = r1;
    }

    public final void run() {
        Object obj;
        AnonymousClass0Dg r3 = this.A00;
        synchronized (r3.A05) {
            obj = r3.A07;
            r3.A07 = AnonymousClass0Dg.A09;
        }
        r3.A02(obj);
    }
}
