package android.hardware.biometrics;

import com.google.protobuf.Internal;

public enum IssueEnum implements Internal.EnumLite {
    ISSUE_UNKNOWN(0),
    ISSUE_HAL_DEATH(1),
    ISSUE_UNKNOWN_TEMPLATE_ENROLLED_FRAMEWORK(2),
    ISSUE_UNKNOWN_TEMPLATE_ENROLLED_HAL(3),
    ISSUE_CANCEL_TIMED_OUT(4);
    
    public static final int ISSUE_CANCEL_TIMED_OUT_VALUE = 4;
    public static final int ISSUE_HAL_DEATH_VALUE = 1;
    public static final int ISSUE_UNKNOWN_TEMPLATE_ENROLLED_FRAMEWORK_VALUE = 2;
    public static final int ISSUE_UNKNOWN_TEMPLATE_ENROLLED_HAL_VALUE = 3;
    public static final int ISSUE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<IssueEnum> internalValueMap = new Internal.EnumLiteMap<IssueEnum>() {
        /* class android.hardware.biometrics.IssueEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public IssueEnum findValueByNumber(int number) {
            return IssueEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static IssueEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static IssueEnum forNumber(int value2) {
        if (value2 == 0) {
            return ISSUE_UNKNOWN;
        }
        if (value2 == 1) {
            return ISSUE_HAL_DEATH;
        }
        if (value2 == 2) {
            return ISSUE_UNKNOWN_TEMPLATE_ENROLLED_FRAMEWORK;
        }
        if (value2 == 3) {
            return ISSUE_UNKNOWN_TEMPLATE_ENROLLED_HAL;
        }
        if (value2 != 4) {
            return null;
        }
        return ISSUE_CANCEL_TIMED_OUT;
    }

    public static Internal.EnumLiteMap<IssueEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private IssueEnum(int value2) {
        this.value = value2;
    }
}
