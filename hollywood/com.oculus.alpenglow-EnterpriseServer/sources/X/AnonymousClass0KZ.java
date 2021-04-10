package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0KZ  reason: invalid class name */
public final class AnonymousClass0KZ extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public AnonymousClass0KZ() {
        super(Byte.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -128 && parseInt <= 255) {
            return Byte.valueOf((byte) parseInt);
        }
        throw r4.A0E(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
    }
}
