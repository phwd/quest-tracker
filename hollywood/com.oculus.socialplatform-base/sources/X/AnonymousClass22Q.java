package X;

import android.os.SystemClock;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.22Q  reason: invalid class name */
public class AnonymousClass22Q implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$6";
    public final /* synthetic */ C142322l A00;
    public final /* synthetic */ C143522x A01;

    public AnonymousClass22Q(C142322l r1, C143522x r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        AnonymousClass22J r1;
        int i;
        AnonymousClass22b remove;
        long j;
        long j2;
        long j3;
        C142322l r0 = this.A00;
        AnonymousClass22H r5 = r0.A01;
        if (r5.A0n == r0.A00) {
            AnonymousClass1QO r15 = AnonymousClass1QP.A00;
            C143522x r2 = this.A01;
            AnonymousClass23P r12 = r2.A00;
            switch (r12.A03.ordinal()) {
                case 2:
                    if (r12.A02 == EnumC143322v.ACKNOWLEDGED_DELIVERY.getValue()) {
                        r5.A05();
                        break;
                    }
                    break;
                case 3:
                case 8:
                case 10:
                    i = ((C145023o) r2.A01()).A00;
                    r15 = AnonymousClass1QO.A00(Integer.valueOf(i));
                    break;
                case 11:
                    r5.A05();
                    if (r5.A0Y && (r1 = r5.A0n) != null && r1.A04()) {
                        AnonymousClass22H.A03(r5, r5.A0F.A03(SystemClock.elapsedRealtime() - r1.A0U), true);
                        break;
                    }
                case 12:
                    i = -1;
                    r15 = AnonymousClass1QO.A00(Integer.valueOf(i));
                    break;
            }
            if (r15.A02()) {
                r15.A01();
                AnonymousClass22P r122 = r5.A0M;
                int intValue = ((Number) r15.A01()).intValue();
                Map<Integer, AnonymousClass22b> map = r122.A03;
                synchronized (map) {
                    remove = map.remove(Integer.valueOf(intValue));
                }
                if (remove != null) {
                    if (remove.A07 != null) {
                        remove.A07.onSuccess(remove.A01);
                    }
                    if (remove.A06 != null) {
                        remove.A06.cancel(false);
                    }
                    SystemClock.elapsedRealtime();
                    long elapsedRealtime = SystemClock.elapsedRealtime() - remove.A02;
                    if (remove.A04.equals(EnumC142622o.PUBACK)) {
                        AtomicLong atomicLong = (AtomicLong) ((AnonymousClass1Q3) r122.A01.A04(AnonymousClass1QA.class)).A00(AnonymousClass1Q9.PublishAcknowledgementMs);
                        if (elapsedRealtime > 0) {
                            do {
                                j2 = atomicLong.get();
                                if (j2 == 0) {
                                    j3 = elapsedRealtime;
                                } else {
                                    j3 = (long) ((((double) j2) * 0.8d) + (((double) elapsedRealtime) * 0.2d));
                                }
                            } while (!atomicLong.compareAndSet(j2, j3));
                        }
                    }
                    int i2 = remove.A01;
                    AnonymousClass22J r02 = remove.A03;
                    if (r02 == null) {
                        j = 0;
                    } else {
                        j = r02.A0U;
                    }
                    r122.A00.A04(remove.A05, EnumC143322v.ACKNOWLEDGED_DELIVERY.getValue(), intValue, elapsedRealtime, i2, j);
                }
                ((Number) r15.A01()).intValue();
            }
        }
    }
}
