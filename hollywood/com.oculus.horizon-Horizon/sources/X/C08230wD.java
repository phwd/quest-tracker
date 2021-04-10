package X;

/* renamed from: X.0wD  reason: invalid class name and case insensitive filesystem */
public class C08230wD {
    public int A00;
    public long A01;
    public long A02;
    public String A03;
    public C08430wZ A04;
    public C08420wY A05;
    public EnumC08350wP A06;
    public C08330wN A07;
    public C08220wC A08;
    public C08220wC A09;
    public C08220wC A0A;
    public AbstractC08210wB A0B;

    public final C08220wC A00() {
        String str;
        if (this.A07 == null) {
            str = "request == null";
        } else if (this.A06 != null) {
            int i = this.A00;
            if (i >= 0) {
                return new C08220wC(this);
            }
            str = AnonymousClass006.A01("code < 0: ", i);
        } else {
            str = "protocol == null";
        }
        throw new IllegalStateException(str);
    }

    public C08230wD() {
        this.A00 = -1;
        this.A05 = new C08420wY();
    }

    public C08230wD(C08220wC r3) {
        this.A00 = -1;
        this.A07 = r3.A07;
        this.A06 = r3.A00;
        this.A00 = r3.A01;
        this.A03 = r3.A04;
        this.A04 = r3.A05;
        this.A05 = r3.A06.A01();
        this.A0B = r3.A0B;
        this.A09 = r3.A09;
        this.A08 = r3.A08;
        this.A0A = r3.A0A;
        this.A02 = r3.A03;
        this.A01 = r3.A02;
    }
}
