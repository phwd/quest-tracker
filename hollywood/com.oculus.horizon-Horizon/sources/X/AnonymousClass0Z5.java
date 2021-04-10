package X;

/* renamed from: X.0Z5  reason: invalid class name */
public class AnonymousClass0Z5 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$10";
    public final /* synthetic */ AnonymousClass0ZF A00;

    public AnonymousClass0Z5(AnonymousClass0ZF r1) {
        this.A00 = r1;
    }

    public final void run() {
        try {
            AnonymousClass0ZF r1 = this.A00;
            if (!r1.A0I) {
                C05890m2 r3 = r1.A0B;
                synchronized (r3) {
                    C05890m2.A02(r3, r3.A01, new C02150Zl(new C02080Zc(EnumC02120Zg.PINGRESP), null, null));
                }
            }
        } catch (Throwable th) {
            AnonymousClass0ZF.A03(this.A00, EnumC01660Wk.getFromWriteException(th), AnonymousClass0ZP.PINGRESP, th);
        }
    }
}
