package X;

import com.facebook.infer.annotation.Nullsafe;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0xh  reason: invalid class name and case insensitive filesystem */
public final class EnumC08710xh extends Enum<EnumC08710xh> {
    public static final /* synthetic */ EnumC08710xh[] $VALUES;
    public static final EnumC08710xh AGENT;
    public static final EnumC08710xh APP_ID;
    public static final EnumC08710xh APP_SPECIFIC_INFO;
    public static final EnumC08710xh CAPABILITIES;
    public static final EnumC08710xh CLIENT_MQTT_SESSION_ID;
    public static final EnumC08710xh CLIENT_STACK;
    public static final EnumC08710xh CLIENT_TYPE;
    public static final EnumC08710xh CONNECT_HASH;
    public static final EnumC08710xh DATACENTER_PREFERENCE;
    public static final EnumC08710xh DEVICE_ID;
    public static final EnumC08710xh DEVICE_SECRET;
    public static final EnumC08710xh ENDPOINT_CAPABILITIES;
    public static final EnumC08710xh FBNS_CONNECTION_KEY;
    public static final EnumC08710xh FBNS_CONNECTION_SECRET;
    public static final EnumC08710xh FBNS_DEVICE_ID;
    public static final EnumC08710xh FBNS_DEVICE_SECRET;
    public static final EnumC08710xh INITIAL_FOREGROUND_STATE;
    public static final EnumC08710xh LOGGER_USER_ID;
    public static final EnumC08710xh MAKE_USER_AVAILABLE_IN_FOREGROUND;
    public static final EnumC08710xh NETWORK_SUBTYPE;
    public static final EnumC08710xh NETWORK_TYPE;
    public static final EnumC08710xh NETWORK_TYPE_INFO;
    public static final EnumC08710xh NO_AUTOMATIC_FOREGROUND;
    public static final EnumC08710xh OVERRIDE_NECTAR_LOGGING;
    public static final EnumC08710xh PUBLISH_FORMAT;
    public static final EnumC08710xh SESSION_ID;
    public static final EnumC08710xh SUBSCRIBE_TOPICS;
    public static final EnumC08710xh USER_ID;
    public final String mJsonKey;

    static {
        EnumC08710xh r32 = new EnumC08710xh("USER_ID", 0, "u");
        USER_ID = r32;
        EnumC08710xh r30 = new EnumC08710xh("SESSION_ID", 1, "s");
        SESSION_ID = r30;
        EnumC08710xh r29 = new EnumC08710xh("AGENT", 2, "a");
        AGENT = r29;
        EnumC08710xh r28 = new EnumC08710xh("CAPABILITIES", 3, "cp");
        CAPABILITIES = r28;
        EnumC08710xh r27 = new EnumC08710xh("ENDPOINT_CAPABILITIES", 4, "ecp");
        ENDPOINT_CAPABILITIES = r27;
        EnumC08710xh r26 = new EnumC08710xh("PUBLISH_FORMAT", 5, "pf");
        PUBLISH_FORMAT = r26;
        EnumC08710xh r25 = new EnumC08710xh("NO_AUTOMATIC_FOREGROUND", 6, "no_auto_fg");
        NO_AUTOMATIC_FOREGROUND = r25;
        EnumC08710xh r24 = new EnumC08710xh("MAKE_USER_AVAILABLE_IN_FOREGROUND", 7, "chat_on");
        MAKE_USER_AVAILABLE_IN_FOREGROUND = r24;
        EnumC08710xh r23 = new EnumC08710xh("INITIAL_FOREGROUND_STATE", 8, "fg");
        INITIAL_FOREGROUND_STATE = r23;
        EnumC08710xh r14 = new EnumC08710xh("DEVICE_ID", 9, "d");
        DEVICE_ID = r14;
        EnumC08710xh r22 = new EnumC08710xh("DEVICE_SECRET", 10, "ds");
        DEVICE_SECRET = r22;
        EnumC08710xh r21 = new EnumC08710xh("NETWORK_TYPE", 11, "nwt");
        NETWORK_TYPE = r21;
        EnumC08710xh r20 = new EnumC08710xh("NETWORK_SUBTYPE", 12, "nwst");
        NETWORK_SUBTYPE = r20;
        EnumC08710xh r19 = new EnumC08710xh("CLIENT_MQTT_SESSION_ID", 13, "mqtt_sid");
        CLIENT_MQTT_SESSION_ID = r19;
        EnumC08710xh r18 = new EnumC08710xh("SUBSCRIBE_TOPICS", 14, "st");
        SUBSCRIBE_TOPICS = r18;
        EnumC08710xh r17 = new EnumC08710xh("CLIENT_TYPE", 15, "ct");
        CLIENT_TYPE = r17;
        EnumC08710xh r15 = new EnumC08710xh("APP_ID", 16, "aid");
        APP_ID = r15;
        EnumC08710xh r13 = new EnumC08710xh("OVERRIDE_NECTAR_LOGGING", 17, "log");
        OVERRIDE_NECTAR_LOGGING = r13;
        EnumC08710xh r12 = new EnumC08710xh("DATACENTER_PREFERENCE", 18, "dc");
        DATACENTER_PREFERENCE = r12;
        EnumC08710xh r11 = new EnumC08710xh("CONNECT_HASH", 19, "h");
        CONNECT_HASH = r11;
        EnumC08710xh r10 = new EnumC08710xh("FBNS_CONNECTION_KEY", 20, "fbnsck");
        FBNS_CONNECTION_KEY = r10;
        EnumC08710xh r9 = new EnumC08710xh("FBNS_CONNECTION_SECRET", 21, "fbnscs");
        FBNS_CONNECTION_SECRET = r9;
        EnumC08710xh r8 = new EnumC08710xh("FBNS_DEVICE_ID", 22, "fbnsdi");
        FBNS_DEVICE_ID = r8;
        EnumC08710xh r7 = new EnumC08710xh("FBNS_DEVICE_SECRET", 23, "fbnsds");
        FBNS_DEVICE_SECRET = r7;
        EnumC08710xh r6 = new EnumC08710xh("LOGGER_USER_ID", 24, "luid");
        LOGGER_USER_ID = r6;
        EnumC08710xh r5 = new EnumC08710xh("CLIENT_STACK", 25, "clientStack");
        CLIENT_STACK = r5;
        EnumC08710xh r1 = new EnumC08710xh("APP_SPECIFIC_INFO", 26, "app_specific_info");
        APP_SPECIFIC_INFO = r1;
        EnumC08710xh r16 = new EnumC08710xh("NETWORK_TYPE_INFO", 27, "nwti");
        NETWORK_TYPE_INFO = r16;
        EnumC08710xh[] r2 = new EnumC08710xh[28];
        System.arraycopy(new EnumC08710xh[]{r32, r30, r29, r28, r27, r26, r25, r24, r23, r14, r22, r21, r20, r19, r18, r17, r15, r13, r12, r11, r10, r9, r8, r7, r6, r5, r1}, 0, r2, 0, 27);
        System.arraycopy(new EnumC08710xh[]{r16}, 0, r2, 27, 1);
        $VALUES = r2;
    }

    public EnumC08710xh(String str, int i, String str2) {
        this.mJsonKey = str2;
    }

    public String getJsonKey() {
        return this.mJsonKey;
    }
}
