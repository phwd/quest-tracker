package android.bluetooth.hci;

import com.google.protobuf.Internal;

public enum EventEnum implements Internal.EnumLite {
    EVT_UNKNOWN(4095),
    EVT_INQUIRY_COMP(1),
    EVT_INQUIRY_RESULT(2),
    EVT_CONNECTION_COMP(3),
    EVT_CONNECTION_REQUEST(4),
    EVT_DISCONNECTION_COMP(5),
    EVT_AUTHENTICATION_COMP(6),
    EVT_RMT_NAME_REQUEST_COMP(7),
    EVT_ENCRYPTION_CHANGE(8),
    EVT_CHANGE_CONN_LINK_KEY(9),
    EVT_MASTER_LINK_KEY_COMP(10),
    EVT_READ_RMT_FEATURES_COMP(11),
    EVT_READ_RMT_VERSION_COMP(12),
    EVT_QOS_SETUP_COMP(13),
    EVT_COMMAND_COMPLETE(14),
    EVT_COMMAND_STATUS(15),
    EVT_HARDWARE_ERROR(16),
    EVT_FLUSH_OCCURRED(17),
    EVT_ROLE_CHANGE(18),
    EVT_NUM_COMPL_DATA_PKTS(19),
    EVT_MODE_CHANGE(20),
    EVT_RETURN_LINK_KEYS(21),
    EVT_PIN_CODE_REQUEST(22),
    EVT_LINK_KEY_REQUEST(23),
    EVT_LINK_KEY_NOTIFICATION(24),
    EVT_LOOPBACK_COMMAND(25),
    EVT_DATA_BUF_OVERFLOW(26),
    EVT_MAX_SLOTS_CHANGED(27),
    EVT_READ_CLOCK_OFF_COMP(28),
    EVT_CONN_PKT_TYPE_CHANGE(29),
    EVT_QOS_VIOLATION(30),
    EVT_PAGE_SCAN_MODE_CHANGE(31),
    EVT_PAGE_SCAN_REP_MODE_CHNG(32),
    EVT_FLOW_SPECIFICATION_COMP(33),
    EVT_INQUIRY_RSSI_RESULT(34),
    EVT_READ_RMT_EXT_FEATURES_COMP(35),
    EVT_ESCO_CONNECTION_COMP(44),
    EVT_ESCO_CONNECTION_CHANGED(45),
    EVT_SNIFF_SUB_RATE(46),
    EVT_EXTENDED_INQUIRY_RESULT(47),
    EVT_ENCRYPTION_KEY_REFRESH_COMP(48),
    EVT_IO_CAPABILITY_REQUEST(49),
    EVT_IO_CAPABILITY_RESPONSE(50),
    EVT_USER_CONFIRMATION_REQUEST(51),
    EVT_USER_PASSKEY_REQUEST(52),
    EVT_REMOTE_OOB_DATA_REQUEST(53),
    EVT_SIMPLE_PAIRING_COMPLETE(54),
    EVT_LINK_SUPER_TOUT_CHANGED(56),
    EVT_ENHANCED_FLUSH_COMPLETE(57),
    EVT_USER_PASSKEY_NOTIFY(59),
    EVT_KEYPRESS_NOTIFY(60),
    EVT_RMT_HOST_SUP_FEAT_NOTIFY(61),
    EVT_BLE_META(62),
    EVT_PHYSICAL_LINK_COMP(64),
    EVT_CHANNEL_SELECTED(65),
    EVT_DISC_PHYSICAL_LINK_COMP(66),
    EVT_PHY_LINK_LOSS_EARLY_WARNING(67),
    EVT_PHY_LINK_RECOVERY(68),
    EVT_LOGICAL_LINK_COMP(69),
    EVT_DISC_LOGICAL_LINK_COMP(70),
    EVT_FLOW_SPEC_MODIFY_COMP(71),
    EVT_NUM_COMPL_DATA_BLOCKS(72),
    EVT_AMP_TEST_START(73),
    EVT_AMP_TEST_END(74),
    EVT_AMP_RECEIVER_RPT(75),
    EVT_SHORT_RANGE_MODE_COMPLETE(76),
    EVT_AMP_STATUS_CHANGE(77),
    EVT_SET_TRIGGERED_CLOCK_CAPTURE(78),
    EVT_SYNC_TRAIN_CMPL(79),
    EVT_SYNC_TRAIN_RCVD(80),
    EVT_CONNLESS_SLAVE_BROADCAST_RCVD(81),
    EVT_CONNLESS_SLAVE_BROADCAST_TIMEOUT(82),
    EVT_TRUNCATED_PAGE_CMPL(83),
    EVT_SLAVE_PAGE_RES_TIMEOUT(84),
    EVT_CONNLESS_SLAVE_BROADCAST_CHNL_MAP_CHANGE(85),
    EVT_INQUIRY_RES_NOTIFICATION(86),
    EVT_AUTHED_PAYLOAD_TIMEOUT(87),
    EVT_SAM_STATUS_CHANGE(88);
    
    public static final int EVT_AMP_RECEIVER_RPT_VALUE = 75;
    public static final int EVT_AMP_STATUS_CHANGE_VALUE = 77;
    public static final int EVT_AMP_TEST_END_VALUE = 74;
    public static final int EVT_AMP_TEST_START_VALUE = 73;
    public static final int EVT_AUTHED_PAYLOAD_TIMEOUT_VALUE = 87;
    public static final int EVT_AUTHENTICATION_COMP_VALUE = 6;
    public static final int EVT_BLE_META_VALUE = 62;
    public static final int EVT_CHANGE_CONN_LINK_KEY_VALUE = 9;
    public static final int EVT_CHANNEL_SELECTED_VALUE = 65;
    public static final int EVT_COMMAND_COMPLETE_VALUE = 14;
    public static final int EVT_COMMAND_STATUS_VALUE = 15;
    public static final int EVT_CONNECTION_COMP_VALUE = 3;
    public static final int EVT_CONNECTION_REQUEST_VALUE = 4;
    public static final int EVT_CONNLESS_SLAVE_BROADCAST_CHNL_MAP_CHANGE_VALUE = 85;
    public static final int EVT_CONNLESS_SLAVE_BROADCAST_RCVD_VALUE = 81;
    public static final int EVT_CONNLESS_SLAVE_BROADCAST_TIMEOUT_VALUE = 82;
    public static final int EVT_CONN_PKT_TYPE_CHANGE_VALUE = 29;
    public static final int EVT_DATA_BUF_OVERFLOW_VALUE = 26;
    public static final int EVT_DISCONNECTION_COMP_VALUE = 5;
    public static final int EVT_DISC_LOGICAL_LINK_COMP_VALUE = 70;
    public static final int EVT_DISC_PHYSICAL_LINK_COMP_VALUE = 66;
    public static final int EVT_ENCRYPTION_CHANGE_VALUE = 8;
    public static final int EVT_ENCRYPTION_KEY_REFRESH_COMP_VALUE = 48;
    public static final int EVT_ENHANCED_FLUSH_COMPLETE_VALUE = 57;
    public static final int EVT_ESCO_CONNECTION_CHANGED_VALUE = 45;
    public static final int EVT_ESCO_CONNECTION_COMP_VALUE = 44;
    public static final int EVT_EXTENDED_INQUIRY_RESULT_VALUE = 47;
    public static final int EVT_FLOW_SPECIFICATION_COMP_VALUE = 33;
    public static final int EVT_FLOW_SPEC_MODIFY_COMP_VALUE = 71;
    public static final int EVT_FLUSH_OCCURRED_VALUE = 17;
    public static final int EVT_HARDWARE_ERROR_VALUE = 16;
    public static final int EVT_INQUIRY_COMP_VALUE = 1;
    public static final int EVT_INQUIRY_RESULT_VALUE = 2;
    public static final int EVT_INQUIRY_RES_NOTIFICATION_VALUE = 86;
    public static final int EVT_INQUIRY_RSSI_RESULT_VALUE = 34;
    public static final int EVT_IO_CAPABILITY_REQUEST_VALUE = 49;
    public static final int EVT_IO_CAPABILITY_RESPONSE_VALUE = 50;
    public static final int EVT_KEYPRESS_NOTIFY_VALUE = 60;
    public static final int EVT_LINK_KEY_NOTIFICATION_VALUE = 24;
    public static final int EVT_LINK_KEY_REQUEST_VALUE = 23;
    public static final int EVT_LINK_SUPER_TOUT_CHANGED_VALUE = 56;
    public static final int EVT_LOGICAL_LINK_COMP_VALUE = 69;
    public static final int EVT_LOOPBACK_COMMAND_VALUE = 25;
    public static final int EVT_MASTER_LINK_KEY_COMP_VALUE = 10;
    public static final int EVT_MAX_SLOTS_CHANGED_VALUE = 27;
    public static final int EVT_MODE_CHANGE_VALUE = 20;
    public static final int EVT_NUM_COMPL_DATA_BLOCKS_VALUE = 72;
    public static final int EVT_NUM_COMPL_DATA_PKTS_VALUE = 19;
    public static final int EVT_PAGE_SCAN_MODE_CHANGE_VALUE = 31;
    public static final int EVT_PAGE_SCAN_REP_MODE_CHNG_VALUE = 32;
    public static final int EVT_PHYSICAL_LINK_COMP_VALUE = 64;
    public static final int EVT_PHY_LINK_LOSS_EARLY_WARNING_VALUE = 67;
    public static final int EVT_PHY_LINK_RECOVERY_VALUE = 68;
    public static final int EVT_PIN_CODE_REQUEST_VALUE = 22;
    public static final int EVT_QOS_SETUP_COMP_VALUE = 13;
    public static final int EVT_QOS_VIOLATION_VALUE = 30;
    public static final int EVT_READ_CLOCK_OFF_COMP_VALUE = 28;
    public static final int EVT_READ_RMT_EXT_FEATURES_COMP_VALUE = 35;
    public static final int EVT_READ_RMT_FEATURES_COMP_VALUE = 11;
    public static final int EVT_READ_RMT_VERSION_COMP_VALUE = 12;
    public static final int EVT_REMOTE_OOB_DATA_REQUEST_VALUE = 53;
    public static final int EVT_RETURN_LINK_KEYS_VALUE = 21;
    public static final int EVT_RMT_HOST_SUP_FEAT_NOTIFY_VALUE = 61;
    public static final int EVT_RMT_NAME_REQUEST_COMP_VALUE = 7;
    public static final int EVT_ROLE_CHANGE_VALUE = 18;
    public static final int EVT_SAM_STATUS_CHANGE_VALUE = 88;
    public static final int EVT_SET_TRIGGERED_CLOCK_CAPTURE_VALUE = 78;
    public static final int EVT_SHORT_RANGE_MODE_COMPLETE_VALUE = 76;
    public static final int EVT_SIMPLE_PAIRING_COMPLETE_VALUE = 54;
    public static final int EVT_SLAVE_PAGE_RES_TIMEOUT_VALUE = 84;
    public static final int EVT_SNIFF_SUB_RATE_VALUE = 46;
    public static final int EVT_SYNC_TRAIN_CMPL_VALUE = 79;
    public static final int EVT_SYNC_TRAIN_RCVD_VALUE = 80;
    public static final int EVT_TRUNCATED_PAGE_CMPL_VALUE = 83;
    public static final int EVT_UNKNOWN_VALUE = 4095;
    public static final int EVT_USER_CONFIRMATION_REQUEST_VALUE = 51;
    public static final int EVT_USER_PASSKEY_NOTIFY_VALUE = 59;
    public static final int EVT_USER_PASSKEY_REQUEST_VALUE = 52;
    private static final Internal.EnumLiteMap<EventEnum> internalValueMap = new Internal.EnumLiteMap<EventEnum>() {
        /* class android.bluetooth.hci.EventEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public EventEnum findValueByNumber(int number) {
            return EventEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static EventEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static EventEnum forNumber(int value2) {
        if (value2 == 56) {
            return EVT_LINK_SUPER_TOUT_CHANGED;
        }
        if (value2 == 57) {
            return EVT_ENHANCED_FLUSH_COMPLETE;
        }
        if (value2 == 4095) {
            return EVT_UNKNOWN;
        }
        switch (value2) {
            case 1:
                return EVT_INQUIRY_COMP;
            case 2:
                return EVT_INQUIRY_RESULT;
            case 3:
                return EVT_CONNECTION_COMP;
            case 4:
                return EVT_CONNECTION_REQUEST;
            case 5:
                return EVT_DISCONNECTION_COMP;
            case 6:
                return EVT_AUTHENTICATION_COMP;
            case 7:
                return EVT_RMT_NAME_REQUEST_COMP;
            case 8:
                return EVT_ENCRYPTION_CHANGE;
            case 9:
                return EVT_CHANGE_CONN_LINK_KEY;
            case 10:
                return EVT_MASTER_LINK_KEY_COMP;
            case 11:
                return EVT_READ_RMT_FEATURES_COMP;
            case 12:
                return EVT_READ_RMT_VERSION_COMP;
            case 13:
                return EVT_QOS_SETUP_COMP;
            case 14:
                return EVT_COMMAND_COMPLETE;
            case 15:
                return EVT_COMMAND_STATUS;
            case 16:
                return EVT_HARDWARE_ERROR;
            case 17:
                return EVT_FLUSH_OCCURRED;
            case 18:
                return EVT_ROLE_CHANGE;
            case 19:
                return EVT_NUM_COMPL_DATA_PKTS;
            case 20:
                return EVT_MODE_CHANGE;
            case 21:
                return EVT_RETURN_LINK_KEYS;
            case 22:
                return EVT_PIN_CODE_REQUEST;
            case 23:
                return EVT_LINK_KEY_REQUEST;
            case 24:
                return EVT_LINK_KEY_NOTIFICATION;
            case 25:
                return EVT_LOOPBACK_COMMAND;
            case 26:
                return EVT_DATA_BUF_OVERFLOW;
            case 27:
                return EVT_MAX_SLOTS_CHANGED;
            case 28:
                return EVT_READ_CLOCK_OFF_COMP;
            case 29:
                return EVT_CONN_PKT_TYPE_CHANGE;
            case 30:
                return EVT_QOS_VIOLATION;
            case 31:
                return EVT_PAGE_SCAN_MODE_CHANGE;
            case 32:
                return EVT_PAGE_SCAN_REP_MODE_CHNG;
            case 33:
                return EVT_FLOW_SPECIFICATION_COMP;
            case 34:
                return EVT_INQUIRY_RSSI_RESULT;
            case 35:
                return EVT_READ_RMT_EXT_FEATURES_COMP;
            default:
                switch (value2) {
                    case 44:
                        return EVT_ESCO_CONNECTION_COMP;
                    case 45:
                        return EVT_ESCO_CONNECTION_CHANGED;
                    case 46:
                        return EVT_SNIFF_SUB_RATE;
                    case 47:
                        return EVT_EXTENDED_INQUIRY_RESULT;
                    case 48:
                        return EVT_ENCRYPTION_KEY_REFRESH_COMP;
                    case 49:
                        return EVT_IO_CAPABILITY_REQUEST;
                    case 50:
                        return EVT_IO_CAPABILITY_RESPONSE;
                    case 51:
                        return EVT_USER_CONFIRMATION_REQUEST;
                    case 52:
                        return EVT_USER_PASSKEY_REQUEST;
                    case 53:
                        return EVT_REMOTE_OOB_DATA_REQUEST;
                    case 54:
                        return EVT_SIMPLE_PAIRING_COMPLETE;
                    default:
                        switch (value2) {
                            case 59:
                                return EVT_USER_PASSKEY_NOTIFY;
                            case 60:
                                return EVT_KEYPRESS_NOTIFY;
                            case 61:
                                return EVT_RMT_HOST_SUP_FEAT_NOTIFY;
                            case 62:
                                return EVT_BLE_META;
                            default:
                                switch (value2) {
                                    case 64:
                                        return EVT_PHYSICAL_LINK_COMP;
                                    case 65:
                                        return EVT_CHANNEL_SELECTED;
                                    case 66:
                                        return EVT_DISC_PHYSICAL_LINK_COMP;
                                    case 67:
                                        return EVT_PHY_LINK_LOSS_EARLY_WARNING;
                                    case 68:
                                        return EVT_PHY_LINK_RECOVERY;
                                    case 69:
                                        return EVT_LOGICAL_LINK_COMP;
                                    case 70:
                                        return EVT_DISC_LOGICAL_LINK_COMP;
                                    case 71:
                                        return EVT_FLOW_SPEC_MODIFY_COMP;
                                    case 72:
                                        return EVT_NUM_COMPL_DATA_BLOCKS;
                                    case 73:
                                        return EVT_AMP_TEST_START;
                                    case 74:
                                        return EVT_AMP_TEST_END;
                                    case 75:
                                        return EVT_AMP_RECEIVER_RPT;
                                    case 76:
                                        return EVT_SHORT_RANGE_MODE_COMPLETE;
                                    case 77:
                                        return EVT_AMP_STATUS_CHANGE;
                                    case 78:
                                        return EVT_SET_TRIGGERED_CLOCK_CAPTURE;
                                    case 79:
                                        return EVT_SYNC_TRAIN_CMPL;
                                    case 80:
                                        return EVT_SYNC_TRAIN_RCVD;
                                    case 81:
                                        return EVT_CONNLESS_SLAVE_BROADCAST_RCVD;
                                    case 82:
                                        return EVT_CONNLESS_SLAVE_BROADCAST_TIMEOUT;
                                    case 83:
                                        return EVT_TRUNCATED_PAGE_CMPL;
                                    case 84:
                                        return EVT_SLAVE_PAGE_RES_TIMEOUT;
                                    case 85:
                                        return EVT_CONNLESS_SLAVE_BROADCAST_CHNL_MAP_CHANGE;
                                    case 86:
                                        return EVT_INQUIRY_RES_NOTIFICATION;
                                    case 87:
                                        return EVT_AUTHED_PAYLOAD_TIMEOUT;
                                    case 88:
                                        return EVT_SAM_STATUS_CHANGE;
                                    default:
                                        return null;
                                }
                        }
                }
        }
    }

    public static Internal.EnumLiteMap<EventEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private EventEnum(int value2) {
        this.value = value2;
    }
}
