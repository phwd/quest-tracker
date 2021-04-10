package X;

import java.io.IOException;

public final class S0 implements XS {
    public final S3 A00;

    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        this.A00.A00.getAndIncrement();
        XN xn = eu.A01;
        synchronized (S2.class) {
        }
        try {
            XK A002 = eu.A00(xn);
            synchronized (S2.class) {
            }
            return A002;
        } catch (IOException e) {
            synchronized (S2.class) {
                throw e;
            }
        }
    }

    public S0() {
        S3 s3;
        synchronized (S3.class) {
            s3 = S3.A01;
            if (s3 == null) {
                s3 = new S3();
                S3.A01 = s3;
            }
        }
        this.A00 = s3;
    }
}
