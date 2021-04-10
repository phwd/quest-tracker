package X;

public class SR extends Pa {
    @Override // X.Pa
    public final void destruct() {
        throw new IllegalStateException("Cannot destroy Terminus Destructor.");
    }
}
