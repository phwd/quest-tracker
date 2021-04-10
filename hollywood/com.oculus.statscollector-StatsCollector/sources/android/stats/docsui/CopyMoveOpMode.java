package android.stats.docsui;

import com.google.protobuf.Internal;

public enum CopyMoveOpMode implements Internal.EnumLite {
    MODE_UNKNOWN(0),
    MODE_PROVIDER(1),
    MODE_CONVERTED(2),
    MODE_CONVENTIONAL(3);
    
    public static final int MODE_CONVENTIONAL_VALUE = 3;
    public static final int MODE_CONVERTED_VALUE = 2;
    public static final int MODE_PROVIDER_VALUE = 1;
    public static final int MODE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<CopyMoveOpMode> internalValueMap = new Internal.EnumLiteMap<CopyMoveOpMode>() {
        /* class android.stats.docsui.CopyMoveOpMode.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public CopyMoveOpMode findValueByNumber(int number) {
            return CopyMoveOpMode.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static CopyMoveOpMode valueOf(int value2) {
        return forNumber(value2);
    }

    public static CopyMoveOpMode forNumber(int value2) {
        if (value2 == 0) {
            return MODE_UNKNOWN;
        }
        if (value2 == 1) {
            return MODE_PROVIDER;
        }
        if (value2 == 2) {
            return MODE_CONVERTED;
        }
        if (value2 != 3) {
            return null;
        }
        return MODE_CONVENTIONAL;
    }

    public static Internal.EnumLiteMap<CopyMoveOpMode> internalGetValueMap() {
        return internalValueMap;
    }

    private CopyMoveOpMode(int value2) {
        this.value = value2;
    }
}
