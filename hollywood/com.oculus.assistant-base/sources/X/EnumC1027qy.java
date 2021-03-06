package X;

/* renamed from: X.qy  reason: case insensitive filesystem */
public enum EnumC1027qy implements OT {
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

    /* access modifiers changed from: public */
    EnumC1027qy(boolean z) {
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
