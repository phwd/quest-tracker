package X;

/* renamed from: X.Wc  reason: case insensitive filesystem */
public enum EnumC0217Wc implements dU {
    WRAP_ROOT_VALUE(false),
    INDENT_OUTPUT(false),
    FAIL_ON_EMPTY_BEANS(true),
    WRAP_EXCEPTIONS(true),
    CLOSE_CLOSEABLE(false),
    FLUSH_AFTER_WRITE_VALUE(true),
    WRITE_DATES_AS_TIMESTAMPS(true),
    WRITE_DATE_KEYS_AS_TIMESTAMPS(false),
    WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS(false),
    WRITE_ENUMS_USING_TO_STRING(false),
    WRITE_ENUMS_USING_INDEX(false),
    WRITE_NULL_MAP_VALUES(true),
    WRITE_EMPTY_JSON_ARRAYS(true),
    WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED(false),
    WRITE_BIGDECIMAL_AS_PLAIN(false),
    WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS(true),
    ORDER_MAP_ENTRIES_BY_KEYS(false),
    EAGER_SERIALIZER_FETCH(true);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    EnumC0217Wc(boolean z) {
        this._defaultState = z;
    }

    @Override // X.dU
    public int getMask() {
        return 1 << ordinal();
    }

    @Override // X.dU
    public boolean enabledByDefault() {
        return this._defaultState;
    }
}
