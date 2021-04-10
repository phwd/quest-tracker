package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class CU extends WN {
    public static final long serialVersionUID = 1;

    public CU() {
        super(Long.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return Long.valueOf(Long.parseLong(str));
    }
}
