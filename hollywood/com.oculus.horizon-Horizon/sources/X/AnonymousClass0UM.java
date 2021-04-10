package X;

import javax.inject.Provider;

/* renamed from: X.0UM  reason: invalid class name */
public class AnonymousClass0UM implements Provider<T> {
    public final /* synthetic */ Object A00;

    public AnonymousClass0UM(Object obj) {
        this.A00 = obj;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("of(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // javax.inject.Provider
    public final T get() {
        return (T) this.A00;
    }
}
