package X;

/* renamed from: X.0M5  reason: invalid class name */
public class AnonymousClass0M5 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.datasource.AbstractDataSource$2";
    public final /* synthetic */ AbstractC03830od A00;
    public final /* synthetic */ AnonymousClass0MB A01;

    public AnonymousClass0M5(AbstractC03830od r1, AnonymousClass0MB r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        this.A01.onProgressUpdate(this.A00);
    }
}
