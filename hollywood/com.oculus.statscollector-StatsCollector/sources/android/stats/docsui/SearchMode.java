package android.stats.docsui;

import com.google.protobuf.Internal;

public enum SearchMode implements Internal.EnumLite {
    SEARCH_UNKNOWN(0),
    SEARCH_KEYWORD(1),
    SEARCH_CHIPS(2),
    SEARCH_KEYWORD_N_CHIPS(3);
    
    public static final int SEARCH_CHIPS_VALUE = 2;
    public static final int SEARCH_KEYWORD_N_CHIPS_VALUE = 3;
    public static final int SEARCH_KEYWORD_VALUE = 1;
    public static final int SEARCH_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<SearchMode> internalValueMap = new Internal.EnumLiteMap<SearchMode>() {
        /* class android.stats.docsui.SearchMode.AnonymousClass1 */

        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SearchMode findValueByNumber(int number) {
            return SearchMode.forNumber(number);
        }
    };
    private final int value;

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static SearchMode valueOf(int value2) {
        return forNumber(value2);
    }

    public static SearchMode forNumber(int value2) {
        if (value2 == 0) {
            return SEARCH_UNKNOWN;
        }
        if (value2 == 1) {
            return SEARCH_KEYWORD;
        }
        if (value2 == 2) {
            return SEARCH_CHIPS;
        }
        if (value2 != 3) {
            return null;
        }
        return SEARCH_KEYWORD_N_CHIPS;
    }

    public static Internal.EnumLiteMap<SearchMode> internalGetValueMap() {
        return internalValueMap;
    }

    private SearchMode(int value2) {
        this.value = value2;
    }
}
