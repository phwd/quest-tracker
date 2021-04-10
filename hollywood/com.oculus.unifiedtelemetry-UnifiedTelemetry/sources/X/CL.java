package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class CL extends WN {
    public static final CL A00 = new CL(Object.class);
    public static final CL A01 = new CL(String.class);
    public static final long serialVersionUID = 1;

    public CL(Class<?> cls) {
        super(cls);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return str;
    }
}
