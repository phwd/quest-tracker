package X;

public class AV implements Runnable {
    public static final String __redex_internal_original_name = "androidx.lifecycle.LiveData$1";
    public final /* synthetic */ AX A00;

    public AV(AX ax) {
        this.A00 = ax;
    }

    public final void run() {
        Object obj;
        AX ax = this.A00;
        synchronized (ax.A05) {
            obj = ax.A07;
            ax.A07 = AX.A09;
        }
        ax.A02(obj);
    }
}
