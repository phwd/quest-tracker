package X;

/* renamed from: X.1fC  reason: invalid class name */
public class AnonymousClass1fC implements Runnable {
    public static final String __redex_internal_original_name = "com.bumptech.glide.load.engine.EngineJob$CallResourceReady";
    public final AnonymousClass1f3 A00;
    public final /* synthetic */ AbstractC08651f2 A01;

    public AnonymousClass1fC(AbstractC08651f2 r1, AnonymousClass1f3 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1f3 r5 = this.A00;
        r5.A0F.A00();
        synchronized (r5.A0H) {
            AbstractC08651f2 r3 = this.A01;
            synchronized (r3) {
                if (r3.A0G.A00.contains(new AnonymousClass1fY(r5, C07681cq.A00))) {
                    r3.A03.A01();
                    try {
                        r5.A03(r3.A03, r3.A00);
                        r3.A04(r5);
                    } catch (Throwable th) {
                        throw new C08921fk(th);
                    }
                }
                r3.A02();
            }
        }
    }
}
