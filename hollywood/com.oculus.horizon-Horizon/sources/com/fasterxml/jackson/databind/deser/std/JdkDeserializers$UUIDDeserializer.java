package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AnonymousClass006;
import X.AnonymousClass0jg;
import X.C03990gZ;
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
    public final UUID A0P(Object obj, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            if (length != 16) {
                C03990gZ.A00(null, AnonymousClass006.A02("Can only construct UUIDs from 16 byte arrays; got ", length, " bytes"));
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
        }
        super.A0P(obj, r7);
        return null;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final UUID A0Q(String str, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return UUID.fromString(str);
    }
}
