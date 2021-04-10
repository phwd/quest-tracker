package X;

import java.io.IOException;

/* renamed from: X.0hL  reason: invalid class name and case insensitive filesystem */
public final class C04710hL extends IOException {
    public final EnumC04880hs errorCode;

    public C04710hL(EnumC04880hs r3) {
        super("stream was reset: " + r3);
        this.errorCode = r3;
    }
}
