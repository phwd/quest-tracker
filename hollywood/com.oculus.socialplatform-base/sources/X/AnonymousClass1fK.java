package X;

/* renamed from: X.1fK  reason: invalid class name */
public class AnonymousClass1fK implements Runnable {
    public static final String __redex_internal_original_name = "com.bumptech.glide.load.engine.ActiveResources$2";
    public final /* synthetic */ C08691f7 A00;

    public AnonymousClass1fK(C08691f7 r1) {
        this.A00 = r1;
    }

    public final void run() {
        C08691f7 r1 = this.A00;
        while (!r1.A04) {
            try {
                C08691f7.A00(r1, (C08761fF) r1.A01.remove());
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
