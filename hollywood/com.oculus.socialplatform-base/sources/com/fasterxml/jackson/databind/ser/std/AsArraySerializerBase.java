package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC01990hm;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AbstractC04690qz;
import X.AnonymousClass0i4;
import X.C01810h8;
import X.C02180iD;
import X.C02310iT;
import X.C04670qx;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.Modifier;

public abstract class AsArraySerializerBase<T> extends ContainerSerializer<T> implements AbstractC04600qk {
    public AbstractC04690qz A00;
    public final AbstractC02190iF A01;
    public final AbstractC04550qd A02;
    public final AbstractC02220iI A03;
    public final JsonSerializer<Object> A04;
    public final boolean A05;

    public abstract AsArraySerializerBase<T> A07(AbstractC02220iI v, AbstractC04550qd v2, JsonSerializer<?> jsonSerializer);

    public abstract void A08(T t, AbstractC02300iS v, AbstractC02120i3 v2) throws IOException, C02310iT;

    public final JsonSerializer<Object> A05(AbstractC04690qz r4, AbstractC02190iF r5, AbstractC02120i3 r6) throws C02180iD {
        JsonSerializer<Object> A09 = r6.A09(r5, this.A03);
        C04670qx r1 = new C04670qx(A09, r4.A01(r5._class, A09));
        AbstractC04690qz r0 = r1.A01;
        if (r4 != r0) {
            this.A00 = r0;
        }
        return r1.A00;
    }

    public final JsonSerializer<Object> A06(AbstractC04690qz r4, Class<?> cls, AbstractC02120i3 r6) throws C02180iD {
        JsonSerializer<Object> A0B = r6.A0B(cls, this.A03);
        C04670qx r1 = new C04670qx(A0B, r4.A01(cls, A0B));
        AbstractC04690qz r0 = r1.A01;
        if (r4 != r0) {
            this.A00 = r0;
        }
        return r1.A00;
    }

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r6, AbstractC02220iI r7) throws C02180iD {
        JsonSerializer<Object> jsonSerializer;
        AbstractC01990hm A4N;
        Object A0U;
        AbstractC04550qd r4 = this.A02;
        if (r4 != null) {
            r4 = r4.A00(r7);
        }
        if (r7 == null || (A4N = r7.A4N()) == null || (A0U = r6._config.A01().A0U(A4N)) == null || (jsonSerializer = r6.A0A(A4N, A0U)) == null) {
            jsonSerializer = this.A04;
        }
        JsonSerializer<?> A032 = StdSerializer.A03(r6, r7, jsonSerializer);
        if (A032 == null) {
            AbstractC02190iF r1 = this.A01;
            if (r1 != null && (this.A05 || ContainerSerializer.A02(r6, r7))) {
                A032 = r6.A09(r1, r7);
            }
        } else if (A032 instanceof AbstractC04600qk) {
            A032 = ((AbstractC04600qk) A032).A2P(r6, r7);
        }
        if (A032 == this.A04 && r7 == this.A03 && r4 == r4) {
            return this;
        }
        return A07(r7, r4, A032);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectArrayFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(T t, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        if (!r5._config.A06(AnonymousClass0i4.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !A04(t)) {
            r4.A0H();
            A08(t, r4, r5);
            r4.A0E();
            return;
        }
        A08(t, r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(T t, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C02310iT {
        r4.A01(t, r2);
        A08(t, r2, r3);
        r4.A04(t, r2);
    }

    public AsArraySerializerBase(AsArraySerializerBase<?> asArraySerializerBase, AbstractC02220iI r3, AbstractC04550qd r4, JsonSerializer<?> jsonSerializer) {
        super(asArraySerializerBase);
        this.A01 = asArraySerializerBase.A01;
        this.A05 = asArraySerializerBase.A05;
        this.A02 = r4;
        this.A03 = r3;
        this.A04 = jsonSerializer;
        this.A00 = asArraySerializerBase.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsArraySerializerBase(Class<?> cls, AbstractC02190iF r4, boolean z, AbstractC04550qd r6, AbstractC02220iI r7, JsonSerializer<Object> jsonSerializer) {
        super(cls, false);
        boolean z2 = false;
        this.A01 = r4;
        if (z || (r4 != null && Modifier.isFinal(r4._class.getModifiers()))) {
            z2 = true;
        }
        this.A05 = z2;
        this.A02 = r6;
        this.A03 = r7;
        this.A04 = jsonSerializer;
        this.A00 = C01810h8.A00;
    }
}
