package android.server.location;

import com.google.protobuf.Internal;

public enum NfwResponseType implements Internal.EnumLite {
    REJECTED(0),
    ACCEPTED_NO_LOCATION_PROVIDED(1),
    ACCEPTED_LOCATION_PROVIDED(2);
    
    public static final int ACCEPTED_LOCATION_PROVIDED_VALUE = 2;
    public static final int ACCEPTED_NO_LOCATION_PROVIDED_VALUE = 1;
    public static final int REJECTED_VALUE = 0;
    private static final Internal.EnumLiteMap<NfwResponseType> internalValueMap = new Internal.EnumLiteMap<NfwResponseType>() {
        /* class android.server.location.NfwResponseType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NfwResponseType findValueByNumber(int number) {
            return NfwResponseType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static NfwResponseType valueOf(int value2) {
        return forNumber(value2);
    }

    public static NfwResponseType forNumber(int value2) {
        if (value2 == 0) {
            return REJECTED;
        }
        if (value2 == 1) {
            return ACCEPTED_NO_LOCATION_PROVIDED;
        }
        if (value2 != 2) {
            return null;
        }
        return ACCEPTED_LOCATION_PROVIDED;
    }

    public static Internal.EnumLiteMap<NfwResponseType> internalGetValueMap() {
        return internalValueMap;
    }

    private NfwResponseType(int value2) {
        this.value = value2;
    }
}
