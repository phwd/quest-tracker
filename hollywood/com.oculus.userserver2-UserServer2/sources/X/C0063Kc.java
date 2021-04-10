package X;

/* renamed from: X.Kc  reason: case insensitive filesystem */
public class C0063Kc implements AbstractC0237hg {
    public final /* synthetic */ hh A00;
    public final /* synthetic */ Class A01;
    public final /* synthetic */ Class A02;

    public C0063Kc(Class cls, Class cls2, hh hhVar) {
        this.A02 = cls;
        this.A01 = cls2;
        this.A00 = hhVar;
    }

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Class<? super T> cls = h6Var.rawType;
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
