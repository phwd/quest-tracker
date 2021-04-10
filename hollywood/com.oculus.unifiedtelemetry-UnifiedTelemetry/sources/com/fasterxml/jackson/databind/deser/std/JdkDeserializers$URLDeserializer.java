package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.q0;
import java.io.IOException;
import java.net.URL;

public class JdkDeserializers$URLDeserializer extends FromStringDeserializer<URL> {
    public static final JdkDeserializers$URLDeserializer A00 = new JdkDeserializers$URLDeserializer();

    public JdkDeserializers$URLDeserializer() {
        super(URL.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final URL A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
        return new URL(str);
    }
}
