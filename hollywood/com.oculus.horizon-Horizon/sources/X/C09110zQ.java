package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: X.0zQ  reason: invalid class name and case insensitive filesystem */
public final class C09110zQ<T> {
    public final int A00;
    public final Class<? super T> A01;
    public final Type A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C09110zQ) || !C08910yw.A07(this.A02, ((C09110zQ) obj).A02)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return C08910yw.A01(this.A02);
    }

    public final int hashCode() {
        return this.A00;
    }

    public C09110zQ() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            Type A022 = C08910yw.A02(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            this.A02 = A022;
            this.A01 = (Class<? super T>) C08910yw.A00(A022);
            this.A00 = this.A02.hashCode();
            return;
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public C09110zQ(Type type) {
        if (type != null) {
            Type A022 = C08910yw.A02(type);
            this.A02 = A022;
            this.A01 = (Class<? super T>) C08910yw.A00(A022);
            this.A00 = this.A02.hashCode();
            return;
        }
        throw null;
    }
}
