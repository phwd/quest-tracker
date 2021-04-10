package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Pc  reason: invalid class name and case insensitive filesystem */
public final class C01010Pc extends AbstractC02030hq {
    public static final long serialVersionUID = 1;

    public C01010Pc() {
        super(Long.class);
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r4) throws Exception {
        return Long.valueOf(Long.parseLong(str));
    }
}
