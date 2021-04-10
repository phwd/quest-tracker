package android.stats.docsui;

import com.google.protobuf.Internal;

public enum Provider implements Internal.EnumLite {
    PROVIDER_UNKNOWN(0),
    PROVIDER_SYSTEM(1),
    PROVIDER_EXTERNAL(2);
    
    public static final int PROVIDER_EXTERNAL_VALUE = 2;
    public static final int PROVIDER_SYSTEM_VALUE = 1;
    public static final int PROVIDER_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<Provider> internalValueMap = new Internal.EnumLiteMap<Provider>() {
        /* class android.stats.docsui.Provider.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public Provider findValueByNumber(int number) {
            return Provider.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Provider valueOf(int value2) {
        return forNumber(value2);
    }

    public static Provider forNumber(int value2) {
        if (value2 == 0) {
            return PROVIDER_UNKNOWN;
        }
        if (value2 == 1) {
            return PROVIDER_SYSTEM;
        }
        if (value2 != 2) {
            return null;
        }
        return PROVIDER_EXTERNAL;
    }

    public static Internal.EnumLiteMap<Provider> internalGetValueMap() {
        return internalValueMap;
    }

    private Provider(int value2) {
        this.value = value2;
    }
}
