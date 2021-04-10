package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.Cc  reason: case insensitive filesystem */
public final class C0069Cc extends WN {
    public static final long serialVersionUID = 1;

    public C0069Cc() {
        super(Double.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return Double.valueOf(p2.A00(str));
    }
}
