package X;

import android.content.Context;
import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PK  reason: invalid class name */
public final class AnonymousClass1PK {
    public long A00 = -1;
    public long A01 = -1;
    public long A02 = 0;
    public final AnonymousClass1PM A03;
    public final AnonymousClass1QK A04;
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
                    AnonymousClass1PM r10 = this.A03;
                    AnonymousClass1PL r9 = AnonymousClass1PL.MQTT_RADIO_ACTIVE_TIME;
                    this.A02 = j + r10.A00(r9).A00.getLong("total_wake_ms", 0);
                    AnonymousClass1PB A002 = r10.A00(r9).A00();
                    A002.A00.putLong("total_wake_ms", this.A02);
                    A002.A00.apply();
                    this.A02 = 0;
                    this.A01 = elapsedRealtime;
                }
                AnonymousClass1PM r6 = this.A03;
                AnonymousClass1PL r5 = AnonymousClass1PL.MQTT_RADIO_ACTIVE_TIME;
                if (elapsedRealtime - r6.A00(r5).A00.getLong("last_log_ms", elapsedRealtime) > 3600000) {
                    AnonymousClass1QK.A00(this.A04, "mqtt_radio_active_time", AnonymousClass1Ks.A00("total_wake_ms", Long.toString(r6.A00(r5).A00.getLong("total_wake_ms", 0))));
                    AnonymousClass1PB A003 = r6.A00(r5).A00();
                    A003.A00.clear();
                    A003.A00.apply();
                    AnonymousClass1PB A004 = r6.A00(r5).A00();
                    A004.A00.putLong("last_log_ms", elapsedRealtime);
                    A004.A00.apply();
                }
            }
        }
    }

    public AnonymousClass1PK(Context context, AnonymousClass1QK r9, RealtimeSinceBootClock realtimeSinceBootClock, boolean z, AnonymousClass1PM r12) {
        this.A05 = context;
        this.A04 = r9;
        this.A06 = realtimeSinceBootClock;
        this.A07 = z;
        this.A03 = r12;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        AnonymousClass1PL r1 = AnonymousClass1PL.MQTT_RADIO_ACTIVE_TIME;
        if (r12.A00(r1).A00.getLong("last_log_ms", elapsedRealtime) >= elapsedRealtime) {
            AnonymousClass1PB A002 = this.A03.A00(r1).A00();
            A002.A00.putLong("last_log_ms", elapsedRealtime);
            A002.A00.apply();
        }
    }
}
