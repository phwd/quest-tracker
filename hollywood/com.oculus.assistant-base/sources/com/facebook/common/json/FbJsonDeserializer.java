package com.facebook.common.json;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass08;
import X.CH;
import X.NX;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class FbJsonDeserializer extends JsonDeserializer {
    public Class A00;

    public FbJsonField A0D(String str) {
        return null;
    }

    public final Object A0E() {
        try {
            Constructor declaredConstructor = this.A00.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(new Object[0]);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(AnonymousClass08.A04(this.A00.getName(), " missing default constructor"), e);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        try {
            Object A0E = A0E();
            while (true) {
                NX A0o = qiVar.A0o();
                if (A0o == null) {
                    throw new IOException("Unexpected end of json input");
                } else if (A0o == NX.END_OBJECT) {
                    return A0E;
                } else {
                    if (qiVar.A0U() == NX.FIELD_NAME) {
                        String A0b = qiVar.A0b();
                        qiVar.A0o();
                        FbJsonField A0D = A0D(A0b);
                        if (A0D != null) {
                            A0D.deserialize(A0E, qiVar, qrVar);
                        } else {
                            qiVar.A0T();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            CH.A00(this.A00, qiVar, e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
