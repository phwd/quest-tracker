package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Ch;
import X.AnonymousClass0Cp;
import X.AnonymousClass0Fe;
import X.C01220Fb;
import X.C02150Wt;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

public final class MapTypeAdapterFactory implements AnonymousClass0C3 {
    public final AnonymousClass0Cp A00;

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r14, AnonymousClass0Fe<T> r15) {
        Type[] typeArr;
        Type type;
        Type type2;
        AnonymousClass0Bd<Boolean> r9;
        Type type3 = r15.A02;
        if (!Map.class.isAssignableFrom(r15.A01)) {
            return null;
        }
        Class<?> A002 = AnonymousClass0Ch.A00(type3);
        if (type3 == Properties.class) {
            typeArr = new Type[2];
            type = String.class;
        } else {
            Type A03 = AnonymousClass0Ch.A03(type3, A002, Map.class);
            if (A03 instanceof ParameterizedType) {
                typeArr = ((ParameterizedType) A03).getActualTypeArguments();
                type2 = typeArr[0];
                if (type2 != Boolean.TYPE || type2 == Boolean.class) {
                    r9 = C01220Fb.A07;
                } else {
                    r9 = r14.A07(new AnonymousClass0Fe<>(type2));
                }
                return new C02150Wt(this, r14, typeArr[0], r9, typeArr[1], r14.A07(new AnonymousClass0Fe<>(typeArr[1])), this.A00.A00(r15));
            }
            typeArr = new Type[2];
            type = Object.class;
        }
        typeArr[0] = type;
        typeArr[1] = type;
        type2 = typeArr[0];
        if (type2 != Boolean.TYPE) {
        }
        r9 = C01220Fb.A07;
        return new C02150Wt(this, r14, typeArr[0], r9, typeArr[1], r14.A07(new AnonymousClass0Fe<>(typeArr[1])), this.A00.A00(r15));
    }

    public MapTypeAdapterFactory(AnonymousClass0Cp r1) {
        this.A00 = r1;
    }
}
