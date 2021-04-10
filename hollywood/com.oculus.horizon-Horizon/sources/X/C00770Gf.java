package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.UUID;

@JacksonStdImpl
/* renamed from: X.0Gf  reason: invalid class name and case insensitive filesystem */
public final class C00770Gf extends AbstractC03840gD {
    public static final long serialVersionUID = 1;

    public C00770Gf() {
        super(UUID.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r3) throws IllegalArgumentException, C03990gZ {
        return UUID.fromString(str);
    }
}
