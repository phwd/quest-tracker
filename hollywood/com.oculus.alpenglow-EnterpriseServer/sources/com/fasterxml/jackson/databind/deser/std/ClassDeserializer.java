package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C07130om;
import X.EnumC05930lf;
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
    public final Class<?> A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_STRING) {
            try {
                return C07130om.A01(r4.A0P().trim());
            } catch (Exception e) {
                e = e;
                Class<?> cls = this._valueClass;
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw r5.A0F(cls, e);
            }
        } else {
            throw r5.A0C(this._valueClass, A0G);
        }
    }
}
