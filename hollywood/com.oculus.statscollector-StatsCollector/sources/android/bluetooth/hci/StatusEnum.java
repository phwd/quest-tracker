package android.bluetooth.hci;

import com.google.protobuf.Internal;

public enum StatusEnum implements Internal.EnumLite {
    STATUS_UNKNOWN(4095),
    STATUS_SUCCESS(0),
    STATUS_ILLEGAL_COMMAND(1),
    STATUS_NO_CONNECTION(2),
    STATUS_HW_FAILURE(3),
    STATUS_PAGE_TIMEOUT(4),
    STATUS_AUTH_FAILURE(5),
    STATUS_KEY_MISSING(6),
    STATUS_MEMORY_FULL(7),
    STATUS_CONNECTION_TOUT(8),
    STATUS_MAX_NUM_OF_CONNECTIONS(9),
    STATUS_MAX_NUM_OF_SCOS(10),
    STATUS_CONNECTION_EXISTS(11),
    STATUS_COMMAND_DISALLOWED(12),
    STATUS_HOST_REJECT_RESOURCES(13),
    STATUS_HOST_REJECT_SECURITY(14),
    STATUS_HOST_REJECT_DEVICE(15),
    STATUS_HOST_TIMEOUT(16),
    STATUS_UNSUPPORTED_VALUE(17),
    STATUS_ILLEGAL_PARAMETER_FMT(18),
    STATUS_PEER_USER(19),
    STATUS_PEER_LOW_RESOURCES(20),
    STATUS_PEER_POWER_OFF(21),
    STATUS_CONN_CAUSE_LOCAL_HOST(22),
    STATUS_REPEATED_ATTEMPTS(23),
    STATUS_PAIRING_NOT_ALLOWED(24),
    STATUS_UNKNOWN_LMP_PDU(25),
    STATUS_UNSUPPORTED_REM_FEATURE(26),
    STATUS_SCO_OFFSET_REJECTED(27),
    STATUS_SCO_INTERVAL_REJECTED(28),
    STATUS_SCO_AIR_MODE(29),
    STATUS_INVALID_LMP_PARAM(30),
    STATUS_UNSPECIFIED(31),
    STATUS_UNSUPPORTED_LMP_FEATURE(32),
    STATUS_ROLE_CHANGE_NOT_ALLOWED(33),
    STATUS_LMP_RESPONSE_TIMEOUT(34),
    STATUS_LMP_STATUS_TRANS_COLLISION(35),
    STATUS_LMP_PDU_NOT_ALLOWED(36),
    STATUS_ENCRY_MODE_NOT_ACCEPTABLE(37),
    STATUS_UNIT_KEY_USED(38),
    STATUS_QOS_NOT_SUPPORTED(39),
    STATUS_INSTANT_PASSED(40),
    STATUS_PAIRING_WITH_UNIT_KEY_NOT_SUPPORTED(41),
    STATUS_DIFF_TRANSACTION_COLLISION(42),
    STATUS_UNDEFINED_0x2B(43),
    STATUS_QOS_UNACCEPTABLE_PARAM(44),
    STATUS_QOS_REJECTED(45),
    STATUS_CHAN_CLASSIF_NOT_SUPPORTED(46),
    STATUS_INSUFFCIENT_SECURITY(47),
    STATUS_PARAM_OUT_OF_RANGE(48),
    STATUS_UNDEFINED_0x31(49),
    STATUS_ROLE_SWITCH_PENDING(50),
    STATUS_UNDEFINED_0x33(51),
    STATUS_RESERVED_SLOT_VIOLATION(52),
    STATUS_ROLE_SWITCH_FAILED(53),
    STATUS_INQ_RSP_DATA_TOO_LARGE(54),
    STATUS_SIMPLE_PAIRING_NOT_SUPPORTED(55),
    STATUS_HOST_BUSY_PAIRING(56),
    STATUS_REJ_NO_SUITABLE_CHANNEL(57),
    STATUS_CONTROLLER_BUSY(58),
    STATUS_UNACCEPT_CONN_INTERVAL(59),
    STATUS_ADVERTISING_TIMEOUT(60),
    STATUS_CONN_TOUT_DUE_TO_MIC_FAILURE(61),
    STATUS_CONN_FAILED_ESTABLISHMENT(62),
    STATUS_MAC_CONNECTION_FAILED(63),
    STATUS_LT_ADDR_ALREADY_IN_USE(64),
    STATUS_LT_ADDR_NOT_ALLOCATED(65),
    STATUS_CLB_NOT_ENABLED(66),
    STATUS_CLB_DATA_TOO_BIG(67),
    STATUS_OPERATION_CANCELED_BY_HOST(68);
    
    public static final int STATUS_ADVERTISING_TIMEOUT_VALUE = 60;
    public static final int STATUS_AUTH_FAILURE_VALUE = 5;
    public static final int STATUS_CHAN_CLASSIF_NOT_SUPPORTED_VALUE = 46;
    public static final int STATUS_CLB_DATA_TOO_BIG_VALUE = 67;
    public static final int STATUS_CLB_NOT_ENABLED_VALUE = 66;
    public static final int STATUS_COMMAND_DISALLOWED_VALUE = 12;
    public static final int STATUS_CONNECTION_EXISTS_VALUE = 11;
    public static final int STATUS_CONNECTION_TOUT_VALUE = 8;
    public static final int STATUS_CONN_CAUSE_LOCAL_HOST_VALUE = 22;
    public static final int STATUS_CONN_FAILED_ESTABLISHMENT_VALUE = 62;
    public static final int STATUS_CONN_TOUT_DUE_TO_MIC_FAILURE_VALUE = 61;
    public static final int STATUS_CONTROLLER_BUSY_VALUE = 58;
    public static final int STATUS_DIFF_TRANSACTION_COLLISION_VALUE = 42;
    public static final int STATUS_ENCRY_MODE_NOT_ACCEPTABLE_VALUE = 37;
    public static final int STATUS_HOST_BUSY_PAIRING_VALUE = 56;
    public static final int STATUS_HOST_REJECT_DEVICE_VALUE = 15;
    public static final int STATUS_HOST_REJECT_RESOURCES_VALUE = 13;
    public static final int STATUS_HOST_REJECT_SECURITY_VALUE = 14;
    public static final int STATUS_HOST_TIMEOUT_VALUE = 16;
    public static final int STATUS_HW_FAILURE_VALUE = 3;
    public static final int STATUS_ILLEGAL_COMMAND_VALUE = 1;
    public static final int STATUS_ILLEGAL_PARAMETER_FMT_VALUE = 18;
    public static final int STATUS_INQ_RSP_DATA_TOO_LARGE_VALUE = 54;
    public static final int STATUS_INSTANT_PASSED_VALUE = 40;
    public static final int STATUS_INSUFFCIENT_SECURITY_VALUE = 47;
    public static final int STATUS_INVALID_LMP_PARAM_VALUE = 30;
    public static final int STATUS_KEY_MISSING_VALUE = 6;
    public static final int STATUS_LMP_PDU_NOT_ALLOWED_VALUE = 36;
    public static final int STATUS_LMP_RESPONSE_TIMEOUT_VALUE = 34;
    public static final int STATUS_LMP_STATUS_TRANS_COLLISION_VALUE = 35;
    public static final int STATUS_LT_ADDR_ALREADY_IN_USE_VALUE = 64;
    public static final int STATUS_LT_ADDR_NOT_ALLOCATED_VALUE = 65;
    public static final int STATUS_MAC_CONNECTION_FAILED_VALUE = 63;
    public static final int STATUS_MAX_NUM_OF_CONNECTIONS_VALUE = 9;
    public static final int STATUS_MAX_NUM_OF_SCOS_VALUE = 10;
    public static final int STATUS_MEMORY_FULL_VALUE = 7;
    public static final int STATUS_NO_CONNECTION_VALUE = 2;
    public static final int STATUS_OPERATION_CANCELED_BY_HOST_VALUE = 68;
    public static final int STATUS_PAGE_TIMEOUT_VALUE = 4;
    public static final int STATUS_PAIRING_NOT_ALLOWED_VALUE = 24;
    public static final int STATUS_PAIRING_WITH_UNIT_KEY_NOT_SUPPORTED_VALUE = 41;
    public static final int STATUS_PARAM_OUT_OF_RANGE_VALUE = 48;
    public static final int STATUS_PEER_LOW_RESOURCES_VALUE = 20;
    public static final int STATUS_PEER_POWER_OFF_VALUE = 21;
    public static final int STATUS_PEER_USER_VALUE = 19;
    public static final int STATUS_QOS_NOT_SUPPORTED_VALUE = 39;
    public static final int STATUS_QOS_REJECTED_VALUE = 45;
    public static final int STATUS_QOS_UNACCEPTABLE_PARAM_VALUE = 44;
    public static final int STATUS_REJ_NO_SUITABLE_CHANNEL_VALUE = 57;
    public static final int STATUS_REPEATED_ATTEMPTS_VALUE = 23;
    public static final int STATUS_RESERVED_SLOT_VIOLATION_VALUE = 52;
    public static final int STATUS_ROLE_CHANGE_NOT_ALLOWED_VALUE = 33;
    public static final int STATUS_ROLE_SWITCH_FAILED_VALUE = 53;
    public static final int STATUS_ROLE_SWITCH_PENDING_VALUE = 50;
    public static final int STATUS_SCO_AIR_MODE_VALUE = 29;
    public static final int STATUS_SCO_INTERVAL_REJECTED_VALUE = 28;
    public static final int STATUS_SCO_OFFSET_REJECTED_VALUE = 27;
    public static final int STATUS_SIMPLE_PAIRING_NOT_SUPPORTED_VALUE = 55;
    public static final int STATUS_SUCCESS_VALUE = 0;
    public static final int STATUS_UNACCEPT_CONN_INTERVAL_VALUE = 59;
    public static final int STATUS_UNDEFINED_0x2B_VALUE = 43;
    public static final int STATUS_UNDEFINED_0x31_VALUE = 49;
    public static final int STATUS_UNDEFINED_0x33_VALUE = 51;
    public static final int STATUS_UNIT_KEY_USED_VALUE = 38;
    public static final int STATUS_UNKNOWN_LMP_PDU_VALUE = 25;
    public static final int STATUS_UNKNOWN_VALUE = 4095;
    public static final int STATUS_UNSPECIFIED_VALUE = 31;
    public static final int STATUS_UNSUPPORTED_LMP_FEATURE_VALUE = 32;
    public static final int STATUS_UNSUPPORTED_REM_FEATURE_VALUE = 26;
    public static final int STATUS_UNSUPPORTED_VALUE_VALUE = 17;
    private static final Internal.EnumLiteMap<StatusEnum> internalValueMap = new Internal.EnumLiteMap<StatusEnum>() {
        /* class android.bluetooth.hci.StatusEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public StatusEnum findValueByNumber(int number) {
            return StatusEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static StatusEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static StatusEnum forNumber(int value2) {
        if (value2 == 4095) {
            return STATUS_UNKNOWN;
        }
        switch (value2) {
            case 0:
                return STATUS_SUCCESS;
            case 1:
                return STATUS_ILLEGAL_COMMAND;
            case 2:
                return STATUS_NO_CONNECTION;
            case 3:
                return STATUS_HW_FAILURE;
            case 4:
                return STATUS_PAGE_TIMEOUT;
            case 5:
                return STATUS_AUTH_FAILURE;
            case 6:
                return STATUS_KEY_MISSING;
            case 7:
                return STATUS_MEMORY_FULL;
            case 8:
                return STATUS_CONNECTION_TOUT;
            case 9:
                return STATUS_MAX_NUM_OF_CONNECTIONS;
            case 10:
                return STATUS_MAX_NUM_OF_SCOS;
            case 11:
                return STATUS_CONNECTION_EXISTS;
            case 12:
                return STATUS_COMMAND_DISALLOWED;
            case 13:
                return STATUS_HOST_REJECT_RESOURCES;
            case 14:
                return STATUS_HOST_REJECT_SECURITY;
            case 15:
                return STATUS_HOST_REJECT_DEVICE;
            case 16:
                return STATUS_HOST_TIMEOUT;
            case 17:
                return STATUS_UNSUPPORTED_VALUE;
            case 18:
                return STATUS_ILLEGAL_PARAMETER_FMT;
            case 19:
                return STATUS_PEER_USER;
            case 20:
                return STATUS_PEER_LOW_RESOURCES;
            case 21:
                return STATUS_PEER_POWER_OFF;
            case 22:
                return STATUS_CONN_CAUSE_LOCAL_HOST;
            case 23:
                return STATUS_REPEATED_ATTEMPTS;
            case 24:
                return STATUS_PAIRING_NOT_ALLOWED;
            case 25:
                return STATUS_UNKNOWN_LMP_PDU;
            case 26:
                return STATUS_UNSUPPORTED_REM_FEATURE;
            case 27:
                return STATUS_SCO_OFFSET_REJECTED;
            case 28:
                return STATUS_SCO_INTERVAL_REJECTED;
            case 29:
                return STATUS_SCO_AIR_MODE;
            case 30:
                return STATUS_INVALID_LMP_PARAM;
            case 31:
                return STATUS_UNSPECIFIED;
            case 32:
                return STATUS_UNSUPPORTED_LMP_FEATURE;
            case 33:
                return STATUS_ROLE_CHANGE_NOT_ALLOWED;
            case 34:
                return STATUS_LMP_RESPONSE_TIMEOUT;
            case 35:
                return STATUS_LMP_STATUS_TRANS_COLLISION;
            case 36:
                return STATUS_LMP_PDU_NOT_ALLOWED;
            case 37:
                return STATUS_ENCRY_MODE_NOT_ACCEPTABLE;
            case 38:
                return STATUS_UNIT_KEY_USED;
            case 39:
                return STATUS_QOS_NOT_SUPPORTED;
            case 40:
                return STATUS_INSTANT_PASSED;
            case 41:
                return STATUS_PAIRING_WITH_UNIT_KEY_NOT_SUPPORTED;
            case 42:
                return STATUS_DIFF_TRANSACTION_COLLISION;
            case 43:
                return STATUS_UNDEFINED_0x2B;
            case 44:
                return STATUS_QOS_UNACCEPTABLE_PARAM;
            case 45:
                return STATUS_QOS_REJECTED;
            case 46:
                return STATUS_CHAN_CLASSIF_NOT_SUPPORTED;
            case 47:
                return STATUS_INSUFFCIENT_SECURITY;
            case 48:
                return STATUS_PARAM_OUT_OF_RANGE;
            case 49:
                return STATUS_UNDEFINED_0x31;
            case 50:
                return STATUS_ROLE_SWITCH_PENDING;
            case 51:
                return STATUS_UNDEFINED_0x33;
            case 52:
                return STATUS_RESERVED_SLOT_VIOLATION;
            case 53:
                return STATUS_ROLE_SWITCH_FAILED;
            case 54:
                return STATUS_INQ_RSP_DATA_TOO_LARGE;
            case 55:
                return STATUS_SIMPLE_PAIRING_NOT_SUPPORTED;
            case 56:
                return STATUS_HOST_BUSY_PAIRING;
            case 57:
                return STATUS_REJ_NO_SUITABLE_CHANNEL;
            case 58:
                return STATUS_CONTROLLER_BUSY;
            case 59:
                return STATUS_UNACCEPT_CONN_INTERVAL;
            case 60:
                return STATUS_ADVERTISING_TIMEOUT;
            case 61:
                return STATUS_CONN_TOUT_DUE_TO_MIC_FAILURE;
            case 62:
                return STATUS_CONN_FAILED_ESTABLISHMENT;
            case 63:
                return STATUS_MAC_CONNECTION_FAILED;
            case 64:
                return STATUS_LT_ADDR_ALREADY_IN_USE;
            case 65:
                return STATUS_LT_ADDR_NOT_ALLOCATED;
            case 66:
                return STATUS_CLB_NOT_ENABLED;
            case 67:
                return STATUS_CLB_DATA_TOO_BIG;
            case 68:
                return STATUS_OPERATION_CANCELED_BY_HOST;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<StatusEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private StatusEnum(int value2) {
        this.value = value2;
    }
}
