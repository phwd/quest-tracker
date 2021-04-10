package java.util.jar;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.Manifest;
import sun.misc.ASCIICaseInsensitiveComparator;

public class Attributes implements Map, Cloneable {
    protected Map map;

    public Attributes() {
        this(11);
    }

    public Attributes(int i) {
        this.map = new HashMap(i);
    }

    public Attributes(Attributes attributes) {
        this.map = new HashMap(attributes);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.map.get(obj);
    }

    public String getValue(Name name) {
        return (String) get(name);
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        return this.map.put((Name) obj, (String) obj2);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public void putAll(Map map2) {
        if (Attributes.class.isInstance(map2)) {
            for (Map.Entry entry : map2.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new ClassCastException();
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public Set keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public Collection values() {
        return this.map.values();
    }

    @Override // java.util.Map
    public Set entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    public Object clone() {
        return new Attributes(this);
    }

    /* access modifiers changed from: package-private */
    public void read(Manifest.FastInputStream fastInputStream, byte[] bArr) {
        int readLine = fastInputStream.readLine(bArr);
        if (readLine != -1) {
            int i = readLine - 1;
            if (bArr[i] == 10) {
                if (i > 0 && bArr[i - 1] == 13) {
                    i--;
                }
                if (i != 0) {
                    if (bArr[0] == 32) {
                        throw new IOException("misplaced continuation line");
                    }
                    int i2 = 0;
                    while (true) {
                        int i3 = i2 + 1;
                        if (bArr[i2] == 58) {
                            int i4 = i3 + 1;
                            if (bArr[i3] != 32) {
                                throw new IOException("invalid header field");
                            }
                            new String(bArr, 0, 0, i4 - 2);
                            throw null;
                        } else if (i3 < i) {
                            i2 = i3;
                        } else {
                            throw new IOException("invalid header field");
                        }
                    }
                }
            } else {
                throw new IOException("line too long");
            }
        }
    }

    public static class Name {
        public static final Name CLASS_PATH = new Name("Class-Path");
        public static final Name CONTENT_TYPE = new Name("Content-Type");
        public static final Name EXTENSION_INSTALLATION = new Name("Extension-Installation");
        public static final Name EXTENSION_LIST = new Name("Extension-List");
        public static final Name EXTENSION_NAME = new Name("Extension-Name");
        public static final Name IMPLEMENTATION_TITLE = new Name("Implementation-Title");
        public static final Name IMPLEMENTATION_URL = new Name("Implementation-URL");
        public static final Name IMPLEMENTATION_VENDOR = new Name("Implementation-Vendor");
        public static final Name IMPLEMENTATION_VENDOR_ID = new Name("Implementation-Vendor-Id");
        public static final Name IMPLEMENTATION_VERSION = new Name("Implementation-Version");
        public static final Name MAIN_CLASS = new Name("Main-Class");
        public static final Name MANIFEST_VERSION = new Name("Manifest-Version");
        public static final Name SEALED = new Name("Sealed");
        public static final Name SIGNATURE_VERSION = new Name("Signature-Version");
        public static final Name SPECIFICATION_TITLE = new Name("Specification-Title");
        public static final Name SPECIFICATION_VENDOR = new Name("Specification-Vendor");
        public static final Name SPECIFICATION_VERSION = new Name("Specification-Version");
        private int hashCode = -1;
        private String name;

        private static boolean isAlpha(char c) {
            return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }

        private static boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        public Name(String str) {
            if (str == null) {
                throw new NullPointerException("name");
            } else if (isValid(str)) {
                this.name = str.intern();
            } else {
                throw new IllegalArgumentException(str);
            }
        }

        private static boolean isValid(String str) {
            int length = str.length();
            if (length > 70 || length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (!isValid(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isValid(char c) {
            return isAlpha(c) || isDigit(c) || c == '_' || c == '-';
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Name) || ASCIICaseInsensitiveComparator.CASE_INSENSITIVE_ORDER.compare(this.name, ((Name) obj).name) != 0) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (this.hashCode == -1) {
                this.hashCode = ASCIICaseInsensitiveComparator.lowerCaseHashCode(this.name);
            }
            return this.hashCode;
        }

        public String toString() {
            return this.name;
        }
    }
}
