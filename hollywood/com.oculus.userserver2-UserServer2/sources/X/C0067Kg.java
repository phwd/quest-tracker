package X;

/* renamed from: X.Kg  reason: case insensitive filesystem */
public class C0067Kg implements AbstractC0237hg {
    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Class<? super T> cls = h6Var.rawType;
        if (!Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
            return null;
        }
        if (!cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        return new KG(cls);
    }
}
