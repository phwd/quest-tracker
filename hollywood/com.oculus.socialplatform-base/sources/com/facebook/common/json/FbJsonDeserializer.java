package com.facebook.common.json;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass006;
import X.AnonymousClass0It;
import X.C00780Ix;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class FbJsonDeserializer extends JsonDeserializer<Object> {
    public Class<?> A00;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    public final Object A0F() throws Exception {
        try {
            Constructor<?> declaredConstructor = this.A00.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(new Object[0]);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(AnonymousClass006.A07(this.A00.getName(), " missing default constructor"), e);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException {
        try {
            Object A0F = A0F();
            while (AnonymousClass0It.A00(r4) != EnumC03640oE.END_OBJECT) {
                if (r4.A0i() == EnumC03640oE.FIELD_NAME) {
                    r4.A0j();
                    r4.A0h();
                }
            }
            return A0F;
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            C00780Ix.A00(this.A00, r4, e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
