package android.stats.docsui;

import com.google.protobuf.Internal;

public enum LaunchAction implements Internal.EnumLite {
    UNKNOWN(0),
    OPEN(1),
    CREATE(2),
    GET_CONTENT(3),
    OPEN_TREE(4),
    PICK_COPY_DEST(5),
    BROWSE(6),
    OTHER(7);
    
    public static final int BROWSE_VALUE = 6;
    public static final int CREATE_VALUE = 2;
    public static final int GET_CONTENT_VALUE = 3;
    public static final int OPEN_TREE_VALUE = 4;
    public static final int OPEN_VALUE = 1;
    public static final int OTHER_VALUE = 7;
    public static final int PICK_COPY_DEST_VALUE = 5;
    public static final int UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<LaunchAction> internalValueMap = new Internal.EnumLiteMap<LaunchAction>() {
        /* class android.stats.docsui.LaunchAction.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LaunchAction findValueByNumber(int number) {
            return LaunchAction.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LaunchAction valueOf(int value2) {
        return forNumber(value2);
    }

    public static LaunchAction forNumber(int value2) {
        switch (value2) {
            case 0:
                return UNKNOWN;
            case 1:
                return OPEN;
            case 2:
                return CREATE;
            case 3:
                return GET_CONTENT;
            case 4:
                return OPEN_TREE;
            case 5:
                return PICK_COPY_DEST;
            case 6:
                return BROWSE;
            case 7:
                return OTHER;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<LaunchAction> internalGetValueMap() {
        return internalValueMap;
    }

    private LaunchAction(int value2) {
        this.value = value2;
    }
}
