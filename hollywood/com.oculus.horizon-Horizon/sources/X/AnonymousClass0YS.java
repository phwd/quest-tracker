package X;

import android.os.SystemClock;
import android.util.Pair;
import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0YS  reason: invalid class name */
public class AnonymousClass0YS implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$2";
    public final /* synthetic */ C06120mX A00;
    public final /* synthetic */ C02060Yx A01;

    public AnonymousClass0YS(C06120mX r1, C02060Yx r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        boolean z;
        C06120mX r3;
        Throwable th;
        ArrayList arrayList;
        ArrayList arrayList2;
        boolean A03;
        C06120mX r1 = this.A00;
        AnonymousClass0YZ r4 = r1.A01;
        AnonymousClass0ZF r5 = r1.A00;
        if (null == r5) {
            AnonymousClass0NO.A09("FbnsConnectionManager", "Preemptive connection succeeded, switch to new connection");
            r4.A09(r4.A0l, EnumC01660Wk.PREEMPTIVE_RECONNECT_SUCCESS, AnonymousClass007.A0F);
            AnonymousClass0YZ.A00(r4);
        }
        if (r4.A0l == r5) {
            C02060Yx r7 = this.A01;
            AnonymousClass0W8<AnonymousClass0Y2> r2 = r7.A02;
            if (r2.A02()) {
                z = r4.A0Q.A01(r2.A01()) | false;
            } else {
                z = false;
            }
            if (!C01880Xv.A00(r4.A0R)) {
                AnonymousClass0W8<AnonymousClass0Y3> r22 = r7.A03;
                if (r22.A02()) {
                    z |= r4.A0P.A02(r22.A01());
                }
            }
            if (C01880Xv.A00(r4.A0R)) {
                if (AnonymousClass0W4.A00(r4.A04)) {
                    A03 = r4.A0P.A03(true);
                } else {
                    A03 = r4.A0P.A03(false);
                }
                z |= A03;
            }
            if (z) {
                r4.A0J.A00.A0K();
            }
            synchronized (r4.A0S) {
                AnonymousClass0ZF r0 = r4.A0l;
                if (r0 != null) {
                    Map<String, SubscribeTopic> map = r4.A0S;
                    Map<String, SubscribeTopic> map2 = r0.A0F;
                    synchronized (map2) {
                        arrayList = null;
                        for (SubscribeTopic subscribeTopic : map.values()) {
                            String str = subscribeTopic.A01;
                            if (!map2.containsKey(str)) {
                                map2.put(str, subscribeTopic);
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(subscribeTopic);
                            }
                        }
                        Iterator<Map.Entry<String, SubscribeTopic>> it = map2.entrySet().iterator();
                        arrayList2 = null;
                        while (it.hasNext()) {
                            Map.Entry<String, SubscribeTopic> next = it.next();
                            if (!map.containsKey(next.getKey())) {
                                if (arrayList2 == null) {
                                    arrayList2 = new ArrayList();
                                }
                                arrayList2.add(next.getValue());
                                it.remove();
                            }
                        }
                        boolean z2 = false;
                        if (map.size() == map2.size()) {
                            z2 = true;
                        }
                        AnonymousClass0W9.A01(z2);
                    }
                    if (arrayList != null || arrayList2 != null) {
                        r4.A0T.execute(new AnonymousClass0YO(r4, new Pair(arrayList, arrayList2)));
                    }
                }
            }
            r4.A06();
            AnonymousClass0Wu r8 = r4.A0B;
            long elapsedRealtime = SystemClock.elapsedRealtime() - r4.A0h.get();
            AnonymousClass0nJ r72 = (AnonymousClass0nJ) r8.A06(AnonymousClass0Ie.class);
            ((AtomicLong) r72.A00(AnonymousClass0nK.CountSuccessfulConnection)).incrementAndGet();
            ((AtomicLong) r72.A00(AnonymousClass0nK.ConnectingMs)).set(elapsedRealtime);
            r8.A00.A01.set(SystemClock.elapsedRealtime());
            if (r4.A0X) {
                AnonymousClass0Wu r6 = r4.A0B;
                AnonymousClass0YZ.A02(r4, new AnonymousClass0Ws(AnonymousClass0Wu.A00(r6), null, (AnonymousClass0Ie) r6.A06(AnonymousClass0Ie.class), null, r6.A00.A00(true), (AnonymousClass0Ic) r6.A06(AnonymousClass0Ic.class), (AnonymousClass0IU) r6.A06(AnonymousClass0IU.class), null, false, false), false);
            }
            ((AnonymousClass0nJ) r4.A0B.A06(AnonymousClass0Ie.class)).A02(AnonymousClass0nK.LastConnectFailureReason, null);
            AbstractServiceC06020mN r23 = r4.A0J.A00;
            r23.A00 = SystemClock.elapsedRealtime();
            AbstractServiceC06020mN.A09(r23);
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            C02270a7 r24 = r4.A0N;
            AnonymousClass0Wo r62 = r4.A0A;
            Map<String, String> A002 = AnonymousClass0VY.A00("retry_count", String.valueOf(r24.A00), "retry_duration_ms", String.valueOf(elapsedRealtime2 - r24.A01));
            AnonymousClass0Wo.A00(r62, "mqtt_connection_retries", A002);
            AnonymousClass0WB r02 = r62.A01;
            if (r02 != null) {
                r02.A5K("mqtt_connection_retries", A002);
            }
            r4.A0Y = false;
            synchronized (r5) {
                List<MqttPublishRequestBody> list = r5.A01;
                if (r5.A0H.incrementAndGet() > 1) {
                    r3 = r5.A0W;
                    th = new Throwable();
                } else {
                    if (list == null) {
                        r3 = r5.A0W;
                        th = new Throwable();
                    }
                    r5.A01 = null;
                }
                r3.A01.A05.post(new AnonymousClass0YX(r3, th));
                r5.A01 = null;
            }
            r4.A0b = SystemClock.elapsedRealtime();
        }
    }
}
