package android.bluetooth;

import com.google.protobuf.Internal;

public enum LinkTypeEnum implements Internal.EnumLite {
    LINK_TYPE_UNKNOWN(4095),
    LINK_TYPE_SCO(0),
    LINK_TYPE_ACL(1),
    LINK_TYPE_ESCO(2);
    
    public static final int LINK_TYPE_ACL_VALUE = 1;
    public static final int LINK_TYPE_ESCO_VALUE = 2;
    public static final int LINK_TYPE_SCO_VALUE = 0;
    public static final int LINK_TYPE_UNKNOWN_VALUE = 4095;
    private static final Internal.EnumLiteMap<LinkTypeEnum> internalValueMap = new Internal.EnumLiteMap<LinkTypeEnum>() {
        /* class android.bluetooth.LinkTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LinkTypeEnum findValueByNumber(int number) {
            return LinkTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LinkTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static LinkTypeEnum forNumber(int value2) {
        if (value2 == 0) {
            return LINK_TYPE_SCO;
        }
        if (value2 == 1) {
            return LINK_TYPE_ACL;
        }
        if (value2 == 2) {
            return LINK_TYPE_ESCO;
        }
        if (value2 != 4095) {
            return null;
        }
        return LINK_TYPE_UNKNOWN;
    }

    public static Internal.EnumLiteMap<LinkTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private LinkTypeEnum(int value2) {
        this.value = value2;
    }
}
