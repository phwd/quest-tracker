package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class OB {
    public OB A00;
    public OB A01;
    public final Class<?> A02;
    public final ParameterizedType A03;
    public final Type A04;

    public final OB A00() {
        OB A002;
        OB ob = this.A01;
        if (ob == null) {
            A002 = null;
        } else {
            A002 = ob.A00();
        }
        OB ob2 = new OB(this.A04, this.A02, this.A03, A002);
        if (A002 != null) {
            A002.A00 = ob2;
        }
        return ob2;
    }

    public final String toString() {
        ParameterizedType parameterizedType = this.A03;
        if (parameterizedType != null) {
            return parameterizedType.toString();
        }
        return this.A02.getName();
    }

    public OB(Type type) {
        this.A04 = type;
        if (type instanceof Class) {
            this.A02 = (Class) type;
            this.A03 = null;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.A03 = parameterizedType;
            this.A02 = (Class) parameterizedType.getRawType();
        } else {
            throw new IllegalArgumentException(AnonymousClass06.A05("Type ", type.getClass().getName(), " can not be used to construct HierarchicType"));
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/reflect/Type;Ljava/lang/Class<*>;Ljava/lang/reflect/ParameterizedType;LX/OB;LX/OB;)V */
    public OB(Type type, Class cls, ParameterizedType parameterizedType, OB ob) {
        this.A04 = type;
        this.A02 = cls;
        this.A03 = parameterizedType;
        this.A01 = ob;
        this.A00 = null;
    }
}
