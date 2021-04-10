package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0KO  reason: invalid class name */
public final class AnonymousClass0KO extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public AnonymousClass0KO() {
        super(Integer.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -32768 && parseInt <= 32767) {
            return Short.valueOf((short) parseInt);
        }
        throw r4.A0E(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
    }
}
