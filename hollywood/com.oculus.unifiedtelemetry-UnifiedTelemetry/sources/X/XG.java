package X;

import java.io.IOException;

public final class XG implements Cdo {
    public final XJ A00;

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        this.A00.A00.getAndIncrement();
        C0362dj djVar = l3.A01;
        synchronized (XI.class) {
        }
        try {
            C0359dg A002 = l3.A00(djVar);
            synchronized (XI.class) {
            }
            return A002;
        } catch (IOException e) {
            synchronized (XI.class) {
                throw e;
            }
        }
    }

    public XG() {
        XJ xj;
        synchronized (XJ.class) {
            xj = XJ.A01;
            if (xj == null) {
                xj = new XJ();
                XJ.A01 = xj;
            }
        }
        this.A00 = xj;
    }
}
