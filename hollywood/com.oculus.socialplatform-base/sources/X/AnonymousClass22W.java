package X;

import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.22W  reason: invalid class name */
public class AnonymousClass22W implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$5";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass22J A01;
    public final /* synthetic */ List A02;

    public AnonymousClass22W(AnonymousClass22J r1, List list, int i) {
        this.A01 = r1;
        this.A02 = list;
        this.A00 = i;
    }

    public final void run() {
        AnonymousClass22J r4 = this.A01;
        List<SubscribeTopic> list = this.A02;
        int i = this.A00;
        try {
            AnonymousClass22J.A01(r4);
            if (r4.A04()) {
                AnonymousClass22M r6 = r4.A0D;
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    for (SubscribeTopic subscribeTopic : list) {
                        arrayList.add(subscribeTopic.A01);
                    }
                }
                synchronized (r6) {
                    AnonymousClass22M.A02(r6, r6.A01, new AnonymousClass23C(new AnonymousClass23P(EnumC142622o.UNSUBSCRIBE), new C145023o(i), new AnonymousClass1Ja(arrayList)));
                }
                C142322l r2 = r4.A0W;
                if (r2 != null) {
                    r2.A01.A06.post(new RunnableC145623u(r2));
                }
            }
        } catch (Throwable th) {
            AnonymousClass22J.A02(r4, EnumC141822g.getFromWriteException(th), EnumC142922r.UNSUBSCRIBE, th);
        }
    }
}
