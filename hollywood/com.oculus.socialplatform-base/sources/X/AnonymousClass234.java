package X;

import android.os.PowerManager;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;

/* renamed from: X.234  reason: invalid class name */
public class AnonymousClass234 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mqtt.client.internal.MqttClientImpl$14";
    public final /* synthetic */ long A00;
    public final /* synthetic */ AnonymousClass22G A01;
    public final /* synthetic */ C143823a A02;
    public final /* synthetic */ String A03;
    public final /* synthetic */ byte[] A04;

    public AnonymousClass234(AnonymousClass22G r1, String str, byte[] bArr, long j, C143823a r6) {
        this.A01 = r1;
        this.A03 = str;
        this.A04 = bArr;
        this.A00 = j;
        this.A02 = r6;
    }

    public final void run() {
        if (this.A01.A0B != null) {
            VrMsysMqttClientCallbacks.lambda$getMqttClientInitParams$0(new AnonymousClass227(this.A03, this.A04));
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
