package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.UUID;

@JacksonStdImpl
/* renamed from: X.0PB  reason: invalid class name */
public final class AnonymousClass0PB extends AbstractC02030hq {
    public static final long serialVersionUID = 1;

    public AnonymousClass0PB() {
        super(UUID.class);
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r3) throws IllegalArgumentException, C02180iD {
        return UUID.fromString(str);
    }
}
