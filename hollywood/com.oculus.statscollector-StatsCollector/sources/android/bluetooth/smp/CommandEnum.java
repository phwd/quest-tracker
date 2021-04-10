package android.bluetooth.smp;

import com.google.protobuf.Internal;

public enum CommandEnum implements Internal.EnumLite {
    CMD_UNKNOWN(0),
    CMD_PAIRING_REQUEST(1),
    CMD_PAIRING_RESPONSE(2),
    CMD_PAIRING_CONFIRM(3),
    CMD_PAIRING_RANDOM(4),
    CMD_PAIRING_FAILED(5),
    CMD_ENCRYPTION_INFON(6),
    CMD_MASTER_IDENTIFICATION(7),
    CMD_IDENTITY_INFO(8),
    CMD_IDENTITY_ADDR_INFO(9),
    CMD_SIGNING_INFO(10),
    CMD_SECURITY_REQUEST(11),
    CMD_PAIRING_PUBLIC_KEY(12),
    CMD_PAIRING_DHKEY_CHECK(13),
    CMD_PAIRING_KEYPRESS_INFO(14);
    
    public static final int CMD_ENCRYPTION_INFON_VALUE = 6;
    public static final int CMD_IDENTITY_ADDR_INFO_VALUE = 9;
    public static final int CMD_IDENTITY_INFO_VALUE = 8;
    public static final int CMD_MASTER_IDENTIFICATION_VALUE = 7;
    public static final int CMD_PAIRING_CONFIRM_VALUE = 3;
    public static final int CMD_PAIRING_DHKEY_CHECK_VALUE = 13;
    public static final int CMD_PAIRING_FAILED_VALUE = 5;
    public static final int CMD_PAIRING_KEYPRESS_INFO_VALUE = 14;
    public static final int CMD_PAIRING_PUBLIC_KEY_VALUE = 12;
    public static final int CMD_PAIRING_RANDOM_VALUE = 4;
    public static final int CMD_PAIRING_REQUEST_VALUE = 1;
    public static final int CMD_PAIRING_RESPONSE_VALUE = 2;
    public static final int CMD_SECURITY_REQUEST_VALUE = 11;
    public static final int CMD_SIGNING_INFO_VALUE = 10;
    public static final int CMD_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<CommandEnum> internalValueMap = new Internal.EnumLiteMap<CommandEnum>() {
        /* class android.bluetooth.smp.CommandEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public CommandEnum findValueByNumber(int number) {
            return CommandEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static CommandEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static CommandEnum forNumber(int value2) {
        switch (value2) {
            case 0:
                return CMD_UNKNOWN;
            case 1:
                return CMD_PAIRING_REQUEST;
            case 2:
                return CMD_PAIRING_RESPONSE;
            case 3:
                return CMD_PAIRING_CONFIRM;
            case 4:
                return CMD_PAIRING_RANDOM;
            case 5:
                return CMD_PAIRING_FAILED;
            case 6:
                return CMD_ENCRYPTION_INFON;
            case 7:
                return CMD_MASTER_IDENTIFICATION;
            case 8:
                return CMD_IDENTITY_INFO;
            case 9:
                return CMD_IDENTITY_ADDR_INFO;
            case 10:
                return CMD_SIGNING_INFO;
            case 11:
                return CMD_SECURITY_REQUEST;
            case 12:
                return CMD_PAIRING_PUBLIC_KEY;
            case 13:
                return CMD_PAIRING_DHKEY_CHECK;
            case 14:
                return CMD_PAIRING_KEYPRESS_INFO;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<CommandEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private CommandEnum(int value2) {
        this.value = value2;
    }
}
