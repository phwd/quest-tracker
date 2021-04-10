package android.bluetooth.hci;

import com.google.protobuf.Internal;

public enum BleMetaEventEnum implements Internal.EnumLite {
    BLE_EVT_UNKNOWN(4095),
    BLE_EVT_CONN_COMPLETE_EVT(1),
    BLE_EVT_ADV_PKT_RPT_EVT(2),
    BLE_EVT_LL_CONN_PARAM_UPD_EVT(3),
    BLE_EVT_READ_REMOTE_FEAT_CMPL_EVT(4),
    BLE_EVT_LTK_REQ_EVT(5),
    BLE_EVT_RC_PARAM_REQ_EVT(6),
    BLE_EVT_DATA_LENGTH_CHANGE_EVT(7),
    BLE_EVT_READ_LOCAL_P256_PUB_KEY(8),
    BLE_EVT_GEN_DHKEY_CMPL(9),
    BLE_EVT_ENHANCED_CONN_COMPLETE_EVT(10),
    BLE_EVT_DIRECT_ADV_EVT(11),
    BLE_EVT_PHY_UPDATE_COMPLETE_EVT(12),
    BLE_EVT_EXTENDED_ADVERTISING_REPORT_EVT(13),
    BLE_EVT_PERIODIC_ADV_SYNC_EST_EVT(14),
    BLE_EVT_PERIODIC_ADV_REPORT_EVT(15),
    BLE_EVT_PERIODIC_ADV_SYNC_LOST_EVT(16),
    BLE_EVT_SCAN_TIMEOUT_EVT(17),
    BLE_EVT_ADVERTISING_SET_TERMINATED_EVT(18),
    BLE_EVT_SCAN_REQ_RX_EVT(19),
    BLE_EVT_CHNL_SELECTION_ALGORITHM(20);
    
    public static final int BLE_EVT_ADVERTISING_SET_TERMINATED_EVT_VALUE = 18;
    public static final int BLE_EVT_ADV_PKT_RPT_EVT_VALUE = 2;
    public static final int BLE_EVT_CHNL_SELECTION_ALGORITHM_VALUE = 20;
    public static final int BLE_EVT_CONN_COMPLETE_EVT_VALUE = 1;
    public static final int BLE_EVT_DATA_LENGTH_CHANGE_EVT_VALUE = 7;
    public static final int BLE_EVT_DIRECT_ADV_EVT_VALUE = 11;
    public static final int BLE_EVT_ENHANCED_CONN_COMPLETE_EVT_VALUE = 10;
    public static final int BLE_EVT_EXTENDED_ADVERTISING_REPORT_EVT_VALUE = 13;
    public static final int BLE_EVT_GEN_DHKEY_CMPL_VALUE = 9;
    public static final int BLE_EVT_LL_CONN_PARAM_UPD_EVT_VALUE = 3;
    public static final int BLE_EVT_LTK_REQ_EVT_VALUE = 5;
    public static final int BLE_EVT_PERIODIC_ADV_REPORT_EVT_VALUE = 15;
    public static final int BLE_EVT_PERIODIC_ADV_SYNC_EST_EVT_VALUE = 14;
    public static final int BLE_EVT_PERIODIC_ADV_SYNC_LOST_EVT_VALUE = 16;
    public static final int BLE_EVT_PHY_UPDATE_COMPLETE_EVT_VALUE = 12;
    public static final int BLE_EVT_RC_PARAM_REQ_EVT_VALUE = 6;
    public static final int BLE_EVT_READ_LOCAL_P256_PUB_KEY_VALUE = 8;
    public static final int BLE_EVT_READ_REMOTE_FEAT_CMPL_EVT_VALUE = 4;
    public static final int BLE_EVT_SCAN_REQ_RX_EVT_VALUE = 19;
    public static final int BLE_EVT_SCAN_TIMEOUT_EVT_VALUE = 17;
    public static final int BLE_EVT_UNKNOWN_VALUE = 4095;
    private static final Internal.EnumLiteMap<BleMetaEventEnum> internalValueMap = new Internal.EnumLiteMap<BleMetaEventEnum>() {
        /* class android.bluetooth.hci.BleMetaEventEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public BleMetaEventEnum findValueByNumber(int number) {
            return BleMetaEventEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BleMetaEventEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static BleMetaEventEnum forNumber(int value2) {
        if (value2 == 4095) {
            return BLE_EVT_UNKNOWN;
        }
        switch (value2) {
            case 1:
                return BLE_EVT_CONN_COMPLETE_EVT;
            case 2:
                return BLE_EVT_ADV_PKT_RPT_EVT;
            case 3:
                return BLE_EVT_LL_CONN_PARAM_UPD_EVT;
            case 4:
                return BLE_EVT_READ_REMOTE_FEAT_CMPL_EVT;
            case 5:
                return BLE_EVT_LTK_REQ_EVT;
            case 6:
                return BLE_EVT_RC_PARAM_REQ_EVT;
            case 7:
                return BLE_EVT_DATA_LENGTH_CHANGE_EVT;
            case 8:
                return BLE_EVT_READ_LOCAL_P256_PUB_KEY;
            case 9:
                return BLE_EVT_GEN_DHKEY_CMPL;
            case 10:
                return BLE_EVT_ENHANCED_CONN_COMPLETE_EVT;
            case 11:
                return BLE_EVT_DIRECT_ADV_EVT;
            case 12:
                return BLE_EVT_PHY_UPDATE_COMPLETE_EVT;
            case 13:
                return BLE_EVT_EXTENDED_ADVERTISING_REPORT_EVT;
            case 14:
                return BLE_EVT_PERIODIC_ADV_SYNC_EST_EVT;
            case 15:
                return BLE_EVT_PERIODIC_ADV_REPORT_EVT;
            case 16:
                return BLE_EVT_PERIODIC_ADV_SYNC_LOST_EVT;
            case 17:
                return BLE_EVT_SCAN_TIMEOUT_EVT;
            case 18:
                return BLE_EVT_ADVERTISING_SET_TERMINATED_EVT;
            case 19:
                return BLE_EVT_SCAN_REQ_RX_EVT;
            case 20:
                return BLE_EVT_CHNL_SELECTION_ALGORITHM;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<BleMetaEventEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private BleMetaEventEnum(int value2) {
        this.value = value2;
    }
}
