package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class CY extends WN {
    public static final long serialVersionUID = 1;

    public CY() {
        super(Float.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return Float.valueOf((float) p2.A00(str));
    }
}
