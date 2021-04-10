package android.stats.docsui;

import com.google.protobuf.Internal;

public enum SearchType implements Internal.EnumLite {
    TYPE_UNKNOWN(0),
    TYPE_CHIP_IMAGES(1),
    TYPE_CHIP_AUDIOS(2),
    TYPE_CHIP_VIDEOS(3),
    TYPE_CHIP_DOCS(4),
    TYPE_SEARCH_HISTORY(5),
    TYPE_SEARCH_STRING(6);
    
    public static final int TYPE_CHIP_AUDIOS_VALUE = 2;
    public static final int TYPE_CHIP_DOCS_VALUE = 4;
    public static final int TYPE_CHIP_IMAGES_VALUE = 1;
    public static final int TYPE_CHIP_VIDEOS_VALUE = 3;
    public static final int TYPE_SEARCH_HISTORY_VALUE = 5;
    public static final int TYPE_SEARCH_STRING_VALUE = 6;
    public static final int TYPE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<SearchType> internalValueMap = new Internal.EnumLiteMap<SearchType>() {
        /* class android.stats.docsui.SearchType.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SearchType findValueByNumber(int number) {
            return SearchType.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SearchType valueOf(int value2) {
        return forNumber(value2);
    }

    public static SearchType forNumber(int value2) {
        switch (value2) {
            case 0:
                return TYPE_UNKNOWN;
            case 1:
                return TYPE_CHIP_IMAGES;
            case 2:
                return TYPE_CHIP_AUDIOS;
            case 3:
                return TYPE_CHIP_VIDEOS;
            case 4:
                return TYPE_CHIP_DOCS;
            case 5:
                return TYPE_SEARCH_HISTORY;
            case 6:
                return TYPE_SEARCH_STRING;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<SearchType> internalGetValueMap() {
        return internalValueMap;
    }

    private SearchType(int value2) {
        this.value = value2;
    }
}
