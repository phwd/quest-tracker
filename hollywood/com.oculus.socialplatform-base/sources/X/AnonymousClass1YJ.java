package X;

import android.content.Context;

/* renamed from: X.1YJ  reason: invalid class name */
public class AnonymousClass1YJ implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mqtt.client.internal.MqttClientImpl$3";
    public final /* synthetic */ AnonymousClass22G A00;

    public AnonymousClass1YJ(AnonymousClass22G r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass22G r1 = this.A00;
        AnonymousClass22G.A01(r1, EnumC141822g.SERVICE_STOP);
        r1.A02.quit();
        AnonymousClass1WV r3 = r1.A0A.A0J;
        synchronized (r3) {
            r3.A03();
            if (r3.A0N != null) {
                C06141Qs r2 = r3.A0F;
                Context context = r3.A0C;
                r2.A04(context, r3.A0A);
                r2.A04(context, r3.A0B);
                r2.A04(context, r3.A09);
            }
        }
    }
}
