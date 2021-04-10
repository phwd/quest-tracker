package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import java.io.IOException;
import java.net.InetAddress;

public class InetAddressSerializer extends StdScalarSerializer<InetAddress> {
    public static final InetAddressSerializer A00 = new InetAddressSerializer();

    public InetAddressSerializer() {
        super(InetAddress.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final /* bridge */ /* synthetic */ void serialize(Object obj, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C02310iT {
        A00((InetAddress) obj, r2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3, X.0qd] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer
    public final void serializeWithType(InetAddress inetAddress, AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C02310iT {
        InetAddress inetAddress2 = inetAddress;
        r5.A07(inetAddress2, r3, InetAddress.class);
        A00(inetAddress2, r3);
        r5.A06(inetAddress2, r3);
    }

    public static final void A00(InetAddress inetAddress, AbstractC02300iS r3) throws IOException, C02310iT {
        String trim = inetAddress.toString().trim();
        int indexOf = trim.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                trim = trim.substring(1);
            } else {
                trim = trim.substring(0, indexOf);
            }
        }
        r3.A0U(trim);
    }
}
