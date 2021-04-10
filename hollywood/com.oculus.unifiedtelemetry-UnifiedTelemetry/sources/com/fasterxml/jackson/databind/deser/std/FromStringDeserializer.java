package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.C0223Wj;
import X.EnumC0470q2;
import X.q0;
import java.io.IOException;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    public static final long serialVersionUID = 1;

    public abstract T A0Q(String str, AbstractC0226Wn wn) throws IOException, q0;

    public T A0P(Object obj, AbstractC0226Wn wn) throws IOException, q0 {
        throw C0223Wj.A00(wn.A00, AnonymousClass06.A06("Don't know how to convert embedded Object of type ", obj.getClass().getName(), " into ", this._valueClass.getName()));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String A0I = ww.A0I();
        if (A0I != null) {
            if (A0I.length() != 0) {
                String trim = A0I.trim();
                if (trim.length() != 0) {
                    try {
                        T A0Q = A0Q(trim, wn);
                        if (A0Q != null) {
                            return A0Q;
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                    throw wn.A0D(trim, this._valueClass, "not a valid textual representation");
                }
            }
        } else if (ww.A0Z() == EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
            T t = (T) ww.A0R();
            if (t != null) {
                if (!this._valueClass.isAssignableFrom(t.getClass())) {
                    return A0P(t, wn);
                }
                return t;
            }
        } else {
            throw wn.A08(this._valueClass);
        }
        return null;
    }

    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }
}
