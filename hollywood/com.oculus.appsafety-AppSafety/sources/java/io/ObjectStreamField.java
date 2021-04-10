package java.io;

import java.lang.reflect.Field;
import sun.reflect.CallerSensitive;

public class ObjectStreamField implements Comparable<Object> {
    private final Field field;
    private final String name;
    private int offset;
    private final String signature;
    private final Class<?> type;
    private final boolean unshared;

    public ObjectStreamField(String name2, Class<?> type2) {
        this(name2, type2, false);
    }

    public ObjectStreamField(String name2, Class<?> type2, boolean unshared2) {
        this.offset = 0;
        if (name2 != null) {
            this.name = name2;
            this.type = type2;
            this.unshared = unshared2;
            this.signature = getClassSignature(type2).intern();
            this.field = null;
            return;
        }
        throw new NullPointerException();
    }

    ObjectStreamField(String name2, String signature2, boolean unshared2) {
        this.offset = 0;
        if (name2 != null) {
            this.name = name2;
            this.signature = signature2.intern();
            this.unshared = unshared2;
            this.field = null;
            char charAt = signature2.charAt(0);
            if (charAt != 'F') {
                if (charAt != 'L') {
                    if (charAt == 'S') {
                        this.type = Short.TYPE;
                        return;
                    } else if (charAt == 'I') {
                        this.type = Integer.TYPE;
                        return;
                    } else if (charAt == 'J') {
                        this.type = Long.TYPE;
                        return;
                    } else if (charAt == 'Z') {
                        this.type = Boolean.TYPE;
                        return;
                    } else if (charAt != '[') {
                        switch (charAt) {
                            case 'B':
                                this.type = Byte.TYPE;
                                return;
                            case 'C':
                                this.type = Character.TYPE;
                                return;
                            case 'D':
                                this.type = Double.TYPE;
                                return;
                            default:
                                throw new IllegalArgumentException("illegal signature");
                        }
                    }
                }
                this.type = Object.class;
                return;
            }
            this.type = Float.TYPE;
            return;
        }
        throw new NullPointerException();
    }

    ObjectStreamField(Field field2, boolean unshared2, boolean showType) {
        this.offset = 0;
        this.field = field2;
        this.unshared = unshared2;
        this.name = field2.getName();
        Class<?> ftype = field2.getType();
        this.type = (showType || ftype.isPrimitive()) ? ftype : Object.class;
        this.signature = getClassSignature(ftype).intern();
    }

    public String getName() {
        return this.name;
    }

    @CallerSensitive
    public Class<?> getType() {
        return this.type;
    }

    public char getTypeCode() {
        return this.signature.charAt(0);
    }

    public String getTypeString() {
        if (isPrimitive()) {
            return null;
        }
        return this.signature;
    }

    public int getOffset() {
        return this.offset;
    }

    /* access modifiers changed from: protected */
    public void setOffset(int offset2) {
        this.offset = offset2;
    }

    public boolean isPrimitive() {
        char tcode = this.signature.charAt(0);
        if (tcode == 'L' || tcode == '[') {
            return false;
        }
        return true;
    }

    public boolean isUnshared() {
        return this.unshared;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        ObjectStreamField other = (ObjectStreamField) obj;
        boolean isPrim = isPrimitive();
        if (isPrim != other.isPrimitive()) {
            return isPrim ? -1 : 1;
        }
        return this.name.compareTo(other.name);
    }

    public String toString() {
        return this.signature + ' ' + this.name;
    }

    /* access modifiers changed from: package-private */
    public Field getField() {
        return this.field;
    }

    /* access modifiers changed from: package-private */
    public String getSignature() {
        return this.signature;
    }

    private static String getClassSignature(Class<?> cl) {
        StringBuilder sbuf = new StringBuilder();
        while (cl.isArray()) {
            sbuf.append('[');
            cl = cl.getComponentType();
        }
        if (!cl.isPrimitive()) {
            sbuf.append('L' + cl.getName().replace('.', '/') + ';');
        } else if (cl == Integer.TYPE) {
            sbuf.append('I');
        } else if (cl == Byte.TYPE) {
            sbuf.append('B');
        } else if (cl == Long.TYPE) {
            sbuf.append('J');
        } else if (cl == Float.TYPE) {
            sbuf.append('F');
        } else if (cl == Double.TYPE) {
            sbuf.append('D');
        } else if (cl == Short.TYPE) {
            sbuf.append('S');
        } else if (cl == Character.TYPE) {
            sbuf.append('C');
        } else if (cl == Boolean.TYPE) {
            sbuf.append('Z');
        } else if (cl == Void.TYPE) {
            sbuf.append('V');
        } else {
            throw new InternalError();
        }
        return sbuf.toString();
    }
}
