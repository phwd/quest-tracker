package android.stats.docsui;

import com.google.protobuf.Internal;

public enum Authority implements Internal.EnumLite {
    AUTH_UNKNOWN(0),
    AUTH_OTHER(1),
    AUTH_MEDIA(2),
    AUTH_STORAGE_INTERNAL(3),
    AUTH_STORAGE_EXTERNAL(4),
    AUTH_DOWNLOADS(5),
    AUTH_MTP(6);
    
    public static final int AUTH_DOWNLOADS_VALUE = 5;
    public static final int AUTH_MEDIA_VALUE = 2;
    public static final int AUTH_MTP_VALUE = 6;
    public static final int AUTH_OTHER_VALUE = 1;
    public static final int AUTH_STORAGE_EXTERNAL_VALUE = 4;
    public static final int AUTH_STORAGE_INTERNAL_VALUE = 3;
    public static final int AUTH_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<Authority> internalValueMap = new Internal.EnumLiteMap<Authority>() {
        /* class android.stats.docsui.Authority.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public Authority findValueByNumber(int number) {
            return Authority.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Authority valueOf(int value2) {
        return forNumber(value2);
    }

    public static Authority forNumber(int value2) {
        switch (value2) {
            case 0:
                return AUTH_UNKNOWN;
            case 1:
                return AUTH_OTHER;
            case 2:
                return AUTH_MEDIA;
            case 3:
                return AUTH_STORAGE_INTERNAL;
            case 4:
                return AUTH_STORAGE_EXTERNAL;
            case 5:
                return AUTH_DOWNLOADS;
            case 6:
                return AUTH_MTP;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<Authority> internalGetValueMap() {
        return internalValueMap;
    }

    private Authority(int value2) {
        this.value = value2;
    }
}
