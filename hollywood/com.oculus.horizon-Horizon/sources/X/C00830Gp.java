package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Gp  reason: invalid class name and case insensitive filesystem */
public final class C00830Gp extends AbstractC03840gD {
    public static final long serialVersionUID = 1;

    public C00830Gp() {
        super(Integer.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r3) throws Exception {
        return Integer.valueOf(Integer.parseInt(str));
    }
}
