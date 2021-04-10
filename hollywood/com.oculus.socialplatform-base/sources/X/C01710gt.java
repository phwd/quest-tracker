package X;

/* renamed from: X.0gt  reason: invalid class name and case insensitive filesystem */
public class C01710gt extends AbstractC04870rR {
    public final AbstractC04870rR A00;
    public final AbstractC04870rR A01;

    @Override // X.AbstractC04870rR
    public final String A00(String str) {
        return this.A00.A00(this.A01.A00(str));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[ChainedTransformer(");
        sb.append(this.A00);
        sb.append(", ");
        sb.append(this.A01);
        sb.append(")]");
        return sb.toString();
    }

    public C01710gt(AbstractC04870rR r1, AbstractC04870rR r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
