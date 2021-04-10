package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

public final class TB implements AbstractC0132Os {
    public final TW A00;

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        Type[] typeArr;
        Type type;
        Type type2;
        AbstractC0131Ob<Boolean> ob;
        Type type3 = lQVar.type;
        if (!Map.class.isAssignableFrom(lQVar.rawType)) {
            return null;
        }
        Class<?> A002 = SV.A00(type3);
        if (type3 == Properties.class) {
            typeArr = new Type[2];
            type = String.class;
        } else {
            Type A03 = SV.A03(type3, A002, Map.class);
            if (A03 instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) A03).getActualTypeArguments();
                type2 = typeArr[0];
                if (type2 != Boolean.TYPE || type2 == Boolean.class) {
                    ob = C0433hz.A07;
                } else {
                    ob = hy.A04(new lQ<>(type2));
                }
                return new TD(this, hy, typeArr[0], ob, typeArr[1], hy.A04(new lQ<>(typeArr[1])), this.A00.A00(lQVar));
            }
            typeArr = new Type[2];
            type = Object.class;
        }
        typeArr[0] = type;
        typeArr[1] = type;
        type2 = typeArr[0];
        if (type2 != Boolean.TYPE) {
        }
        ob = C0433hz.A07;
        return new TD(this, hy, typeArr[0], ob, typeArr[1], hy.A04(new lQ<>(typeArr[1])), this.A00.A00(lQVar));
    }

    public TB(TW tw) {
        this.A00 = tw;
    }
}
