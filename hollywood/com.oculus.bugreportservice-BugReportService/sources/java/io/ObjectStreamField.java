package java.io;

import java.lang.reflect.Field;

public class ObjectStreamField implements Comparable {
    private final Field field;
    private final String name;
    private int offset;
    private final String signature;
    private final Class type;
    private final boolean unshared;

    public ObjectStreamField(String str, Class cls) {
        this(str, cls, false);
    }

    public ObjectStreamField(String str, Class cls, boolean z) {
        this.offset = 0;
        if (str != null) {
            this.name = str;
            this.type = cls;
            this.unshared = z;
            this.signature = getClassSignature(cls).intern();
            this.field = null;
            return;
        }
        throw new NullPointerException();
    }

    public boolean isPrimitive() {
        char charAt = this.signature.charAt(0);
        return (charAt == 'L' || charAt == '[') ? false : true;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        ObjectStreamField objectStreamField = (ObjectStreamField) obj;
        boolean isPrimitive = isPrimitive();
        if (isPrimitive != objectStreamField.isPrimitive()) {
            return isPrimitive ? -1 : 1;
        }
        return this.name.compareTo(objectStreamField.name);
    }

    public String toString() {
        return this.signature + ' ' + this.name;
    }

    private static String getClassSignature(Class cls) {
        StringBuilder sb = new StringBuilder();
        while (cls.isArray()) {
            sb.append('[');
            cls = cls.getComponentType();
        }
        if (!cls.isPrimitive()) {
            sb.append('L' + cls.getName().replace('.', '/') + ';');
        } else if (cls == Integer.TYPE) {
            sb.append('I');
        } else if (cls == Byte.TYPE) {
            sb.append('B');
        } else if (cls == Long.TYPE) {
            sb.append('J');
        } else if (cls == Float.TYPE) {
            sb.append('F');
        } else if (cls == Double.TYPE) {
            sb.append('D');
        } else if (cls == Short.TYPE) {
            sb.append('S');
        } else if (cls == Character.TYPE) {
            sb.append('C');
        } else if (cls == Boolean.TYPE) {
            sb.append('Z');
        } else if (cls == Void.TYPE) {
            sb.append('V');
        } else {
            throw new InternalError();
        }
        return sb.toString();
    }
}
