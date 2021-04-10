package android.hardware.biometrics;

import com.google.protobuf.Internal;

public enum ClientEnum implements Internal.EnumLite {
    CLIENT_UNKNOWN(0),
    CLIENT_KEYGUARD(1),
    CLIENT_BIOMETRIC_PROMPT(2),
    CLIENT_FINGERPRINT_MANAGER(3);
    
    public static final int CLIENT_BIOMETRIC_PROMPT_VALUE = 2;
    public static final int CLIENT_FINGERPRINT_MANAGER_VALUE = 3;
    public static final int CLIENT_KEYGUARD_VALUE = 1;
    public static final int CLIENT_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ClientEnum> internalValueMap = new Internal.EnumLiteMap<ClientEnum>() {
        /* class android.hardware.biometrics.ClientEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ClientEnum findValueByNumber(int number) {
            return ClientEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ClientEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ClientEnum forNumber(int value2) {
        if (value2 == 0) {
            return CLIENT_UNKNOWN;
        }
        if (value2 == 1) {
            return CLIENT_KEYGUARD;
        }
        if (value2 == 2) {
            return CLIENT_BIOMETRIC_PROMPT;
        }
        if (value2 != 3) {
            return null;
        }
        return CLIENT_FINGERPRINT_MANAGER;
    }

    public static Internal.EnumLiteMap<ClientEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ClientEnum(int value2) {
        this.value = value2;
    }
}
