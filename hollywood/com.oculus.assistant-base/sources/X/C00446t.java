package X;

/* renamed from: X.6t  reason: invalid class name and case insensitive filesystem */
public class C00446t {
    public C0847jr A00;
    public long A01;
    public long A02 = 0;
    public AnonymousClass6J A03;
    public EnumC00486y A04;
    public C0847jr A05;
    public String A06;
    public String A07;
    public boolean A08;
    public volatile boolean A09;

    public static final void A00(C00446t r1) {
        if (!r1.A08) {
            throw new IllegalStateException("isSampled was not invoked, how can you have known?");
        } else if (!r1.A09) {
            throw new IllegalStateException("Expected mutability");
        }
    }

    public final C00446t A01(String str, Boolean bool) {
        if (!(this instanceof C0685fM)) {
            A00(this);
            C0847jr.A01(A04(), str, bool);
            synchronized (this) {
                if (bool != null) {
                    this.A01 += 4;
                }
            }
        }
        return this;
    }

    public final C00446t A02(String str, Number number) {
        if (!(this instanceof C0685fM)) {
            A00(this);
            C0847jr.A01(A04(), str, number);
            synchronized (this) {
                if (number != null) {
                    this.A01 += 4;
                }
            }
        }
        return this;
    }

    public final C00446t A03(String str, String str2) {
        if (!(this instanceof C0685fM)) {
            A00(this);
            C0847jr.A01(A04(), str, str2);
            synchronized (this) {
                if (str2 != null) {
                    this.A01 += (long) str2.length();
                }
            }
        }
        return this;
    }

    public final C0847jr A04() {
        EnumC00486y r0;
        if (!(this instanceof C0685fM)) {
            A00(this);
            if (this.A00 == null) {
                C0847jr A002 = this.A03.A0C.A00();
                this.A00 = A002;
                C0847jr jrVar = this.A05;
                if (!(jrVar == null || (r0 = this.A04) == null)) {
                    String extraJsonKey = r0.getExtraJsonKey();
                    C0847jr.A00(jrVar, extraJsonKey);
                    A002.A02();
                    C0847jr.A01(jrVar, extraJsonKey, A002);
                    A002.A02();
                    ((DQ) A002).A00 = jrVar;
                }
            }
            return this.A00;
        }
        C0685fM fMVar = (C0685fM) this;
        C0847jr jrVar2 = fMVar.A00;
        if (jrVar2 != null) {
            return jrVar2;
        }
        C0847jr A003 = C0685fM.A01.A0C.A00();
        fMVar.A00 = A003;
        return A003;
    }

    public final boolean A05() {
        if (this instanceof C0685fM) {
            return false;
        }
        this.A08 = true;
        return true;
    }
}
