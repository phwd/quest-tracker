package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.Br;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class JacksonDeserializers$TokenBufferDeserializer extends StdScalarDeserializer<Br> {
    public static final JacksonDeserializers$TokenBufferDeserializer A00 = new JacksonDeserializers$TokenBufferDeserializer();

    public JacksonDeserializers$TokenBufferDeserializer() {
        super(Br.class);
    }

    public static final Br A00(AbstractC0232Ww ww) throws IOException, q0 {
        Br br = new Br(ww.A0H());
        br.A08(ww);
        return br;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A00(ww);
    }
}
