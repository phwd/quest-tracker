package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.cy  reason: case insensitive filesystem */
public interface AbstractC0331cy {
    public static final AbstractC0331cy A00 = new C0110Kg();

    boolean onData(int i, KC kc, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<C0343dC> list, boolean z);

    boolean onRequest(int i, List<C0343dC> list);
}
