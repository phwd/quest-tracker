package android.stats.docsui;

import com.google.protobuf.Internal;

public enum ContextScope implements Internal.EnumLite {
    SCOPE_UNKNOWN(0),
    SCOPE_FILES(1),
    SCOPE_PICKER(2);
    
    public static final int SCOPE_FILES_VALUE = 1;
    public static final int SCOPE_PICKER_VALUE = 2;
    public static final int SCOPE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<ContextScope> internalValueMap = new Internal.EnumLiteMap<ContextScope>() {
        /* class android.stats.docsui.ContextScope.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ContextScope findValueByNumber(int number) {
            return ContextScope.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ContextScope valueOf(int value2) {
        return forNumber(value2);
    }

    public static ContextScope forNumber(int value2) {
        if (value2 == 0) {
            return SCOPE_UNKNOWN;
        }
        if (value2 == 1) {
            return SCOPE_FILES;
        }
        if (value2 != 2) {
            return null;
        }
        return SCOPE_PICKER;
    }

    public static Internal.EnumLiteMap<ContextScope> internalGetValueMap() {
        return internalValueMap;
    }

    private ContextScope(int value2) {
        this.value = value2;
    }
}
