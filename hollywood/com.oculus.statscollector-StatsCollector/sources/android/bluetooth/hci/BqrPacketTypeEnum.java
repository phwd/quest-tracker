package android.bluetooth.hci;

import com.google.protobuf.Internal;

public enum BqrPacketTypeEnum implements Internal.EnumLite {
    BQR_PACKET_TYPE_UNKNOWN(0),
    BQR_PACKET_TYPE_ID(1),
    BQR_PACKET_TYPE_NULL(2),
    BQR_PACKET_TYPE_POLL(3),
    BQR_PACKET_TYPE_FHS(4),
    BQR_PACKET_TYPE_HV1(5),
    BQR_PACKET_TYPE_HV2(6),
    BQR_PACKET_TYPE_HV3(7),
    BQR_PACKET_TYPE_DV(8),
    BQR_PACKET_TYPE_EV3(9),
    BQR_PACKET_TYPE_EV4(10),
    BQR_PACKET_TYPE_EV5(11),
    BQR_PACKET_TYPE_2EV3(12),
    BQR_PACKET_TYPE_2EV5(13),
    BQR_PACKET_TYPE_3EV3(14),
    BQR_PACKET_TYPE_3EV5(15),
    BQR_PACKET_TYPE_DM1(16),
    BQR_PACKET_TYPE_DH1(17),
    BQR_PACKET_TYPE_DM3(18),
    BQR_PACKET_TYPE_DH3(19),
    BQR_PACKET_TYPE_DM5(20),
    BQR_PACKET_TYPE_DH5(21),
    BQR_PACKET_TYPE_AUX1(22),
    BQR_PACKET_TYPE_2DH1(23),
    BQR_PACKET_TYPE_2DH3(24),
    BQR_PACKET_TYPE_2DH5(25),
    BQR_PACKET_TYPE_3DH1(26),
    BQR_PACKET_TYPE_3DH3(27),
    BQR_PACKET_TYPE_3DH5(28);
    
    public static final int BQR_PACKET_TYPE_2DH1_VALUE = 23;
    public static final int BQR_PACKET_TYPE_2DH3_VALUE = 24;
    public static final int BQR_PACKET_TYPE_2DH5_VALUE = 25;
    public static final int BQR_PACKET_TYPE_2EV3_VALUE = 12;
    public static final int BQR_PACKET_TYPE_2EV5_VALUE = 13;
    public static final int BQR_PACKET_TYPE_3DH1_VALUE = 26;
    public static final int BQR_PACKET_TYPE_3DH3_VALUE = 27;
    public static final int BQR_PACKET_TYPE_3DH5_VALUE = 28;
    public static final int BQR_PACKET_TYPE_3EV3_VALUE = 14;
    public static final int BQR_PACKET_TYPE_3EV5_VALUE = 15;
    public static final int BQR_PACKET_TYPE_AUX1_VALUE = 22;
    public static final int BQR_PACKET_TYPE_DH1_VALUE = 17;
    public static final int BQR_PACKET_TYPE_DH3_VALUE = 19;
    public static final int BQR_PACKET_TYPE_DH5_VALUE = 21;
    public static final int BQR_PACKET_TYPE_DM1_VALUE = 16;
    public static final int BQR_PACKET_TYPE_DM3_VALUE = 18;
    public static final int BQR_PACKET_TYPE_DM5_VALUE = 20;
    public static final int BQR_PACKET_TYPE_DV_VALUE = 8;
    public static final int BQR_PACKET_TYPE_EV3_VALUE = 9;
    public static final int BQR_PACKET_TYPE_EV4_VALUE = 10;
    public static final int BQR_PACKET_TYPE_EV5_VALUE = 11;
    public static final int BQR_PACKET_TYPE_FHS_VALUE = 4;
    public static final int BQR_PACKET_TYPE_HV1_VALUE = 5;
    public static final int BQR_PACKET_TYPE_HV2_VALUE = 6;
    public static final int BQR_PACKET_TYPE_HV3_VALUE = 7;
    public static final int BQR_PACKET_TYPE_ID_VALUE = 1;
    public static final int BQR_PACKET_TYPE_NULL_VALUE = 2;
    public static final int BQR_PACKET_TYPE_POLL_VALUE = 3;
    public static final int BQR_PACKET_TYPE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<BqrPacketTypeEnum> internalValueMap = new Internal.EnumLiteMap<BqrPacketTypeEnum>() {
        /* class android.bluetooth.hci.BqrPacketTypeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BqrPacketTypeEnum findValueByNumber(int number) {
            return BqrPacketTypeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BqrPacketTypeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BqrPacketTypeEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return BQR_PACKET_TYPE_UNKNOWN;
            case 1:
                return BQR_PACKET_TYPE_ID;
            case 2:
                return BQR_PACKET_TYPE_NULL;
            case 3:
                return BQR_PACKET_TYPE_POLL;
            case 4:
                return BQR_PACKET_TYPE_FHS;
            case 5:
                return BQR_PACKET_TYPE_HV1;
            case 6:
                return BQR_PACKET_TYPE_HV2;
            case 7:
                return BQR_PACKET_TYPE_HV3;
            case 8:
                return BQR_PACKET_TYPE_DV;
            case 9:
                return BQR_PACKET_TYPE_EV3;
            case 10:
                return BQR_PACKET_TYPE_EV4;
            case 11:
                return BQR_PACKET_TYPE_EV5;
            case 12:
                return BQR_PACKET_TYPE_2EV3;
            case 13:
                return BQR_PACKET_TYPE_2EV5;
            case 14:
                return BQR_PACKET_TYPE_3EV3;
            case 15:
                return BQR_PACKET_TYPE_3EV5;
            case 16:
                return BQR_PACKET_TYPE_DM1;
            case 17:
                return BQR_PACKET_TYPE_DH1;
            case 18:
                return BQR_PACKET_TYPE_DM3;
            case 19:
                return BQR_PACKET_TYPE_DH3;
            case 20:
                return BQR_PACKET_TYPE_DM5;
            case 21:
                return BQR_PACKET_TYPE_DH5;
            case 22:
                return BQR_PACKET_TYPE_AUX1;
            case 23:
                return BQR_PACKET_TYPE_2DH1;
            case 24:
                return BQR_PACKET_TYPE_2DH3;
            case 25:
                return BQR_PACKET_TYPE_2DH5;
            case 26:
                return BQR_PACKET_TYPE_3DH1;
            case 27:
                return BQR_PACKET_TYPE_3DH3;
            case 28:
                return BQR_PACKET_TYPE_3DH5;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<BqrPacketTypeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BqrPacketTypeEnum(int value2) {
        this.value = value2;
    }
}
