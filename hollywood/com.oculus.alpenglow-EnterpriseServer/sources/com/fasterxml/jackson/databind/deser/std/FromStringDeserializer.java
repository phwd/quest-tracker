package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass006;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.EnumC05930lf;
import java.io.IOException;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    public static final long serialVersionUID = 1;

    public abstract T A0Q(String str, AbstractC02570aK v) throws IOException, C05910ld;

    public T A0P(Object obj, AbstractC02570aK r6) throws IOException, C05910ld {
        throw AnonymousClass0aG.A00(r6.A00, AnonymousClass006.A08("Don't know how to convert embedded Object of type ", obj.getClass().getName(), " into ", this._valueClass.getName()));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AnonymousClass0aT r5, AbstractC02570aK r6) throws IOException, C05910ld {
        String A0N = r5.A0N();
        if (A0N != null) {
            if (A0N.length() != 0) {
                String trim = A0N.trim();
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
        } else if (r5.A0G() == EnumC05930lf.VALUE_EMBEDDED_OBJECT) {
            T t = (T) r5.A0M();
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
