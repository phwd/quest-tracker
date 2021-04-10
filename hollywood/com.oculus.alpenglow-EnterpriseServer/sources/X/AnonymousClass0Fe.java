package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: X.0Fe  reason: invalid class name */
public final class AnonymousClass0Fe<T> {
    public final int A00;
    public final Class<? super T> A01;
    public final Type A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0Fe) || !AnonymousClass0Ch.A07(this.A02, ((AnonymousClass0Fe) obj).A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00;
    }

    public final String toString() {
        return AnonymousClass0Ch.A01(this.A02);
    }

    public AnonymousClass0Fe() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            Type A022 = AnonymousClass0Ch.A02(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            this.A02 = A022;
            this.A01 = (Class<? super T>) AnonymousClass0Ch.A00(A022);
            this.A00 = this.A02.hashCode();
            return;
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public AnonymousClass0Fe(Type type) {
        if (type != null) {
            Type A022 = AnonymousClass0Ch.A02(type);
            this.A02 = A022;
            this.A01 = (Class<? super T>) AnonymousClass0Ch.A00(A022);
            this.A00 = this.A02.hashCode();
            return;
        }
        throw null;
    }
}
