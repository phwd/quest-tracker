package java.util;

import java.io.InputStream;
import java.io.Reader;
import sun.util.ResourceBundleEnumeration;

public class PropertyResourceBundle extends ResourceBundle {
    private final Map lookup;

    public PropertyResourceBundle(InputStream inputStream) {
        Properties properties = new Properties();
        properties.load(inputStream);
        this.lookup = new HashMap(properties);
    }

    public PropertyResourceBundle(Reader reader) {
        Properties properties = new Properties();
        properties.load(reader);
        this.lookup = new HashMap(properties);
    }

    @Override // java.util.ResourceBundle
    public Object handleGetObject(String str) {
        if (str != null) {
            return this.lookup.get(str);
        }
        throw new NullPointerException();
    }

    @Override // java.util.ResourceBundle
    public Enumeration getKeys() {
        ResourceBundle resourceBundle = this.parent;
        return new ResourceBundleEnumeration(this.lookup.keySet(), resourceBundle != null ? resourceBundle.getKeys() : null);
    }
}
