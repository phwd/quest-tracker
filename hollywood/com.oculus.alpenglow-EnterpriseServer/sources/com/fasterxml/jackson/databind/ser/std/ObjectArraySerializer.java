package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02450Zr;
import X.AbstractC02580aL;
import X.AbstractC06840oE;
import X.AbstractC06960oT;
import X.AnonymousClass0ZY;
import X.AnonymousClass0a8;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;

@JacksonStdImpl
public final class ObjectArraySerializer extends ArraySerializerBase<Object[]> implements AbstractC06840oE {
    public JsonSerializer<Object> A00;
    public AbstractC06960oT A01;
    public final AnonymousClass0o6 A02;
    public final AnonymousClass0aI A03;
    public final boolean A04;

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        Object[] objArr = (Object[]) obj;
        if (objArr == null || objArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r5) {
        return new ObjectArraySerializer(this.A03, this.A04, r5, this.A00);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((Object[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006c, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006f, code lost:
        if ((r1 instanceof java.lang.reflect.InvocationTargetException) != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0077, code lost:
        r1 = r1.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00d8, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00db, code lost:
        if ((r1 instanceof java.lang.reflect.InvocationTargetException) != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e3, code lost:
        r1 = r1.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00ea, code lost:
        if ((r1 instanceof java.lang.Error) == false) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00ec, code lost:
        r0 = new X.C06290mV((java.lang.Object) null, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00f6, code lost:
        r0 = new X.C06290mV(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x00ff, code lost:
        throw X.AnonymousClass0aG.A01(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0101, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0102, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x000d, B:24:0x0039] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x000d, B:46:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0101 A[ExcHandler: IOException (r0v0 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:5:0x000d] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0G(java.lang.Object[] r9, X.AbstractC02640aV r10, X.AnonymousClass0a8 r11) throws java.io.IOException, X.C02650aW {
        /*
        // Method dump skipped, instructions count: 260
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer.A0G(java.lang.Object, X.0aV, X.0a8):void");
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r6, AbstractC02580aL r7) throws AnonymousClass0aG {
        JsonSerializer<Object> jsonSerializer;
        AbstractC02450Zr A41;
        Object A0U;
        AnonymousClass0o6 r3 = this.A02;
        if (r3 != null) {
            r3 = r3.A00(r7);
        }
        if (r7 == null || (A41 = r7.A41()) == null || (A0U = r6._config.A01().A0U(A41)) == null || (jsonSerializer = r6.A09(A41, A0U)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer<?> A032 = StdSerializer.A03(r6, r7, jsonSerializer);
        if (A032 == null) {
            AnonymousClass0aI r1 = this.A03;
            if (r1 != null && (this.A04 || ContainerSerializer.A02(r6, r7))) {
                A032 = r6.A08(r1, r7);
            }
        } else if (A032 instanceof AbstractC06840oE) {
            A032 = ((AbstractC06840oE) A032).A1x(r6, r7);
        }
        if (((ArraySerializerBase) this).A00 == r7 && A032 == this.A00 && r3 == r3) {
            return this;
        }
        return new ObjectArraySerializer(this, r7, r3, A032);
    }

    public ObjectArraySerializer(AnonymousClass0aI r3, boolean z, AnonymousClass0o6 r5, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class, (AbstractC02580aL) null);
        this.A03 = r3;
        this.A04 = z;
        this.A02 = r5;
        this.A01 = AnonymousClass0ZY.A00;
        this.A00 = jsonSerializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, AbstractC02580aL r3, AnonymousClass0o6 r4, JsonSerializer<?> jsonSerializer) {
        super(objectArraySerializer, r3);
        this.A03 = objectArraySerializer.A03;
        this.A02 = r4;
        this.A04 = objectArraySerializer.A04;
        this.A01 = objectArraySerializer.A01;
        this.A00 = jsonSerializer;
    }
}
