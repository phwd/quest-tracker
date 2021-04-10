package android.server;

import com.google.protobuf.Internal;

public enum ErrorSource implements Internal.EnumLite {
    ERROR_SOURCE_UNKNOWN(0),
    DATA_APP(1),
    SYSTEM_APP(2),
    SYSTEM_SERVER(3);
    
    public static final int DATA_APP_VALUE = 1;
    public static final int ERROR_SOURCE_UNKNOWN_VALUE = 0;
    public static final int SYSTEM_APP_VALUE = 2;
    public static final int SYSTEM_SERVER_VALUE = 3;
    private static final Internal.EnumLiteMap<ErrorSource> internalValueMap = new Internal.EnumLiteMap<ErrorSource>() {
        /* class android.server.ErrorSource.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ErrorSource findValueByNumber(int number) {
            return ErrorSource.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ErrorSource valueOf(int value2) {
        return forNumber(value2);
    }

    public static ErrorSource forNumber(int value2) {
        if (value2 == 0) {
            return ERROR_SOURCE_UNKNOWN;
        }
        if (value2 == 1) {
            return DATA_APP;
        }
        if (value2 == 2) {
            return SYSTEM_APP;
        }
        if (value2 != 3) {
            return null;
        }
        return SYSTEM_SERVER;
    }

    public static Internal.EnumLiteMap<ErrorSource> internalGetValueMap() {
        return internalValueMap;
    }

    private ErrorSource(int value2) {
        this.value = value2;
    }
}
