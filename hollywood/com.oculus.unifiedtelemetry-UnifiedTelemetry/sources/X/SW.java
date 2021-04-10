package X;

public class SW implements AbstractC0132Os {
    public final /* synthetic */ AbstractC0131Ob A00;
    public final /* synthetic */ Class A01;
    public final /* synthetic */ Class A02;

    public SW(Class cls, Class cls2, AbstractC0131Ob ob) {
        this.A02 = cls;
        this.A01 = cls2;
        this.A00 = ob;
    }

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        Class<? super T> cls = lQVar.rawType;
        if (cls == this.A02 || cls == this.A01) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.A01.getName());
        sb.append("+");
        sb.append(this.A02.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
