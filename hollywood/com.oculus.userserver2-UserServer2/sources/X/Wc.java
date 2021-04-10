package X;

import java.io.IOException;
import java.util.List;

public interface Wc {
    public static final Wc A00 = new E6();

    boolean onData(int i, Dp dp, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<C0165Wq> list, boolean z);

    boolean onRequest(int i, List<C0165Wq> list);
}
