package X;

public class K4 implements AbstractC0192Xx<T> {
    public final /* synthetic */ Object A00;

    public K4(Object obj) {
        this.A00 = obj;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("of(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0192Xx
    public final T get() {
        return (T) this.A00;
    }
}
