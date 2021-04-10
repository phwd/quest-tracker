package X;

/* renamed from: X.1tD  reason: invalid class name */
public class AnonymousClass1tD implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.datasource.AbstractDataSource$1";
    public final /* synthetic */ AnonymousClass1r1 A00;
    public final /* synthetic */ AnonymousClass1tB A01;
    public final /* synthetic */ boolean A02;
    public final /* synthetic */ boolean A03;

    public AnonymousClass1tD(AnonymousClass1r1 r1, boolean z, AnonymousClass1tB r3, boolean z2) {
        this.A00 = r1;
        this.A03 = z;
        this.A01 = r3;
        this.A02 = z2;
    }

    public final void run() {
        if (this.A03) {
            this.A01.A69(this.A00);
        } else if (!this.A02) {
            this.A01.A6K(this.A00);
        }
    }
}
