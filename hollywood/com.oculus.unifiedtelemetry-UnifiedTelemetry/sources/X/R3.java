package X;

import java.lang.ref.ReferenceQueue;

public final class R3 {
    public static final R1 A00 = new R1();
    public static final R2 A01 = new R2();
    public static final ReferenceQueue A02 = new ReferenceQueue();
    public static final Thread A03;

    static {
        C0137Qz qz = new C0137Qz();
        A03 = qz;
        qz.start();
    }
}
