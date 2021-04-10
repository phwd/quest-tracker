package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.C03620oC;
import java.io.IOException;
import java.net.URI;

public class JdkDeserializers$URIDeserializer extends FromStringDeserializer<URI> {
    public static final JdkDeserializers$URIDeserializer A00 = new JdkDeserializers$URIDeserializer();

    public JdkDeserializers$URIDeserializer() {
        super(URI.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final URI A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return URI.create(str);
    }
}
