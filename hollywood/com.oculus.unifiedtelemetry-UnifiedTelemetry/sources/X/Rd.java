package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Rd implements Cloneable {
    public static final Rd A03;
    public static final Rd A04 = new Rd();
    public static final Rd A05;
    public boolean A00 = false;
    public boolean A01 = false;
    public boolean A02 = false;

    static {
        Rd A002 = A00(new Rd());
        A002.A00 = true;
        A03 = A002;
        Rd A003 = A00(new Rd());
        A003.A02 = true;
        A05 = A003;
    }

    public static Rd A00(Rd rd) {
        if (rd != A04 && rd != A03 && rd != A05) {
            return rd;
        }
        try {
            return (Rd) rd.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("");
        }
    }
}
