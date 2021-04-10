package java.lang;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import libcore.util.BasicLruCache;

public abstract class Enum implements Comparable, Serializable {
    private static final BasicLruCache sharedConstantsCache = new BasicLruCache(64) {
        /* class java.lang.Enum.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public Object[] create(Class cls) {
            return Enum.enumValues(cls);
        }
    };
    private final String name;
    private final int ordinal;

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final String name() {
        return this.name;
    }

    public final int ordinal() {
        return this.ordinal;
    }

    protected Enum(String str, int i) {
        this.name = str;
        this.ordinal = i;
    }

    public String toString() {
        return this.name;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: protected */
    public final Object clone() {
        throw new CloneNotSupportedException();
    }

    public final int compareTo(Enum r3) {
        if (getClass() == r3.getClass() || getDeclaringClass() == r3.getDeclaringClass()) {
            return this.ordinal - r3.ordinal;
        }
        throw new ClassCastException();
    }

    public final Class getDeclaringClass() {
        Class cls = getClass();
        Class superclass = cls.getSuperclass();
        return superclass == Enum.class ? cls : superclass;
    }

    public static Enum valueOf(Class cls, String str) {
        Objects.requireNonNull(cls, "enumType == null");
        Objects.requireNonNull(cls, "name == null");
        Enum[] sharedConstants = getSharedConstants(cls);
        if (sharedConstants != null) {
            for (int length = sharedConstants.length - 1; length >= 0; length--) {
                Enum r2 = sharedConstants[length];
                if (str.equals(r2.name())) {
                    return r2;
                }
            }
            throw new IllegalArgumentException("No enum constant " + cls.getCanonicalName() + "." + str);
        }
        throw new IllegalArgumentException(cls.toString() + " is not an enum type.");
    }

    /* access modifiers changed from: private */
    public static Object[] enumValues(Class cls) {
        if (!cls.isEnum()) {
            return null;
        }
        try {
            return (Object[]) cls.getDeclaredMethod("values", new Class[0]).invoke(null, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Enum[] getSharedConstants(Class cls) {
        return (Enum[]) sharedConstantsCache.get(cls);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("can't deserialize enum");
    }
}
