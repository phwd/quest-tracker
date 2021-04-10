package android.bluetooth.a2dp;

import com.google.protobuf.Internal;

public enum AudioCodingModeEnum implements Internal.EnumLite {
    AUDIO_CODING_MODE_UNKNOWN(0),
    AUDIO_CODING_MODE_HARDWARE(1),
    AUDIO_CODING_MODE_SOFTWARE(2);
    
    public static final int AUDIO_CODING_MODE_HARDWARE_VALUE = 1;
    public static final int AUDIO_CODING_MODE_SOFTWARE_VALUE = 2;
    public static final int AUDIO_CODING_MODE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<AudioCodingModeEnum> internalValueMap = new Internal.EnumLiteMap<AudioCodingModeEnum>() {
        /* class android.bluetooth.a2dp.AudioCodingModeEnum.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AudioCodingModeEnum findValueByNumber(int number) {
            return AudioCodingModeEnum.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static AudioCodingModeEnum valueOf(int value2) {
        return forNumber(value2);
    }

    public static AudioCodingModeEnum forNumber(int value2) {
        if (value2 == 0) {
            return AUDIO_CODING_MODE_UNKNOWN;
        }
        if (value2 == 1) {
            return AUDIO_CODING_MODE_HARDWARE;
        }
        if (value2 != 2) {
            return null;
        }
        return AUDIO_CODING_MODE_SOFTWARE;
    }

    public static Internal.EnumLiteMap<AudioCodingModeEnum> internalGetValueMap() {
        return internalValueMap;
    }

    private AudioCodingModeEnum(int value2) {
        this.value = value2;
    }
}
