package X;

import java.util.List;

/* renamed from: X.0xe  reason: invalid class name and case insensitive filesystem */
public class RunnableC08680xe implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$4";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C08300wz A01;
    public final /* synthetic */ List A02;

    public RunnableC08680xe(C08300wz r1, List list, int i) {
        this.A01 = r1;
        this.A02 = list;
        this.A00 = i;
    }

    public final void run() {
        C08300wz r4 = this.A01;
        List list = this.A02;
        int i = this.A00;
        try {
            C08300wz.A02(r4);
            if (r4.A05()) {
                C08610xX r5 = r4.A0B;
                synchronized (r5) {
                    C08610xX.A02(r5, r5.A01, new C09030yN(new AnonymousClass0z5(EnumC08830xt.SUBSCRIBE), new C09590zi(i), new C08990yH(list)));
                }
                C08520xN r2 = r4.A0W;
                if (r2 != null) {
                    EnumC08830xt.SUBSCRIBE.name();
                    r2.A01.A05.post(new RunnableC09530zc(r2));
                }
            }
        } catch (Throwable th) {
            C08300wz.A03(r4, EnumC08720xi.getFromWriteException(th), AnonymousClass0y3.SUBSCRIBE, th);
        }
    }
}
