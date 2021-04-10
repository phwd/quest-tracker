package X;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

public final class LQ implements AbstractC0237hg {
    public final C0232hV A00;

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Type[] typeArr;
        Type type;
        Type type2;
        hh<Boolean> hhVar;
        Type type3 = h6Var.type;
        if (!Map.class.isAssignableFrom(h6Var.rawType)) {
            return null;
        }
        Class<?> A002 = C0233hW.A00(type3);
        if (type3 == Properties.class) {
            typeArr = new Type[2];
            type = String.class;
        } else {
            Type A03 = C0233hW.A03(type3, A002, Map.class);
            if (A03 instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) A03).getActualTypeArguments();
                type2 = typeArr[0];
                if (type2 != Boolean.TYPE || type2 == Boolean.class) {
                    hhVar = hA.A07;
                } else {
                    hhVar = hrVar.A03(new h6<>(type2));
                }
                return new LR(this, hrVar, typeArr[0], hhVar, typeArr[1], hrVar.A03(new h6<>(typeArr[1])), this.A00.A00(h6Var));
            }
            typeArr = new Type[2];
            type = Object.class;
        }
        typeArr[0] = type;
        typeArr[1] = type;
        type2 = typeArr[0];
        if (type2 != Boolean.TYPE) {
        }
        hhVar = hA.A07;
        return new LR(this, hrVar, typeArr[0], hhVar, typeArr[1], hrVar.A03(new h6<>(typeArr[1])), this.A00.A00(h6Var));
    }

    public LQ(C0232hV hVVar) {
        this.A00 = hVVar;
    }
}
