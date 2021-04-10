package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02450Zr;
import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import X.C07170oq;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.Map;

@JacksonStdImpl
public final class EnumMapSerializer extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>> implements AbstractC06840oE {
    public final AbstractC02580aL A00;
    public final JsonSerializer<Object> A01;
    public final AnonymousClass0o6 A02;
    public final C07170oq A03;
    public final AnonymousClass0aI A04;
    public final boolean A05;

    private final void A00(EnumMap<? extends Enum<?>, ?> enumMap, AbstractC02640aV r12, AnonymousClass0a8 r13) throws IOException, C02650aW {
        JsonSerializer<Object> jsonSerializer = this.A01;
        if (jsonSerializer != null) {
            C07170oq r1 = this.A03;
            boolean z = !r13._config.A06(AnonymousClass0a9.WRITE_NULL_MAP_VALUES);
            AnonymousClass0o6 r5 = this.A02;
            for (Map.Entry<? extends Enum<?>, ?> entry : enumMap.entrySet()) {
                Object value = entry.getValue();
                if (!z || value != null) {
                    Enum r2 = (Enum) entry.getKey();
                    if (r1 == null) {
                        r1 = ((EnumSerializer) ((StdSerializer) r13.A0B(r2.getDeclaringClass(), this.A00))).A00;
                    }
                    r12.A0N(r1.A00.get(r2));
                    if (value == null) {
                        r13.A0D(r12);
                    } else if (r5 == null) {
                        try {
                            jsonSerializer.A0D(value, r12, r13);
                        } catch (Exception e) {
                            StdSerializer.A05(r13, e, enumMap, ((Enum) entry.getKey()).name());
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        jsonSerializer.A0A(value, r12, r13, r5);
                    }
                }
            }
            return;
        }
        C07170oq r22 = this.A03;
        boolean z2 = !r13._config.A06(AnonymousClass0a9.WRITE_NULL_MAP_VALUES);
        AnonymousClass0o6 r52 = this.A02;
        Class<?> cls = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        for (Map.Entry<? extends Enum<?>, ?> entry2 : enumMap.entrySet()) {
            Object value2 = entry2.getValue();
            if (!z2 || value2 != null) {
                Enum r7 = (Enum) entry2.getKey();
                if (r22 == null) {
                    r22 = ((EnumSerializer) ((StdSerializer) r13.A0B(r7.getDeclaringClass(), this.A00))).A00;
                }
                r12.A0N(r22.A00.get(r7));
                if (value2 == null) {
                    r13.A0D(r12);
                } else {
                    Class<?> cls2 = value2.getClass();
                    if (cls2 != cls) {
                        jsonSerializer2 = r13.A0B(cls2, this.A00);
                        cls = cls2;
                    }
                    if (r52 == null) {
                        jsonSerializer2.A0D(value2, r12, r13);
                    } else {
                        jsonSerializer2.A0A(value2, r12, r13, r52);
                    }
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C05910ld {
        EnumMap<? extends Enum<?>, ?> enumMap = (EnumMap) obj;
        r5.A02(enumMap, r3);
        if (!enumMap.isEmpty()) {
            A00(enumMap, r3, r4);
        }
        r5.A05(enumMap, r3);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(EnumMap<? extends Enum<?>, ?> enumMap) {
        EnumMap<? extends Enum<?>, ?> enumMap2 = enumMap;
        if (enumMap2 == null || enumMap2.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        EnumMap<? extends Enum<?>, ?> enumMap = (EnumMap) obj;
        r3.A0F();
        if (!enumMap.isEmpty()) {
            A00(enumMap, r3, r4);
        }
        r3.A0C();
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer A0E(AnonymousClass0o6 r7) {
        return new EnumMapSerializer(this.A04, this.A05, this.A03, r7, this.A01);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(EnumMap<? extends Enum<?>, ?> enumMap) {
        if (enumMap.size() != 1) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        JsonSerializer<Object> jsonSerializer;
        AbstractC02450Zr A41;
        Object A0U;
        if (r5 == null || (A41 = r5.A41()) == null || (A0U = r4._config.A01().A0U(A41)) == null || (jsonSerializer = r4.A09(A41, A0U)) == null) {
            jsonSerializer = this.A01;
        }
        JsonSerializer<?> A032 = StdSerializer.A03(r4, r5, jsonSerializer);
        if (A032 == null) {
            if (this.A05) {
                JsonSerializer<Object> A08 = r4.A08(this.A04, r5);
                if (this.A00 == r5 && A08 == this.A01) {
                    return this;
                }
                return new EnumMapSerializer(this, r5, A08);
            }
        } else if (this.A01 instanceof AbstractC06840oE) {
            A032 = ((AbstractC06840oE) A032).A1x(r4, r5);
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

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(AnonymousClass0aI r3, boolean z, C07170oq r5, AnonymousClass0o6 r6, JsonSerializer<Object> jsonSerializer) {
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

    public EnumMapSerializer(EnumMapSerializer enumMapSerializer, AbstractC02580aL r3, JsonSerializer<?> jsonSerializer) {
        super(enumMapSerializer);
        this.A00 = r3;
        this.A05 = enumMapSerializer.A05;
        this.A04 = enumMapSerializer.A04;
        this.A03 = enumMapSerializer.A03;
        this.A02 = enumMapSerializer.A02;
        this.A01 = jsonSerializer;
    }
}
