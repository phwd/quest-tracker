package X;

import android.os.PowerManager;
import android.os.SystemClock;
import java.io.IOException;

/* renamed from: X.0ZA  reason: invalid class name */
public class AnonymousClass0ZA implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.MqttClient$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ C01610Wd A01;
    public final /* synthetic */ AnonymousClass0ZF A02;
    public final /* synthetic */ AnonymousClass0ZO A03;
    public final /* synthetic */ EnumC02170Zn A04;
    public final /* synthetic */ String A05;
    public final /* synthetic */ byte[] A06;

    public AnonymousClass0ZA(AnonymousClass0ZF r1, String str, byte[] bArr, EnumC02170Zn r4, int i, AnonymousClass0ZO r6, C01610Wd r7) {
        this.A02 = r1;
        this.A05 = str;
        this.A06 = bArr;
        this.A04 = r4;
        this.A00 = i;
        this.A03 = r6;
        this.A01 = r7;
    }

    public final void run() {
        AnonymousClass0ZF r4 = this.A02;
        String str = this.A05;
        byte[] bArr = this.A06;
        int value = this.A04.getValue();
        int i = this.A00;
        AnonymousClass0ZO r3 = this.A03;
        try {
            AnonymousClass0ZF.A02(r4);
            if (r4.A05()) {
                C05890m2 r6 = r4.A0B;
                synchronized (r6) {
                    Integer num = AnonymousClass0WL.A01.get(str);
                    if (num != null) {
                        str = num.toString();
                    } else {
                        C05870m0 r5 = r6.A0G;
                        new Object[1][0] = str;
                        r5.A03(null);
                    }
                    try {
                        C05890m2.A02(r6, r6.A01, new C05440l8(new C02080Zc(EnumC02120Zg.PUBLISH, value), new C02180Zo(str, i), bArr));
                    } catch (IOException e) {
                        throw e;
                    }
                }
                C06120mX r2 = r4.A0W;
                if (r2 != null) {
                    r2.A01.A05.post(new AnonymousClass0YV(r2));
                }
                if (r3 != null) {
                    r3.A74(SystemClock.elapsedRealtime());
                }
            } else if (r3 != null) {
                r3.A67();
            }
        } catch (Throwable th) {
            AnonymousClass0ZF.A03(r4, EnumC01660Wk.getFromWriteException(th), AnonymousClass0ZP.PUBLISH, th);
            if (r3 != null) {
                r3.A67();
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
