package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22P  reason: invalid class name */
public final class AnonymousClass22P {
    public final AnonymousClass1QK A00;
    public final AnonymousClass1QF A01;
    public final AnonymousClass1X5 A02;
    @GuardedBy("itself")
    public final Map<Integer, AnonymousClass22b> A03 = new HashMap();
    public final RealtimeSinceBootClock A04;
    public final AnonymousClass1XF A05;

    public static void A00(AnonymousClass22P r14, AnonymousClass22b r15) {
        Integer valueOf;
        boolean z;
        EnumC142922r r1;
        long j;
        EnumC142622o r4 = r15.A04;
        int i = r15.A01;
        AnonymousClass22J r3 = r15.A03;
        Map<Integer, AnonymousClass22b> map = r14.A03;
        synchronized (map) {
            valueOf = Integer.valueOf(i);
            if (map.get(valueOf) == r15) {
                map.remove(valueOf);
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            if (r3 == null) {
                j = 0;
            } else {
                j = r3.A0U;
            }
            r14.A00.A05("timeout", r15.A05, EnumC143322v.ACKNOWLEDGED_DELIVERY.getValue(), i, i, null, j);
        } else {
            AnonymousClass0MD.A0A("MqttOperationManager", "operation/timeout/duplicate; id=%d, operation=%s, client=%s", valueOf, r4.name(), r3);
        }
        r15.A00();
        if (r4.equals(EnumC142622o.PINGRESP) || r4.equals(EnumC142622o.PUBACK)) {
            TimeoutException timeoutException = new TimeoutException();
            if (r4.equals(EnumC142622o.PINGRESP)) {
                r1 = EnumC142922r.PING;
            } else {
                r1 = EnumC142922r.PUBLISH;
            }
            synchronized (r3) {
                AnonymousClass22J.A03(r3, EnumC141822g.OPERATION_TIMEOUT, r1, timeoutException);
            }
        }
    }

    public AnonymousClass22P(AnonymousClass1XF r2, AnonymousClass1X5 r3, AnonymousClass1QK r4, AnonymousClass1QF r5, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A05 = r2;
        this.A02 = r3;
        this.A00 = r4;
        this.A01 = r5;
        this.A04 = realtimeSinceBootClock;
    }

    public final AnonymousClass22b A01(AnonymousClass22J r13, String str, EnumC142622o r15, int i, int i2) {
        AnonymousClass22b put;
        AnonymousClass22b r5 = new AnonymousClass22b(r13, str, r15, i, SystemClock.elapsedRealtime());
        Map<Integer, AnonymousClass22b> map = this.A03;
        synchronized (map) {
            put = map.put(Integer.valueOf(r5.A01), r5);
        }
        if (put != null) {
            put.A00();
            AnonymousClass0MD.A09("MqttOperationManager", "operation/add/duplicate; id=%d, name=%s", Integer.valueOf(put.A01), put.A04.name());
        }
        AbstractScheduledFutureC02560jD<?> A032 = this.A02.schedule(new AnonymousClass23W(this, r5), (long) i2, TimeUnit.SECONDS);
        boolean z = false;
        if (r5.A06 == null) {
            z = true;
        }
        AnonymousClass1ZK.A01(z);
        r5.A06 = A032;
        return r5;
    }
}
