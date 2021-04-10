package X;

/* renamed from: X.0xg  reason: invalid class name and case insensitive filesystem */
public class RunnableC08700xg implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$9";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C08300wz A01;

    public RunnableC08700xg(C08300wz r1, int i) {
        this.A01 = r1;
        this.A00 = i;
    }

    public final void run() {
        try {
            C08610xX r4 = this.A01.A0B;
            int i = this.A00;
            try {
                synchronized (r4) {
                    C08530xO r0 = r4.A0G;
                    if (r0.A00.A0X.equals(AnonymousClass007.A0C)) {
                        C08610xX.A02(r4, r4.A01, new AnonymousClass0yW(new AnonymousClass0z5(EnumC08830xt.PUBACK), new C09590zi(i)));
                    }
                }
            } catch (Throwable th) {
                r4.A0G.A01(EnumC08720xi.getFromWriteException(th), AnonymousClass0y3.PUBACK, th);
                th.getMessage();
            }
        } catch (Throwable th2) {
            C08300wz r2 = this.A01;
            r2.A0C.A03(th2);
            C08300wz.A03(r2, EnumC08720xi.getFromWriteException(th2), AnonymousClass0y3.PUBACK, th2);
        }
    }
}
