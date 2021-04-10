package X;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.La  reason: case insensitive filesystem */
public class C0079La implements AbstractC0237hg {
    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Type componentType;
        Type type = h6Var.type;
        if (!(type instanceof GenericArrayType) && (!(type instanceof Class) || !((Class) type).isArray())) {
            return null;
        }
        if (type instanceof GenericArrayType) {
            componentType = ((GenericArrayType) type).getGenericComponentType();
        } else {
            componentType = ((Class) type).getComponentType();
        }
        return new LZ(hrVar, hrVar.A03(new h6<>(componentType)), C0233hW.A00(componentType));
    }
}
