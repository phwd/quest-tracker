package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class lQ<T> {
    public final int hashCode;
    public final Class<? super T> rawType;
    public final Type type;

    public final boolean equals(Object obj) {
        if (!(obj instanceof lQ) || !SV.A07(this.type, ((lQ) obj).type)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return SV.A01(this.type);
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public lQ() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            Type A02 = SV.A02(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            this.type = A02;
            this.rawType = (Class<? super T>) SV.A00(A02);
            this.hashCode = this.type.hashCode();
            return;
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public lQ(Type type2) {
        if (type2 != null) {
            Type A02 = SV.A02(type2);
            this.type = A02;
            this.rawType = (Class<? super T>) SV.A00(A02);
            this.hashCode = this.type.hashCode();
            return;
        }
        throw null;
    }
}
