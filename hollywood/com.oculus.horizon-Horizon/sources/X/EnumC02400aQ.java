package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* JADX WARN: Init of enum BLOCKED_COUNTRIES_HOSTNAME can be incorrect */
/* JADX WARN: Init of enum BLOCKED_COUNTRIES_ANALYTICS_ENDPOINT can be incorrect */
/* JADX WARN: Init of enum BLOCKED_COUNTRIES_HN_TIMESTAMP can be incorrect */
/* JADX WARN: Init of enum ANALYTIC_FB_UID can be incorrect */
/* JADX WARN: Init of enum ANALYTIC_UID can be incorrect */
/* JADX WARN: Init of enum ANALYTIC_IS_EMPLOYEE can be incorrect */
/* JADX WARN: Init of enum ANALYTIC_YEAR_CLASS can be incorrect */
/* JADX WARN: Init of enum LOGGING_HEALTH_STATS_SAMPLE_RATE can be incorrect */
/* JADX WARN: Init of enum LOG_ANALYTICS_EVENTS can be incorrect */
/* JADX WARN: Init of enum LOGGING_ANALYTICS_EVENTS_SAMPLE_RATE can be incorrect */
/* JADX WARN: Init of enum MQTT_DEVICE_ID can be incorrect */
/* JADX WARN: Init of enum MQTT_DEVICE_SECRET can be incorrect */
/* JADX WARN: Init of enum MQTT_DEVICE_CREDENTIALS_TIMESTAMP can be incorrect */
/* renamed from: X.0aQ  reason: invalid class name and case insensitive filesystem */
public enum EnumC02400aQ {
    BLOCKED_COUNTRIES_HOSTNAME("host_name_ipv6", r7),
    BLOCKED_COUNTRIES_ANALYTICS_ENDPOINT("analytics_endpoint", r7),
    BLOCKED_COUNTRIES_HN_TIMESTAMP("bc_host_name_timestamp", r23),
    ANALYTIC_FB_UID("fb_uid", r7),
    ANALYTIC_UID("user_id", r7),
    ANALYTIC_IS_EMPLOYEE("is_employee", r3),
    ANALYTIC_YEAR_CLASS("year_class", r2),
    LOGGING_HEALTH_STATS_SAMPLE_RATE("logging_health_stats_sample_rate", r2),
    LOG_ANALYTICS_EVENTS("log_analytic_events", r3),
    LOGGING_ANALYTICS_EVENTS_SAMPLE_RATE("logging_analytic_events_sample_rate", r2),
    MQTT_DEVICE_ID("/settings/mqtt/id/mqtt_device_id", r7),
    MQTT_DEVICE_SECRET("/settings/mqtt/id/mqtt_device_secret", r7),
    MQTT_DEVICE_CREDENTIALS_TIMESTAMP("/settings/mqtt/id/timestamp", r23);
    
    public final String mPrefKey;
    public final AbstractC02390aP<?> mWrapper;

    /* access modifiers changed from: public */
    static {
        AbstractC02390aP<String> r7 = AbstractC02390aP.A03;
        AbstractC02390aP<Long> r23 = AbstractC02390aP.A02;
        AbstractC02390aP<Boolean> r3 = AbstractC02390aP.A00;
        AbstractC02390aP<Integer> r2 = AbstractC02390aP.A01;
    }

    /* access modifiers changed from: public */
    EnumC02400aQ(String str, AbstractC02390aP r4) {
        this.mPrefKey = str;
        this.mWrapper = r4;
    }

    public String getPrefKey() {
        return this.mPrefKey;
    }

    public AbstractC02390aP<?> getWrapper() {
        return this.mWrapper;
    }

    public <T> T get(SharedPreferences sharedPreferences, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            return (T) this.mWrapper.A01(sharedPreferences, this.mPrefKey, t);
        }
        StringBuilder sb = new StringBuilder("Cannot cast");
        sb.append(t.getClass());
        throw new ClassCastException(sb.toString());
    }

    public <T> T get(Bundle bundle, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            return (T) this.mWrapper.A02(bundle, name(), t);
        }
        StringBuilder sb = new StringBuilder("Cannot cast");
        sb.append(t.getClass());
        throw new ClassCastException(sb.toString());
    }

    public <T> void set(SharedPreferences.Editor editor, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            this.mWrapper.A03(editor, name(), t);
            return;
        }
        StringBuilder sb = new StringBuilder("Cannot cast");
        sb.append(t.getClass());
        throw new ClassCastException(sb.toString());
    }

    public <T> void set(Bundle bundle, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            this.mWrapper.A04(bundle, name(), t);
            return;
        }
        StringBuilder sb = new StringBuilder("Cannot cast");
        sb.append(t.getClass());
        throw new ClassCastException(sb.toString());
    }
}
