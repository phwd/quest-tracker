package android.bluetooth;

import com.google.protobuf.Internal;

public enum BondSubStateEnum implements Internal.EnumLite {
    BOND_SUB_STATE_UNKNOWN(0),
    BOND_SUB_STATE_LOCAL_OOB_DATA_PROVIDED(1),
    BOND_SUB_STATE_LOCAL_PIN_REQUESTED(2),
    BOND_SUB_STATE_LOCAL_PIN_REPLIED(3),
    BOND_SUB_STATE_LOCAL_SSP_REQUESTED(4),
    BOND_SUB_STATE_LOCAL_SSP_REPLIED(5);
    
    public static final int BOND_SUB_STATE_LOCAL_OOB_DATA_PROVIDED_VALUE = 1;
    public static final int BOND_SUB_STATE_LOCAL_PIN_REPLIED_VALUE = 3;
    public static final int BOND_SUB_STATE_LOCAL_PIN_REQUESTED_VALUE = 2;
    public static final int BOND_SUB_STATE_LOCAL_SSP_REPLIED_VALUE = 5;
    public static final int BOND_SUB_STATE_LOCAL_SSP_REQUESTED_VALUE = 4;
    public static final int BOND_SUB_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<BondSubStateEnum> internalValueMap = new Internal.EnumLiteMap<BondSubStateEnum>() {
        /* class android.bluetooth.BondSubStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BondSubStateEnum findValueByNumber(int number) {
            return BondSubStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BondSubStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BondSubStateEnum forNumber(int value2) {
        if (value2 == 0) {
            return BOND_SUB_STATE_UNKNOWN;
        }
        if (value2 == 1) {
            return BOND_SUB_STATE_LOCAL_OOB_DATA_PROVIDED;
        }
        if (value2 == 2) {
            return BOND_SUB_STATE_LOCAL_PIN_REQUESTED;
        }
        if (value2 == 3) {
            return BOND_SUB_STATE_LOCAL_PIN_REPLIED;
        }
        if (value2 == 4) {
            return BOND_SUB_STATE_LOCAL_SSP_REQUESTED;
        }
        if (value2 != 5) {
            return null;
        }
        return BOND_SUB_STATE_LOCAL_SSP_REPLIED;
    }

    public static Internal.EnumLiteMap<BondSubStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BondSubStateEnum(int value2) {
        this.value = value2;
    }
}
