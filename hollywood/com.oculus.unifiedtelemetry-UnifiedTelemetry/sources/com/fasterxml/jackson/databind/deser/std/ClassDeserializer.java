package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.Mv;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
    public static final ClassDeserializer A00 = new ClassDeserializer();
    public static final long serialVersionUID = 1;

    public ClassDeserializer() {
        super(Class.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Class<?> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            try {
                return Mv.A01(ww.A0d().trim());
            } catch (Exception e) {
                e = e;
                Class<?> cls = this._valueClass;
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw wn.A0C(cls, e);
            }
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
    }
}
