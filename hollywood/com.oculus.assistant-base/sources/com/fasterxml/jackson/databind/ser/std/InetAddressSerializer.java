package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1012qg;
import java.net.InetAddress;

public class InetAddressSerializer extends StdScalarSerializer {
    public static final InetAddressSerializer A00 = new InetAddressSerializer();

    public InetAddressSerializer() {
        super(InetAddress.class);
    }

    public static final void A00(InetAddress inetAddress, AbstractC1012qg qgVar) {
        String trim = inetAddress.toString().trim();
        int indexOf = trim.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                trim = trim.substring(1);
            } else {
                trim = trim.substring(0, indexOf);
            }
        }
        qgVar.A0P(trim);
    }
}
