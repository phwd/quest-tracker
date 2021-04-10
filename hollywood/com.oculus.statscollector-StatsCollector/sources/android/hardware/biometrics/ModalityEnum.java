package android.hardware.biometrics;

import com.google.protobuf.Internal;

public enum ModalityEnum implements Internal.EnumLite {
    MODALITY_UNKNOWN(0),
    MODALITY_FINGERPRINT(1),
    MODALITY_IRIS(2),
    MODALITY_FACE(4);
    
    public static final int MODALITY_FACE_VALUE = 4;
    public static final int MODALITY_FINGERPRINT_VALUE = 1;
    public static final int MODALITY_IRIS_VALUE = 2;
    public static final int MODALITY_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ModalityEnum> internalValueMap = new Internal.EnumLiteMap<ModalityEnum>() {
        /* class android.hardware.biometrics.ModalityEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ModalityEnum findValueByNumber(int number) {
            return ModalityEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ModalityEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static ModalityEnum forNumber(int value2) {
        if (value2 == 0) {
            return MODALITY_UNKNOWN;
        }
        if (value2 == 1) {
            return MODALITY_FINGERPRINT;
        }
        if (value2 == 2) {
            return MODALITY_IRIS;
        }
        if (value2 != 4) {
            return null;
        }
        return MODALITY_FACE;
    }

    public static Internal.EnumLiteMap<ModalityEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private ModalityEnum(int value2) {
        this.value = value2;
    }
}
