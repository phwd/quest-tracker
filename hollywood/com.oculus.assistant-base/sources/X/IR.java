package X;

public final class IR extends AbstractC1094sK {
    public final Qz A00;
    public final R1 A01;
    public final Sl A02;

    public IR(R1 r1, Sl sl, Qz qz) {
        super(2);
        this.A02 = sl;
        this.A01 = r1;
        this.A00 = qz;
        if (r1.A00) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }
}
