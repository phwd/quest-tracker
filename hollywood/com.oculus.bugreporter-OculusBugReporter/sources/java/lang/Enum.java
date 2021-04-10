package java.lang;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.Enum;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import libcore.util.BasicLruCache;

public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {
    private static final BasicLruCache<Class<? extends Enum>, Object[]> sharedConstantsCache = new BasicLruCache<Class<? extends Enum>, Object[]>(64) {
        /* class java.lang.Enum.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public Object[] create(Class<? extends Enum> enumType) {
            return Enum.enumValues(enumType);
        }
    };
    private final String name;
    private final int ordinal;

    public final String name() {
        return this.name;
    }

    public final int ordinal() {
        return this.ordinal;
    }

    protected Enum(String name2, int ordinal2) {
        this.name = name2;
        this.ordinal = ordinal2;
    }

    public String toString() {
        return this.name;
    }

    public final boolean equals(Object other) {
        return this == other;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: protected */
    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public final int compareTo(E o) {
        if (getClass() == o.getClass() || getDeclaringClass() == o.getDeclaringClass()) {
            return this.ordinal - o.ordinal;
        }
        throw new ClassCastException();
    }

    /* JADX DEBUG: Type inference failed for r1v0. Raw type applied. Possible types: java.lang.Class<? super E extends java.lang.Enum<E>>, java.lang.Class<E extends java.lang.Enum<E>> */
    public final Class<E> getDeclaringClass() {
        Class<E> cls = (Class<E>) getClass();
        Class superclass = cls.getSuperclass();
        return superclass == Enum.class ? cls : superclass;
    }

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name2) {
        Objects.requireNonNull(enumType, "enumType == null");
        Objects.requireNonNull(enumType, "name == null");
        Enum[] sharedConstants = getSharedConstants(enumType);
        if (sharedConstants != null) {
            for (int i = sharedConstants.length - 1; i >= 0; i--) {
                T value = (T) sharedConstants[i];
                if (name2.equals(value.name())) {
                    return value;
                }
            }
            throw new IllegalArgumentException("No enum constant " + enumType.getCanonicalName() + "." + name2);
        }
        throw new IllegalArgumentException(enumType.toString() + " is not an enum type.");
    }

    /* access modifiers changed from: private */
    public static Object[] enumValues(Class<? extends Enum> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }
        try {
            return (Object[]) clazz.getDeclaredMethod("values", new Class[0]).invoke(null, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Enum<T>> T[] getSharedConstants(Class<T> enumType) {
        return (T[]) ((Enum[]) sharedConstantsCache.get(enumType));
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new InvalidObjectException("can't deserialize enum");
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("can't deserialize enum");
    }
}
