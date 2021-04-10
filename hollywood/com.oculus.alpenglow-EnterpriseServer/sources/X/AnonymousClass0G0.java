package X;

/* renamed from: X.0G0  reason: invalid class name */
public class AnonymousClass0G0 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.room.TransactionExecutor$1";
    public final /* synthetic */ AnonymousClass0G1 A00;
    public final /* synthetic */ Runnable A01;

    public AnonymousClass0G0(AnonymousClass0G1 r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    public final void run() {
        try {
            this.A01.run();
        } finally {
            this.A00.A00();
        }
    }
}
