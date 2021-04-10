package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.C03620oC;
import java.io.IOException;
import java.net.URL;

public class JdkDeserializers$URLDeserializer extends FromStringDeserializer<URL> {
    public static final JdkDeserializers$URLDeserializer A00 = new JdkDeserializers$URLDeserializer();

    public JdkDeserializers$URLDeserializer() {
        super(URL.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final URL A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return new URL(str);
    }
}
