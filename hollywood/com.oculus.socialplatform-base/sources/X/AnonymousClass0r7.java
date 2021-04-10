package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: X.0r7  reason: invalid class name */
public final class AnonymousClass0r7 {
    public AnonymousClass0r7 A00;
    public AnonymousClass0r7 A01;
    public final Class<?> A02;
    public final ParameterizedType A03;
    public final Type A04;

    public final AnonymousClass0r7 A00() {
        AnonymousClass0r7 A002;
        AnonymousClass0r7 r0 = this.A01;
        if (r0 == null) {
            A002 = null;
        } else {
            A002 = r0.A00();
        }
        AnonymousClass0r7 r02 = new AnonymousClass0r7(this.A04, this.A02, this.A03, A002);
        if (A002 != null) {
            A002.A00 = r02;
        }
        return r02;
    }

    public final String toString() {
        ParameterizedType parameterizedType = this.A03;
        if (parameterizedType != null) {
            return parameterizedType.toString();
        }
        return this.A02.getName();
    }

    public AnonymousClass0r7(Type type) {
        this.A04 = type;
        if (type instanceof Class) {
            this.A02 = (Class) type;
            this.A03 = null;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.A03 = parameterizedType;
            this.A02 = (Class) parameterizedType.getRawType();
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A09("Type ", type.getClass().getName(), " can not be used to construct HierarchicType"));
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/reflect/Type;Ljava/lang/Class<*>;Ljava/lang/reflect/ParameterizedType;LX/0r7;LX/0r7;)V */
    public AnonymousClass0r7(Type type, Class cls, ParameterizedType parameterizedType, AnonymousClass0r7 r5) {
        this.A04 = type;
        this.A02 = cls;
        this.A03 = parameterizedType;
        this.A01 = r5;
        this.A00 = null;
    }
}
