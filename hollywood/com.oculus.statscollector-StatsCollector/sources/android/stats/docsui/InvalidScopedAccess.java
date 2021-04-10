package android.stats.docsui;

import com.google.protobuf.Internal;

public enum InvalidScopedAccess implements Internal.EnumLite {
    SCOPED_DIR_ACCESS_UNKNOWN(0),
    SCOPED_DIR_ACCESS_INVALID_ARGUMENTS(1),
    SCOPED_DIR_ACCESS_INVALID_DIRECTORY(2),
    SCOPED_DIR_ACCESS_ERROR(3),
    SCOPED_DIR_ACCESS_DEPRECATED(4);
    
    public static final int SCOPED_DIR_ACCESS_DEPRECATED_VALUE = 4;
    public static final int SCOPED_DIR_ACCESS_ERROR_VALUE = 3;
    public static final int SCOPED_DIR_ACCESS_INVALID_ARGUMENTS_VALUE = 1;
    public static final int SCOPED_DIR_ACCESS_INVALID_DIRECTORY_VALUE = 2;
    public static final int SCOPED_DIR_ACCESS_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<InvalidScopedAccess> internalValueMap = new Internal.EnumLiteMap<InvalidScopedAccess>() {
        /* class android.stats.docsui.InvalidScopedAccess.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public InvalidScopedAccess findValueByNumber(int number) {
            return InvalidScopedAccess.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static InvalidScopedAccess valueOf(int value2) {
        return forNumber(value2);
    }

    public static InvalidScopedAccess forNumber(int value2) {
        if (value2 == 0) {
            return SCOPED_DIR_ACCESS_UNKNOWN;
        }
        if (value2 == 1) {
            return SCOPED_DIR_ACCESS_INVALID_ARGUMENTS;
        }
        if (value2 == 2) {
            return SCOPED_DIR_ACCESS_INVALID_DIRECTORY;
        }
        if (value2 == 3) {
            return SCOPED_DIR_ACCESS_ERROR;
        }
        if (value2 != 4) {
            return null;
        }
        return SCOPED_DIR_ACCESS_DEPRECATED;
    }

    public static Internal.EnumLiteMap<InvalidScopedAccess> internalGetValueMap() {
        return internalValueMap;
    }

    private InvalidScopedAccess(int value2) {
        this.value = value2;
    }
}
