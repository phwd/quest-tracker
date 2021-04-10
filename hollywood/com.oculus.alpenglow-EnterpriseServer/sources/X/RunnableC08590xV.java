package X;

import android.os.PowerManager;
import android.os.SystemClock;
import java.io.IOException;

/* renamed from: X.0xV  reason: invalid class name and case insensitive filesystem */
public class RunnableC08590xV implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C09440zS A01;
    public final /* synthetic */ C08300wz A02;
    public final /* synthetic */ AnonymousClass10E A03;
    public final /* synthetic */ EnumC08980yG A04;
    public final /* synthetic */ String A05;
    public final /* synthetic */ byte[] A06;

    public RunnableC08590xV(C08300wz r1, String str, byte[] bArr, EnumC08980yG r4, int i, AnonymousClass10E r6, C09440zS r7) {
        this.A02 = r1;
        this.A05 = str;
        this.A06 = bArr;
        this.A04 = r4;
        this.A00 = i;
        this.A03 = r6;
        this.A01 = r7;
    }

    public final void run() {
        C08300wz r4 = this.A02;
        String str = this.A05;
        byte[] bArr = this.A06;
        int value = this.A04.getValue();
        int i = this.A00;
        AnonymousClass10E r3 = this.A03;
        try {
            C08300wz.A02(r4);
            if (r4.A05()) {
                C08610xX r5 = r4.A0B;
                synchronized (r5) {
                    Integer num = C09070yc.A01.get(str);
                    if (num != null) {
                        str = num.toString();
                    } else {
                        C08530xO r2 = r5.A0G;
                        new Object[1][0] = str;
                        r2.A03(null);
                    }
                    try {
                        C08610xX.A02(r5, r5.A01, new AnonymousClass0yP(new AnonymousClass0z5(EnumC08830xt.PUBLISH, value), new AnonymousClass0z0(str, i), bArr));
                    } catch (IOException e) {
                        throw e;
                    }
                }
                C08520xN r22 = r4.A0W;
                if (r22 != null) {
                    EnumC08830xt.PUBLISH.name();
                    r22.A01.A05.post(new RunnableC09530zc(r22));
                }
                if (r3 != null) {
                    r3.A6g(SystemClock.elapsedRealtime());
                }
            } else if (r3 != null) {
                r3.A66();
            }
        } catch (Throwable th) {
            C08300wz.A03(r4, EnumC08720xi.getFromWriteException(th), AnonymousClass0y3.PUBLISH, th);
            if (r3 != null) {
                r3.A66();
            }
            th.getMessage();
        }
        try {
            PowerManager.WakeLock wakeLock = this.A01.A00;
            if (wakeLock != null) {
                wakeLock.release();
            }
        } catch (Throwable unused) {
        }
    }
}
