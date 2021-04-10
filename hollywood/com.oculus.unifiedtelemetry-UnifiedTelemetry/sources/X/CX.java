package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class CX extends WN {
    public static final long serialVersionUID = 1;

    public CX() {
        super(Integer.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return Integer.valueOf(Integer.parseInt(str));
    }
}
