package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04690qz;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer extends AsArraySerializerBase<List<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r7) {
        return new IndexedListSerializer(this.A01, this.A05, r7, this.A03, this.A04);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((List) obj).size() != 1) {
            return false;
        }
        return true;
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<List<?>> A07(AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer jsonSerializer) {
        return new IndexedListSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A08(List<?> list, AbstractC02300iS r10, AbstractC02120i3 r11) throws IOException, C02310iT {
        List<?> list2 = list;
        JsonSerializer<Object> jsonSerializer = this.A04;
        if (jsonSerializer != null) {
            int size = list2.size();
            if (size != 0) {
                AbstractC04550qd r2 = this.A02;
                for (int i = 0; i < size; i++) {
                    Object obj = list2.get(i);
                    if (obj == null) {
                        try {
                            r11.A0E(r10);
                        } catch (Exception e) {
                            StdSerializer.A04(r11, e, list2, i);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else if (r2 == null) {
                        jsonSerializer.serialize(obj, r10, r11);
                    } else {
                        jsonSerializer.serializeWithType(obj, r10, r11, r2);
                    }
                }
                return;
            }
            return;
        }
        AbstractC04550qd r7 = this.A02;
        if (r7 != null) {
            int size2 = list2.size();
            if (size2 != 0) {
                try {
                    AbstractC04690qz r4 = ((AsArraySerializerBase) this).A00;
                    for (int i2 = 0; i2 < size2; i2++) {
                        Object obj2 = list2.get(i2);
                        if (obj2 == null) {
                            r11.A0E(r10);
                        } else {
                            Class<?> cls = obj2.getClass();
                            JsonSerializer<Object> A00 = r4.A00(cls);
                            if (A00 == null) {
                                AbstractC02190iF r1 = this.A01;
                                if (r1.A0H()) {
                                    A00 = A05(r4, r11.A04(r1, cls), r11);
                                } else {
                                    A00 = A06(r4, cls, r11);
                                }
                                r4 = ((AsArraySerializerBase) this).A00;
                            }
                            A00.serializeWithType(obj2, r10, r11, r7);
                        }
                    }
                } catch (Exception e2) {
                    StdSerializer.A04(r11, e2, list2, 0);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else {
            int size3 = list2.size();
            if (size3 != 0) {
                try {
                    AbstractC04690qz r42 = ((AsArraySerializerBase) this).A00;
                    for (int i3 = 0; i3 < size3; i3++) {
                        Object obj3 = list2.get(i3);
                        if (obj3 == null) {
                            r11.A0E(r10);
                        } else {
                            Class<?> cls2 = obj3.getClass();
                            JsonSerializer<Object> A002 = r42.A00(cls2);
                            if (A002 == null) {
                                AbstractC02190iF r12 = this.A01;
                                if (r12.A0H()) {
                                    A002 = A05(r42, r11.A04(r12, cls2), r11);
                                } else {
                                    A002 = A06(r42, cls2, r11);
                                }
                                r42 = ((AsArraySerializerBase) this).A00;
                            }
                            A002.serialize(obj3, r10, r11);
                        }
                    }
                } catch (Exception e3) {
                    StdSerializer.A04(r11, e3, list2, 0);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        List list = (List) obj;
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public IndexedListSerializer(AbstractC02190iF r8, boolean z, AbstractC04550qd r10, AbstractC02220iI r11, JsonSerializer<Object> jsonSerializer) {
        super(List.class, r8, z, r10, r11, jsonSerializer);
    }

    public IndexedListSerializer(IndexedListSerializer indexedListSerializer, AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer<?> jsonSerializer) {
        super(indexedListSerializer, r2, r3, jsonSerializer);
    }
}
