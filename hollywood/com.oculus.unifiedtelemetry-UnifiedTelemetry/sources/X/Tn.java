package X;

import java.util.ArrayDeque;

public class Tn implements VE<T> {
    public final /* synthetic */ TW A00;

    public Tn(TW tw) {
        this.A00 = tw;
    }

    @Override // X.VE
    public final T A1b() {
        return (T) new ArrayDeque();
    }
}
