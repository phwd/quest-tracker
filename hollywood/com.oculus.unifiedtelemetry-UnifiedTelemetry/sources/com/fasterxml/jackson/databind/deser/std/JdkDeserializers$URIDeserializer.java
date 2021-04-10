package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.q0;
import java.io.IOException;
import java.net.URI;

public class JdkDeserializers$URIDeserializer extends FromStringDeserializer<URI> {
    public static final JdkDeserializers$URIDeserializer A00 = new JdkDeserializers$URIDeserializer();

    public JdkDeserializers$URIDeserializer() {
        super(URI.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final URI A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
        return URI.create(str);
    }
}
