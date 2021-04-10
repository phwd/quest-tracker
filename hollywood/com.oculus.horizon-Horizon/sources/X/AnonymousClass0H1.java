package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0H1  reason: invalid class name */
public final class AnonymousClass0H1 extends AbstractC03840gD {
    public static final long serialVersionUID = 1;

    public AnonymousClass0H1() {
        super(Character.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r4) throws Exception {
        if (str.length() == 1) {
            return Character.valueOf(str.charAt(0));
        }
        r4.A0G(this._keyClass, str);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
