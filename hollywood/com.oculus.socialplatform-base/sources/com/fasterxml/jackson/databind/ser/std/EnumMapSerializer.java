package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC01990hm;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AnonymousClass0i4;
import X.C02180iD;
import X.C02310iT;
import X.C03620oC;
import X.C04830rM;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.Map;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>> implements AbstractC04600qk {
    public final AbstractC02220iI A00;
    public final JsonSerializer<Object> A01;
    public final AbstractC04550qd A02;
    public final C04830rM A03;
    public final AbstractC02190iF A04;
    public final boolean A05;

    private final void A00(EnumMap<? extends Enum<?>, ?> enumMap, AbstractC02300iS r12, AbstractC02120i3 r13) throws IOException, C02310iT {
        JsonSerializer<Object> jsonSerializer = this.A01;
        if (jsonSerializer != null) {
            C04830rM r1 = this.A03;
            boolean z = !r13._config.A06(AnonymousClass0i4.WRITE_NULL_MAP_VALUES);
            AbstractC04550qd r5 = this.A02;
            for (Map.Entry<K, ?> entry : enumMap.entrySet()) {
                Object value = entry.getValue();
                if (!z || value != null) {
                    K key = entry.getKey();
                    if (r1 == null) {
                        r1 = ((EnumSerializer) ((StdSerializer) r13.A0B(key.getDeclaringClass(), this.A00))).A00;
                    }
                    r12.A0P(r1.A00.get(key));
                    if (value == null) {
                        r13.A0E(r12);
                    } else if (r5 == null) {
                        try {
                            jsonSerializer.serialize(value, r12, r13);
                        } catch (Exception e) {
                            StdSerializer.A05(r13, e, enumMap, entry.getKey().name());
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        jsonSerializer.serializeWithType(value, r12, r13, r5);
                    }
                }
            }
            return;
        }
        C04830rM r2 = this.A03;
        boolean z2 = !r13._config.A06(AnonymousClass0i4.WRITE_NULL_MAP_VALUES);
        AbstractC04550qd r52 = this.A02;
        Class<?> cls = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        for (Map.Entry<K, ?> entry2 : enumMap.entrySet()) {
            Object value2 = entry2.getValue();
            if (!z2 || value2 != null) {
                K key2 = entry2.getKey();
                if (r2 == null) {
                    r2 = ((EnumSerializer) ((StdSerializer) r13.A0B(key2.getDeclaringClass(), this.A00))).A00;
                }
                r12.A0P(r2.A00.get(key2));
                if (value2 == null) {
                    r13.A0E(r12);
                } else {
                    Class<?> cls2 = value2.getClass();
                    if (cls2 != cls) {
                        jsonSerializer2 = r13.A0B(cls2, this.A00);
                        cls = cls2;
                    }
                    if (r52 == null) {
                        try {
                            jsonSerializer2.serialize(value2, r12, r13);
                        } catch (Exception e2) {
                            StdSerializer.A05(r13, e2, enumMap, entry2.getKey().name());
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        jsonSerializer2.serializeWithType(value2, r12, r13, r52);
                    }
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer A03(AbstractC04550qd r7) {
        return new EnumMapSerializer(this.A04, this.A05, this.A03, r7, this.A01);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(EnumMap<? extends Enum<?>, ?> enumMap) {
        if (enumMap.size() != 1) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r4, AbstractC02220iI r5) throws C02180iD {
        JsonSerializer<Object> jsonSerializer;
        AbstractC01990hm A4N;
        Object A0U;
        if (r5 == null || (A4N = r5.A4N()) == null || (A0U = r4._config.A01().A0U(A4N)) == null || (jsonSerializer = r4.A0A(A4N, A0U)) == null) {
            jsonSerializer = this.A01;
        }
        JsonSerializer<?> A032 = StdSerializer.A03(r4, r5, jsonSerializer);
        if (A032 == null) {
            if (this.A05) {
                JsonSerializer<Object> A09 = r4.A09(this.A04, r5);
                if (this.A00 == r5 && A09 == this.A01) {
                    return this;
                }
                return new EnumMapSerializer(this, r5, A09);
            }
        } else if (this.A01 instanceof AbstractC04600qk) {
            A032 = ((AbstractC04600qk) A032).A2P(r4, r5);
        }
        JsonSerializer<?> jsonSerializer2 = this.A01;
        if (A032 == jsonSerializer2) {
            return this;
        }
        if (this.A00 == r5 && A032 == jsonSerializer2) {
            return this;
        }
        return new EnumMapSerializer(this, r5, A032);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectObjectFormat");
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(EnumMap<? extends Enum<?>, ?> enumMap) {
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        if (enumMap2 == null || enumMap2.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        EnumMap<? extends Enum<?>, ?> enumMap = (EnumMap) obj;
        r3.A0I();
        if (!enumMap.isEmpty()) {
            A00(enumMap, r3, r4);
        }
        r3.A0F();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C03620oC {
        EnumMap<? extends Enum<?>, ?> enumMap = (EnumMap) obj;
        r5.A02(enumMap, r3);
        if (!enumMap.isEmpty()) {
            A00(enumMap, r3, r4);
        }
        r5.A05(enumMap, r3);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(AbstractC02190iF r3, boolean z, C04830rM r5, AbstractC04550qd r6, JsonSerializer<Object> jsonSerializer) {
        super(EnumMap.class, false);
        boolean z2 = false;
        this.A00 = null;
        if (z || (r3 != null && Modifier.isFinal(r3._class.getModifiers()))) {
            z2 = true;
        }
        this.A05 = z2;
        this.A04 = r3;
        this.A03 = r5;
        this.A02 = r6;
        this.A01 = jsonSerializer;
    }

    public EnumMapSerializer(EnumMapSerializer enumMapSerializer, AbstractC02220iI r3, JsonSerializer<?> jsonSerializer) {
        super(enumMapSerializer);
        this.A00 = r3;
        this.A05 = enumMapSerializer.A05;
        this.A04 = enumMapSerializer.A04;
        this.A03 = enumMapSerializer.A03;
        this.A02 = enumMapSerializer.A02;
        this.A01 = jsonSerializer;
    }
}
