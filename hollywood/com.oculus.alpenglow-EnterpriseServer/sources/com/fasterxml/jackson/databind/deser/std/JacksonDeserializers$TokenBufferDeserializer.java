package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C01570Jk;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class JacksonDeserializers$TokenBufferDeserializer extends StdScalarDeserializer<C01570Jk> {
    public static final JacksonDeserializers$TokenBufferDeserializer A00 = new JacksonDeserializers$TokenBufferDeserializer();

    public JacksonDeserializers$TokenBufferDeserializer() {
        super(C01570Jk.class);
    }

    public static final C01570Jk A00(AnonymousClass0aT r2) throws IOException, C05910ld {
        C01570Jk r0 = new C01570Jk(r2.A0I());
        r0.A0a(r2);
        return r0;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A00(r2);
    }
}
