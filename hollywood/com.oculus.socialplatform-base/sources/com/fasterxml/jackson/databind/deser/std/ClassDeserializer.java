package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.C04810rI;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
    public static final ClassDeserializer A00 = new ClassDeserializer();
    public static final long serialVersionUID = 1;

    public ClassDeserializer() {
        super(Class.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Class<?> A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_STRING) {
            try {
                return C04810rI.A01(r4.A0m().trim());
            } catch (Exception e) {
                e = e;
                Class<?> cls = this._valueClass;
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw r5.A0F(cls, e);
            }
        } else {
            throw r5.A0C(this._valueClass, A0i);
        }
    }
}
