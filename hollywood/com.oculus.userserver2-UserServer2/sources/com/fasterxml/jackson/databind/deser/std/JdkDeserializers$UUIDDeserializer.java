package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public class JdkDeserializers$UUIDDeserializer extends FromStringDeserializer<UUID> {
    public static final JdkDeserializers$UUIDDeserializer A00 = new JdkDeserializers$UUIDDeserializer();

    public JdkDeserializers$UUIDDeserializer() {
        super(UUID.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final UUID A09(Object obj, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (bArr.length != 16) {
                throw null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
        }
        super.A09(obj, rd);
        return null;
    }
}
