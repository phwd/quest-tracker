package X;

import java.util.concurrent.Future;

/* renamed from: X.0YM  reason: invalid class name */
public class AnonymousClass0YM implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$5";
    public final /* synthetic */ AnonymousClass0YZ A00;

    public AnonymousClass0YM(AnonymousClass0YZ r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass0YZ r2 = this.A00;
        if (r2.A0J.A00.A0W()) {
            AnonymousClass0ZF r0 = r2.A0l;
            if (r0 == null || !r0.A05()) {
                AnonymousClass0WB r1 = r2.A06;
                if (r1 != null) {
                    r1.A5J("keep_alive", "not connected");
                }
                AnonymousClass0ZF r02 = r2.A0l;
                if (r02 == null || !r02.A06()) {
                    r2.A07();
                    C02270a7 r12 = r2.A0N;
                    synchronized (r12) {
                        Future<?> future = r12.A07;
                        if (future == null || future.isDone()) {
                            if (r12.A04 == null) {
                                C02270a7.A00(r12);
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
            AnonymousClass0WB r13 = r2.A06;
            if (r13 != null) {
                r13.A5J("keep_alive", "send ping");
            }
            AnonymousClass0YZ.A04(r2, null);
            return;
        }
        AnonymousClass0WB r14 = r2.A06;
        if (r14 != null) {
            r14.A5J("keep_alive", "should_not_be_connected");
        }
        r2.A08(EnumC01660Wk.KEEPALIVE_SHOULD_NOT_CONNECT);
    }
}
