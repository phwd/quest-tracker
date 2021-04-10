package X;

import java.util.Map;
import java.util.concurrent.TimeoutException;

/* renamed from: X.0yu  reason: invalid class name and case insensitive filesystem */
public class RunnableC09230yu implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.MqttOperationManager$1";
    public final /* synthetic */ C08510xM A00;
    public final /* synthetic */ C08370x7 A01;

    public RunnableC09230yu(C08370x7 r1, C08510xM r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        Integer valueOf;
        boolean z;
        AnonymousClass0y3 r1;
        long j;
        C08370x7 r6 = this.A01;
        C08510xM r5 = this.A00;
        EnumC08830xt r4 = r5.A04;
        int i = r5.A01;
        C08300wz r3 = r5.A03;
        Map<Integer, C08510xM> map = r6.A03;
        synchronized (map) {
            valueOf = Integer.valueOf(i);
            if (map.get(valueOf) == r5) {
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
            r6.A00.A05("timeout", r5.A05, EnumC08980yG.ACKNOWLEDGED_DELIVERY.getValue(), i, i, null, j);
        } else {
            AnonymousClass0NK.A07("MqttOperationManager", "operation/timeout/duplicate; id=%d, operation=%s, client=%s", valueOf, r4.name(), r3);
        }
        r5.A00();
        if (r4.equals(EnumC08830xt.PINGRESP) || r4.equals(EnumC08830xt.PUBACK)) {
            TimeoutException timeoutException = new TimeoutException();
            if (r4.equals(EnumC08830xt.PINGRESP)) {
                r1 = AnonymousClass0y3.PING;
            } else {
                r1 = AnonymousClass0y3.PUBLISH;
            }
            synchronized (r3) {
                C08300wz.A04(r3, EnumC08720xi.OPERATION_TIMEOUT, r1, timeoutException);
            }
        }
    }
}
