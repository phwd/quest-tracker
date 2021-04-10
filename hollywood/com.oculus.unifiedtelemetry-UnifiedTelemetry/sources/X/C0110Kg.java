package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.Kg  reason: case insensitive filesystem */
public class C0110Kg implements AbstractC0331cy {
    @Override // X.AbstractC0331cy
    public final boolean onData(int i, KC kc, int i2, boolean z) throws IOException {
        kc.A5F((long) i2);
        return true;
    }

    @Override // X.AbstractC0331cy
    public final boolean onHeaders(int i, List<C0343dC> list, boolean z) {
        return true;
    }

    @Override // X.AbstractC0331cy
    public final boolean onRequest(int i, List<C0343dC> list) {
        return true;
    }
}
