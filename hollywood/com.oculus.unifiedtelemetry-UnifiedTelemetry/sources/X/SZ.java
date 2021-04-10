package X;

public class SZ implements AbstractC0132Os {
    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        Class<? super T> cls = lQVar.rawType;
        if (!Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
            return null;
        }
        if (!cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        return new S5(cls);
    }
}
