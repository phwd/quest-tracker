package X;

import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0Z9  reason: invalid class name */
public class AnonymousClass0Z9 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$5";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass0ZF A01;
    public final /* synthetic */ List A02;

    public AnonymousClass0Z9(AnonymousClass0ZF r1, List list, int i) {
        this.A01 = r1;
        this.A02 = list;
        this.A00 = i;
    }

    public final void run() {
        AnonymousClass0ZF r4 = this.A01;
        List<SubscribeTopic> list = this.A02;
        int i = this.A00;
        try {
            AnonymousClass0ZF.A02(r4);
            if (r4.A05()) {
                C05890m2 r6 = r4.A0B;
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    for (SubscribeTopic subscribeTopic : list) {
                        arrayList.add(subscribeTopic.A01);
                    }
                }
                synchronized (r6) {
                    C05890m2.A02(r6, r6.A01, new C05260ke(new C02080Zc(EnumC02120Zg.UNSUBSCRIBE), new C02100Ze(i), new C02220Zu(arrayList)));
                }
                C06120mX r2 = r4.A0W;
                if (r2 != null) {
                    r2.A01.A05.post(new AnonymousClass0YV(r2));
                }
            }
        } catch (Throwable th) {
            AnonymousClass0ZF.A03(r4, EnumC01660Wk.getFromWriteException(th), AnonymousClass0ZP.UNSUBSCRIBE, th);
        }
    }
}
