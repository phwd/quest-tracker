package X;

import java.lang.ref.ReferenceQueue;

/* renamed from: X.Pd  reason: case insensitive filesystem */
public final class C0114Pd {
    public static final C0112Pb A00 = new C0112Pb();
    public static final C0113Pc A01 = new C0113Pc();
    public static final ReferenceQueue A02 = new ReferenceQueue();
    public static final Thread A03;

    static {
        PZ pz = new PZ();
        A03 = pz;
        pz.start();
    }
}
