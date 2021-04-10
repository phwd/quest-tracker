package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0Fv;
import X.AnonymousClass0jg;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class JacksonDeserializers$TokenBufferDeserializer extends StdScalarDeserializer<AnonymousClass0Fv> {
    public static final JacksonDeserializers$TokenBufferDeserializer A00 = new JacksonDeserializers$TokenBufferDeserializer();

    public JacksonDeserializers$TokenBufferDeserializer() {
        super(AnonymousClass0Fv.class);
    }

    public static final AnonymousClass0Fv A00(AbstractC04100gp r2) throws IOException, AnonymousClass0jg {
        AnonymousClass0Fv r0 = new AnonymousClass0Fv(r2.A0I());
        r0.A08(r2);
        return r0;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return A00(r2);
    }
}
