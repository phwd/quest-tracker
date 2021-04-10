package android.service.notification;

import com.google.protobuf.Internal;

public enum ZenMode implements Internal.EnumLite {
    ZEN_MODE_OFF(0),
    ZEN_MODE_IMPORTANT_INTERRUPTIONS(1),
    ZEN_MODE_NO_INTERRUPTIONS(2),
    ZEN_MODE_ALARMS(3);
    
    public static final int ZEN_MODE_ALARMS_VALUE = 3;
    public static final int ZEN_MODE_IMPORTANT_INTERRUPTIONS_VALUE = 1;
    public static final int ZEN_MODE_NO_INTERRUPTIONS_VALUE = 2;
    public static final int ZEN_MODE_OFF_VALUE = 0;
    private static final Internal.EnumLiteMap<ZenMode> internalValueMap = new Internal.EnumLiteMap<ZenMode>() {
        /* class android.service.notification.ZenMode.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ZenMode findValueByNumber(int number) {
            return ZenMode.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ZenMode valueOf(int value2) {
        return forNumber(value2);
    }

    public static ZenMode forNumber(int value2) {
        if (value2 == 0) {
            return ZEN_MODE_OFF;
        }
        if (value2 == 1) {
            return ZEN_MODE_IMPORTANT_INTERRUPTIONS;
        }
        if (value2 == 2) {
            return ZEN_MODE_NO_INTERRUPTIONS;
        }
        if (value2 != 3) {
            return null;
        }
        return ZEN_MODE_ALARMS;
    }

    public static Internal.EnumLiteMap<ZenMode> internalGetValueMap() {
        return internalValueMap;
    }

    private ZenMode(int value2) {
        this.value = value2;
    }
}
