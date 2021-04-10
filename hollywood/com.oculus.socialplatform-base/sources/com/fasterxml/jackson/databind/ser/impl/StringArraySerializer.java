package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC01900ha;
import X.AbstractC01990hm;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AnonymousClass0C7;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase<String[]> implements AbstractC04600qk {
    public static final StringArraySerializer A01 = new StringArraySerializer();
    public static final AbstractC02190iF A02 = new AnonymousClass0C7(String.class);
    public final JsonSerializer<Object> A00;

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r1) {
        return this;
    }

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r4, AbstractC02220iI r5) throws C02180iD {
        JsonSerializer<Object> jsonSerializer;
        AbstractC01990hm A4N;
        Object A0U;
        if (r5 == null || (A4N = r5.A4N()) == null || (A0U = r4._config.A01().A0U(A4N)) == null || (jsonSerializer = r4.A0A(A4N, A0U)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer<?> A03 = StdSerializer.A03(r4, r5, jsonSerializer);
        if (A03 == null) {
            A03 = r4.A0B(String.class, r5);
        } else if (A03 instanceof AbstractC04600qk) {
            A03 = ((AbstractC04600qk) A03).A2P(r4, r5);
        }
        if (StdSerializer.A06(A03)) {
            A03 = null;
        }
        if (A03 == this.A00) {
            return this;
        }
        return new StringArraySerializer(this, r5, A03);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((String[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A05(String[] strArr, AbstractC02300iS r6, AbstractC02120i3 r7) throws IOException, C02310iT {
        String[] strArr2 = strArr;
        int length = strArr2.length;
        if (length != 0) {
            JsonSerializer<Object> jsonSerializer = this.A00;
            if (jsonSerializer != null) {
                int i = 0;
                do {
                    if (strArr2[i] == null) {
                        r7.A0E(r6);
                    } else {
                        jsonSerializer.serialize(strArr2[i], r6, r7);
                    }
                    i++;
                } while (i < length);
                return;
            }
            int i2 = 0;
            do {
                if (strArr2[i2] == null) {
                    r6.A0G();
                } else {
                    r6.A0U(strArr2[i2]);
                }
                i2++;
            } while (i2 < length);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectArrayFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        String[] strArr = (String[]) obj;
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        return false;
    }

    public StringArraySerializer() {
        super(String[].class, (AbstractC02220iI) null);
        this.A00 = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, AbstractC02220iI r2, JsonSerializer<?> jsonSerializer) {
        super(stringArraySerializer, r2);
        this.A00 = jsonSerializer;
    }
}
