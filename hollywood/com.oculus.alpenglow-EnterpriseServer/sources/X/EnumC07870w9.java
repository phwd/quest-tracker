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
/* renamed from: X.0w9  reason: invalid class name and case insensitive filesystem */
public enum EnumC07870w9 {
    BLOCKED_COUNTRIES_HOSTNAME("host_name_ipv6", r2),
    BLOCKED_COUNTRIES_ANALYTICS_ENDPOINT("analytics_endpoint", r2),
    BLOCKED_COUNTRIES_HN_TIMESTAMP("bc_host_name_timestamp", r13),
    ANALYTIC_FB_UID("fb_uid", r2),
    ANALYTIC_UID("user_id", r2),
    ANALYTIC_IS_EMPLOYEE("is_employee", r5),
    ANALYTIC_YEAR_CLASS("year_class", r4),
    LOGGING_HEALTH_STATS_SAMPLE_RATE("logging_health_stats_sample_rate", r4),
    LOG_ANALYTICS_EVENTS("log_analytic_events", r5),
    LOGGING_ANALYTICS_EVENTS_SAMPLE_RATE("logging_analytic_events_sample_rate", r4),
    MQTT_DEVICE_ID("/settings/mqtt/id/mqtt_device_id", r2),
    MQTT_DEVICE_SECRET("/settings/mqtt/id/mqtt_device_secret", r2),
    MQTT_DEVICE_CREDENTIALS_TIMESTAMP("/settings/mqtt/id/timestamp", r13);
    
    public final String mPrefKey;
    public final AbstractC07890wB<?> mWrapper;

    /* access modifiers changed from: public */
    static {
        AbstractC07890wB<String> r2 = AbstractC07890wB.A03;
        AbstractC07890wB<Long> r13 = AbstractC07890wB.A02;
        AbstractC07890wB<Boolean> r5 = AbstractC07890wB.A00;
        AbstractC07890wB<Integer> r4 = AbstractC07890wB.A01;
    }

    /* access modifiers changed from: public */
    EnumC07870w9(String str, AbstractC07890wB r4) {
        this.mPrefKey = str;
        this.mWrapper = r4;
    }

    public String getPrefKey() {
        return this.mPrefKey;
    }

    public AbstractC07890wB<?> getWrapper() {
        return this.mWrapper;
    }

    public <T> T get(SharedPreferences sharedPreferences, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            return (T) this.mWrapper.A01(sharedPreferences, this.mPrefKey, t);
        }
        throw new ClassCastException("Cannot cast" + t.getClass());
    }

    public <T> T get(Bundle bundle, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            return (T) this.mWrapper.A02(bundle, name(), t);
        }
        throw new ClassCastException("Cannot cast" + t.getClass());
    }

    public <T> void set(SharedPreferences.Editor editor, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            this.mWrapper.A03(editor, name(), t);
            return;
        }
        throw new ClassCastException("Cannot cast" + t.getClass());
    }

    public <T> void set(Bundle bundle, @Nullable T t) {
        if (t == null || this.mWrapper.A00().isInstance(t)) {
            this.mWrapper.A04(bundle, name(), t);
            return;
        }
        throw new ClassCastException("Cannot cast" + t.getClass());
    }
}
