package X;

/* renamed from: X.Kd  reason: case insensitive filesystem */
public class C0064Kd implements AbstractC0237hg {
    public final /* synthetic */ hh A00;
    public final /* synthetic */ Class A01;

    public C0064Kd(Class cls, hh hhVar) {
        this.A01 = cls;
        this.A00 = hhVar;
    }

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        if (h6Var.rawType == this.A01) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.A01.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
