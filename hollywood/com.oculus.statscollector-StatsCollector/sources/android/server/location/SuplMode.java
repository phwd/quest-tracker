package android.server.location;

import com.google.protobuf.Internal;

public enum SuplMode implements Internal.EnumLite {
    MSB(1),
    MSA(2);
    
    public static final int MSA_VALUE = 2;
    public static final int MSB_VALUE = 1;
    private static final Internal.EnumLiteMap<SuplMode> internalValueMap = new Internal.EnumLiteMap<SuplMode>() {
        /* class android.server.location.SuplMode.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SuplMode findValueByNumber(int number) {
            return SuplMode.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SuplMode valueOf(int value2) {
        return forNumber(value2);
    }

    public static SuplMode forNumber(int value2) {
        if (value2 == 1) {
            return MSB;
        }
        if (value2 != 2) {
            return null;
        }
        return MSA;
    }

    public static Internal.EnumLiteMap<SuplMode> internalGetValueMap() {
        return internalValueMap;
    }

    private SuplMode(int value2) {
        this.value = value2;
    }
}
