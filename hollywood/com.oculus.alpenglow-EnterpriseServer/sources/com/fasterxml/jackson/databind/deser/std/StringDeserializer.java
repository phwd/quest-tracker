package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05840lV;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringDeserializer extends StdScalarDeserializer<String> {
    public static final StringDeserializer A00 = new StringDeserializer();
    public static final long serialVersionUID = 1;

    public StringDeserializer() {
        super(String.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        String A0N = r4.A0N();
        if (A0N != null) {
            return A0N;
        }
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_EMBEDDED_OBJECT) {
            Object A0M = r4.A0M();
            if (A0M == null) {
                return null;
            }
            if (A0M instanceof byte[]) {
                return C05840lV.A01.A01((byte[]) A0M, false);
            }
            return A0M.toString();
        }
        throw r5.A0C(this._valueClass, A0G);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return A09(r2, r3);
    }
}
