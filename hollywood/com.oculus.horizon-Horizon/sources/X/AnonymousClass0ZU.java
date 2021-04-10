package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.internal.AttributionIdentifiers;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ZU  reason: invalid class name */
public enum AnonymousClass0ZU {
    USER_ID("u"),
    SESSION_ID("s"),
    AGENT("a"),
    CAPABILITIES("cp"),
    ENDPOINT_CAPABILITIES("ecp"),
    PUBLISH_FORMAT("pf"),
    NO_AUTOMATIC_FOREGROUND("no_auto_fg"),
    MAKE_USER_AVAILABLE_IN_FOREGROUND("chat_on"),
    INITIAL_FOREGROUND_STATE("fg"),
    DEVICE_ID("d"),
    DEVICE_SECRET("ds"),
    NETWORK_TYPE("nwt"),
    NETWORK_SUBTYPE("nwst"),
    CLIENT_MQTT_SESSION_ID("mqtt_sid"),
    SUBSCRIBE_TOPICS("st"),
    CLIENT_TYPE("ct"),
    APP_ID(AttributionIdentifiers.ATTRIBUTION_ID_COLUMN_NAME),
    OVERRIDE_NECTAR_LOGGING("log"),
    DATACENTER_PREFERENCE("dc"),
    CONNECT_HASH("h"),
    FBNS_CONNECTION_KEY("fbnsck"),
    FBNS_CONNECTION_SECRET("fbnscs"),
    FBNS_DEVICE_ID("fbnsdi"),
    FBNS_DEVICE_SECRET("fbnsds"),
    LOGGER_USER_ID("luid"),
    CLIENT_STACK("clientStack"),
    APP_SPECIFIC_INFO("app_specific_info"),
    NETWORK_TYPE_INFO("nwti");
    
    public final String mJsonKey;

    /* access modifiers changed from: public */
    AnonymousClass0ZU(String str) {
        this.mJsonKey = str;
    }

    public String getJsonKey() {
        return this.mJsonKey;
    }
}
