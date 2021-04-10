package X;

import android.content.Intent;
import android.os.SystemClock;
import android.util.Pair;
import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import com.facebook.rti.push.service.FbnsService;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0x1  reason: invalid class name and case insensitive filesystem */
public class RunnableC08320x1 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$2";
    public final /* synthetic */ C08520xN A00;
    public final /* synthetic */ C08730xj A01;

    public RunnableC08320x1(C08520xN r1, C08730xj r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        boolean z;
        C08520xN r3;
        Throwable th;
        ArrayList arrayList;
        ArrayList arrayList2;
        boolean A03;
        C08520xN r1 = this.A00;
        C08290wy r4 = r1.A01;
        C08300wz r5 = r1.A00;
        if (null == r5) {
            AnonymousClass0NK.A02("FbnsConnectionManager", "Preemptive connection succeeded, switch to new connection");
            r4.A09(r4.A0l, EnumC08720xi.PREEMPTIVE_RECONNECT_SUCCESS, AnonymousClass007.A0F);
            C08290wy.A00(r4);
        }
        if (r4.A0l == r5) {
            C08730xj r7 = this.A01;
            AbstractC09150yk<C07760vx> r2 = r7.A02;
            if (r2.A02()) {
                z = r4.A0Q.A01(r2.A01()) | false;
            } else {
                z = false;
            }
            if (!AnonymousClass0vB.A00(r4.A0R)) {
                AbstractC09150yk<C07920wE> r22 = r7.A03;
                if (r22.A02()) {
                    z |= r4.A0P.A02(r22.A01());
                }
            }
            if (AnonymousClass0vB.A00(r4.A0R)) {
                if (AnonymousClass0vF.A00(r4.A04)) {
                    A03 = r4.A0P.A03(true);
                } else {
                    A03 = r4.A0P.A03(false);
                }
                z |= A03;
            }
            if (z) {
                AbstractServiceC08280wx r8 = r4.A0J.A00;
                if (r8 instanceof FbnsService) {
                    FbnsService fbnsService = (FbnsService) r8;
                    Integer num = null;
                    List<AnonymousClass0un> A032 = fbnsService.A06.A03();
                    fbnsService.A06.A04();
                    fbnsService.A01.A02(AnonymousClass007.A0B, String.valueOf(A032.size()));
                    AnonymousClass0ux A002 = fbnsService.A0A.A03.A00(EnumC07690vn.RUNTIME_PARAMS);
                    if (A002.A1p("DELIVERY_RETRY_INTERVAL")) {
                        num = Integer.valueOf(A002.A3n("DELIVERY_RETRY_INTERVAL", 300));
                    }
                    fbnsService.A0N(AnonymousClass007.A05, new C09450zT(num));
                    for (AnonymousClass0un r6 : A032) {
                        Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER");
                        intent.putExtra("pkg_name", r6.A02);
                        intent.putExtra("appid", r6.A01);
                        intent.setClassName(fbnsService.getPackageName(), fbnsService.getClass().getName());
                        FbnsService.A03(fbnsService, intent);
                    }
                }
            }
            synchronized (r4.A0S) {
                C08300wz r0 = r4.A0l;
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
                        C08170wh.A01(z2);
                    }
                    if (arrayList != null || arrayList2 != null) {
                        r4.A0T.execute(new RunnableC08420xD(r4, new Pair(arrayList, arrayList2)));
                    }
                }
            }
            r4.A06();
            C08310x0 r82 = r4.A0B;
            long elapsedRealtime = SystemClock.elapsedRealtime() - r4.A0h.get();
            AbstractC08430xE r72 = (AbstractC08430xE) r82.A06(C09660zz.class);
            ((AtomicLong) r72.A00(AnonymousClass0y2.CountSuccessfulConnection)).incrementAndGet();
            ((AtomicLong) r72.A00(AnonymousClass0y2.ConnectingMs)).set(elapsedRealtime);
            r82.A00.A01.set(SystemClock.elapsedRealtime());
            if (r4.A0X) {
                C08310x0 r62 = r4.A0B;
                C08290wy.A02(r4, new C08550xR(C08310x0.A00(r62), null, (C09660zz) r62.A06(C09660zz.class), null, r62.A00.A00(true), (C09510za) r62.A06(C09510za.class), (C09490zY) r62.A06(C09490zY.class), null, false, false), false);
            }
            ((AbstractC08430xE) r4.A0B.A06(C09660zz.class)).A02(AnonymousClass0y2.LastConnectFailureReason, null);
            AbstractServiceC08280wx r23 = r4.A0J.A00;
            r23.A00 = SystemClock.elapsedRealtime();
            AbstractServiceC08280wx.A08(r23);
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            C08330x3 r24 = r4.A0N;
            long j = elapsedRealtime2 - r24.A01;
            AnonymousClass0x2 r73 = r4.A0A;
            Map<String, String> A003 = C09120yh.A00(LoggerConstants.CONFIGURATION_FETCH_RETRY_COUNT, String.valueOf(r24.A00), "retry_duration_ms", String.valueOf(j));
            AnonymousClass0x2.A00(r73, "mqtt_connection_retries", A003);
            AbstractC09080yd r02 = r73.A01;
            if (r02 != null) {
                r02.A5h("mqtt_connection_retries", A003);
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
                r3.A01.A05.post(new RunnableC09420zO(r3, th));
                r5.A01 = null;
            }
            r4.A0b = SystemClock.elapsedRealtime();
        }
    }
}
