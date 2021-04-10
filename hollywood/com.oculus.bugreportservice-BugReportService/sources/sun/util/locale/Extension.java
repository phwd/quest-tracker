package sun.util.locale;

/* access modifiers changed from: package-private */
public class Extension {
    private String id;
    private final char key;
    private String value;

    protected Extension(char c) {
        this.key = c;
    }

    Extension(char c, String str) {
        this.key = c;
        setValue(str);
    }

    /* access modifiers changed from: protected */
    public void setValue(String str) {
        this.value = str;
        this.id = this.key + "-" + str;
    }

    public String getValue() {
        return this.value;
    }

    public String getID() {
        return this.id;
    }

    public String toString() {
        return getID();
    }
}
