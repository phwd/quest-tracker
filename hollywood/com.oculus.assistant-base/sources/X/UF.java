package X;

public final class UF {
    public int A00 = 1;
    public C1168uP A01;
    public C1168uP A02;

    public UF(C1168uP uPVar) {
        this.A01 = uPVar;
        this.A02 = uPVar;
        uPVar.A01 = null;
        uPVar.A00 = null;
    }
}
