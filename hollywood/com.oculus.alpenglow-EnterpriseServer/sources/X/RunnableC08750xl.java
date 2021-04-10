package X;

import android.os.SystemClock;

/* renamed from: X.0xl  reason: invalid class name and case insensitive filesystem */
public class RunnableC08750xl implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$7";
    public final /* synthetic */ C08300wz A00;

    public RunnableC08750xl(C08300wz r1) {
        this.A00 = r1;
    }

    public final void run() {
        C08300wz r4 = this.A00;
        try {
            if (r4.A05()) {
                C08610xX r3 = r4.A0B;
                synchronized (r3) {
                    C08610xX.A02(r3, r3.A01, new AnonymousClass0yD(new AnonymousClass0z5(EnumC08830xt.PINGREQ), null, null));
                }
                r4.A0T = SystemClock.elapsedRealtime();
            }
        } catch (Throwable th) {
            C08300wz.A03(r4, EnumC08720xi.getFromWriteException(th), AnonymousClass0y3.PING, th);
        }
    }
}
