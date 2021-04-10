package android.bluetooth.hfp;

import com.google.protobuf.Internal;

public enum ScoCodec implements Internal.EnumLite {
    SCO_CODEC_UNKNOWN(0),
    SCO_CODEC_CVSD(1),
    SCO_CODEC_MSBC(2);
    
    public static final int SCO_CODEC_CVSD_VALUE = 1;
    public static final int SCO_CODEC_MSBC_VALUE = 2;
    public static final int SCO_CODEC_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ScoCodec> internalValueMap = new Internal.EnumLiteMap<ScoCodec>() {
        /* class android.bluetooth.hfp.ScoCodec.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ScoCodec findValueByNumber(int number) {
            return ScoCodec.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ScoCodec valueOf(int value2) {
        return forNumber(value2);
    }

    public static ScoCodec forNumber(int value2) {
        if (value2 == 0) {
            return SCO_CODEC_UNKNOWN;
        }
        if (value2 == 1) {
            return SCO_CODEC_CVSD;
        }
        if (value2 != 2) {
            return null;
        }
        return SCO_CODEC_MSBC;
    }

    public static Internal.EnumLiteMap<ScoCodec> internalGetValueMap() {
        return internalValueMap;
    }

    private ScoCodec(int value2) {
        this.value = value2;
    }
}
