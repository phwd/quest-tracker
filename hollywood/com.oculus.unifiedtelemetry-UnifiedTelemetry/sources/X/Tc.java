package X;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class Tc implements AbstractC0132Os {
    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        Type componentType;
        Type type = lQVar.type;
        if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
            return null;
        }
        if (type instanceof GenericArrayType) {
            componentType = ((GenericArrayType) type).getGenericComponentType();
        } else {
            componentType = ((Class) type).getComponentType();
        }
        return new C0165Tb(hy, hy.A04(new lQ<>(componentType)), SV.A00(componentType));
    }
}
