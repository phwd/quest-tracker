package X;

public class SH implements AbstractC0132Os {
    public final /* synthetic */ AbstractC0131Ob A00;
    public final /* synthetic */ Class A01;

    public SH(Class cls, AbstractC0131Ob ob) {
        this.A01 = cls;
        this.A00 = ob;
    }

    @Override // X.AbstractC0132Os
    public final <T2> AbstractC0131Ob<T2> A1e(HY hy, lQ<T2> lQVar) {
        Class<? super T> cls = lQVar.rawType;
        if (!this.A01.isAssignableFrom(cls)) {
            return null;
        }
        return new SI(this, cls);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[typeHierarchy=");
        sb.append(this.A01.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
