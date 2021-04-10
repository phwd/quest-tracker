package X;

import java.util.List;

/* renamed from: X.22a  reason: invalid class name */
public class AnonymousClass22a implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$4";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass22J A01;
    public final /* synthetic */ List A02;

    public AnonymousClass22a(AnonymousClass22J r1, List list, int i) {
        this.A01 = r1;
        this.A02 = list;
        this.A00 = i;
    }

    public final void run() {
        AnonymousClass22J r4 = this.A01;
        List list = this.A02;
        int i = this.A00;
        try {
            AnonymousClass22J.A01(r4);
            if (r4.A04()) {
                AnonymousClass22M r5 = r4.A0D;
                synchronized (r5) {
                    AnonymousClass22M.A02(r5, r5.A01, new AnonymousClass23B(new AnonymousClass23P(EnumC142622o.SUBSCRIBE), new C145023o(i), new AnonymousClass1Jb(list)));
                }
                C142322l r2 = r4.A0W;
                if (r2 != null) {
                    r2.A01.A06.post(new RunnableC145623u(r2));
                }
            }
        } catch (Throwable th) {
            AnonymousClass22J.A02(r4, EnumC141822g.getFromWriteException(th), EnumC142922r.SUBSCRIBE, th);
        }
    }
}
