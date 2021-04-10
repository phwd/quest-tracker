package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.C03620oC;
import java.io.IOException;
import java.net.InetAddress;

public class JdkDeserializers$InetAddressDeserializer extends FromStringDeserializer<InetAddress> {
    public static final JdkDeserializers$InetAddressDeserializer A00 = new JdkDeserializers$InetAddressDeserializer();

    public JdkDeserializers$InetAddressDeserializer() {
        super(InetAddress.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final InetAddress A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return InetAddress.getByName(str);
    }
}
