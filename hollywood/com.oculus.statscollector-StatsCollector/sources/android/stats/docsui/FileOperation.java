package android.stats.docsui;

import com.google.protobuf.Internal;

public enum FileOperation implements Internal.EnumLite {
    OP_UNKNOWN(0),
    OP_OTHER(1),
    OP_COPY(2),
    OP_COPY_INTRA_PROVIDER(3),
    OP_COPY_SYSTEM_PROVIDER(4),
    OP_COPY_EXTERNAL_PROVIDER(5),
    OP_MOVE(6),
    OP_MOVE_INTRA_PROVIDER(7),
    OP_MOVE_SYSTEM_PROVIDER(8),
    OP_MOVE_EXTERNAL_PROVIDER(9),
    OP_DELETE(10),
    OP_RENAME(11),
    OP_CREATE_DIR(12),
    OP_OTHER_ERROR(13),
    OP_DELETE_ERROR(14),
    OP_MOVE_ERROR(15),
    OP_COPY_ERROR(16),
    OP_RENAME_ERROR(17),
    OP_CREATE_DIR_ERROR(18),
    OP_COMPRESS_INTRA_PROVIDER(19),
    OP_COMPRESS_SYSTEM_PROVIDER(20),
    OP_COMPRESS_EXTERNAL_PROVIDER(21),
    OP_EXTRACT_INTRA_PROVIDER(22),
    OP_EXTRACT_SYSTEM_PROVIDER(23),
    OP_EXTRACT_EXTERNAL_PROVIDER(24),
    OP_COMPRESS_ERROR(25),
    OP_EXTRACT_ERROR(26);
    
    public static final int OP_COMPRESS_ERROR_VALUE = 25;
    public static final int OP_COMPRESS_EXTERNAL_PROVIDER_VALUE = 21;
    public static final int OP_COMPRESS_INTRA_PROVIDER_VALUE = 19;
    public static final int OP_COMPRESS_SYSTEM_PROVIDER_VALUE = 20;
    public static final int OP_COPY_ERROR_VALUE = 16;
    public static final int OP_COPY_EXTERNAL_PROVIDER_VALUE = 5;
    public static final int OP_COPY_INTRA_PROVIDER_VALUE = 3;
    public static final int OP_COPY_SYSTEM_PROVIDER_VALUE = 4;
    public static final int OP_COPY_VALUE = 2;
    public static final int OP_CREATE_DIR_ERROR_VALUE = 18;
    public static final int OP_CREATE_DIR_VALUE = 12;
    public static final int OP_DELETE_ERROR_VALUE = 14;
    public static final int OP_DELETE_VALUE = 10;
    public static final int OP_EXTRACT_ERROR_VALUE = 26;
    public static final int OP_EXTRACT_EXTERNAL_PROVIDER_VALUE = 24;
    public static final int OP_EXTRACT_INTRA_PROVIDER_VALUE = 22;
    public static final int OP_EXTRACT_SYSTEM_PROVIDER_VALUE = 23;
    public static final int OP_MOVE_ERROR_VALUE = 15;
    public static final int OP_MOVE_EXTERNAL_PROVIDER_VALUE = 9;
    public static final int OP_MOVE_INTRA_PROVIDER_VALUE = 7;
    public static final int OP_MOVE_SYSTEM_PROVIDER_VALUE = 8;
    public static final int OP_MOVE_VALUE = 6;
    public static final int OP_OTHER_ERROR_VALUE = 13;
    public static final int OP_OTHER_VALUE = 1;
    public static final int OP_RENAME_ERROR_VALUE = 17;
    public static final int OP_RENAME_VALUE = 11;
    public static final int OP_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<FileOperation> internalValueMap = new Internal.EnumLiteMap<FileOperation>() {
        /* class android.stats.docsui.FileOperation.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public FileOperation findValueByNumber(int number) {
            return FileOperation.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static FileOperation valueOf(int value2) {
        return forNumber(value2);
    }

    public static FileOperation forNumber(int value2) {
        switch (value2) {
            case 0:
                return OP_UNKNOWN;
            case 1:
                return OP_OTHER;
            case 2:
                return OP_COPY;
            case 3:
                return OP_COPY_INTRA_PROVIDER;
            case 4:
                return OP_COPY_SYSTEM_PROVIDER;
            case 5:
                return OP_COPY_EXTERNAL_PROVIDER;
            case 6:
                return OP_MOVE;
            case 7:
                return OP_MOVE_INTRA_PROVIDER;
            case 8:
                return OP_MOVE_SYSTEM_PROVIDER;
            case 9:
                return OP_MOVE_EXTERNAL_PROVIDER;
            case 10:
                return OP_DELETE;
            case 11:
                return OP_RENAME;
            case 12:
                return OP_CREATE_DIR;
            case 13:
                return OP_OTHER_ERROR;
            case 14:
                return OP_DELETE_ERROR;
            case 15:
                return OP_MOVE_ERROR;
            case 16:
                return OP_COPY_ERROR;
            case 17:
                return OP_RENAME_ERROR;
            case 18:
                return OP_CREATE_DIR_ERROR;
            case 19:
                return OP_COMPRESS_INTRA_PROVIDER;
            case 20:
                return OP_COMPRESS_SYSTEM_PROVIDER;
            case 21:
                return OP_COMPRESS_EXTERNAL_PROVIDER;
            case 22:
                return OP_EXTRACT_INTRA_PROVIDER;
            case 23:
                return OP_EXTRACT_SYSTEM_PROVIDER;
            case 24:
                return OP_EXTRACT_EXTERNAL_PROVIDER;
            case 25:
                return OP_COMPRESS_ERROR;
            case 26:
                return OP_EXTRACT_ERROR;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<FileOperation> internalGetValueMap() {
        return internalValueMap;
    }

    private FileOperation(int value2) {
        this.value = value2;
    }
}
