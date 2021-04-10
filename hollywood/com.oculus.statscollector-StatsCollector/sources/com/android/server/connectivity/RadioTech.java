package com.android.server.connectivity;

import com.google.protobuf.Internal;

public enum RadioTech implements Internal.EnumLite {
    RADIO_TECHNOLOGY_UNKNOWN(0),
    RADIO_TECHNOLOGY_GPRS(1),
    RADIO_TECHNOLOGY_EDGE(2),
    RADIO_TECHNOLOGY_UMTS(3),
    RADIO_TECHNOLOGY_IS95A(4),
    RADIO_TECHNOLOGY_IS95B(5),
    RADIO_TECHNOLOGY_1XRTT(6),
    RADIO_TECHNOLOGY_EVDO_0(7),
    RADIO_TECHNOLOGY_EVDO_A(8),
    RADIO_TECHNOLOGY_HSDPA(9),
    RADIO_TECHNOLOGY_HSUPA(10),
    RADIO_TECHNOLOGY_HSPA(11),
    RADIO_TECHNOLOGY_EVDO_B(12),
    RADIO_TECHNOLOGY_EHRPD(13),
    RADIO_TECHNOLOGY_LTE(14),
    RADIO_TECHNOLOGY_HSPAP(15),
    RADIO_TECHNOLOGY_GSM(16),
    RADIO_TECHNOLOGY_TD_SCDMA(17),
    RADIO_TECHNOLOGY_IWLAN(18),
    RADIO_TECHNOLOGY_LTE_CA(19),
    RADIO_TECHNOLOGY_NR(20);
    
    public static final int RADIO_TECHNOLOGY_1XRTT_VALUE = 6;
    public static final int RADIO_TECHNOLOGY_EDGE_VALUE = 2;
    public static final int RADIO_TECHNOLOGY_EHRPD_VALUE = 13;
    public static final int RADIO_TECHNOLOGY_EVDO_0_VALUE = 7;
    public static final int RADIO_TECHNOLOGY_EVDO_A_VALUE = 8;
    public static final int RADIO_TECHNOLOGY_EVDO_B_VALUE = 12;
    public static final int RADIO_TECHNOLOGY_GPRS_VALUE = 1;
    public static final int RADIO_TECHNOLOGY_GSM_VALUE = 16;
    public static final int RADIO_TECHNOLOGY_HSDPA_VALUE = 9;
    public static final int RADIO_TECHNOLOGY_HSPAP_VALUE = 15;
    public static final int RADIO_TECHNOLOGY_HSPA_VALUE = 11;
    public static final int RADIO_TECHNOLOGY_HSUPA_VALUE = 10;
    public static final int RADIO_TECHNOLOGY_IS95A_VALUE = 4;
    public static final int RADIO_TECHNOLOGY_IS95B_VALUE = 5;
    public static final int RADIO_TECHNOLOGY_IWLAN_VALUE = 18;
    public static final int RADIO_TECHNOLOGY_LTE_CA_VALUE = 19;
    public static final int RADIO_TECHNOLOGY_LTE_VALUE = 14;
    public static final int RADIO_TECHNOLOGY_NR_VALUE = 20;
    public static final int RADIO_TECHNOLOGY_TD_SCDMA_VALUE = 17;
    public static final int RADIO_TECHNOLOGY_UMTS_VALUE = 3;
    public static final int RADIO_TECHNOLOGY_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<RadioTech> internalValueMap = new Internal.EnumLiteMap<RadioTech>() {
        /* class com.android.server.connectivity.RadioTech.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public RadioTech findValueByNumber(int number) {
            return RadioTech.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static RadioTech valueOf(int value2) {
        return forNumber(value2);
    }

    public static RadioTech forNumber(int value2) {
        switch (value2) {
            case 0:
                return RADIO_TECHNOLOGY_UNKNOWN;
            case 1:
                return RADIO_TECHNOLOGY_GPRS;
            case 2:
                return RADIO_TECHNOLOGY_EDGE;
            case 3:
                return RADIO_TECHNOLOGY_UMTS;
            case 4:
                return RADIO_TECHNOLOGY_IS95A;
            case 5:
                return RADIO_TECHNOLOGY_IS95B;
            case 6:
                return RADIO_TECHNOLOGY_1XRTT;
            case 7:
                return RADIO_TECHNOLOGY_EVDO_0;
            case 8:
                return RADIO_TECHNOLOGY_EVDO_A;
            case 9:
                return RADIO_TECHNOLOGY_HSDPA;
            case 10:
                return RADIO_TECHNOLOGY_HSUPA;
            case 11:
                return RADIO_TECHNOLOGY_HSPA;
            case 12:
                return RADIO_TECHNOLOGY_EVDO_B;
            case 13:
                return RADIO_TECHNOLOGY_EHRPD;
            case 14:
                return RADIO_TECHNOLOGY_LTE;
            case 15:
                return RADIO_TECHNOLOGY_HSPAP;
            case 16:
                return RADIO_TECHNOLOGY_GSM;
            case 17:
                return RADIO_TECHNOLOGY_TD_SCDMA;
            case 18:
                return RADIO_TECHNOLOGY_IWLAN;
            case 19:
                return RADIO_TECHNOLOGY_LTE_CA;
            case 20:
                return RADIO_TECHNOLOGY_NR;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<RadioTech> internalGetValueMap() {
        return internalValueMap;
    }

    private RadioTech(int value2) {
        this.value = value2;
    }
}
