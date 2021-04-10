package X;

import android.content.Context;
import android.os.SystemClock;
import android.util.Pair;
import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.22K  reason: invalid class name */
public class AnonymousClass22K implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$2";
    public final /* synthetic */ C142322l A00;
    public final /* synthetic */ C142022i A01;

    public AnonymousClass22K(C142322l r1, C142022i r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        C142322l r3;
        Throwable th;
        ArrayList arrayList;
        ArrayList arrayList2;
        Context context;
        C142322l r1 = this.A00;
        AnonymousClass22H r4 = r1.A01;
        AnonymousClass22J r5 = r1.A00;
        if (null == r5) {
            AnonymousClass0MD.A05(r4.A0R, "Preemptive connection succeeded, switch to new connection");
            r4.A08(r4.A0n, EnumC141822g.PREEMPTIVE_RECONNECT_SUCCESS, AnonymousClass007.A06);
            AnonymousClass22H.A01(r4);
        }
        if (r4.A0n == r5) {
            C142022i r32 = this.A01;
            AnonymousClass1QO<C141521z> r12 = r32.A02;
            if (r12.A02()) {
                C144423g r2 = r4.A08;
                C141521z A012 = r12.A01();
                if (A012 == null) {
                    throw null;
                } else if (!r2.A01.equals(A012)) {
                    r2.A01 = A012;
                }
            }
            boolean A002 = C06111Qo.A00(r4.A0S);
            if (!A002) {
                AnonymousClass1QO<C143622y> r13 = r32.A03;
                if (r13.A02()) {
                    r13.A01();
                }
            }
            if (A002 && (context = r4.A05) != null) {
                context.getSharedPreferences("rti.mqtt.oxygen_fbns_config", 0).getBoolean("fbns_secure_auth", false);
            }
            synchronized (r4.A0T) {
                AnonymousClass22J r0 = r4.A0n;
                if (r0 != null) {
                    Map<String, SubscribeTopic> map = r4.A0T;
                    Map<String, SubscribeTopic> map2 = r0.A0G;
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
                        boolean z = false;
                        if (map.size() == map2.size()) {
                            z = true;
                        }
                        AnonymousClass1ZK.A01(z);
                    }
                    if (arrayList != null || arrayList2 != null) {
                        r4.A0U.execute(new AnonymousClass22X(r4, new Pair(arrayList, arrayList2)));
                    }
                }
            }
            r4.A05();
            AnonymousClass1QF r8 = r4.A0F;
            AnonymousClass1Q3 r7 = (AnonymousClass1Q3) r8.A04(AnonymousClass1QB.class);
            ((AtomicLong) r7.A00(AnonymousClass1QC.CountSuccessfulConnection)).incrementAndGet();
            ((AtomicLong) r7.A00(AnonymousClass1QC.ConnectingMs)).set(SystemClock.elapsedRealtime() - r4.A0i.get());
            r8.A00.A01.set(SystemClock.elapsedRealtime());
            if (r4.A0Y) {
                AnonymousClass1QF r14 = r4.A0F;
                AnonymousClass22H.A03(r4, new AnonymousClass1Q6(AnonymousClass1QF.A00(r14), null, (AnonymousClass1QB) r14.A04(AnonymousClass1QB.class), null, r14.A00.A00(), (C06101Pi) r14.A04(C06101Pi.class), (C06081Pg) r14.A04(C06081Pg.class), null, false, false), false);
            }
            ((AnonymousClass1Q3) r4.A0F.A04(AnonymousClass1QB.class)).A02(AnonymousClass1QC.LastConnectFailureReason, null);
            AnonymousClass22G.A02(r4.A0A, null);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            AnonymousClass22S r22 = r4.A0Q;
            AnonymousClass1QK.A00(r4.A0E, "mqtt_connection_retries", AnonymousClass1Ks.A00("retry_count", String.valueOf(r22.A00), "retry_duration_ms", String.valueOf(elapsedRealtime - r22.A01)));
            r4.A0Z = false;
            synchronized (r5) {
                List<MqttPublishRequestBody> list = r5.A01;
                if (r5.A0I.incrementAndGet() > 1) {
                    r3 = r5.A0W;
                    th = new Throwable();
                } else {
                    if (list == null) {
                        r3 = r5.A0W;
                        th = new Throwable();
                    }
                    r5.A01 = null;
                }
                r3.A01.A06.post(new RunnableC145323r(r3, th));
                r5.A01 = null;
            }
            r4.A02 = SystemClock.elapsedRealtime();
        }
    }
}
