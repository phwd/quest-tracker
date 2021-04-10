package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import java.io.IOException;
import java.net.InetAddress;

public final class InetAddressSerializer extends StdScalarSerializer<InetAddress> {
    public static final InetAddressSerializer A00 = new InetAddressSerializer();

    public InetAddressSerializer() {
        super(InetAddress.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8, X.0o6] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer
    public final void A0A(InetAddress inetAddress, AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C02650aW {
        InetAddress inetAddress2 = inetAddress;
        r5.A07(inetAddress2, r3, InetAddress.class);
        A00(inetAddress2, r3);
        r5.A06(inetAddress2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final /* bridge */ /* synthetic */ void A0D(Object obj, AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C02650aW {
        A00((InetAddress) obj, r2);
    }

    public static final void A00(InetAddress inetAddress, AbstractC02640aV r3) throws IOException, C02650aW {
        String trim = inetAddress.toString().trim();
        int indexOf = trim.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                trim = trim.substring(1);
            } else {
                trim = trim.substring(0, indexOf);
            }
        }
        r3.A0S(trim);
    }
}
