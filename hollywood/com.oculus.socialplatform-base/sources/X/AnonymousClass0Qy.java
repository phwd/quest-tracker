package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Qy  reason: invalid class name */
public final class AnonymousClass0Qy extends AbstractC02030hq {
    public static final long serialVersionUID = 1;

    public AnonymousClass0Qy() {
        super(Byte.class);
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r4) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -128 && parseInt <= 255) {
            return Byte.valueOf((byte) parseInt);
        }
        throw r4.A0E(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
    }
}
