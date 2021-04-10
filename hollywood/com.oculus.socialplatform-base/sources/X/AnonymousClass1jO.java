package X;

/* renamed from: X.1jO  reason: invalid class name */
public class AnonymousClass1jO extends AbstractC03820oc<T> {
    public final /* synthetic */ AbstractC09891kF A00;
    public final /* synthetic */ String A01;
    public final /* synthetic */ boolean A02;

    @Override // X.AbstractC03820oc
    public final void onNewResultImpl(AnonymousClass0M8<T> r11) {
        boolean A5y = r11.A5y();
        boolean A5Q = r11.A5Q();
        float A4g = r11.A4g();
        T A4p = r11.A4p();
        if (A4p != null) {
            AbstractC09891kF.A05(this.A00, this.A01, r11, A4p, A4g, A5y, this.A02, A5Q);
        } else if (A5y) {
            AbstractC09891kF.A06(this.A00, this.A01, r11, new NullPointerException(), true);
        }
    }

    public AnonymousClass1jO(AbstractC09891kF r1, String str, boolean z) {
        this.A00 = r1;
        this.A01 = str;
        this.A02 = z;
    }

    @Override // X.AbstractC03820oc
    public final void onFailureImpl(AnonymousClass0M8<T> r5) {
        AbstractC09891kF.A06(this.A00, this.A01, r5, r5.A3w(), true);
    }

    @Override // X.AnonymousClass0MB, X.AbstractC03820oc
    public final void onProgressUpdate(AnonymousClass0M8<T> r5) {
        boolean A5y = r5.A5y();
        float A4g = r5.A4g();
        AbstractC09891kF r1 = this.A00;
        if (!AbstractC09891kF.A09(r1, this.A01, r5)) {
            r5.A29();
        } else if (!A5y) {
            r1.A04.A08(A4g, false);
        }
    }
}
