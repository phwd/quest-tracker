package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.api.graphql.GraphQLEventsQuery;
import libraries.marauder.analytics.AnalyticsUtil;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1PL  reason: invalid class name */
public enum AnonymousClass1PL {
    ADDRESSES("address", false),
    ANALYTICS(AnalyticsUtil.ANALYTICS_DIRECTORY_NAME, true),
    FBNS_NOTIFICATION_STORE("fbns_notification_store", true),
    FBNS_STATE("fbns_state", false),
    FLAGS("flags", true),
    IDS(GraphQLEventsQuery.EVENT_IDS_PARAM, true),
    OXYGEN_FBNS_CONFIG("oxygen_fbns_config", false),
    REGISTRATIONS("registrations", false),
    RETRY("retry", false),
    GATEKEEPERS("gk", false),
    MQTT_RADIO_ACTIVE_TIME("mqtt_radio_active_time", false),
    TOKEN_STORE("token_store", false),
    RUNTIME_PARAMS("runtime_params", false),
    DEBUG("mqtt_debug", false),
    MQTT_CONFIG("mqtt_config", false),
    LAST_HOST("mqtt_last_host", false);
    
    public static final String RTI_PREFIX = "rti.mqtt.";
    public final String key;
    public final boolean multiProc;

    public String getName() {
        return this.key;
    }

    public boolean isMultiProc() {
        return this.multiProc;
    }

    /* access modifiers changed from: public */
    AnonymousClass1PL(String str, boolean z) {
        this.key = str;
        this.multiProc = z;
    }
}
