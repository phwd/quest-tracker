package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: X.14H  reason: invalid class name */
public final class AnonymousClass14H<T> {
    public final int A00;
    public final Class<? super T> A01;
    public final Type A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass14H) || !AnonymousClass13j.A07(this.A02, ((AnonymousClass14H) obj).A02)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return AnonymousClass13j.A01(this.A02);
    }

    public final int hashCode() {
        return this.A00;
    }

    public AnonymousClass14H() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            Type A022 = AnonymousClass13j.A02(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            this.A02 = A022;
            this.A01 = (Class<? super T>) AnonymousClass13j.A00(A022);
            this.A00 = this.A02.hashCode();
            return;
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public AnonymousClass14H(Type type) {
        if (type != null) {
            Type A022 = AnonymousClass13j.A02(type);
            this.A02 = A022;
            this.A01 = (Class<? super T>) AnonymousClass13j.A00(A022);
            this.A00 = this.A02.hashCode();
            return;
        }
        throw null;
    }
}
