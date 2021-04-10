package X;

import android.content.Context;
import android.os.SystemClock;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Wl  reason: invalid class name and case insensitive filesystem */
public final class C01670Wl {
    public long A00 = -1;
    public long A01 = -1;
    public long A02 = 0;
    public final C06510nV A03;
    public final AnonymousClass0Wo A04;
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
                if (j3 > StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
                    j = this.A02 + StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS;
                } else {
                    j = this.A02 + j3;
                }
                this.A02 = j;
                if (elapsedRealtime - this.A01 > 20000) {
                    C06510nV r10 = this.A03;
                    AnonymousClass0WE r9 = AnonymousClass0WE.MQTT_RADIO_ACTIVE_TIME;
                    this.A02 = j + r10.A00(r9).A3l("total_wake_ms", 0);
                    C06520nY A2L = r10.A00(r9).A2L();
                    A2L.A00.putLong("total_wake_ms", this.A02);
                    A2L.A00();
                    this.A02 = 0;
                    this.A01 = elapsedRealtime;
                }
                C06510nV r6 = this.A03;
                AnonymousClass0WE r4 = AnonymousClass0WE.MQTT_RADIO_ACTIVE_TIME;
                if (elapsedRealtime - r6.A00(r4).A3l("last_log_ms", elapsedRealtime) > 3600000) {
                    AnonymousClass0Wo.A00(this.A04, "mqtt_radio_active_time", AnonymousClass0VY.A00("total_wake_ms", Long.toString(r6.A00(r4).A3l("total_wake_ms", 0))));
                    AnonymousClass0WE r42 = AnonymousClass0WE.MQTT_RADIO_ACTIVE_TIME;
                    C06520nY A2L2 = r6.A00(r42).A2L();
                    A2L2.A00.clear();
                    A2L2.A00();
                    C06520nY A2L3 = r6.A00(r42).A2L();
                    A2L3.A00.putLong("last_log_ms", elapsedRealtime);
                    A2L3.A00();
                }
            }
        }
    }

    public C01670Wl(Context context, AnonymousClass0Wo r9, RealtimeSinceBootClock realtimeSinceBootClock, boolean z, C06510nV r12) {
        this.A05 = context;
        this.A04 = r9;
        this.A06 = realtimeSinceBootClock;
        this.A07 = z;
        this.A03 = r12;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        AnonymousClass0WE r1 = AnonymousClass0WE.MQTT_RADIO_ACTIVE_TIME;
        if (r12.A00(r1).A3l("last_log_ms", elapsedRealtime) >= elapsedRealtime) {
            C06520nY A2L = this.A03.A00(r1).A2L();
            A2L.A00.putLong("last_log_ms", elapsedRealtime);
            A2L.A00();
        }
    }
}
