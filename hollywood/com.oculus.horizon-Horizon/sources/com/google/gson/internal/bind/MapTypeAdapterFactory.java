package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C01690Wr;
import X.C08780ya;
import X.C08910yw;
import X.C08920yx;
import X.C09080zN;
import X.C09110zQ;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

public final class MapTypeAdapterFactory implements AbstractC08860ym {
    public final C08920yx A00;

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r14, C09110zQ<T> r15) {
        Type[] typeArr;
        Type type;
        Type type2;
        AnonymousClass0yl<Boolean> r9;
        Type type3 = r15.A02;
        if (!Map.class.isAssignableFrom(r15.A01)) {
            return null;
        }
        Class<?> A002 = C08910yw.A00(type3);
        if (type3 == Properties.class) {
            typeArr = new Type[2];
            type = String.class;
        } else {
            Type A03 = C08910yw.A03(type3, A002, Map.class);
            if (A03 instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) A03).getActualTypeArguments();
                type2 = typeArr[0];
                if (type2 != Boolean.TYPE || type2 == Boolean.class) {
                    r9 = C09080zN.A07;
                } else {
                    r9 = r14.A04(new C09110zQ<>(type2));
                }
                return new C01690Wr(this, r14, typeArr[0], r9, typeArr[1], r14.A04(new C09110zQ<>(typeArr[1])), this.A00.A00(r15));
            }
            typeArr = new Type[2];
            type = Object.class;
        }
        typeArr[0] = type;
        typeArr[1] = type;
        type2 = typeArr[0];
        if (type2 != Boolean.TYPE) {
        }
        r9 = C09080zN.A07;
        return new C01690Wr(this, r14, typeArr[0], r9, typeArr[1], r14.A04(new C09110zQ<>(typeArr[1])), this.A00.A00(r15));
    }

    public MapTypeAdapterFactory(C08920yx r1) {
        this.A00 = r1;
    }
}
