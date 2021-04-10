package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass0OD;
import X.C03620oC;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class JacksonDeserializers$TokenBufferDeserializer extends StdScalarDeserializer<AnonymousClass0OD> {
    public static final JacksonDeserializers$TokenBufferDeserializer A00 = new JacksonDeserializers$TokenBufferDeserializer();

    public JacksonDeserializers$TokenBufferDeserializer() {
        super(AnonymousClass0OD.class);
    }

    public static final AnonymousClass0OD A00(AbstractC02280iQ r2) throws IOException, C03620oC {
        AnonymousClass0OD r0 = new AnonymousClass0OD(r2.A0N());
        r0.A0c(r2);
        return r0;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        return A00(r2);
    }
}
