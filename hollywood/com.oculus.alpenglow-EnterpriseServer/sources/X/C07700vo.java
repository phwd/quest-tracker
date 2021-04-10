package X;

import android.content.Context;
import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vo  reason: invalid class name and case insensitive filesystem */
public final class C07700vo {
    public long A00 = -1;
    public long A01 = -1;
    public long A02 = 0;
    public final C07710vp A03;
    public final AnonymousClass0x2 A04;
    public final Context A05;
    public final RealtimeSinceBootClock A06;
    public final boolean A07;

    public final synchronized void A00() {
        long j;
        if (this.A07) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j2 = this.A00;
            if (j2 < 0) {
                this.A00 = elapsedRealtime;
                this.A01 = elapsedRealtime;
            } else {
                long j3 = elapsedRealtime - j2;
                this.A00 = elapsedRealtime;
                if (j3 > 10000) {
                    j = this.A02 + 10000;
                } else {
                    j = this.A02 + j3;
                }
                this.A02 = j;
                if (elapsedRealtime - this.A01 > 20000) {
                    C07710vp r10 = this.A03;
                    EnumC07690vn r9 = EnumC07690vn.MQTT_RADIO_ACTIVE_TIME;
                    this.A02 = j + r10.A00(r9).A3x("total_wake_ms", 0);
                    C07720vq A2E = r10.A00(r9).A2E();
                    A2E.A00.putLong("total_wake_ms", this.A02);
                    A2E.A00();
                    this.A02 = 0;
                    this.A01 = elapsedRealtime;
                }
                C07710vp r6 = this.A03;
                EnumC07690vn r4 = EnumC07690vn.MQTT_RADIO_ACTIVE_TIME;
                if (elapsedRealtime - r6.A00(r4).A3x("last_log_ms", elapsedRealtime) > 3600000) {
                    AnonymousClass0x2.A00(this.A04, "mqtt_radio_active_time", C09120yh.A00("total_wake_ms", Long.toString(r6.A00(r4).A3x("total_wake_ms", 0))));
                    EnumC07690vn r42 = EnumC07690vn.MQTT_RADIO_ACTIVE_TIME;
                    C07720vq A2E2 = r6.A00(r42).A2E();
                    A2E2.A00.clear();
                    A2E2.A00();
                    C07720vq A2E3 = r6.A00(r42).A2E();
                    A2E3.A00.putLong("last_log_ms", elapsedRealtime);
                    A2E3.A00();
                }
            }
        }
    }

    public C07700vo(Context context, AnonymousClass0x2 r9, RealtimeSinceBootClock realtimeSinceBootClock, boolean z, C07710vp r12) {
        this.A05 = context;
        this.A04 = r9;
        this.A06 = realtimeSinceBootClock;
        this.A07 = z;
        this.A03 = r12;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        EnumC07690vn r1 = EnumC07690vn.MQTT_RADIO_ACTIVE_TIME;
        if (r12.A00(r1).A3x("last_log_ms", elapsedRealtime) >= elapsedRealtime) {
            C07720vq A2E = this.A03.A00(r1).A2E();
            A2E.A00.putLong("last_log_ms", elapsedRealtime);
            A2E.A00();
        }
    }
}
