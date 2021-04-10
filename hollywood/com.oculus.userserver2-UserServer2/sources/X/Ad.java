package X;

public enum Ad {
    NONE(null),
    CLASS("@class"),
    MINIMAL_CLASS("@c"),
    NAME("@type"),
    CUSTOM(null);
    
    public final String _defaultPropertyName;

    /* access modifiers changed from: public */
    Ad(String str) {
        this._defaultPropertyName = str;
    }

    public String getDefaultPropertyName() {
        return this._defaultPropertyName;
    }
}
