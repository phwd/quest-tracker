package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1022qr;
import X.AnonymousClass08;
import X.C1025qv;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.UUID;

public class JdkDeserializers$UUIDDeserializer extends FromStringDeserializer {
    public static final JdkDeserializers$UUIDDeserializer A00 = new JdkDeserializers$UUIDDeserializer();

    public JdkDeserializers$UUIDDeserializer() {
        super(UUID.class);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Object A0N(Object obj, AbstractC1022qr qrVar) {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            if (length != 16) {
                C1025qv.A00(null, AnonymousClass08.A01("Can only construct UUIDs from 16 byte arrays; got ", length, " bytes"));
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
        }
        super.A0N(obj, qrVar);
        return null;
    }
}
