package android.media;

import com.google.protobuf.Internal;

public enum Usage implements Internal.EnumLite {
    USAGE_UNKNOWN(0),
    MEDIA(1),
    VOICE_COMMUNICATION(2),
    VOICE_COMMUNICATION_SIGNALLING(3),
    ALARM(4),
    NOTIFICATION(5),
    NOTIFICATION_RINGTONE(6),
    NOTIFICATION_COMMUNICATION_REQUEST(7),
    NOTIFICATION_COMMUNICATION_INSTANT(8),
    NOTIFICATION_COMMUNICATION_DELAYED(9),
    NOTIFICATION_EVENT(10),
    ASSISTANCE_ACCESSIBILITY(11),
    ASSISTANCE_NAVIGATION_GUIDANCE(12),
    ASSISTANCE_SONIFICATION(13),
    GAME(14),
    VIRTUAL_SOURCE(15),
    ASSISTANT(16);
    
    public static final int ALARM_VALUE = 4;
    public static final int ASSISTANCE_ACCESSIBILITY_VALUE = 11;
    public static final int ASSISTANCE_NAVIGATION_GUIDANCE_VALUE = 12;
    public static final int ASSISTANCE_SONIFICATION_VALUE = 13;
    public static final int ASSISTANT_VALUE = 16;
    public static final int GAME_VALUE = 14;
    public static final int MEDIA_VALUE = 1;
    public static final int NOTIFICATION_COMMUNICATION_DELAYED_VALUE = 9;
    public static final int NOTIFICATION_COMMUNICATION_INSTANT_VALUE = 8;
    public static final int NOTIFICATION_COMMUNICATION_REQUEST_VALUE = 7;
    public static final int NOTIFICATION_EVENT_VALUE = 10;
    public static final int NOTIFICATION_RINGTONE_VALUE = 6;
    public static final int NOTIFICATION_VALUE = 5;
    public static final int USAGE_UNKNOWN_VALUE = 0;
    public static final int VIRTUAL_SOURCE_VALUE = 15;
    public static final int VOICE_COMMUNICATION_SIGNALLING_VALUE = 3;
    public static final int VOICE_COMMUNICATION_VALUE = 2;
    private static final Internal.EnumLiteMap<Usage> internalValueMap = new Internal.EnumLiteMap<Usage>() {
        /* class android.media.Usage.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public Usage findValueByNumber(int number) {
            return Usage.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Usage valueOf(int value2) {
        return forNumber(value2);
    }

    public static Usage forNumber(int value2) {
        switch (value2) {
            case 0:
                return USAGE_UNKNOWN;
            case 1:
                return MEDIA;
            case 2:
                return VOICE_COMMUNICATION;
            case 3:
                return VOICE_COMMUNICATION_SIGNALLING;
            case 4:
                return ALARM;
            case 5:
                return NOTIFICATION;
            case 6:
                return NOTIFICATION_RINGTONE;
            case 7:
                return NOTIFICATION_COMMUNICATION_REQUEST;
            case 8:
                return NOTIFICATION_COMMUNICATION_INSTANT;
            case 9:
                return NOTIFICATION_COMMUNICATION_DELAYED;
            case 10:
                return NOTIFICATION_EVENT;
            case 11:
                return ASSISTANCE_ACCESSIBILITY;
            case 12:
                return ASSISTANCE_NAVIGATION_GUIDANCE;
            case 13:
                return ASSISTANCE_SONIFICATION;
            case 14:
                return GAME;
            case 15:
                return VIRTUAL_SOURCE;
            case 16:
                return ASSISTANT;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<Usage> internalGetValueMap() {
        return internalValueMap;
    }

    private Usage(int value2) {
        this.value = value2;
    }
}
