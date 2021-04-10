package X;

import java.util.concurrent.Future;

/* renamed from: X.0xK  reason: invalid class name and case insensitive filesystem */
public class RunnableC08490xK implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$5";
    public final /* synthetic */ C08290wy A00;

    public RunnableC08490xK(C08290wy r1) {
        this.A00 = r1;
    }

    public final void run() {
        C08290wy r2 = this.A00;
        if (r2.A0J.A00.A0P()) {
            C08300wz r0 = r2.A0l;
            if (r0 == null || !r0.A05()) {
                AbstractC09080yd r1 = r2.A06;
                if (r1 != null) {
                    r1.A5g("keep_alive", "not connected");
                }
                C08300wz r02 = r2.A0l;
                if (r02 == null || !r02.A06()) {
                    r2.A07();
                    C08330x3 r12 = r2.A0N;
                    synchronized (r12) {
                        Future<?> future = r12.A07;
                        if (future == null || future.isDone()) {
                            if (r12.A04 == null) {
                                C08330x3.A00(r12);
                                r12.A02();
                            } else {
                                r12.A02();
                            }
                            r2.A0B.A0D = AnonymousClass007.A0I;
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            AbstractC09080yd r13 = r2.A06;
            if (r13 != null) {
                r13.A5g("keep_alive", "send ping");
            }
            C08290wy.A04(r2, null);
            return;
        }
        AbstractC09080yd r14 = r2.A06;
        if (r14 != null) {
            r14.A5g("keep_alive", "should_not_be_connected");
        }
        r2.A08(EnumC08720xi.KEEPALIVE_SHOULD_NOT_CONNECT);
    }
}
