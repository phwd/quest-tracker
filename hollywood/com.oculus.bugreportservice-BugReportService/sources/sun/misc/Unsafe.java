package sun.misc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.reflect.Reflection;

public final class Unsafe {
    private static final Unsafe THE_ONE = new Unsafe();
    private static final Unsafe theUnsafe = THE_ONE;

    private static native int getArrayBaseOffsetForComponentType(Class cls);

    private static native int getArrayIndexScaleForComponentType(Class cls);

    public native boolean compareAndSwapInt(Object obj, long j, int i, int i2);

    public native boolean compareAndSwapLong(Object obj, long j, long j2, long j3);

    public native boolean compareAndSwapObject(Object obj, long j, Object obj2, Object obj3);

    public native void fullFence();

    public native int getInt(Object obj, long j);

    public native int getIntVolatile(Object obj, long j);

    public native long getLong(Object obj, long j);

    public native long getLongVolatile(Object obj, long j);

    public native Object getObject(Object obj, long j);

    public native Object getObjectVolatile(Object obj, long j);

    public native void park(boolean z, long j);

    public native void putInt(Object obj, long j, int i);

    public native void putLong(Object obj, long j, long j2);

    public native void putLongVolatile(Object obj, long j, long j2);

    public native void putObject(Object obj, long j, Object obj2);

    public native void putObjectVolatile(Object obj, long j, Object obj2);

    public native void putOrderedInt(Object obj, long j, int i);

    public native void putOrderedObject(Object obj, long j, Object obj2);

    public native void storeFence();

    public native void unpark(Object obj);

    private Unsafe() {
    }

    public static Unsafe getUnsafe() {
        ClassLoader classLoader;
        Class callerClass = Reflection.getCallerClass();
        if (callerClass == null) {
            classLoader = null;
        } else {
            classLoader = callerClass.getClassLoader();
        }
        if (classLoader == null || classLoader == Unsafe.class.getClassLoader()) {
            return THE_ONE;
        }
        throw new SecurityException("Unsafe access denied");
    }

    public long objectFieldOffset(Field field) {
        if (!Modifier.isStatic(field.getModifiers())) {
            return (long) field.getOffset();
        }
        throw new IllegalArgumentException("valid for instance fields only");
    }

    public int arrayBaseOffset(Class cls) {
        Class componentType = cls.getComponentType();
        if (componentType != null) {
            return getArrayBaseOffsetForComponentType(componentType);
        }
        throw new IllegalArgumentException("Valid for array classes only: " + cls);
    }

    public int arrayIndexScale(Class cls) {
        Class componentType = cls.getComponentType();
        if (componentType != null) {
            return getArrayIndexScaleForComponentType(componentType);
        }
        throw new IllegalArgumentException("Valid for array classes only: " + cls);
    }

    public final int getAndAddInt(Object obj, long j, int i) {
        int intVolatile;
        do {
            intVolatile = getIntVolatile(obj, j);
        } while (!compareAndSwapInt(obj, j, intVolatile, intVolatile + i));
        return intVolatile;
    }

    public final long getAndAddLong(Object obj, long j, long j2) {
        long longVolatile;
        do {
            longVolatile = getLongVolatile(obj, j);
        } while (!compareAndSwapLong(obj, j, longVolatile, longVolatile + j2));
        return longVolatile;
    }

    public final int getAndSetInt(Object obj, long j, int i) {
        int intVolatile;
        do {
            intVolatile = getIntVolatile(obj, j);
        } while (!compareAndSwapInt(obj, j, intVolatile, i));
        return intVolatile;
    }

    public final Object getAndSetObject(Object obj, long j, Object obj2) {
        Object objectVolatile;
        do {
            objectVolatile = getObjectVolatile(obj, j);
        } while (!compareAndSwapObject(obj, j, objectVolatile, obj2));
        return objectVolatile;
    }
}
