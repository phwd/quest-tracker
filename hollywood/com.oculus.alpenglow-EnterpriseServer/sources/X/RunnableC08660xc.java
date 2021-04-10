package X;

import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0xc  reason: invalid class name and case insensitive filesystem */
public class RunnableC08660xc implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$5";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C08300wz A01;
    public final /* synthetic */ List A02;

    public RunnableC08660xc(C08300wz r1, List list, int i) {
        this.A01 = r1;
        this.A02 = list;
        this.A00 = i;
    }

    public final void run() {
        C08300wz r4 = this.A01;
        List<SubscribeTopic> list = this.A02;
        int i = this.A00;
        try {
            C08300wz.A02(r4);
            if (r4.A05()) {
                C08610xX r6 = r4.A0B;
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    for (SubscribeTopic subscribeTopic : list) {
                        arrayList.add(subscribeTopic.A01);
                    }
                }
                synchronized (r6) {
                    C08610xX.A02(r6, r6.A01, new C09020yM(new AnonymousClass0z5(EnumC08830xt.UNSUBSCRIBE), new C09590zi(i), new AnonymousClass0yI(arrayList)));
                }
                C08520xN r2 = r4.A0W;
                if (r2 != null) {
                    EnumC08830xt.UNSUBSCRIBE.name();
                    r2.A01.A05.post(new RunnableC09530zc(r2));
                }
            }
        } catch (Throwable th) {
            C08300wz.A03(r4, EnumC08720xi.getFromWriteException(th), AnonymousClass0y3.UNSUBSCRIBE, th);
        }
    }
}
