package X;

/* renamed from: X.22u  reason: invalid class name and case insensitive filesystem */
public class RunnableC143222u implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$10";
    public final /* synthetic */ AnonymousClass22J A00;

    public RunnableC143222u(AnonymousClass22J r1) {
        this.A00 = r1;
    }

    public final void run() {
        try {
            AnonymousClass22J r1 = this.A00;
            if (!r1.A0J) {
                AnonymousClass22M r3 = r1.A0D;
                synchronized (r3) {
                    AnonymousClass22M.A02(r3, r3.A01, new C143522x(new AnonymousClass23P(EnumC142622o.PINGRESP), null, null));
                }
            }
        } catch (Throwable th) {
            AnonymousClass22J.A02(this.A00, EnumC141822g.getFromWriteException(th), EnumC142922r.PINGRESP, th);
        }
    }
}
