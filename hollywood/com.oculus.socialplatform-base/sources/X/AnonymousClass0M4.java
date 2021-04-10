package X;

/* renamed from: X.0M4  reason: invalid class name */
public class AnonymousClass0M4 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.datasource.AbstractDataSource$1";
    public final /* synthetic */ AbstractC03830od A00;
    public final /* synthetic */ AnonymousClass0MB A01;
    public final /* synthetic */ boolean A02;
    public final /* synthetic */ boolean A03;

    public AnonymousClass0M4(AbstractC03830od r1, boolean z, AnonymousClass0MB r3, boolean z2) {
        this.A00 = r1;
        this.A03 = z;
        this.A01 = r3;
        this.A02 = z2;
    }

    public final void run() {
        if (this.A03) {
            this.A01.onFailure(this.A00);
        } else if (!this.A02) {
            this.A01.onNewResult(this.A00);
        }
    }
}
