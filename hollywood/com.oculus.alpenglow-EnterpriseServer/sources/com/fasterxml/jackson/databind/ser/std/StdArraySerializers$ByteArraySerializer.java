package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StdArraySerializers$ByteArraySerializer extends StdSerializer<byte[]> {
    public StdArraySerializers$ByteArraySerializer() {
        super(byte[].class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6, AnonymousClass0o6 r7) throws IOException, C05910ld {
        byte[] bArr = (byte[]) obj;
        r7.A03(bArr, r5);
        r5.A0L(r6._config._base._defaultBase64, bArr, 0, bArr.length);
        r7.A06(bArr, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        byte[] bArr = (byte[]) obj;
        if (bArr == null || bArr.length == 0) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(byte[] bArr, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        byte[] bArr2 = bArr;
        r5.A0L(r6._config._base._defaultBase64, bArr2, 0, bArr2.length);
    }
}
