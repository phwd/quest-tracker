package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.C06330mu;
import X.EnumC04820ji;
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
    public final Class<?> A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_STRING) {
            try {
                return C06330mu.A01(r4.A0e().trim());
            } catch (Exception e) {
                e = e;
                Class<?> cls = this._valueClass;
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw r5.A09(cls, e);
            }
        } else {
            throw r5.A07(this._valueClass, A0a);
        }
    }
}
