package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

public final class LX implements AbstractC0237hg {
    public final C0232hV A00;

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Type type;
        Type type2 = h6Var.type;
        Class<? super T> cls = h6Var.rawType;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type A03 = C0233hW.A03(type2, cls, Collection.class);
        if (A03 instanceof WildcardType) {
            A03 = ((WildcardType) A03).getUpperBounds()[0];
        }
        if (A03 instanceof ParameterizedType) {
            type = ((ParameterizedType) A03).getActualTypeArguments()[0];
        } else {
            type = Object.class;
        }
        return new LY(hrVar, type, hrVar.A03(new h6<>(type)), this.A00.A00(h6Var));
    }

    public LX(C0232hV hVVar) {
        this.A00 = hVVar;
    }
}
