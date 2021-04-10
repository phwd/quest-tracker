package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aG;
import X.C02650aW;
import X.C05750kz;
import X.C07170oq;
import X.EnumC05740ky;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class EnumSerializer extends StdScalarSerializer<Enum<?>> implements AbstractC06840oE {
    public final C07170oq A00;
    public final Boolean A01;

    public static Boolean A00(Class<?> cls, C05750kz r4, boolean z) {
        EnumC05740ky r2;
        String str;
        if (r4 == null || (r2 = r4.A00) == null || r2 == EnumC05740ky.ANY || r2 == EnumC05740ky.SCALAR) {
            return null;
        }
        if (r2 == EnumC05740ky.STRING) {
            return Boolean.FALSE;
        }
        if (r2.isNumeric()) {
            return Boolean.TRUE;
        }
        StringBuilder sb = new StringBuilder("Unsupported serialization shape (");
        sb.append(r2);
        sb.append(") for Enum ");
        sb.append(cls.getName());
        sb.append(", not supported as ");
        if (z) {
            str = "class";
        } else {
            str = "property";
        }
        sb.append(str);
        sb.append(" annotation");
        throw new IllegalArgumentException(sb.toString());
    }

    public EnumSerializer(C07170oq r3, Boolean bool) {
        super(Enum.class, false);
        this.A00 = r3;
        this.A01 = bool;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        boolean A06;
        Enum r3 = (Enum) obj;
        Boolean bool = this.A01;
        if (bool != null) {
            A06 = bool.booleanValue();
        } else {
            A06 = r5._config.A06(AnonymousClass0a9.WRITE_ENUMS_USING_INDEX);
        }
        if (A06) {
            r4.A0J(r3.ordinal());
        } else {
            r4.A0O(this.A00.A00.get(r3));
        }
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        C05750kz A012;
        Boolean A002;
        if (r5 == null || (A012 = r4._config.A01().A01(r5.A41())) == null || (A002 = A00(r5.A4h()._class, A012, false)) == this.A01) {
            return this;
        }
        return new EnumSerializer(this.A00, A002);
    }
}
