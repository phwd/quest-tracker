package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.UUID;

@JacksonStdImpl
public final class CK extends WN {
    public static final long serialVersionUID = 1;

    public CK() {
        super(UUID.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws IllegalArgumentException, C0223Wj {
        return UUID.fromString(str);
    }
}
