package com.fasterxml.jackson.databind.deser.std;

import X.A3;
import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class JacksonDeserializers$TokenBufferDeserializer extends StdScalarDeserializer<A3> {
    public static final JacksonDeserializers$TokenBufferDeserializer A00 = new JacksonDeserializers$TokenBufferDeserializer();

    public static final A3 A00(Rn rn) throws IOException, AnonymousClass9r {
        A3 a3 = new A3(null);
        a3.A07(rn);
        return a3;
    }

    public JacksonDeserializers$TokenBufferDeserializer() {
        super(A3.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }
}
