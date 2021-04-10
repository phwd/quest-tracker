package android.bluetooth;

import com.google.protobuf.Internal;

public enum BondStateEnum implements Internal.EnumLite {
    BOND_STATE_UNKNOWN(0),
    BOND_STATE_NONE(10),
    BOND_STATE_BONDING(11),
    BOND_STATE_BONDED(12);
    
    public static final int BOND_STATE_BONDED_VALUE = 12;
    public static final int BOND_STATE_BONDING_VALUE = 11;
    public static final int BOND_STATE_NONE_VALUE = 10;
    public static final int BOND_STATE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<BondStateEnum> internalValueMap = new Internal.EnumLiteMap<BondStateEnum>() {
        /* class android.bluetooth.BondStateEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BondStateEnum findValueByNumber(int number) {
            return BondStateEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BondStateEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BondStateEnum forNumber(int value2) {
        if (value2 == 0) {
            return BOND_STATE_UNKNOWN;
        }
        switch (value2) {
            case 10:
                return BOND_STATE_NONE;
            case 11:
                return BOND_STATE_BONDING;
            case 12:
                return BOND_STATE_BONDED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<BondStateEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BondStateEnum(int value2) {
        this.value = value2;
    }
}
