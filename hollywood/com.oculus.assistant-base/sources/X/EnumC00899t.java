package X;

import com.facebook.acra.AppComponentStats;
import com.facebook.proxygen.TraceFieldType;

/* renamed from: X.9t  reason: invalid class name and case insensitive filesystem */
public enum EnumC00899t {
    MESSAGE_BUS_LAG("message_bus_lag"),
    CANCEL_REASON("cancel_reason"),
    ERROR_TYPE("error_type"),
    BUILD_SERIAL("build_serial"),
    IS_SENSITIVE("is_sensitive"),
    SHORTWAVE_ID("shortwave_id"),
    ASSISTANT_APP_VERSION("assistant_app_version"),
    OACR_ENDPOINT("oacr_endpoint"),
    ASSISTANT_RESPONSE_TYPE("assistant_response_type"),
    AUDIO_DURATION("audio_duration"),
    PROTOCOL(TraceFieldType.Protocol),
    OACR_INTERACTION_ID("oacr_interaction_id"),
    VOICE_SEARCH_ASSISTANT_CREATION_LAG_MS("voice_search_assistant_creation_lag_ms"),
    SERVICE(AppComponentStats.TAG_SERVICE),
    OACR_MODULE("oacr_module"),
    IS_TEST_TRAFFIC("is_test_traffic"),
    OACR_TRIM_NAME("oacr_trim_name"),
    IS_DEBUG_BUILD("is_debug_build"),
    IS_PERF_TEST_BUILD("is_perf_test_build"),
    IS_SCREEN_LOCKED("is_screen_locked"),
    IS_CHARGING("is_charging"),
    JOIN_ID("join_id"),
    JOIN_SOURCE("source"),
    PROCESS_CPU_SET("process_cpu_set");
    
    public String mExtraName;

    /* access modifiers changed from: public */
    EnumC00899t(String str) {
        this.mExtraName = str;
    }

    public String getExtraName() {
        return this.mExtraName;
    }
}
