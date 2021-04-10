package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.C03620oC;
import java.io.IOException;
import java.nio.charset.Charset;

public class JdkDeserializers$CharsetDeserializer extends FromStringDeserializer<Charset> {
    public static final JdkDeserializers$CharsetDeserializer A00 = new JdkDeserializers$CharsetDeserializer();

    public JdkDeserializers$CharsetDeserializer() {
        super(Charset.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Charset A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return Charset.forName(str);
    }
}
