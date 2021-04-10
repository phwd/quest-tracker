package com.fasterxml.jackson.datatype.guava.ser;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AbstractC05440vj;
import X.AnonymousClass0C8;
import X.C02180iD;
import X.C03620oC;
import X.C05250uz;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MultimapSerializer extends JsonSerializer<AbstractC05440vj<?, ?>> implements AbstractC04600qk {
    public final AbstractC02220iI A00;
    public final JsonSerializer<Object> A01;
    public final JsonSerializer<Object> A02;
    public final AbstractC04550qd A03;
    public final AnonymousClass0C8 A04;

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r8, AbstractC02220iI r9) throws C02180iD {
        JsonSerializer<?> jsonSerializer = this.A02;
        if (jsonSerializer == null) {
            AbstractC02190iF A042 = this.A04.A04();
            if (Modifier.isFinal(A042._class.getModifiers())) {
                jsonSerializer = r8.A09(A042, r9);
            }
        } else if (jsonSerializer instanceof AbstractC04600qk) {
            jsonSerializer = ((AbstractC04600qk) jsonSerializer).A2P(r8, r9);
        }
        JsonSerializer<?> jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            jsonSerializer2 = r8.A08(this.A04.A05(), r9);
        } else if (jsonSerializer2 instanceof AbstractC04600qk) {
            jsonSerializer2 = ((AbstractC04600qk) jsonSerializer2).A2P(r8, r9);
        }
        AbstractC04550qd r5 = this.A03;
        if (r5 != null) {
            r5 = r5.A00(r9);
        }
        return new MultimapSerializer(this, r9, jsonSerializer2, r5, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(AbstractC05440vj<?, ?> r2, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C03620oC {
        AbstractC05440vj<?, ?> r22 = r2;
        r3.A0I();
        if (!r22.isEmpty()) {
            A00(r22, r3, r4);
        }
        r3.A0F();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3, X.0qd] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(AbstractC05440vj<?, ?> r1, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C03620oC {
        AbstractC05440vj<?, ?> r12 = r1;
        r4.A02(r12, r2);
        A00(r12, r2, r3);
        r4.A05(r12, r2);
    }

    private final void A00(AbstractC05440vj<?, ?> r6, AbstractC02300iS r7, AbstractC02120i3 r8) throws IOException, C03620oC {
        ArrayList arrayList;
        for (Map.Entry<?, Collection<?>> entry : r6.A1T().entrySet()) {
            JsonSerializer<Object> jsonSerializer = this.A01;
            if (jsonSerializer == null) {
                jsonSerializer = r8.A08(r8.A07().A09(String.class, null), this.A00);
            }
            jsonSerializer.serialize(entry.getKey(), r7, r8);
            JsonSerializer<Object> jsonSerializer2 = this.A02;
            if (jsonSerializer2 != null) {
                r7.A0H();
                Iterator<?> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    jsonSerializer2.serialize(it.next(), r7, r8);
                }
                r7.A0E();
            } else {
                Collection<?> value = entry.getValue();
                if (value != null) {
                    if (value instanceof Collection) {
                        arrayList = new ArrayList(value);
                    } else {
                        Iterator<T> it2 = value.iterator();
                        arrayList = new ArrayList();
                        C05250uz.A00(arrayList, it2);
                    }
                    r8.A0F(arrayList, r7);
                } else {
                    throw null;
                }
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0HM;LX/0C8;LX/0oz;Lcom/fasterxml/jackson/databind/JsonSerializer<Ljava/lang/Object;>;LX/0qd;Lcom/fasterxml/jackson/databind/JsonSerializer<Ljava/lang/Object;>;)V */
    public MultimapSerializer(AnonymousClass0C8 r2, JsonSerializer jsonSerializer, AbstractC04550qd r4, JsonSerializer jsonSerializer2) {
        this.A04 = r2;
        this.A00 = null;
        this.A01 = jsonSerializer;
        this.A03 = r4;
        this.A02 = jsonSerializer2;
    }

    public MultimapSerializer(MultimapSerializer multimapSerializer, AbstractC02220iI r3, JsonSerializer<?> jsonSerializer, AbstractC04550qd r5, JsonSerializer<?> jsonSerializer2) {
        this.A04 = multimapSerializer.A04;
        this.A00 = r3;
        this.A01 = jsonSerializer;
        this.A03 = r5;
        this.A02 = jsonSerializer2;
    }
}
