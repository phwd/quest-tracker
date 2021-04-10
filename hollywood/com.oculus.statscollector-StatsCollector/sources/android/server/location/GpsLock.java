package android.server.location;

import com.google.protobuf.Internal;

public enum GpsLock implements Internal.EnumLite {
    MO(1),
    NI(2);
    
    public static final int MO_VALUE = 1;
    public static final int NI_VALUE = 2;
    private static final Internal.EnumLiteMap<GpsLock> internalValueMap = new Internal.EnumLiteMap<GpsLock>() {
        /* class android.server.location.GpsLock.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public GpsLock findValueByNumber(int number) {
            return GpsLock.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static GpsLock valueOf(int value2) {
        return forNumber(value2);
    }

    public static GpsLock forNumber(int value2) {
        if (value2 == 1) {
            return MO;
        }
        if (value2 != 2) {
            return null;
        }
        return NI;
    }

    public static Internal.EnumLiteMap<GpsLock> internalGetValueMap() {
        return internalValueMap;
    }

    private GpsLock(int value2) {
        this.value = value2;
    }
}
