package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02450Zr;
import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AnonymousClass0C9;
import X.AnonymousClass0a8;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArraySerializer extends ArraySerializerBase<String[]> implements AbstractC06840oE {
    public static final StringArraySerializer A01 = new StringArraySerializer();
    public static final AnonymousClass0aI A02 = new AnonymousClass0C9(String.class);
    public final JsonSerializer<Object> A00;

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r1) {
        return this;
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        JsonSerializer<Object> jsonSerializer;
        AbstractC02450Zr A41;
        Object A0U;
        if (r5 == null || (A41 = r5.A41()) == null || (A0U = r4._config.A01().A0U(A41)) == null || (jsonSerializer = r4.A09(A41, A0U)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer<?> A03 = StdSerializer.A03(r4, r5, jsonSerializer);
        if (A03 == null) {
            A03 = r4.A0B(String.class, r5);
        } else if (A03 instanceof AbstractC06840oE) {
            A03 = ((AbstractC06840oE) A03).A1x(r4, r5);
        }
        if (StdSerializer.A06(A03)) {
            A03 = null;
        }
        if (A03 == this.A00) {
            return this;
        }
        return new StringArraySerializer(this, r5, A03);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        String[] strArr = (String[]) obj;
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((String[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A0G(String[] strArr, AbstractC02640aV r6, AnonymousClass0a8 r7) throws IOException, C02650aW {
        String[] strArr2 = strArr;
        int length = strArr2.length;
        if (length != 0) {
            JsonSerializer<Object> jsonSerializer = this.A00;
            if (jsonSerializer != null) {
                int i = 0;
                do {
                    if (strArr2[i] == null) {
                        r7.A0D(r6);
                    } else {
                        jsonSerializer.A0D(strArr2[i], r6, r7);
                    }
                    i++;
                } while (i < length);
                return;
            }
            int i2 = 0;
            do {
                if (strArr2[i2] == null) {
                    r6.A0D();
                } else {
                    r6.A0S(strArr2[i2]);
                }
                i2++;
            } while (i2 < length);
        }
    }

    public StringArraySerializer() {
        super(String[].class, (AbstractC02580aL) null);
        this.A00 = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, AbstractC02580aL r2, JsonSerializer<?> jsonSerializer) {
        super(stringArraySerializer, r2);
        this.A00 = jsonSerializer;
    }
}
