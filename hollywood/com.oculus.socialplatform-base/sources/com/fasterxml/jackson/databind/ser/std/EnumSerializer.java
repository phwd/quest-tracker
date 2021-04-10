package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04600qk;
import X.AnonymousClass0i4;
import X.AnonymousClass0nW;
import X.C02180iD;
import X.C02310iT;
import X.C03550nX;
import X.C04830rM;
import bolts.WebViewAppLinkResolver;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements AbstractC04600qk {
    public final C04830rM A00;
    public final Boolean A01;

    public static Boolean A00(Class<?> cls, C03550nX r4, boolean z) {
        AnonymousClass0nW r2;
        String str;
        if (r4 == null || (r2 = r4.A00) == null || r2 == AnonymousClass0nW.ANY || r2 == AnonymousClass0nW.SCALAR) {
            return null;
        }
        if (r2 == AnonymousClass0nW.STRING) {
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
            str = WebViewAppLinkResolver.KEY_CLASS;
        } else {
            str = "property";
        }
        sb.append(str);
        sb.append(" annotation");
        throw new IllegalArgumentException(sb.toString());
    }

    public EnumSerializer(C04830rM r3, Boolean bool) {
        super(Enum.class, false);
        this.A00 = r3;
        this.A01 = bool;
    }

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r4, AbstractC02220iI r5) throws C02180iD {
        C03550nX A012;
        Boolean A002;
        if (r5 == null || (A012 = r4._config.A01().A01(r5.A4N())) == null || (A002 = A00(r5.A59()._class, A012, false)) == this.A01) {
            return this;
        }
        return new EnumSerializer(this.A00, A002);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("getProvider");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        boolean A06;
        Enum r3 = (Enum) obj;
        Boolean bool = this.A01;
        if (bool != null) {
            A06 = bool.booleanValue();
        } else {
            A06 = r5._config.A06(AnonymousClass0i4.WRITE_ENUMS_USING_INDEX);
        }
        if (A06) {
            r4.A0M(r3.ordinal());
        } else {
            r4.A0Q(this.A00.A00.get(r3));
        }
    }
}
