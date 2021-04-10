package java.util;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public abstract class EnumSet extends AbstractSet implements Cloneable, Serializable {
    private static Enum[] ZERO_LENGTH_ENUM_ARRAY = new Enum[0];
    final Class elementType;
    final Enum[] universe;

    EnumSet(Class cls, Enum[] enumArr) {
        this.elementType = cls;
        this.universe = enumArr;
    }

    public static EnumSet noneOf(Class cls) {
        Enum[] universe2 = getUniverse(cls);
        if (universe2 == null) {
            throw new ClassCastException(cls + " not an enum");
        } else if (universe2.length <= 64) {
            return new RegularEnumSet(cls, universe2);
        } else {
            return new JumboEnumSet(cls, universe2);
        }
    }

    public static EnumSet of(Enum r1) {
        EnumSet noneOf = noneOf(r1.getDeclaringClass());
        noneOf.add(r1);
        return noneOf;
    }

    public static EnumSet of(Enum r1, Enum r2) {
        EnumSet noneOf = noneOf(r1.getDeclaringClass());
        noneOf.add(r1);
        noneOf.add(r2);
        return noneOf;
    }

    public static EnumSet of(Enum r1, Enum r2, Enum r3) {
        EnumSet noneOf = noneOf(r1.getDeclaringClass());
        noneOf.add(r1);
        noneOf.add(r2);
        noneOf.add(r3);
        return noneOf;
    }

    public static EnumSet of(Enum r3, Enum... enumArr) {
        EnumSet noneOf = noneOf(r3.getDeclaringClass());
        noneOf.add(r3);
        for (Enum r2 : enumArr) {
            noneOf.add(r2);
        }
        return noneOf;
    }

    public EnumSet clone() {
        try {
            return (EnumSet) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void typeCheck(Enum r3) {
        Class cls = r3.getClass();
        if (cls != this.elementType && cls.getSuperclass() != this.elementType) {
            throw new ClassCastException(cls + " != " + this.elementType);
        }
    }

    private static Enum[] getUniverse(Class cls) {
        return (Enum[]) cls.getEnumConstantsShared();
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 362491234563181265L;
        private final Class elementType;
        private final Enum[] elements;

        SerializationProxy(EnumSet enumSet) {
            this.elementType = enumSet.elementType;
            this.elements = (Enum[]) enumSet.toArray(EnumSet.ZERO_LENGTH_ENUM_ARRAY);
        }

        private Object readResolve() {
            EnumSet noneOf = EnumSet.noneOf(this.elementType);
            for (Enum r3 : this.elements) {
                noneOf.add(r3);
            }
            return noneOf;
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Proxy required");
    }
}
