package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AnonymousClass0i4;
import X.C02180iD;
import X.C02310iT;
import X.C03620oC;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StdArraySerializers$CharArraySerializer extends StdSerializer<char[]> {
    public StdArraySerializers$CharArraySerializer() {
        super(char[].class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectArrayFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        char[] cArr = (char[]) obj;
        if (cArr == null || cArr.length == 0) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(char[] cArr, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02310iT {
        char[] cArr2 = cArr;
        if (r6._config.A06(AnonymousClass0i4.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            r5.A0H();
            int length = cArr2.length;
            for (int i = 0; i < length; i++) {
                r5.A0a(cArr2, i, 1);
            }
            r5.A0E();
            return;
        }
        r5.A0a(cArr2, 0, cArr2.length);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r5, AbstractC02120i3 r6, AbstractC04550qd r7) throws IOException, C03620oC {
        char[] cArr = (char[]) obj;
        if (r6._config.A06(AnonymousClass0i4.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            r7.A01(cArr, r5);
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                r5.A0a(cArr, i, 1);
            }
            r7.A04(cArr, r5);
            return;
        }
        r7.A03(cArr, r5);
        r5.A0a(cArr, 0, cArr.length);
        r7.A06(cArr, r5);
    }
}
