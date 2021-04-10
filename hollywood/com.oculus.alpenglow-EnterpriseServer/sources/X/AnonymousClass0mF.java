package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: X.0mF  reason: invalid class name */
public abstract class AnonymousClass0mF<T> implements Comparable<AnonymousClass0mF<T>> {
    public final Type A00;

    public AnonymousClass0mF() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            this.A00 = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            return;
        }
        throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return 0;
    }
}
