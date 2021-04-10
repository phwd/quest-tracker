package libcore.reflect;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class AnnotationMember implements Serializable {
    protected static final char ARRAY = '[';
    protected static final char ERROR = '!';
    protected static final Object NO_VALUE = DefaultValues.NO_VALUE;
    protected static final char OTHER = '*';
    protected transient Method definingMethod;
    protected transient Class<?> elementType;
    protected final String name;
    protected final char tag;
    protected final Object value;

    private enum DefaultValues {
        NO_VALUE
    }

    public AnnotationMember(String name2, Object val) {
        this.name = name2;
        this.value = val == null ? NO_VALUE : val;
        Object obj = this.value;
        if (obj instanceof Throwable) {
            this.tag = ERROR;
        } else if (obj.getClass().isArray()) {
            this.tag = ARRAY;
        } else {
            this.tag = OTHER;
        }
    }

    public AnnotationMember(String name2, Object val, Class type, Method m) {
        this(name2, val);
        this.definingMethod = m;
        if (type == Integer.TYPE) {
            this.elementType = Integer.class;
        } else if (type == Boolean.TYPE) {
            this.elementType = Boolean.class;
        } else if (type == Character.TYPE) {
            this.elementType = Character.class;
        } else if (type == Float.TYPE) {
            this.elementType = Float.class;
        } else if (type == Double.TYPE) {
            this.elementType = Double.class;
        } else if (type == Long.TYPE) {
            this.elementType = Long.class;
        } else if (type == Short.TYPE) {
            this.elementType = Short.class;
        } else if (type == Byte.TYPE) {
            this.elementType = Byte.class;
        } else {
            this.elementType = type;
        }
    }

    /* access modifiers changed from: protected */
    public AnnotationMember setDefinition(AnnotationMember copy) {
        this.definingMethod = copy.definingMethod;
        this.elementType = copy.elementType;
        return this;
    }

    public String toString() {
        if (this.tag == '[') {
            StringBuilder sb = new StringBuilder(80);
            sb.append(this.name);
            sb.append("=[");
            int len = Array.getLength(this.value);
            for (int i = 0; i < len; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(Array.get(this.value, i));
            }
            sb.append("]");
            return sb.toString();
        }
        return this.name + "=" + this.value;
    }

    public boolean equals(Object obj) {
        char c;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AnnotationMember) {
            AnnotationMember that = (AnnotationMember) obj;
            if (this.name.equals(that.name) && (c = this.tag) == that.tag) {
                if (c == '[') {
                    return equalArrayValue(that.value);
                }
                if (c == '!') {
                    return false;
                }
                return this.value.equals(that.value);
            }
        }
        return false;
    }

    public boolean equalArrayValue(Object otherValue) {
        Object obj = this.value;
        if ((obj instanceof Object[]) && (otherValue instanceof Object[])) {
            return Arrays.equals((Object[]) obj, (Object[]) otherValue);
        }
        Class type = this.value.getClass();
        if (type != otherValue.getClass()) {
            return false;
        }
        if (type == int[].class) {
            return Arrays.equals((int[]) this.value, (int[]) otherValue);
        }
        if (type == byte[].class) {
            return Arrays.equals((byte[]) this.value, (byte[]) otherValue);
        }
        if (type == short[].class) {
            return Arrays.equals((short[]) this.value, (short[]) otherValue);
        }
        if (type == long[].class) {
            return Arrays.equals((long[]) this.value, (long[]) otherValue);
        }
        if (type == char[].class) {
            return Arrays.equals((char[]) this.value, (char[]) otherValue);
        }
        if (type == boolean[].class) {
            return Arrays.equals((boolean[]) this.value, (boolean[]) otherValue);
        }
        if (type == float[].class) {
            return Arrays.equals((float[]) this.value, (float[]) otherValue);
        }
        if (type == double[].class) {
            return Arrays.equals((double[]) this.value, (double[]) otherValue);
        }
        return false;
    }

    public int hashCode() {
        int hash = this.name.hashCode() * 127;
        if (this.tag != '[') {
            return this.value.hashCode() ^ hash;
        }
        Class type = this.value.getClass();
        if (type == int[].class) {
            return Arrays.hashCode((int[]) this.value) ^ hash;
        }
        if (type == byte[].class) {
            return Arrays.hashCode((byte[]) this.value) ^ hash;
        }
        if (type == short[].class) {
            return Arrays.hashCode((short[]) this.value) ^ hash;
        }
        if (type == long[].class) {
            return Arrays.hashCode((long[]) this.value) ^ hash;
        }
        if (type == char[].class) {
            return Arrays.hashCode((char[]) this.value) ^ hash;
        }
        if (type == boolean[].class) {
            return Arrays.hashCode((boolean[]) this.value) ^ hash;
        }
        if (type == float[].class) {
            return Arrays.hashCode((float[]) this.value) ^ hash;
        }
        if (type == double[].class) {
            return Arrays.hashCode((double[]) this.value) ^ hash;
        }
        return Arrays.hashCode((Object[]) this.value) ^ hash;
    }

    /* JADX INFO: Multiple debug info for r0v1 java.lang.Object: [D('error' java.lang.Throwable), D('ecnpe' java.lang.EnumConstantNotPresentException), D('ase' java.lang.ArrayStoreException)] */
    /* JADX INFO: Multiple debug info for r0v2 java.lang.TypeNotPresentException: [D('ecnpe' java.lang.EnumConstantNotPresentException), D('tnpe' java.lang.TypeNotPresentException)] */
    /* JADX INFO: Multiple debug info for r0v3 java.lang.EnumConstantNotPresentException: [D('ase' java.lang.ArrayStoreException), D('ecnpe' java.lang.EnumConstantNotPresentException)] */
    /* JADX INFO: Multiple debug info for r0v4 java.lang.ArrayStoreException: [D('ase' java.lang.ArrayStoreException), D('error' java.lang.Throwable)] */
    public void rethrowError() throws Throwable {
        if (this.tag == '!') {
            Object obj = this.value;
            if (obj instanceof TypeNotPresentException) {
                TypeNotPresentException tnpe = (TypeNotPresentException) obj;
                throw new TypeNotPresentException(tnpe.typeName(), tnpe.getCause());
            } else if (obj instanceof EnumConstantNotPresentException) {
                EnumConstantNotPresentException ecnpe = (EnumConstantNotPresentException) obj;
                throw new EnumConstantNotPresentException(ecnpe.enumType(), ecnpe.constantName());
            } else if (!(obj instanceof ArrayStoreException)) {
                Throwable error = (Throwable) obj;
                StackTraceElement[] ste = error.getStackTrace();
                ByteArrayOutputStream bos = new ByteArrayOutputStream(ste == null ? 512 : (ste.length + 1) * 80);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(error);
                oos.flush();
                oos.close();
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
                Throwable error2 = (Throwable) ois.readObject();
                ois.close();
                throw error2;
            } else {
                throw new ArrayStoreException(((ArrayStoreException) obj).getMessage());
            }
        }
    }

    public Object validateValue() throws Throwable {
        if (this.tag == '!') {
            rethrowError();
        }
        Object obj = this.value;
        if (obj == NO_VALUE) {
            return null;
        }
        if (this.elementType == obj.getClass() || this.elementType.isInstance(this.value)) {
            return copyValue();
        }
        throw new AnnotationTypeMismatchException(this.definingMethod, this.value.getClass().getName());
    }

    public Object copyValue() throws Throwable {
        if (this.tag != '[' || Array.getLength(this.value) == 0) {
            return this.value;
        }
        Class type = this.value.getClass();
        if (type == int[].class) {
            return ((int[]) this.value).clone();
        }
        if (type == byte[].class) {
            return ((byte[]) this.value).clone();
        }
        if (type == short[].class) {
            return ((short[]) this.value).clone();
        }
        if (type == long[].class) {
            return ((long[]) this.value).clone();
        }
        if (type == char[].class) {
            return ((char[]) this.value).clone();
        }
        if (type == boolean[].class) {
            return ((boolean[]) this.value).clone();
        }
        if (type == float[].class) {
            return ((float[]) this.value).clone();
        }
        if (type == double[].class) {
            return ((double[]) this.value).clone();
        }
        return ((Object[]) this.value).clone();
    }
}
