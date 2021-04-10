package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class Ch extends WN {
    public static final long serialVersionUID = 1;

    public Ch() {
        super(Boolean.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        if ("true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("false".equals(str)) {
            return Boolean.FALSE;
        }
        throw wn.A0B(this._keyClass, str, "value not 'true' or 'false'");
    }
}
