package X;

import java.lang.ref.ReferenceQueue;

public final class F8 {
    public static final F6 A00 = new F6();
    public static final F7 A01 = new F7();
    public static final ReferenceQueue A02 = new ReferenceQueue();
    public static final Thread A03;

    static {
        F4 f4 = new F4();
        A03 = f4;
        f4.start();
    }
}
