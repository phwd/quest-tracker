package X;

/* renamed from: X.0ZD  reason: invalid class name */
public class AnonymousClass0ZD implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$9";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass0ZF A01;

    public AnonymousClass0ZD(AnonymousClass0ZF r1, int i) {
        this.A01 = r1;
        this.A00 = i;
    }

    public final void run() {
        try {
            C05890m2 r4 = this.A01.A0B;
            int i = this.A00;
            try {
                synchronized (r4) {
                    C05870m0 r0 = r4.A0G;
                    if (r0.A00.A0X.equals(AnonymousClass007.A0C)) {
                        C05890m2.A02(r4, r4.A01, new C05750ln(new C02080Zc(EnumC02120Zg.PUBACK), new C02100Ze(i)));
                    }
                }
            } catch (Throwable th) {
                r4.A0G.A01(EnumC01660Wk.getFromWriteException(th), AnonymousClass0ZP.PUBACK, th);
                th.getMessage();
            }
        } catch (Throwable th2) {
            AnonymousClass0ZF r2 = this.A01;
            r2.A0C.A03(th2);
            AnonymousClass0ZF.A03(r2, EnumC01660Wk.getFromWriteException(th2), AnonymousClass0ZP.PUBACK, th2);
        }
    }
}
