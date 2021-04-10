package X;

/* renamed from: X.0ka  reason: invalid class name and case insensitive filesystem */
public class C05680ka {
    public int A00;
    public long A01;
    public long A02;
    public String A03;
    public C06040lr A04;
    public C06030lq A05;
    public AnonymousClass0kh A06;
    public C05700ke A07;
    public C05660kD A08;
    public C05660kD A09;
    public C05660kD A0A;
    public AbstractC05650kC A0B;

    public final C05660kD A00() {
        String str;
        if (this.A07 == null) {
            str = "request == null";
        } else if (this.A06 != null) {
            int i = this.A00;
            if (i >= 0) {
                return new C05660kD(this);
            }
            str = AnonymousClass006.A01("code < 0: ", i);
        } else {
            str = "protocol == null";
        }
        throw new IllegalStateException(str);
    }

    public C05680ka() {
        this.A00 = -1;
        this.A05 = new C06030lq();
    }

    public C05680ka(C05660kD r3) {
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
