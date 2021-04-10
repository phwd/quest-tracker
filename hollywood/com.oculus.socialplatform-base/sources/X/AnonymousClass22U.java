package X;

import android.os.PowerManager;
import android.os.SystemClock;
import java.io.IOException;

/* renamed from: X.22U  reason: invalid class name */
public class AnonymousClass22U implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass238 A01;
    public final /* synthetic */ C143823a A02;
    public final /* synthetic */ AnonymousClass22J A03;
    public final /* synthetic */ EnumC143322v A04;
    public final /* synthetic */ String A05;
    public final /* synthetic */ byte[] A06;

    public AnonymousClass22U(AnonymousClass22J r1, String str, byte[] bArr, EnumC143322v r4, int i, AnonymousClass238 r6, C143823a r7) {
        this.A03 = r1;
        this.A05 = str;
        this.A06 = bArr;
        this.A04 = r4;
        this.A00 = i;
        this.A01 = r6;
        this.A02 = r7;
    }

    public final void run() {
        AnonymousClass22J r4 = this.A03;
        String str = this.A05;
        byte[] bArr = this.A06;
        int value = this.A04.getValue();
        int i = this.A00;
        AnonymousClass238 r3 = this.A01;
        try {
            AnonymousClass22J.A01(r4);
            if (r4.A04()) {
                AnonymousClass22M r5 = r4.A0D;
                synchronized (r5) {
                    Integer num = AnonymousClass1N0.A01.get(str);
                    if (num != null) {
                        str = num.toString();
                    } else {
                        C141922h r2 = r5.A0F;
                        new Object[1][0] = str;
                        r2.A03(null);
                    }
                    try {
                        AnonymousClass22M.A02(r5, r5.A01, new AnonymousClass23A(new AnonymousClass23P(EnumC142622o.PUBLISH, value), new C144323f(str, i), bArr));
                    } catch (IOException e) {
                        throw e;
                    }
                }
                C142322l r22 = r4.A0W;
                if (r22 != null) {
                    r22.A01.A06.post(new RunnableC145623u(r22));
                }
                if (r3 != null) {
                    SystemClock.elapsedRealtime();
                    AnonymousClass22G.A03(r3.A01, new RunnableC144523h(r3));
                }
            } else if (r3 != null) {
                AnonymousClass22G.A03(r3.A01, new RunnableC144023c(r3));
            }
        } catch (Throwable th) {
            AnonymousClass22J.A02(r4, EnumC141822g.getFromWriteException(th), EnumC142922r.PUBLISH, th);
            if (r3 != null) {
                AnonymousClass22G.A03(r3.A01, new RunnableC144023c(r3));
            }
            th.getMessage();
        }
        try {
            PowerManager.WakeLock wakeLock = this.A02.A00;
            if (wakeLock != null) {
                wakeLock.release();
            }
        } catch (Throwable unused) {
        }
    }
}
