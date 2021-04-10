package X;

/* renamed from: X.2aF  reason: invalid class name */
public final class AnonymousClass2aF {
    public String A00;
    public float A01;
    public int A02;
    public int A03;
    public Integer A04;
    public String A05;
    public boolean A06;

    private final void A00(Object obj) {
        switch (this.A04.intValue()) {
            case 0:
                this.A03 = ((Number) obj).intValue();
                return;
            case 1:
            case 6:
                this.A01 = ((Number) obj).floatValue();
                return;
            case 2:
            case 3:
                this.A02 = ((Number) obj).intValue();
                return;
            case 4:
                this.A05 = (String) obj;
                return;
            case 5:
                this.A06 = ((Boolean) obj).booleanValue();
                return;
            default:
                return;
        }
    }

    public AnonymousClass2aF(AnonymousClass2aF r2, Object obj) {
        this.A00 = r2.A00;
        this.A04 = r2.A04;
        A00(obj);
    }

    public AnonymousClass2aF(String str, Integer num, Object obj) {
        this.A00 = str;
        this.A04 = num;
        A00(obj);
    }
}
