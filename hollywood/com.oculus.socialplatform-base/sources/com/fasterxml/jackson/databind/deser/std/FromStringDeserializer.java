package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass006;
import X.C02180iD;
import X.C03620oC;
import X.EnumC03640oE;
import java.io.IOException;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    public static final long serialVersionUID = 1;

    public abstract T A0Q(String str, AbstractC02210iH v) throws IOException, C03620oC;

    public T A0P(Object obj, AbstractC02210iH r6) throws IOException, C03620oC {
        throw C02180iD.A00(r6.A00, AnonymousClass006.A0B("Don't know how to convert embedded Object of type ", obj.getClass().getName(), " into ", this._valueClass.getName()));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A0A(AbstractC02280iQ r5, AbstractC02210iH r6) throws IOException, C03620oC {
        String A0P = r5.A0P();
        if (A0P != null) {
            if (A0P.length() != 0) {
                String trim = A0P.trim();
                if (trim.length() != 0) {
                    try {
                        T A0Q = A0Q(trim, r6);
                        if (A0Q != null) {
                            return A0Q;
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                    throw r6.A0G(trim, this._valueClass, "not a valid textual representation");
                }
            }
        } else if (r5.A0i() == EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
            T t = (T) r5.A0Z();
            if (t != null) {
                if (!this._valueClass.isAssignableFrom(t.getClass())) {
                    return A0P(t, r6);
                }
                return t;
            }
        } else {
            throw r6.A0B(this._valueClass);
        }
        return null;
    }

    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }
}
