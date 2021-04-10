package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StdArraySerializers$CharArraySerializer extends StdSerializer<char[]> {
    public StdArraySerializers$CharArraySerializer() {
        super(char[].class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6, AnonymousClass0o6 r7) throws IOException, C05910ld {
        char[] cArr = (char[]) obj;
        if (r6._config.A06(AnonymousClass0a9.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            r7.A01(cArr, r5);
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                r5.A0Y(cArr, i, 1);
            }
            r7.A04(cArr, r5);
            return;
        }
        r7.A03(cArr, r5);
        r5.A0Y(cArr, 0, cArr.length);
        r7.A06(cArr, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        char[] cArr = (char[]) obj;
        if (cArr == null || cArr.length == 0) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(char[] cArr, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        char[] cArr2 = cArr;
        if (r6._config.A06(AnonymousClass0a9.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            r5.A0E();
            int length = cArr2.length;
            for (int i = 0; i < length; i++) {
                r5.A0Y(cArr2, i, 1);
            }
            r5.A0B();
            return;
        }
        r5.A0Y(cArr2, 0, cArr2.length);
    }
}
