package android.server.location;

import com.google.protobuf.Internal;

public enum NfwRequestor implements Internal.EnumLite {
    CARRIER(0),
    OEM(10),
    MODEM_CHIPSET_VENDOR(11),
    GNSS_CHIPSET_VENDOR(12),
    OTHER_CHIPSET_VENDOR(13),
    AUTOMOBILE_CLIENT(20),
    OTHER_REQUESTOR(100);
    
    public static final int AUTOMOBILE_CLIENT_VALUE = 20;
    public static final int CARRIER_VALUE = 0;
    public static final int GNSS_CHIPSET_VENDOR_VALUE = 12;
    public static final int MODEM_CHIPSET_VENDOR_VALUE = 11;
    public static final int OEM_VALUE = 10;
    public static final int OTHER_CHIPSET_VENDOR_VALUE = 13;
    public static final int OTHER_REQUESTOR_VALUE = 100;
    private static final Internal.EnumLiteMap<NfwRequestor> internalValueMap = new Internal.EnumLiteMap<NfwRequestor>() {
        /* class android.server.location.NfwRequestor.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NfwRequestor findValueByNumber(int number) {
            return NfwRequestor.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static NfwRequestor valueOf(int value2) {
        return forNumber(value2);
    }

    public static NfwRequestor forNumber(int value2) {
        if (value2 == 0) {
            return CARRIER;
        }
        if (value2 == 20) {
            return AUTOMOBILE_CLIENT;
        }
        if (value2 == 100) {
            return OTHER_REQUESTOR;
        }
        switch (value2) {
            case 10:
                return OEM;
            case 11:
                return MODEM_CHIPSET_VENDOR;
            case 12:
                return GNSS_CHIPSET_VENDOR;
            case 13:
                return OTHER_CHIPSET_VENDOR;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<NfwRequestor> internalGetValueMap() {
        return internalValueMap;
    }

    private NfwRequestor(int value2) {
        this.value = value2;
    }
}
