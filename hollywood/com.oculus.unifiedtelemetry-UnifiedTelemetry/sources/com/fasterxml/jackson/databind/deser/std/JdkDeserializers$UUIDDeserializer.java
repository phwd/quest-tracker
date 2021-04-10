package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AnonymousClass06;
import X.C0223Wj;
import X.q0;
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
    public final UUID A0P(Object obj, AbstractC0226Wn wn) throws IOException, q0 {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            if (length != 16) {
                C0223Wj.A00(wn.A00, AnonymousClass06.A02("Can only construct UUIDs from 16 byte arrays; got ", length, " bytes"));
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
        }
        super.A0P(obj, wn);
        return null;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final UUID A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
        return UUID.fromString(str);
    }
}
