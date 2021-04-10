package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.UUID;

@JacksonStdImpl
/* renamed from: X.0KK  reason: invalid class name */
public final class AnonymousClass0KK extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public AnonymousClass0KK() {
        super(UUID.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r3) throws IllegalArgumentException, AnonymousClass0aG {
        return UUID.fromString(str);
    }
}
