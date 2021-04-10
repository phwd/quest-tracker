package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.C05910ld;
import java.io.IOException;
import java.net.InetAddress;

public class JdkDeserializers$InetAddressDeserializer extends FromStringDeserializer<InetAddress> {
    public static final JdkDeserializers$InetAddressDeserializer A00 = new JdkDeserializers$InetAddressDeserializer();

    public JdkDeserializers$InetAddressDeserializer() {
        super(InetAddress.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final InetAddress A0Q(String str, AbstractC02570aK r3) throws IOException, C05910ld {
        return InetAddress.getByName(str);
    }
}
