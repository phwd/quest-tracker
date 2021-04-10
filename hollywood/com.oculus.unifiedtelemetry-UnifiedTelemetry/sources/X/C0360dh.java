package X;

/* renamed from: X.dh  reason: case insensitive filesystem */
public class C0360dh {
    public int A00;
    public long A01;
    public long A02;
    public String A03;
    public C0371du A04;
    public C0370dt A05;
    public EnumC0364dl A06;
    public C0362dj A07;
    public C0359dg A08;
    public C0359dg A09;
    public C0359dg A0A;
    public AbstractC0358df A0B;

    public final C0359dg A00() {
        String str;
        if (this.A07 == null) {
            str = "request == null";
        } else if (this.A06 != null) {
            int i = this.A00;
            if (i >= 0) {
                return new C0359dg(this);
            }
            str = AnonymousClass06.A01("code < 0: ", i);
        } else {
            str = "protocol == null";
        }
        throw new IllegalStateException(str);
    }

    public C0360dh() {
        this.A00 = -1;
        this.A05 = new C0370dt();
    }

    public C0360dh(C0359dg dgVar) {
        this.A00 = -1;
        this.A07 = dgVar.A07;
        this.A06 = dgVar.A00;
        this.A00 = dgVar.A01;
        this.A03 = dgVar.A04;
        this.A04 = dgVar.A05;
        this.A05 = dgVar.A06.A01();
        this.A0B = dgVar.A0B;
        this.A09 = dgVar.A09;
        this.A08 = dgVar.A08;
        this.A0A = dgVar.A0A;
        this.A02 = dgVar.A03;
        this.A01 = dgVar.A02;
    }
}
