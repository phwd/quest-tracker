package X;

/* renamed from: X.0y1  reason: invalid class name and case insensitive filesystem */
public class RunnableC08900y1 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$10";
    public final /* synthetic */ C08300wz A00;

    public RunnableC08900y1(C08300wz r1) {
        this.A00 = r1;
    }

    public final void run() {
        try {
            C08300wz r1 = this.A00;
            if (!r1.A0I) {
                C08610xX r3 = r1.A0B;
                synchronized (r3) {
                    C08610xX.A02(r3, r3.A01, new AnonymousClass0yD(new AnonymousClass0z5(EnumC08830xt.PINGRESP), null, null));
                }
            }
        } catch (Throwable th) {
            C08300wz.A03(this.A00, EnumC08720xi.getFromWriteException(th), AnonymousClass0y3.PINGRESP, th);
        }
    }
}
