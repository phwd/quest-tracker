package android.icu.impl.locale;

public class Extension {
    private char _key;
    protected String _value;

    protected Extension(char c) {
        this._key = c;
    }

    Extension(char c, String str) {
        this._key = c;
        this._value = str;
    }

    public String getValue() {
        return this._value;
    }

    public String getID() {
        return this._key + "-" + this._value;
    }

    public String toString() {
        return getID();
    }
}
