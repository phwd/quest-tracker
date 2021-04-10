package X;

import com.facebook.acra.criticaldata.CriticalAppData;
import com.facebook.infer.annotation.Nullsafe;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22f  reason: invalid class name and case insensitive filesystem */
public final class EnumC141722f extends Enum<EnumC141722f> {
    public static final /* synthetic */ EnumC141722f[] $VALUES;
    public static final EnumC141722f AGENT;
    public static final EnumC141722f APP_ID;
    public static final EnumC141722f APP_SPECIFIC_INFO;
    public static final EnumC141722f CAPABILITIES;
    public static final EnumC141722f CLIENT_MQTT_SESSION_ID;
    public static final EnumC141722f CLIENT_STACK;
    public static final EnumC141722f CLIENT_TYPE;
    public static final EnumC141722f CONNECT_HASH;
    public static final EnumC141722f DATACENTER_PREFERENCE;
    public static final EnumC141722f DEVICE_ID;
    public static final EnumC141722f DEVICE_SECRET;
    public static final EnumC141722f ENDPOINT_CAPABILITIES;
    public static final EnumC141722f FBNS_CONNECTION_KEY;
    public static final EnumC141722f FBNS_CONNECTION_SECRET;
    public static final EnumC141722f FBNS_DEVICE_ID;
    public static final EnumC141722f FBNS_DEVICE_SECRET;
    public static final EnumC141722f INITIAL_FOREGROUND_STATE;
    public static final EnumC141722f LOGGER_USER_ID;
    public static final EnumC141722f MAKE_USER_AVAILABLE_IN_FOREGROUND;
    public static final EnumC141722f NETWORK_SUBTYPE;
    public static final EnumC141722f NETWORK_TYPE;
    public static final EnumC141722f NETWORK_TYPE_INFO;
    public static final EnumC141722f NO_AUTOMATIC_FOREGROUND;
    public static final EnumC141722f OVERRIDE_NECTAR_LOGGING;
    public static final EnumC141722f PUBLISH_FORMAT;
    public static final EnumC141722f SESSION_ID;
    public static final EnumC141722f SUBSCRIBE_TOPICS;
    public static final EnumC141722f USER_ID;
    public final String mJsonKey;

    static {
        EnumC141722f r32 = new EnumC141722f(CriticalAppData.USER_ID, 0, "u");
        USER_ID = r32;
        EnumC141722f r30 = new EnumC141722f("SESSION_ID", 1, "s");
        SESSION_ID = r30;
        EnumC141722f r29 = new EnumC141722f("AGENT", 2, "a");
        AGENT = r29;
        EnumC141722f r28 = new EnumC141722f("CAPABILITIES", 3, "cp");
        CAPABILITIES = r28;
        EnumC141722f r27 = new EnumC141722f("ENDPOINT_CAPABILITIES", 4, "ecp");
        ENDPOINT_CAPABILITIES = r27;
        EnumC141722f r26 = new EnumC141722f("PUBLISH_FORMAT", 5, "pf");
        PUBLISH_FORMAT = r26;
        EnumC141722f r25 = new EnumC141722f("NO_AUTOMATIC_FOREGROUND", 6, "no_auto_fg");
        NO_AUTOMATIC_FOREGROUND = r25;
        EnumC141722f r24 = new EnumC141722f("MAKE_USER_AVAILABLE_IN_FOREGROUND", 7, "chat_on");
        MAKE_USER_AVAILABLE_IN_FOREGROUND = r24;
        EnumC141722f r23 = new EnumC141722f("INITIAL_FOREGROUND_STATE", 8, "fg");
        INITIAL_FOREGROUND_STATE = r23;
        EnumC141722f r14 = new EnumC141722f(CriticalAppData.DEVICE_ID, 9, "d");
        DEVICE_ID = r14;
        EnumC141722f r22 = new EnumC141722f("DEVICE_SECRET", 10, "ds");
        DEVICE_SECRET = r22;
        EnumC141722f r21 = new EnumC141722f("NETWORK_TYPE", 11, "nwt");
        NETWORK_TYPE = r21;
        EnumC141722f r20 = new EnumC141722f("NETWORK_SUBTYPE", 12, "nwst");
        NETWORK_SUBTYPE = r20;
        EnumC141722f r19 = new EnumC141722f("CLIENT_MQTT_SESSION_ID", 13, "mqtt_sid");
        CLIENT_MQTT_SESSION_ID = r19;
        EnumC141722f r18 = new EnumC141722f("SUBSCRIBE_TOPICS", 14, "st");
        SUBSCRIBE_TOPICS = r18;
        EnumC141722f r17 = new EnumC141722f("CLIENT_TYPE", 15, "ct");
        CLIENT_TYPE = r17;
        EnumC141722f r15 = new EnumC141722f("APP_ID", 16, "aid");
        APP_ID = r15;
        EnumC141722f r13 = new EnumC141722f("OVERRIDE_NECTAR_LOGGING", 17, "log");
        OVERRIDE_NECTAR_LOGGING = r13;
        EnumC141722f r12 = new EnumC141722f("DATACENTER_PREFERENCE", 18, "dc");
        DATACENTER_PREFERENCE = r12;
        EnumC141722f r11 = new EnumC141722f("CONNECT_HASH", 19, "h");
        CONNECT_HASH = r11;
        EnumC141722f r10 = new EnumC141722f("FBNS_CONNECTION_KEY", 20, "fbnsck");
        FBNS_CONNECTION_KEY = r10;
        EnumC141722f r9 = new EnumC141722f("FBNS_CONNECTION_SECRET", 21, "fbnscs");
        FBNS_CONNECTION_SECRET = r9;
        EnumC141722f r8 = new EnumC141722f("FBNS_DEVICE_ID", 22, "fbnsdi");
        FBNS_DEVICE_ID = r8;
        EnumC141722f r7 = new EnumC141722f("FBNS_DEVICE_SECRET", 23, "fbnsds");
        FBNS_DEVICE_SECRET = r7;
        EnumC141722f r6 = new EnumC141722f("LOGGER_USER_ID", 24, "luid");
        LOGGER_USER_ID = r6;
        EnumC141722f r5 = new EnumC141722f("CLIENT_STACK", 25, "clientStack");
        CLIENT_STACK = r5;
        EnumC141722f r1 = new EnumC141722f("APP_SPECIFIC_INFO", 26, "app_specific_info");
        APP_SPECIFIC_INFO = r1;
        EnumC141722f r16 = new EnumC141722f("NETWORK_TYPE_INFO", 27, "nwti");
        NETWORK_TYPE_INFO = r16;
        EnumC141722f[] r2 = new EnumC141722f[28];
        System.arraycopy(new EnumC141722f[]{r32, r30, r29, r28, r27, r26, r25, r24, r23, r14, r22, r21, r20, r19, r18, r17, r15, r13, r12, r11, r10, r9, r8, r7, r6, r5, r1}, 0, r2, 0, 27);
        System.arraycopy(new EnumC141722f[]{r16}, 0, r2, 27, 1);
        $VALUES = r2;
    }

    public String getJsonKey() {
        return this.mJsonKey;
    }

    public EnumC141722f(String str, int i, String str2) {
        this.mJsonKey = str2;
    }
}
