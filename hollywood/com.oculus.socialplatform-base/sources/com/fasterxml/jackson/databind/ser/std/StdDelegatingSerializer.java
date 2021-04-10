package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04490qS;
import X.AbstractC04500qW;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AbstractC04620qo;
import X.AnonymousClass006;
import X.C02180iD;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public class StdDelegatingSerializer extends StdSerializer<Object> implements AbstractC04600qk, AbstractC04620qo, AbstractC04490qS, AbstractC04500qW {
    public final AbstractC02190iF A00;
    public final JsonSerializer<Object> A01;

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r5, AbstractC02220iI r6) throws C02180iD {
        JsonSerializer<?> jsonSerializer;
        AbstractC02190iF r2;
        JsonSerializer<?> jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            r2 = this.A00;
            if (r2 == null) {
                throw new NullPointerException("getOutputType");
            }
            jsonSerializer = r5.A09(r2, r6);
            Class<?> cls = getClass();
            if (cls != StdDelegatingSerializer.class) {
                throw new IllegalStateException(AnonymousClass006.A09("Sub-class ", cls.getName(), " must override 'withDelegate'"));
            }
        } else if (!(jsonSerializer2 instanceof AbstractC04600qk) || (jsonSerializer = ((AbstractC04600qk) jsonSerializer2).A2P(r5, r6)) == jsonSerializer2) {
            return this;
        } else {
            r2 = this.A00;
            Class<?> cls2 = getClass();
            if (cls2 != StdDelegatingSerializer.class) {
                throw new IllegalStateException(AnonymousClass006.A09("Sub-class ", cls2.getName(), " must override 'withDelegate'"));
            }
        }
        return new StdDelegatingSerializer(r2, jsonSerializer);
    }

    @Override // X.AbstractC04620qo
    public final void A9O(AbstractC02120i3 r3) throws C02180iD {
        JsonSerializer<Object> jsonSerializer = this.A01;
        if (jsonSerializer != null && (jsonSerializer instanceof AbstractC04620qo)) {
            ((AbstractC04620qo) jsonSerializer).A9O(r3);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r2, AbstractC02190iF r3) throws C02180iD {
        this.A01.acceptJsonFormatVisitor(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        throw new NullPointerException("convert");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C03620oC {
        throw new NullPointerException("convert");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5, AbstractC04550qd r6) throws IOException, C03620oC {
        throw new NullPointerException("convert");
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0rJ<Ljava/lang/Object;*>;LX/0iF;Lcom/fasterxml/jackson/databind/JsonSerializer<*>;)V */
    public StdDelegatingSerializer(AbstractC02190iF r1, JsonSerializer jsonSerializer) {
        super(r1);
        this.A00 = r1;
        this.A01 = jsonSerializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<?> getDelegatee() {
        return this.A01;
    }
}
