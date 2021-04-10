package X;

public class KZ implements AbstractC0237hg {
    public final /* synthetic */ Class A00;
    public final /* synthetic */ hh A01;

    public KZ(Class cls, hh hhVar) {
        this.A00 = cls;
        this.A01 = hhVar;
    }

    @Override // X.AbstractC0237hg
    public final <T2> hh<T2> A1F(C0246hr hrVar, h6<T2> h6Var) {
        Class<? super T> cls = h6Var.rawType;
        if (!this.A00.isAssignableFrom(cls)) {
            return null;
        }
        return new C0061Ka(this, cls);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[typeHierarchy=");
        sb.append(this.A00.getName());
        sb.append(",adapter=");
        sb.append(this.A01);
        sb.append("]");
        return sb.toString();
    }
}
