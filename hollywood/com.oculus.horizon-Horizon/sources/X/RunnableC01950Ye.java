package X;

import java.util.Map;
import java.util.concurrent.TimeoutException;

/* renamed from: X.0Ye  reason: invalid class name and case insensitive filesystem */
public class RunnableC01950Ye implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.MqttOperationManager$1";
    public final /* synthetic */ C06050mQ A00;
    public final /* synthetic */ C01970Yh A01;

    public RunnableC01950Ye(C01970Yh r1, C06050mQ r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        Integer valueOf;
        boolean z;
        AnonymousClass0ZP r1;
        long j;
        C01970Yh r8 = this.A01;
        C06050mQ r4 = this.A00;
        EnumC02120Zg r3 = r4.A04;
        int i = r4.A01;
        AnonymousClass0ZF r2 = r4.A03;
        Map<Integer, C06050mQ> map = r8.A03;
        synchronized (map) {
            valueOf = Integer.valueOf(i);
            if (map.get(valueOf) == r4) {
                map.remove(valueOf);
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            if (r2 == null) {
                j = 0;
            } else {
                j = r2.A0U;
            }
            r8.A00.A05("timeout", r4.A05, EnumC02170Zn.ACKNOWLEDGED_DELIVERY.getValue(), i, i, null, j);
        } else {
            AnonymousClass0NO.A0F("MqttOperationManager", "operation/timeout/duplicate; id=%d, operation=%s, client=%s", valueOf, r3.name(), r2);
        }
        r4.A00();
        if (r3.equals(EnumC02120Zg.PINGRESP) || r3.equals(EnumC02120Zg.PUBACK)) {
            TimeoutException timeoutException = new TimeoutException();
            if (r3.equals(EnumC02120Zg.PINGRESP)) {
                r1 = AnonymousClass0ZP.PING;
            } else {
                r1 = AnonymousClass0ZP.PUBLISH;
            }
            synchronized (r2) {
                AnonymousClass0ZF.A04(r2, EnumC01660Wk.OPERATION_TIMEOUT, r1, timeoutException);
            }
        }
    }
}
