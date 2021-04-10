package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02180iD;
import X.C02310iT;
import X.C03620oC;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StdArraySerializers$ByteArraySerializer extends StdSerializer<byte[]> {
    public StdArraySerializers$ByteArraySerializer() {
        super(byte[].class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectArrayFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        byte[] bArr = (byte[]) obj;
        if (bArr == null || bArr.length == 0) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(byte[] bArr, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02310iT {
        byte[] bArr2 = bArr;
        r5.A0O(r6._config._base._defaultBase64, bArr2, 0, bArr2.length);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r5, AbstractC02120i3 r6, AbstractC04550qd r7) throws IOException, C03620oC {
        byte[] bArr = (byte[]) obj;
        r7.A03(bArr, r5);
        r5.A0O(r6._config._base._defaultBase64, bArr, 0, bArr.length);
        r7.A06(bArr, r5);
    }
}
