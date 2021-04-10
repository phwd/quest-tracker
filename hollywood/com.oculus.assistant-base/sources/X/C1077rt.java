package X;

/* renamed from: X.rt  reason: case insensitive filesystem */
public final class C1077rt extends QC {
    public final QC A00;
    public final QC A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("[ChainedTransformer(");
        sb.append(this.A00);
        sb.append(", ");
        sb.append(this.A01);
        sb.append(")]");
        return sb.toString();
    }

    public C1077rt(QC qc, QC qc2) {
        this.A00 = qc;
        this.A01 = qc2;
    }
}
