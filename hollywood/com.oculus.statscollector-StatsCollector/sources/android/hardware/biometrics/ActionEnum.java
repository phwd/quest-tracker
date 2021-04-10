package android.hardware.biometrics;

import com.google.protobuf.Internal;

public enum ActionEnum implements Internal.EnumLite {
    ACTION_UNKNOWN(0),
    ACTION_ENROLL(1),
    ACTION_AUTHENTICATE(2),
    ACTION_ENUMERATE(3),
    ACTION_REMOVE(4);
    
    public static final int ACTION_AUTHENTICATE_VALUE = 2;
    public static final int ACTION_ENROLL_VALUE = 1;
    public static final int ACTION_ENUMERATE_VALUE = 3;
    public static final int ACTION_REMOVE_VALUE = 4;
    public static final int ACTION_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ActionEnum> internalValueMap = new Internal.EnumLiteMap<ActionEnum>() {
        /* class android.hardware.biometrics.ActionEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ActionEnum findValueByNumber(int number) {
            return ActionEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ActionEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ActionEnum forNumber(int value2) {
        if (value2 == 0) {
            return ACTION_UNKNOWN;
        }
        if (value2 == 1) {
            return ACTION_ENROLL;
        }
        if (value2 == 2) {
            return ACTION_AUTHENTICATE;
        }
        if (value2 == 3) {
            return ACTION_ENUMERATE;
        }
        if (value2 != 4) {
            return null;
        }
        return ACTION_REMOVE;
    }

    public static Internal.EnumLiteMap<ActionEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ActionEnum(int value2) {
        this.value = value2;
    }
}
