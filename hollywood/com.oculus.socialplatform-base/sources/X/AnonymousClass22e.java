package X;

/* renamed from: X.22e  reason: invalid class name */
public class AnonymousClass22e implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$9";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass22J A01;

    public AnonymousClass22e(AnonymousClass22J r1, int i) {
        this.A01 = r1;
        this.A00 = i;
    }

    public final void run() {
        try {
            AnonymousClass22M r4 = this.A01.A0D;
            int i = this.A00;
            try {
                synchronized (r4) {
                    C141922h r0 = r4.A0F;
                    if (r0.A00.A0X.equals(AnonymousClass007.A03)) {
                        AnonymousClass22M.A02(r4, r4.A01, new AnonymousClass235(new AnonymousClass23P(EnumC142622o.PUBACK), new C145023o(i)));
                    }
                }
            } catch (Throwable th) {
                r4.A0F.A01(EnumC141822g.getFromWriteException(th), EnumC142922r.PUBACK, th);
                th.getMessage();
            }
        } catch (Throwable th2) {
            AnonymousClass22J r2 = this.A01;
            r2.A0E.A03(th2);
            AnonymousClass22J.A02(r2, EnumC141822g.getFromWriteException(th2), EnumC142922r.PUBACK, th2);
        }
    }
}
