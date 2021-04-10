package X;

import android.os.SystemClock;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0x6  reason: invalid class name and case insensitive filesystem */
public class RunnableC08360x6 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$6";
    public final /* synthetic */ C08520xN A00;
    public final /* synthetic */ AnonymousClass0yD A01;

    public RunnableC08360x6(C08520xN r1, AnonymousClass0yD r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        int i;
        C08300wz r1;
        C08510xM remove;
        long j;
        long j2;
        long j3;
        C08520xN r0 = this.A00;
        C08290wy r9 = r0.A01;
        if (r9.A0l == r0.A00) {
            AbstractC09150yk r17 = C09340zG.A00;
            AnonymousClass0yD r11 = this.A01;
            AnonymousClass0z5 r12 = r11.A00;
            switch (r12.A03.ordinal()) {
                case 2:
                    if (r12.A02 == EnumC08980yG.ACKNOWLEDGED_DELIVERY.getValue()) {
                        r9.A06();
                        break;
                    }
                    break;
                case 3:
                case 8:
                case 10:
                    i = ((C09590zi) r11.A01()).A00;
                    r17 = AbstractC09150yk.A00(Integer.valueOf(i));
                    break;
                case 11:
                    r9.A06();
                    if (r9.A0X && (r1 = r9.A0l) != null && r1.A05()) {
                        C08290wy.A02(r9, r9.A0B.A05(SystemClock.elapsedRealtime() - r1.A0U), true);
                        break;
                    }
                case 12:
                    i = -1;
                    r17 = AbstractC09150yk.A00(Integer.valueOf(i));
                    break;
            }
            if (r17.A02()) {
                r17.A01();
                C08370x7 r10 = r9.A0I;
                int intValue = ((Integer) r17.A01()).intValue();
                Map<Integer, C08510xM> map = r10.A03;
                synchronized (map) {
                    remove = map.remove(Integer.valueOf(intValue));
                }
                if (remove != null) {
                    if (remove.A06 != null) {
                        remove.A06.cancel(false);
                    }
                    SystemClock.elapsedRealtime();
                    long elapsedRealtime = SystemClock.elapsedRealtime() - remove.A02;
                    if (remove.A04.equals(EnumC08830xt.PUBACK)) {
                        AtomicLong atomicLong = (AtomicLong) ((AbstractC08430xE) r10.A01.A06(C09210yr.class)).A00(AnonymousClass0y6.PublishAcknowledgementMs);
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
                    C08300wz r02 = remove.A03;
                    if (r02 == null) {
                        j = 0;
                    } else {
                        j = r02.A0U;
                    }
                    r10.A00.A04(remove.A05, EnumC08980yG.ACKNOWLEDGED_DELIVERY.getValue(), intValue, elapsedRealtime, i2, j);
                }
                r17.A01();
            }
            r9.A0J.A00.A0M(r11);
        }
    }
}
