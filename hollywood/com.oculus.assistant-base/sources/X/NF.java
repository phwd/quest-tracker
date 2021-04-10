package X;

public enum NF {
    NONE(null),
    CLASS("@class"),
    MINIMAL_CLASS("@c"),
    NAME("@type"),
    CUSTOM(null);
    
    public final String _defaultPropertyName;

    /* access modifiers changed from: public */
    NF(String str) {
        this._defaultPropertyName = str;
    }

    public String getDefaultPropertyName() {
        return this._defaultPropertyName;
    }
}
