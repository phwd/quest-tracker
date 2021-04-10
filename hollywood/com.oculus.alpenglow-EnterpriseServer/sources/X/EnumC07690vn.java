package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vn  reason: invalid class name and case insensitive filesystem */
public enum EnumC07690vn {
    ADDRESSES("address", false),
    ANALYTICS("analytics", true),
    FBNS_NOTIFICATION_STORE("fbns_notification_store", true),
    FBNS_STATE("fbns_state", false),
    FLAGS("flags", true),
    IDS("ids", true),
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

    /* access modifiers changed from: public */
    EnumC07690vn(String str, boolean z) {
        this.key = str;
        this.multiProc = z;
    }

    public String getName() {
        return this.key;
    }

    public boolean isMultiProc() {
        return this.multiProc;
    }
}
