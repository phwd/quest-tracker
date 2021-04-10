package X;

import android.os.SystemClock;

/* renamed from: X.0ZB  reason: invalid class name */
public class AnonymousClass0ZB implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$7";
    public final /* synthetic */ AnonymousClass0ZF A00;

    public AnonymousClass0ZB(AnonymousClass0ZF r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass0ZF r4 = this.A00;
        try {
            if (r4.A05()) {
                C05890m2 r3 = r4.A0B;
                synchronized (r3) {
                    C05890m2.A02(r3, r3.A01, new C02150Zl(new C02080Zc(EnumC02120Zg.PINGREQ), null, null));
                }
                r4.A0T = SystemClock.elapsedRealtime();
            }
        } catch (Throwable th) {
            AnonymousClass0ZF.A03(r4, EnumC01660Wk.getFromWriteException(th), AnonymousClass0ZP.PING, th);
        }
    }
}
