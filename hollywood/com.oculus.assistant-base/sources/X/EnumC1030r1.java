package X;

/* renamed from: X.r1  reason: case insensitive filesystem */
public enum EnumC1030r1 implements OT {
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
    EnumC1030r1(boolean z) {
        this._defaultState = z;
    }

    @Override // X.OT
    public boolean enabledByDefault() {
        return this._defaultState;
    }

    @Override // X.OT
    public int getMask() {
        return 1 << ordinal();
    }
}
