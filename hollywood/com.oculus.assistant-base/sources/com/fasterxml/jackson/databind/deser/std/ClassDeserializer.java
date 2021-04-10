package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import X.Q5;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class ClassDeserializer extends StdScalarDeserializer {
    public static final ClassDeserializer A00 = new ClassDeserializer();
    public static final long serialVersionUID = 1;

    public ClassDeserializer() {
        super(Class.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Class A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_STRING) {
            try {
                return Q5.A01(qiVar.A0p().trim());
            } catch (Exception e) {
                e = e;
                Class cls = this._valueClass;
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw qrVar.A0C(cls, e);
            }
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
    }
}
