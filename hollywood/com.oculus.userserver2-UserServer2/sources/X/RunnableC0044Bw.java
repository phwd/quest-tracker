package X;

/* renamed from: X.Bw  reason: case insensitive filesystem */
public class RunnableC0044Bw implements Runnable {
    public static final String __redex_internal_original_name = "androidx.lifecycle.LiveData$1";
    public final /* synthetic */ AbstractC0046By A00;

    public RunnableC0044Bw(AbstractC0046By by) {
        this.A00 = by;
    }

    public final void run() {
        Object obj;
        AbstractC0046By by = this.A00;
        synchronized (by.A05) {
            obj = by.A07;
            by.A07 = AbstractC0046By.A09;
        }
        by.A02(obj);
    }
}
