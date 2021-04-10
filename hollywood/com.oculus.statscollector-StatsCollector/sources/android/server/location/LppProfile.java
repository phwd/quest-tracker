package android.server.location;

import com.google.protobuf.Internal;

public enum LppProfile implements Internal.EnumLite {
    USER_PLANE(1),
    CONTROL_PLANE(2);
    
    public static final int CONTROL_PLANE_VALUE = 2;
    public static final int USER_PLANE_VALUE = 1;
    private static final Internal.EnumLiteMap<LppProfile> internalValueMap = new Internal.EnumLiteMap<LppProfile>() {
        /* class android.server.location.LppProfile.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LppProfile findValueByNumber(int number) {
            return LppProfile.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LppProfile valueOf(int value2) {
        return forNumber(value2);
    }

    public static LppProfile forNumber(int value2) {
        if (value2 == 1) {
            return USER_PLANE;
        }
        if (value2 != 2) {
            return null;
        }
        return CONTROL_PLANE;
    }

    public static Internal.EnumLiteMap<LppProfile> internalGetValueMap() {
        return internalValueMap;
    }

    private LppProfile(int value2) {
        this.value = value2;
    }
}
