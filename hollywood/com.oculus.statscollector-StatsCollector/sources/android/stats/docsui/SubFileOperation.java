package android.stats.docsui;

import com.google.protobuf.Internal;

public enum SubFileOperation implements Internal.EnumLite {
    SUB_OP_UNKNOWN(0),
    SUB_OP_QUERY_DOC(1),
    SUB_OP_QUERY_CHILD(2),
    SUB_OP_OPEN_FILE(3),
    SUB_OP_READ_FILE(4),
    SUB_OP_CREATE_DOC(5),
    SUB_OP_WRITE_FILE(6),
    SUB_OP_DELETE_DOC(7),
    SUB_OP_OBTAIN_STREAM_TYPE(8),
    SUB_OP_QUICK_MOVE(9),
    SUB_OP_QUICK_COPY(10);
    
    public static final int SUB_OP_CREATE_DOC_VALUE = 5;
    public static final int SUB_OP_DELETE_DOC_VALUE = 7;
    public static final int SUB_OP_OBTAIN_STREAM_TYPE_VALUE = 8;
    public static final int SUB_OP_OPEN_FILE_VALUE = 3;
    public static final int SUB_OP_QUERY_CHILD_VALUE = 2;
    public static final int SUB_OP_QUERY_DOC_VALUE = 1;
    public static final int SUB_OP_QUICK_COPY_VALUE = 10;
    public static final int SUB_OP_QUICK_MOVE_VALUE = 9;
    public static final int SUB_OP_READ_FILE_VALUE = 4;
    public static final int SUB_OP_UNKNOWN_VALUE = 0;
    public static final int SUB_OP_WRITE_FILE_VALUE = 6;
    private static final Internal.EnumLiteMap<SubFileOperation> internalValueMap = new Internal.EnumLiteMap<SubFileOperation>() {
        /* class android.stats.docsui.SubFileOperation.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SubFileOperation findValueByNumber(int number) {
            return SubFileOperation.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SubFileOperation valueOf(int value2) {
        return forNumber(value2);
    }

    public static SubFileOperation forNumber(int value2) {
        switch (value2) {
            case 0:
                return SUB_OP_UNKNOWN;
            case 1:
                return SUB_OP_QUERY_DOC;
            case 2:
                return SUB_OP_QUERY_CHILD;
            case 3:
                return SUB_OP_OPEN_FILE;
            case 4:
                return SUB_OP_READ_FILE;
            case 5:
                return SUB_OP_CREATE_DOC;
            case 6:
                return SUB_OP_WRITE_FILE;
            case 7:
                return SUB_OP_DELETE_DOC;
            case 8:
                return SUB_OP_OBTAIN_STREAM_TYPE;
            case 9:
                return SUB_OP_QUICK_MOVE;
            case 10:
                return SUB_OP_QUICK_COPY;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<SubFileOperation> internalGetValueMap() {
        return internalValueMap;
    }

    private SubFileOperation(int value2) {
        this.value = value2;
    }
}
