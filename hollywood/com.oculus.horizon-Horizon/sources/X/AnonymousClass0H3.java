package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0H3  reason: invalid class name */
public final class AnonymousClass0H3 extends AbstractC03840gD {
    public static final long serialVersionUID = 1;

    public AnonymousClass0H3() {
        super(Byte.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r4) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -128 && parseInt <= 255) {
            return Byte.valueOf((byte) parseInt);
        }
        r4.A0G(this._keyClass, str);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
