package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: X.Pu  reason: case insensitive filesystem */
public final class C0298Pu {
    public C0298Pu A00;
    public C0298Pu A01;
    public final Class A02;
    public final ParameterizedType A03;
    public final Type A04;

    public final C0298Pu A00() {
        C0298Pu A002;
        C0298Pu pu = this.A01;
        if (pu == null) {
            A002 = null;
        } else {
            A002 = pu.A00();
        }
        C0298Pu pu2 = new C0298Pu(this.A04, this.A02, this.A03, A002);
        if (A002 != null) {
            A002.A00 = pu2;
        }
        return pu2;
    }

    public final String toString() {
        ParameterizedType parameterizedType = this.A03;
        if (parameterizedType != null) {
            return parameterizedType.toString();
        }
        return this.A02.getName();
    }

    public C0298Pu(Type type) {
        this.A04 = type;
        if (type instanceof Class) {
            this.A02 = (Class) type;
            this.A03 = null;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.A03 = parameterizedType;
            this.A02 = (Class) parameterizedType.getRawType();
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A05("Type ", type.getClass().getName(), " can not be used to construct HierarchicType"));
        }
    }

    public C0298Pu(Type type, Class cls, ParameterizedType parameterizedType, C0298Pu pu) {
        this.A04 = type;
        this.A02 = cls;
        this.A03 = parameterizedType;
        this.A01 = pu;
        this.A00 = null;
    }
}
