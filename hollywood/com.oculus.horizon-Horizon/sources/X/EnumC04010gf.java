package X;

/* renamed from: X.0gf  reason: invalid class name and case insensitive filesystem */
public enum EnumC04010gf implements AbstractC05360kw {
    USE_BIG_DECIMAL_FOR_FLOATS(false),
    USE_BIG_INTEGER_FOR_INTS(false),
    USE_JAVA_ARRAY_FOR_JSON_ARRAY(false),
    READ_ENUMS_USING_TO_STRING(false),
    FAIL_ON_UNKNOWN_PROPERTIES(true),
    FAIL_ON_NULL_FOR_PRIMITIVES(false),
    FAIL_ON_NUMBERS_FOR_ENUMS(false),
    FAIL_ON_INVALID_SUBTYPE(true),
    WRAP_EXCEPTIONS(true),
    ACCEPT_SINGLE_VALUE_AS_ARRAY(false),
    UNWRAP_ROOT_VALUE(false),
    ACCEPT_EMPTY_STRING_AS_NULL_OBJECT(false),
    READ_UNKNOWN_ENUM_VALUES_AS_NULL(false),
    READ_DATE_TIMESTAMPS_AS_NANOSECONDS(true),
    ADJUST_DATES_TO_CONTEXT_TIME_ZONE(true),
    EAGER_DESERIALIZER_FETCH(true);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    EnumC04010gf(boolean z) {
        this._defaultState = z;
    }

    @Override // X.AbstractC05360kw
    public boolean enabledByDefault() {
        return this._defaultState;
    }

    @Override // X.AbstractC05360kw
    public int getMask() {
        return 1 << ordinal();
    }
}