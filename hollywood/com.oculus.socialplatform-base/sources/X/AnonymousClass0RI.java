package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0RI  reason: invalid class name */
public final class AnonymousClass0RI extends AbstractC02030hq {
    public static final long serialVersionUID = 1;

    public AnonymousClass0RI() {
        super(Boolean.class);
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r4) throws Exception {
        if ("true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("false".equals(str)) {
            return Boolean.FALSE;
        }
        throw r4.A0E(this._keyClass, str, "value not 'true' or 'false'");
    }
}
