package X;

public class RL implements eJ<T> {
    public final /* synthetic */ Object A00;

    public RL(Object obj) {
        this.A00 = obj;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("of(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.eJ
    public final T get() {
        return (T) this.A00;
    }
}
