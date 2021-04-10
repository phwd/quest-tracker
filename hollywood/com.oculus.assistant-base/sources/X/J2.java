package X;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class J2 {
    public static J2 A0B;
    public static final C0942pK A0C = new C0942pK();
    public static final J4 A0D = new J4();
    public static final JB A0E = new JB();
    public YA A00 = null;
    public YA A01 = null;
    public X8 A02 = null;
    public AnonymousClass2S A03 = null;
    public X3 A04 = null;
    public YA A05 = null;
    public Map A06 = new HashMap();
    public Map A07 = new HashMap();
    public final List A08 = J1.A00;
    public final Map A09 = new HashMap();
    public final Map A0A = new HashMap();

    public final synchronized YA A01() {
        YA ya;
        ya = this.A05;
        if (ya == null) {
            AnonymousClass2S r3 = this.A03;
            if (r3 == null) {
                r3 = new AnonymousClass2S(A0D, A0C, A0E);
                this.A03 = r3;
            }
            ya = new YA(r3, this.A08);
            this.A05 = ya;
        }
        return ya;
    }

    public static synchronized J2 A00() {
        J2 j2;
        synchronized (J2.class) {
            j2 = A0B;
            if (j2 == null) {
                j2 = new J2();
                A0B = j2;
            }
        }
        return j2;
    }
}
