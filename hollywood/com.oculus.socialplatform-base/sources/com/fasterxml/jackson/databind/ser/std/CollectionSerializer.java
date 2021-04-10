package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04690qz;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer extends AsArraySerializerBase<Collection<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r7) {
        return new CollectionSerializer(this.A01, this.A05, r7, this.A03, this.A04);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        Iterator it = ((Collection) obj).iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next();
        return !it.hasNext();
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<Collection<?>> A07(AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer jsonSerializer) {
        return new CollectionSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A08(Collection<?> collection, AbstractC02300iS r10, AbstractC02120i3 r11) throws IOException, C02310iT {
        Collection<?> collection2 = collection;
        JsonSerializer<Object> jsonSerializer = this.A04;
        if (jsonSerializer != null) {
            Iterator<?> it = collection2.iterator();
            if (it.hasNext()) {
                AbstractC04550qd r2 = this.A02;
                int i = 0;
                do {
                    Object next = it.next();
                    if (next == null) {
                        try {
                            r11.A0E(r10);
                        } catch (Exception e) {
                            StdSerializer.A04(r11, e, collection2, i);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else if (r2 == null) {
                        jsonSerializer.serialize(next, r10, r11);
                    } else {
                        jsonSerializer.serializeWithType(next, r10, r11, r2);
                    }
                    i++;
                } while (it.hasNext());
                return;
            }
            return;
        }
        Iterator<?> it2 = collection2.iterator();
        if (it2.hasNext()) {
            AbstractC04690qz r6 = ((AsArraySerializerBase) this).A00;
            AbstractC04550qd r5 = this.A02;
            int i2 = 0;
            do {
                try {
                    Object next2 = it2.next();
                    if (next2 == null) {
                        r11.A0E(r10);
                    } else {
                        Class<?> cls = next2.getClass();
                        JsonSerializer<Object> A00 = r6.A00(cls);
                        if (A00 == null) {
                            AbstractC02190iF r1 = this.A01;
                            if (r1.A0H()) {
                                A00 = A05(r6, r11.A04(r1, cls), r11);
                            } else {
                                A00 = A06(r6, cls, r11);
                            }
                            r6 = ((AsArraySerializerBase) this).A00;
                        }
                        if (r5 == null) {
                            A00.serialize(next2, r10, r11);
                        } else {
                            A00.serializeWithType(next2, r10, r11, r5);
                        }
                    }
                    i2++;
                } catch (Exception e2) {
                    StdSerializer.A04(r11, e2, collection2, i2);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } while (it2.hasNext());
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        Collection collection = (Collection) obj;
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public CollectionSerializer(AbstractC02190iF r8, boolean z, AbstractC04550qd r10, AbstractC02220iI r11, JsonSerializer<Object> jsonSerializer) {
        super(Collection.class, r8, z, r10, r11, jsonSerializer);
    }

    public CollectionSerializer(CollectionSerializer collectionSerializer, AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer<?> jsonSerializer) {
        super(collectionSerializer, r2, r3, jsonSerializer);
    }
}
