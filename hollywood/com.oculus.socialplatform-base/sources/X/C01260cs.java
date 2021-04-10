package X;

import javax.inject.Provider;

/* renamed from: X.0cs  reason: invalid class name and case insensitive filesystem */
public class C01260cs implements Provider<T> {
    public final /* synthetic */ Object A00;

    public C01260cs(Object obj) {
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
