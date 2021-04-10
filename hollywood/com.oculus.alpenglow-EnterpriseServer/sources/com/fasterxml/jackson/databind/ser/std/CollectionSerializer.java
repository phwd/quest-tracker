package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06960oT;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public final class CollectionSerializer extends AsArraySerializerBase<Collection<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        Collection collection = (Collection) obj;
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r7) {
        return new CollectionSerializer(this.A01, this.A05, r7, this.A03, this.A04);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        Iterator it = ((Collection) obj).iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next();
        return !it.hasNext();
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<Collection<?>> A0I(AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer jsonSerializer) {
        return new CollectionSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A0J(Collection<?> collection, AbstractC02640aV r10, AnonymousClass0a8 r11) throws IOException, C02650aW {
        Collection<?> collection2 = collection;
        JsonSerializer<Object> jsonSerializer = this.A04;
        if (jsonSerializer != null) {
            Iterator<?> it = collection2.iterator();
            if (it.hasNext()) {
                AnonymousClass0o6 r2 = this.A02;
                int i = 0;
                do {
                    Object next = it.next();
                    if (next == null) {
                        try {
                            r11.A0D(r10);
                        } catch (Exception e) {
                            StdSerializer.A04(r11, e, collection2, i);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else if (r2 == null) {
                        jsonSerializer.A0D(next, r10, r11);
                    } else {
                        jsonSerializer.A0A(next, r10, r11, r2);
                    }
                    i++;
                } while (it.hasNext());
                return;
            }
            return;
        }
        Iterator<?> it2 = collection2.iterator();
        if (it2.hasNext()) {
            AbstractC06960oT r6 = ((AsArraySerializerBase) this).A00;
            AnonymousClass0o6 r5 = this.A02;
            int i2 = 0;
            do {
                try {
                    Object next2 = it2.next();
                    if (next2 == null) {
                        r11.A0D(r10);
                    } else {
                        Class<?> cls = next2.getClass();
                        JsonSerializer<Object> A00 = r6.A00(cls);
                        if (A00 == null) {
                            AnonymousClass0aI r1 = this.A01;
                            if (r1.A0H()) {
                                A00 = A0G(r6, r11.A04(r1, cls), r11);
                            } else {
                                A00 = A0H(r6, cls, r11);
                            }
                            r6 = ((AsArraySerializerBase) this).A00;
                        }
                        if (r5 == null) {
                            A00.A0D(next2, r10, r11);
                        } else {
                            A00.A0A(next2, r10, r11, r5);
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

    public CollectionSerializer(AnonymousClass0aI r8, boolean z, AnonymousClass0o6 r10, AbstractC02580aL r11, JsonSerializer<Object> jsonSerializer) {
        super(Collection.class, r8, z, r10, r11, jsonSerializer);
    }

    public CollectionSerializer(CollectionSerializer collectionSerializer, AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer<?> jsonSerializer) {
        super(collectionSerializer, r2, r3, jsonSerializer);
    }
}
