package X;

import android.os.SystemClock;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0YW  reason: invalid class name */
public class AnonymousClass0YW implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$6";
    public final /* synthetic */ C06120mX A00;
    public final /* synthetic */ C02150Zl A01;

    public AnonymousClass0YW(C06120mX r1, C02150Zl r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        int i;
        AnonymousClass0ZF r1;
        C06050mQ remove;
        long j;
        long j2;
        long j3;
        C06120mX r0 = this.A00;
        AnonymousClass0YZ r9 = r0.A01;
        if (r9.A0l == r0.A00) {
            AnonymousClass0W8 r17 = C06530na.A00;
            C02150Zl r11 = this.A01;
            C02080Zc r12 = r11.A00;
            switch (r12.A03.ordinal()) {
                case 2:
                    if (r12.A02 == EnumC02170Zn.ACKNOWLEDGED_DELIVERY.getValue()) {
                        r9.A06();
                        break;
                    }
                    break;
                case 3:
                case 8:
                case 10:
                    i = ((C02100Ze) r11.A01()).A00;
                    r17 = AnonymousClass0W8.A00(Integer.valueOf(i));
                    break;
                case 11:
                    r9.A06();
                    if (r9.A0X && (r1 = r9.A0l) != null && r1.A05()) {
                        AnonymousClass0YZ.A02(r9, r9.A0B.A05(SystemClock.elapsedRealtime() - r1.A0U), true);
                        break;
                    }
                case 12:
                    i = -1;
                    r17 = AnonymousClass0W8.A00(Integer.valueOf(i));
                    break;
            }
            if (r17.A02()) {
                r17.A01();
                C01970Yh r10 = r9.A0I;
                int intValue = ((Number) r17.A01()).intValue();
                Map<Integer, C06050mQ> map = r10.A03;
                synchronized (map) {
                    remove = map.remove(Integer.valueOf(intValue));
                }
                if (remove != null) {
                    if (remove.A06 != null) {
                        remove.A06.cancel(false);
                    }
                    SystemClock.elapsedRealtime();
                    long elapsedRealtime = SystemClock.elapsedRealtime() - remove.A02;
                    if (remove.A04.equals(EnumC02120Zg.PUBACK)) {
                        AtomicLong atomicLong = (AtomicLong) ((AnonymousClass0nJ) r10.A01.A06(AnonymousClass0Ia.class)).A00(AnonymousClass0nG.PublishAcknowledgementMs);
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
                    AnonymousClass0ZF r02 = remove.A03;
                    if (r02 == null) {
                        j = 0;
                    } else {
                        j = r02.A0U;
                    }
                    r10.A00.A04(remove.A05, EnumC02170Zn.ACKNOWLEDGED_DELIVERY.getValue(), intValue, elapsedRealtime, i2, j);
                }
                ((Number) r17.A01()).intValue();
            }
            r9.A0J.A00.A0T(r11);
        }
    }
}
