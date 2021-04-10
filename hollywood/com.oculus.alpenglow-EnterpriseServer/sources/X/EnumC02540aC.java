package X;

/* renamed from: X.0aC  reason: invalid class name and case insensitive filesystem */
public enum EnumC02540aC implements AbstractC06430ms {
    USE_ANNOTATIONS(true),
    AUTO_DETECT_CREATORS(true),
    AUTO_DETECT_FIELDS(true),
    AUTO_DETECT_GETTERS(true),
    AUTO_DETECT_IS_GETTERS(true),
    AUTO_DETECT_SETTERS(true),
    REQUIRE_SETTERS_FOR_GETTERS(false),
    USE_GETTERS_AS_SETTERS(true),
    CAN_OVERRIDE_ACCESS_MODIFIERS(true),
    INFER_PROPERTY_MUTATORS(true),
    ALLOW_FINAL_FIELDS_AS_MUTATORS(true),
    USE_STATIC_TYPING(false),
    DEFAULT_VIEW_INCLUSION(true),
    SORT_PROPERTIES_ALPHABETICALLY(false),
    USE_WRAPPER_NAME_AS_PROPERTY_NAME(false);
    
    public final boolean _defaultState;

    @Override // X.AbstractC06430ms
    public boolean enabledByDefault() {
        return this._defaultState;
    }

    /* access modifiers changed from: public */
    EnumC02540aC(boolean z) {
        this._defaultState = z;
    }

    @Override // X.AbstractC06430ms
    public int getMask() {
        return 1 << ordinal();
    }
}
