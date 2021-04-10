package X;

import com.facebook.acra.NativeCrashDumpReporterUtil;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import libraries.marauder.analytics.request.protocol.AnalyticsService;
import org.json.JSONObject;

@Immutable
/* renamed from: X.22Y  reason: invalid class name */
public final class AnonymousClass22Y {
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final int A05;
    public final int A06;
    public final int A07;
    public final int A08;
    public final int A09;
    public final int A0A;
    public final int A0B;
    public final int A0C;
    public final int A0D;
    public final int A0E;
    public final int A0F;
    public final int A0G;
    public final int A0H;
    public final int A0I;
    public final int A0J;
    public final int A0K;
    public final int A0L;
    public final int A0M;
    public final int A0N;
    public final long A0O;
    public final long A0P;
    public final String A0Q;
    public final String A0R;
    @Nullable
    public final String A0S;
    public final boolean A0T;
    public final boolean A0U;
    public final boolean A0V;
    public final boolean A0W;

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00bc, code lost:
        if (r1.equals(r8.A0R) == false) goto L_0x00be;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r8) {
        /*
        // Method dump skipped, instructions count: 253
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22Y.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        int i2;
        String str = this.A0R;
        int i3 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i4 = i * 31;
        String str2 = this.A0S;
        if (str2 != null) {
            i2 = str2.hashCode();
        } else {
            i2 = 0;
        }
        int i5 = (i4 + i2) * 31;
        String str3 = this.A0Q;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        long j = this.A0P;
        long j2 = this.A0O;
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((i5 + i3) * 31) + this.A0K) * 31) + this.A07) * 31) + this.A0B) * 31) + this.A0N) * 31) + this.A0H) * 31) + this.A0I) * 31) + this.A02) * 31) + this.A05) * 31) + this.A03) * 31) + this.A00) * 31) + this.A04) * 31) + this.A01) * 31) + this.A0C) * 31) + this.A06) * 31) + this.A0M) * 31) + this.A0J) * 31) + (this.A0T ? 1 : 0)) * 31) + this.A0E) * 31) + this.A0F) * 31) + this.A0G) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.A0D) * 31) + (this.A0V ? 1 : 0)) * 31) + (this.A0U ? 1 : 0)) * 31) + this.A08) * 31) + this.A0L) * 31) + this.A0A) * 31) + this.A09) * 31) + (this.A0W ? 1 : 0);
    }

    public AnonymousClass22Y(JSONObject jSONObject) {
        this.A0R = jSONObject.optString("host_name_v6", "mqtt-mini.facebook.com");
        this.A0S = jSONObject.optString("php_sandbox_host_name", null);
        this.A0Q = jSONObject.optString("analytics_endpoint", AnalyticsService.LOGGING_ENDPOINT);
        this.A0K = jSONObject.optInt("default_port", 443);
        this.A07 = jSONObject.optInt("backup_port", 443);
        this.A0B = jSONObject.optInt("dns_timeout_sec", 60);
        this.A0N = jSONObject.optInt("socket_timeout_sec", 60);
        this.A0H = jSONObject.optInt("mqtt_connect_timeout_sec", 60);
        this.A0I = jSONObject.optInt("response_timeout_sec", 59);
        this.A02 = jSONObject.optInt("back_to_back_retry_attempts", 3);
        this.A05 = jSONObject.optInt("background_back_to_back_retry_attempts", 1);
        this.A03 = jSONObject.optInt("back_to_back_retry_interval_sec", 0);
        this.A00 = jSONObject.optInt("back_off_initial_retry_interval_sec", 2);
        this.A04 = jSONObject.optInt("background_back_off_initial_retry_interval_sec", 10);
        this.A01 = jSONObject.optInt("back_off_max_retry_interval_sec", 900);
        this.A0C = jSONObject.optInt("foreground_keepalive_interval_sec", 60);
        this.A06 = jSONObject.optInt("background_keepalive_interval_persistent_sec", 900);
        this.A0M = jSONObject.optInt("skip_ping_threshold_s", 10);
        this.A0J = jSONObject.optInt("ping_delay_s", 60);
        this.A0T = jSONObject.optBoolean("force_server_ping", false);
        this.A0E = jSONObject.optInt("happy_eyeballs_delay_ms", 25);
        this.A0F = jSONObject.optInt("mqtt_client_thread_priority_ui", 5);
        this.A0G = jSONObject.optInt("mqtt_client_thread_priority_worker", 5);
        this.A0P = jSONObject.optLong("analytics_log_min_interval_for_sent_ms", 0);
        this.A0O = jSONObject.optLong("analytics_log_min_interval_for_received_ms", 0);
        this.A0D = jSONObject.optInt("gcm_ping_mqtt_delay_sec", 30);
        this.A0V = jSONObject.optBoolean("use_ssl", true);
        this.A0U = jSONObject.optBoolean("use_compression", true);
        this.A08 = jSONObject.optInt("ct", NativeCrashDumpReporterUtil.MAX_TIME_DIFF_BETWEEN_COREDUMP_AND_MINIDUMP_MS);
        this.A0L = jSONObject.optInt("short_mqtt_connection_sec", 5);
        this.A0A = jSONObject.optInt("connect_rate_limiter_limit", 40);
        this.A09 = jSONObject.optInt("connect_rate_limiter_interval_s", 2400);
        this.A0W = jSONObject.optBoolean("verbose_connection_health_log", false);
    }
}
