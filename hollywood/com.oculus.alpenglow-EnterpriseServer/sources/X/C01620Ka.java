package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Ka  reason: invalid class name and case insensitive filesystem */
public final class C01620Ka extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public C01620Ka() {
        super(Boolean.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws Exception {
        if ("true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("false".equals(str)) {
            return Boolean.FALSE;
        }
        throw r4.A0E(this._keyClass, str, "value not 'true' or 'false'");
    }
}
