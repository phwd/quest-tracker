package android.telephony;

import com.google.protobuf.Internal;

public enum SignalStrengthEnum implements Internal.EnumLite {
    SIGNAL_STRENGTH_NONE_OR_UNKNOWN(0),
    SIGNAL_STRENGTH_POOR(1),
    SIGNAL_STRENGTH_MODERATE(2),
    SIGNAL_STRENGTH_GOOD(3),
    SIGNAL_STRENGTH_GREAT(4);
    
    public static final int SIGNAL_STRENGTH_GOOD_VALUE = 3;
    public static final int SIGNAL_STRENGTH_GREAT_VALUE = 4;
    public static final int SIGNAL_STRENGTH_MODERATE_VALUE = 2;
    public static final int SIGNAL_STRENGTH_NONE_OR_UNKNOWN_VALUE = 0;
    public static final int SIGNAL_STRENGTH_POOR_VALUE = 1;
    private static final Internal.EnumLiteMap<SignalStrengthEnum> internalValueMap = new Internal.EnumLiteMap<SignalStrengthEnum>() {
        /* class android.telephony.SignalStrengthEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SignalStrengthEnum findValueByNumber(int number) {
            return SignalStrengthEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SignalStrengthEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static SignalStrengthEnum forNumber(int value2) {
        if (value2 == 0) {
            return SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
        }
        if (value2 == 1) {
            return SIGNAL_STRENGTH_POOR;
        }
        if (value2 == 2) {
            return SIGNAL_STRENGTH_MODERATE;
        }
        if (value2 == 3) {
            return SIGNAL_STRENGTH_GOOD;
        }
        if (value2 != 4) {
            return null;
        }
        return SIGNAL_STRENGTH_GREAT;
    }

    public static Internal.EnumLiteMap<SignalStrengthEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private SignalStrengthEnum(int value2) {
        this.value = value2;
    }
}
