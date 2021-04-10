package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.JD;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class JacksonDeserializers$TokenBufferDeserializer extends StdScalarDeserializer {
    public static final JacksonDeserializers$TokenBufferDeserializer A00 = new JacksonDeserializers$TokenBufferDeserializer();

    public JacksonDeserializers$TokenBufferDeserializer() {
        super(JD.class);
    }

    public static final JD A00(AbstractC1014qi qiVar) {
        JD jd = new JD(qiVar.A0W());
        jd.A0X(qiVar);
        return jd;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A00(qiVar);
    }
}
