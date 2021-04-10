package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

public final class TV implements AbstractC0132Os {
    public final TW A00;

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        Type type;
        Type type2 = lQVar.type;
        Class<? super T> cls = lQVar.rawType;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type A03 = SV.A03(type2, cls, Collection.class);
        if (A03 instanceof WildcardType) {
            A03 = ((WildcardType) A03).getUpperBounds()[0];
        }
        if (A03 instanceof ParameterizedType) {
            type = ((ParameterizedType) A03).getActualTypeArguments()[0];
        } else {
            type = Object.class;
        }
        return new TZ(hy, type, hy.A04(new lQ<>(type)), this.A00.A00(lQVar));
    }

    public TV(TW tw) {
        this.A00 = tw;
    }
}
