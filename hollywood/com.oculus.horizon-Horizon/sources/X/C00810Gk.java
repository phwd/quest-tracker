package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0Gk  reason: invalid class name and case insensitive filesystem */
public final class C00810Gk extends AbstractC03840gD {
    public static final long serialVersionUID = 1;

    public C00810Gk() {
        super(Integer.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r4) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -32768 && parseInt <= 32767) {
            return Short.valueOf((short) parseInt);
        }
        r4.A0G(this._keyClass, str);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
