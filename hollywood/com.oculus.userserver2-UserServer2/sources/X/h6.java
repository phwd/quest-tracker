package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class h6<T> {
    public final int hashCode;
    public final Class<? super T> rawType;
    public final Type type;

    public final boolean equals(Object obj) {
        if (!(obj instanceof h6) || !C0233hW.A07(this.type, ((h6) obj).type)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return C0233hW.A01(this.type);
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public h6() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            Type A02 = C0233hW.A02(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            this.type = A02;
            this.rawType = (Class<? super T>) C0233hW.A00(A02);
            this.hashCode = this.type.hashCode();
            return;
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public h6(Type type2) {
        if (type2 != null) {
            Type A02 = C0233hW.A02(type2);
            this.type = A02;
            this.rawType = (Class<? super T>) C0233hW.A00(A02);
            this.hashCode = this.type.hashCode();
            return;
        }
        throw null;
    }
}
