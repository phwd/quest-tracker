package android.telephony;

import com.google.protobuf.Internal;

public enum NetworkTypeEnum implements Internal.EnumLite {
    NETWORK_TYPE_UNKNOWN(0),
    NETWORK_TYPE_GPRS(1),
    NETWORK_TYPE_EDGE(2),
    NETWORK_TYPE_UMTS(3),
    NETWORK_TYPE_CDMA(4),
    NETWORK_TYPE_EVDO_0(5),
    NETWORK_TYPE_EVDO_A(6),
    NETWORK_TYPE_1XRTT(7),
    NETWORK_TYPE_HSDPA(8),
    NETWORK_TYPE_HSUPA(9),
    NETWORK_TYPE_HSPA(10),
    NETWORK_TYPE_IDEN(11),
    NETWORK_TYPE_EVDO_B(12),
    NETWORK_TYPE_LTE(13),
    NETWORK_TYPE_EHRPD(14),
    NETWORK_TYPE_HSPAP(15),
    NETWORK_TYPE_GSM(16),
    NETWORK_TYPE_TD_SCDMA(17),
    NETWORK_TYPE_IWLAN(18),
    NETWORK_TYPE_LTE_CA(19),
    NETWORK_TYPE_NR(20);
    
    public static final int NETWORK_TYPE_1XRTT_VALUE = 7;
    public static final int NETWORK_TYPE_CDMA_VALUE = 4;
    public static final int NETWORK_TYPE_EDGE_VALUE = 2;
    public static final int NETWORK_TYPE_EHRPD_VALUE = 14;
    public static final int NETWORK_TYPE_EVDO_0_VALUE = 5;
    public static final int NETWORK_TYPE_EVDO_A_VALUE = 6;
    public static final int NETWORK_TYPE_EVDO_B_VALUE = 12;
    public static final int NETWORK_TYPE_GPRS_VALUE = 1;
    public static final int NETWORK_TYPE_GSM_VALUE = 16;
    public static final int NETWORK_TYPE_HSDPA_VALUE = 8;
    public static final int NETWORK_TYPE_HSPAP_VALUE = 15;
    public static final int NETWORK_TYPE_HSPA_VALUE = 10;
    public static final int NETWORK_TYPE_HSUPA_VALUE = 9;
    public static final int NETWORK_TYPE_IDEN_VALUE = 11;
    public static final int NETWORK_TYPE_IWLAN_VALUE = 18;
    public static final int NETWORK_TYPE_LTE_CA_VALUE = 19;
    public static final int NETWORK_TYPE_LTE_VALUE = 13;
    public static final int NETWORK_TYPE_NR_VALUE = 20;
    public static final int NETWORK_TYPE_TD_SCDMA_VALUE = 17;
    public static final int NETWORK_TYPE_UMTS_VALUE = 3;
    public static final int NETWORK_TYPE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<NetworkTypeEnum> internalValueMap = new Internal.EnumLiteMap<NetworkTypeEnum>() {
        /* class android.telephony.NetworkTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NetworkTypeEnum findValueByNumber(int number) {
            return NetworkTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static NetworkTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static NetworkTypeEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return NETWORK_TYPE_UNKNOWN;
            case 1:
                return NETWORK_TYPE_GPRS;
            case 2:
                return NETWORK_TYPE_EDGE;
            case 3:
                return NETWORK_TYPE_UMTS;
            case 4:
                return NETWORK_TYPE_CDMA;
            case 5:
                return NETWORK_TYPE_EVDO_0;
            case 6:
                return NETWORK_TYPE_EVDO_A;
            case 7:
                return NETWORK_TYPE_1XRTT;
            case 8:
                return NETWORK_TYPE_HSDPA;
            case 9:
                return NETWORK_TYPE_HSUPA;
            case 10:
                return NETWORK_TYPE_HSPA;
            case 11:
                return NETWORK_TYPE_IDEN;
            case 12:
                return NETWORK_TYPE_EVDO_B;
            case 13:
                return NETWORK_TYPE_LTE;
            case 14:
                return NETWORK_TYPE_EHRPD;
            case 15:
                return NETWORK_TYPE_HSPAP;
            case 16:
                return NETWORK_TYPE_GSM;
            case 17:
                return NETWORK_TYPE_TD_SCDMA;
            case 18:
                return NETWORK_TYPE_IWLAN;
            case 19:
                return NETWORK_TYPE_LTE_CA;
            case 20:
                return NETWORK_TYPE_NR;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<NetworkTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private NetworkTypeEnum(int value2) {
        this.value = value2;
    }
}
