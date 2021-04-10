package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06960oT;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer extends AsArraySerializerBase<List<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        List list = (List) obj;
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r7) {
        return new IndexedListSerializer(this.A01, this.A05, r7, this.A03, this.A04);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((List) obj).size() != 1) {
            return false;
        }
        return true;
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<List<?>> A0I(AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer jsonSerializer) {
        return new IndexedListSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A0J(List<?> list, AbstractC02640aV r10, AnonymousClass0a8 r11) throws IOException, C02650aW {
        List<?> list2 = list;
        JsonSerializer<Object> jsonSerializer = this.A04;
        if (jsonSerializer != null) {
            int size = list2.size();
            if (size != 0) {
                AnonymousClass0o6 r2 = this.A02;
                for (int i = 0; i < size; i++) {
                    Object obj = list2.get(i);
                    if (obj == null) {
                        try {
                            r11.A0D(r10);
                        } catch (Exception e) {
                            StdSerializer.A04(r11, e, list2, i);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else if (r2 == null) {
                        jsonSerializer.A0D(obj, r10, r11);
                    } else {
                        jsonSerializer.A0A(obj, r10, r11, r2);
                    }
                }
                return;
            }
            return;
        }
        AnonymousClass0o6 r7 = this.A02;
        if (r7 != null) {
            int size2 = list2.size();
            if (size2 != 0) {
                try {
                    AbstractC06960oT r4 = ((AsArraySerializerBase) this).A00;
                    for (int i2 = 0; i2 < size2; i2++) {
                        Object obj2 = list2.get(i2);
                        if (obj2 == null) {
                            r11.A0D(r10);
                        } else {
                            Class<?> cls = obj2.getClass();
                            JsonSerializer<Object> A00 = r4.A00(cls);
                            if (A00 == null) {
                                AnonymousClass0aI r1 = this.A01;
                                if (r1.A0H()) {
                                    A00 = A0G(r4, r11.A04(r1, cls), r11);
                                } else {
                                    A00 = A0H(r4, cls, r11);
                                }
                                r4 = ((AsArraySerializerBase) this).A00;
                            }
                            A00.A0A(obj2, r10, r11, r7);
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
                AbstractC06960oT r42 = ((AsArraySerializerBase) this).A00;
                for (int i3 = 0; i3 < size3; i3++) {
                    Object obj3 = list2.get(i3);
                    if (obj3 == null) {
                        r11.A0D(r10);
                    } else {
                        Class<?> cls2 = obj3.getClass();
                        JsonSerializer<Object> A002 = r42.A00(cls2);
                        if (A002 == null) {
                            AnonymousClass0aI r12 = this.A01;
                            if (r12.A0H()) {
                                A002 = A0G(r42, r11.A04(r12, cls2), r11);
                            } else {
                                A002 = A0H(r42, cls2, r11);
                            }
                            r42 = ((AsArraySerializerBase) this).A00;
                        }
                        A002.A0D(obj3, r10, r11);
                    }
                }
            }
        }
    }

    public IndexedListSerializer(AnonymousClass0aI r8, boolean z, AnonymousClass0o6 r10, AbstractC02580aL r11, JsonSerializer<Object> jsonSerializer) {
        super(List.class, r8, z, r10, r11, jsonSerializer);
    }

    public IndexedListSerializer(IndexedListSerializer indexedListSerializer, AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer<?> jsonSerializer) {
        super(indexedListSerializer, r2, r3, jsonSerializer);
    }
}
