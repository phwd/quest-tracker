package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.Cg  reason: case insensitive filesystem */
public final class C0072Cg extends WN {
    public static final long serialVersionUID = 1;

    public C0072Cg() {
        super(Byte.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -128 && parseInt <= 255) {
            return Byte.valueOf((byte) parseInt);
        }
        throw wn.A0B(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
    }
}
