package X;

import android.os.SystemClock;

/* renamed from: X.22p  reason: invalid class name and case insensitive filesystem */
public class RunnableC142722p implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$7";
    public final /* synthetic */ AnonymousClass22J A00;

    public RunnableC142722p(AnonymousClass22J r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass22J r4 = this.A00;
        try {
            if (r4.A04()) {
                AnonymousClass22M r3 = r4.A0D;
                synchronized (r3) {
                    AnonymousClass22M.A02(r3, r3.A01, new C143522x(new AnonymousClass23P(EnumC142622o.PINGREQ), null, null));
                }
                r4.A0T = SystemClock.elapsedRealtime();
            }
        } catch (Throwable th) {
            AnonymousClass22J.A02(r4, EnumC141822g.getFromWriteException(th), EnumC142922r.PING, th);
        }
    }
}
