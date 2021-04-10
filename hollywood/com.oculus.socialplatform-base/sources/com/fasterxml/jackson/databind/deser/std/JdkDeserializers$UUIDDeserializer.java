package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AnonymousClass006;
import X.C02180iD;
import X.C03620oC;
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
    public final UUID A0P(Object obj, AbstractC02210iH r7) throws IOException, C03620oC {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            if (length != 16) {
                C02180iD.A00(r7.A00, AnonymousClass006.A04("Can only construct UUIDs from 16 byte arrays; got ", length, " bytes"));
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
        }
        super.A0P(obj, r7);
        return null;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final UUID A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return UUID.fromString(str);
    }
}
