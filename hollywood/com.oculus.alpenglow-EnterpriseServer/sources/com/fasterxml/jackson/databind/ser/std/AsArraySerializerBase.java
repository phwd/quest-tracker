package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02450Zr;
import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AbstractC06960oT;
import X.AnonymousClass0ZY;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C06940oR;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.Modifier;

public abstract class AsArraySerializerBase<T> extends ContainerSerializer<T> implements AbstractC06840oE {
    public AbstractC06960oT A00;
    public final AnonymousClass0aI A01;
    public final AnonymousClass0o6 A02;
    public final AbstractC02580aL A03;
    public final JsonSerializer<Object> A04;
    public final boolean A05;

    public abstract AsArraySerializerBase<T> A0I(AbstractC02580aL v, AnonymousClass0o6 v2, JsonSerializer<?> jsonSerializer);

    public abstract void A0J(T t, AbstractC02640aV v, AnonymousClass0a8 v2) throws IOException, C02650aW;

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(T t, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        if (!r5._config.A06(AnonymousClass0a9.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !A0F(t)) {
            r4.A0E();
            A0J(t, r4, r5);
            r4.A0B();
            return;
        }
        A0J(t, r4, r5);
    }

    public final JsonSerializer<Object> A0G(AbstractC06960oT r4, AnonymousClass0aI r5, AnonymousClass0a8 r6) throws AnonymousClass0aG {
        JsonSerializer<Object> A08 = r6.A08(r5, this.A03);
        C06940oR r1 = new C06940oR(A08, r4.A01(r5._class, A08));
        AbstractC06960oT r0 = r1.A01;
        if (r4 != r0) {
            this.A00 = r0;
        }
        return r1.A00;
    }

    public final JsonSerializer<Object> A0H(AbstractC06960oT r4, Class<?> cls, AnonymousClass0a8 r6) throws AnonymousClass0aG {
        JsonSerializer<Object> A0B = r6.A0B(cls, this.A03);
        C06940oR r1 = new C06940oR(A0B, r4.A01(cls, A0B));
        AbstractC06960oT r0 = r1.A01;
        if (r4 != r0) {
            this.A00 = r0;
        }
        return r1.A00;
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r6, AbstractC02580aL r7) throws AnonymousClass0aG {
        JsonSerializer<Object> jsonSerializer;
        AbstractC02450Zr A41;
        Object A0U;
        AnonymousClass0o6 r4 = this.A02;
        if (r4 != null) {
            r4 = r4.A00(r7);
        }
        if (r7 == null || (A41 = r7.A41()) == null || (A0U = r6._config.A01().A0U(A41)) == null || (jsonSerializer = r6.A09(A41, A0U)) == null) {
            jsonSerializer = this.A04;
        }
        JsonSerializer<?> A032 = StdSerializer.A03(r6, r7, jsonSerializer);
        if (A032 == null) {
            AnonymousClass0aI r1 = this.A01;
            if (r1 != null && (this.A05 || ContainerSerializer.A02(r6, r7))) {
                A032 = r6.A08(r1, r7);
            }
        } else if (A032 instanceof AbstractC06840oE) {
            A032 = ((AbstractC06840oE) A032).A1x(r6, r7);
        }
        if (A032 == this.A04 && r7 == this.A03 && r4 == r4) {
            return this;
        }
        return A0I(r7, r4, A032);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(T t, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C02650aW {
        r4.A01(t, r2);
        A0J(t, r2, r3);
        r4.A04(t, r2);
    }

    public AsArraySerializerBase(AsArraySerializerBase<?> asArraySerializerBase, AbstractC02580aL r3, AnonymousClass0o6 r4, JsonSerializer<?> jsonSerializer) {
        super(asArraySerializerBase);
        this.A01 = asArraySerializerBase.A01;
        this.A05 = asArraySerializerBase.A05;
        this.A02 = r4;
        this.A03 = r3;
        this.A04 = jsonSerializer;
        this.A00 = asArraySerializerBase.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsArraySerializerBase(Class<?> cls, AnonymousClass0aI r4, boolean z, AnonymousClass0o6 r6, AbstractC02580aL r7, JsonSerializer<Object> jsonSerializer) {
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
        this.A00 = AnonymousClass0ZY.A00;
    }
}
