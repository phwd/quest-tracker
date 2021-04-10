package X;

/* renamed from: X.bq  reason: case insensitive filesystem */
public final class C0553bq {
    public int A00;
    public long A01;
    public long A02;
    public String A03;
    public C0540bd A04;
    public C0541be A05;
    public EnumC0549bm A06;
    public C0551bo A07;
    public C0554br A08;
    public C0554br A09;
    public C0554br A0A;
    public AbstractC0555bs A0B;

    public final C0554br A00() {
        if (this.A07 == null) {
            throw new IllegalStateException("request == null");
        } else if (this.A06 != null) {
            int i = this.A00;
            if (i >= 0) {
                return new C0554br(this);
            }
            throw new IllegalStateException(AnonymousClass08.A00("code < 0: ", i));
        } else {
            throw new IllegalStateException("protocol == null");
        }
    }

    public C0553bq() {
        this.A00 = -1;
        this.A05 = new C0541be();
    }

    public C0553bq(C0554br brVar) {
        this.A00 = -1;
        this.A07 = brVar.A07;
        this.A06 = brVar.A01;
        this.A00 = brVar.A02;
        this.A03 = brVar.A00;
        this.A04 = brVar.A05;
        this.A05 = brVar.A06.A01();
        this.A0B = brVar.A0B;
        this.A09 = brVar.A09;
        this.A08 = brVar.A08;
        this.A0A = brVar.A0A;
        this.A02 = brVar.A04;
        this.A01 = brVar.A03;
    }
}
