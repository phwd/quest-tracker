package com.google.gson.internal.bind;

import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass13j;
import X.AnonymousClass13k;
import X.AnonymousClass14E;
import X.AnonymousClass14H;
import X.C01400du;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

public final class MapTypeAdapterFactory implements AnonymousClass13Z {
    public final AnonymousClass13k A00;

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r14, AnonymousClass14H<T> r15) {
        Type[] typeArr;
        Type type;
        Type type2;
        AnonymousClass13Y<Boolean> r9;
        Type type3 = r15.A02;
        if (!Map.class.isAssignableFrom(r15.A01)) {
            return null;
        }
        Class<?> A002 = AnonymousClass13j.A00(type3);
        if (type3 == Properties.class) {
            typeArr = new Type[2];
            type = String.class;
        } else {
            Type A03 = AnonymousClass13j.A03(type3, A002, Map.class);
            if (A03 instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) A03).getActualTypeArguments();
                type2 = typeArr[0];
                if (type2 != Boolean.TYPE || type2 == Boolean.class) {
                    r9 = AnonymousClass14E.A07;
                } else {
                    r9 = r14.A03(new AnonymousClass14H<>(type2));
                }
                return new C01400du(this, r14, typeArr[0], r9, typeArr[1], r14.A03(new AnonymousClass14H<>(typeArr[1])), this.A00.A00(r15));
            }
            typeArr = new Type[2];
            type = Object.class;
        }
        typeArr[0] = type;
        typeArr[1] = type;
        type2 = typeArr[0];
        if (type2 != Boolean.TYPE) {
        }
        r9 = AnonymousClass14E.A07;
        return new C01400du(this, r14, typeArr[0], r9, typeArr[1], r14.A03(new AnonymousClass14H<>(typeArr[1])), this.A00.A00(r15));
    }

    public MapTypeAdapterFactory(AnonymousClass13k r1) {
        this.A00 = r1;
    }
}
