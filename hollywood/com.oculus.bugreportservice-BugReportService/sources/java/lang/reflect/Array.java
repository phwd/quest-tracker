package java.lang.reflect;

public final class Array {
    private static native Object createObjectArray(Class cls, int i);

    public static Object newInstance(Class cls, int i) {
        return newArray(cls, i);
    }

    private static Object newArray(Class cls, int i) {
        if (!cls.isPrimitive()) {
            return createObjectArray(cls, i);
        }
        if (cls == Character.TYPE) {
            return new char[i];
        }
        if (cls == Integer.TYPE) {
            return new int[i];
        }
        if (cls == Byte.TYPE) {
            return new byte[i];
        }
        if (cls == Boolean.TYPE) {
            return new boolean[i];
        }
        if (cls == Short.TYPE) {
            return new short[i];
        }
        if (cls == Long.TYPE) {
            return new long[i];
        }
        if (cls == Float.TYPE) {
            return new float[i];
        }
        if (cls == Double.TYPE) {
            return new double[i];
        }
        if (cls == Void.TYPE) {
            throw new IllegalArgumentException("Can't allocate an array of void");
        }
        throw new AssertionError();
    }
}
