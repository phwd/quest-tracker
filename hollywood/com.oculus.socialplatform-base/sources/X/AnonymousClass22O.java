package X;

import android.os.SystemClock;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.22O  reason: invalid class name */
public class AnonymousClass22O implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$5";
    public final /* synthetic */ AnonymousClass22H A00;

    public AnonymousClass22O(AnonymousClass22H r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass22H r3 = this.A00;
        if (r3.A0A.A04()) {
            AnonymousClass22J r0 = r3.A0n;
            if (r0 == null || !r0.A04()) {
                AnonymousClass22J r02 = r3.A0n;
                if (r02 == null || !r02.A05()) {
                    r3.A06();
                    AnonymousClass22S r1 = r3.A0Q;
                    synchronized (r1) {
                        Future<?> future = r1.A06;
                        if (future == null || future.isDone()) {
                            if (r1.A03 == null) {
                                AnonymousClass22S.A00(r1);
                                r1.A02();
                            } else {
                                r1.A02();
                            }
                            r3.A0F.A0E = AnonymousClass007.A09;
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            AnonymousClass22J r8 = r3.A0n;
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if ((elapsedRealtime - r3.A0l) / 1000 >= ((long) ((AnonymousClass1YE) r3.A0G).A00.A0M)) {
                    r3.A0l = elapsedRealtime;
                    if (r3.A0Y) {
                        if (r3.A0V.get()) {
                            ((AtomicLong) ((AnonymousClass1Q3) r3.A0F.A04(AnonymousClass1Q5.class)).A00(AnonymousClass1QD.ForegroundPing)).incrementAndGet();
                        } else {
                            ((AtomicLong) ((AnonymousClass1Q3) r3.A0F.A04(AnonymousClass1Q5.class)).A00(AnonymousClass1QD.BackgroundPing)).incrementAndGet();
                        }
                        if (r8 != null && r8.A04()) {
                            long j = r8.A0U;
                            AnonymousClass22H.A03(r3, r3.A0F.A03(SystemClock.elapsedRealtime() - j), false);
                        }
                    } else if (r8 != null && r8.A04()) {
                        r3.A0M.A01(r8, "callPing", EnumC142622o.PINGRESP, -1, ((AnonymousClass1YE) r3.A0G).A00.A0I);
                        synchronized (r8) {
                            if (r8.A04()) {
                                r8.A0H.execute(new RunnableC142722p(r8));
                            } else {
                                throw new AnonymousClass23U(AnonymousClass007.A00);
                            }
                        }
                    }
                }
            } catch (AnonymousClass23U e) {
                AnonymousClass0MD.A0C(r3.A0R, e, "exception/send_keepalive");
                r3.A08(r8, EnumC141822g.SEND_FAILURE, AnonymousClass007.A01);
            }
        } else {
            r3.A07(EnumC141822g.KEEPALIVE_SHOULD_NOT_CONNECT);
        }
    }
}
