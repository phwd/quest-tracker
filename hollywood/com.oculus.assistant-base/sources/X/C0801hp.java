package X;

import java.io.InputStream;

/* renamed from: X.hp  reason: case insensitive filesystem */
public final class C0801hp extends AbstractC0100Ae implements AbstractC0106Ak {
    public final At A00;
    public final InputStream A01;

    public C0801hp(InputStream inputStream) {
        At at = At.RAW;
        this.A01 = inputStream;
        this.A00 = at;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
