package android.bluetooth.smp;

import com.google.protobuf.Internal;

public enum PairingFailReasonEnum implements Internal.EnumLite {
    PAIRING_FAIL_REASON_RESERVED(0),
    PAIRING_FAIL_REASON_PASSKEY_ENTRY(1),
    PAIRING_FAIL_REASON_OOB(2),
    PAIRING_FAIL_REASON_AUTH_REQ(3),
    PAIRING_FAIL_REASON_CONFIRM_VALUE(4),
    PAIRING_FAIL_REASON_PAIR_NOT_SUPPORT(5),
    PAIRING_FAIL_REASON_ENC_KEY_SIZE(6),
    PAIRING_FAIL_REASON_INVALID_CMD(7),
    PAIRING_FAIL_REASON_UNSPECIFIED(8),
    PAIRING_FAIL_REASON_REPEATED_ATTEMPTS(9),
    PAIRING_FAIL_REASON_INVALID_PARAMETERS(10),
    PAIRING_FAIL_REASON_DHKEY_CHK(11),
    PAIRING_FAIL_REASON_NUMERIC_COMPARISON(12),
    PAIRING_FAIL_REASON_CLASSIC_PAIRING_IN_PROGR(13),
    PAIRING_FAIL_REASON_XTRANS_DERIVE_NOT_ALLOW(14);
    
    public static final int PAIRING_FAIL_REASON_AUTH_REQ_VALUE = 3;
    public static final int PAIRING_FAIL_REASON_CLASSIC_PAIRING_IN_PROGR_VALUE = 13;
    public static final int PAIRING_FAIL_REASON_CONFIRM_VALUE_VALUE = 4;
    public static final int PAIRING_FAIL_REASON_DHKEY_CHK_VALUE = 11;
    public static final int PAIRING_FAIL_REASON_ENC_KEY_SIZE_VALUE = 6;
    public static final int PAIRING_FAIL_REASON_INVALID_CMD_VALUE = 7;
    public static final int PAIRING_FAIL_REASON_INVALID_PARAMETERS_VALUE = 10;
    public static final int PAIRING_FAIL_REASON_NUMERIC_COMPARISON_VALUE = 12;
    public static final int PAIRING_FAIL_REASON_OOB_VALUE = 2;
    public static final int PAIRING_FAIL_REASON_PAIR_NOT_SUPPORT_VALUE = 5;
    public static final int PAIRING_FAIL_REASON_PASSKEY_ENTRY_VALUE = 1;
    public static final int PAIRING_FAIL_REASON_REPEATED_ATTEMPTS_VALUE = 9;
    public static final int PAIRING_FAIL_REASON_RESERVED_VALUE = 0;
    public static final int PAIRING_FAIL_REASON_UNSPECIFIED_VALUE = 8;
    public static final int PAIRING_FAIL_REASON_XTRANS_DERIVE_NOT_ALLOW_VALUE = 14;
    private static final Internal.EnumLiteMap<PairingFailReasonEnum> internalValueMap = new Internal.EnumLiteMap<PairingFailReasonEnum>() {
        /* class android.bluetooth.smp.PairingFailReasonEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public PairingFailReasonEnum findValueByNumber(int number) {
            return PairingFailReasonEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static PairingFailReasonEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static PairingFailReasonEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return PAIRING_FAIL_REASON_RESERVED;
            case 1:
                return PAIRING_FAIL_REASON_PASSKEY_ENTRY;
            case 2:
                return PAIRING_FAIL_REASON_OOB;
            case 3:
                return PAIRING_FAIL_REASON_AUTH_REQ;
            case 4:
                return PAIRING_FAIL_REASON_CONFIRM_VALUE;
            case 5:
                return PAIRING_FAIL_REASON_PAIR_NOT_SUPPORT;
            case 6:
                return PAIRING_FAIL_REASON_ENC_KEY_SIZE;
            case 7:
                return PAIRING_FAIL_REASON_INVALID_CMD;
            case 8:
                return PAIRING_FAIL_REASON_UNSPECIFIED;
            case 9:
                return PAIRING_FAIL_REASON_REPEATED_ATTEMPTS;
            case 10:
                return PAIRING_FAIL_REASON_INVALID_PARAMETERS;
            case 11:
                return PAIRING_FAIL_REASON_DHKEY_CHK;
            case 12:
                return PAIRING_FAIL_REASON_NUMERIC_COMPARISON;
            case 13:
                return PAIRING_FAIL_REASON_CLASSIC_PAIRING_IN_PROGR;
            case 14:
                return PAIRING_FAIL_REASON_XTRANS_DERIVE_NOT_ALLOW;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<PairingFailReasonEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private PairingFailReasonEnum(int value2) {
        this.value = value2;
    }
}
