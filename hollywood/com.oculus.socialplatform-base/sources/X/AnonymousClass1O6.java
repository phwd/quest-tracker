package X;

/* renamed from: X.1O6  reason: invalid class name */
public class AnonymousClass1O6 extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.network.system.HttpUrlConnectionNetworkSessionListenerManager$4";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ int A02;
    public final /* synthetic */ AnonymousClass1O1 A03;
    public final /* synthetic */ AnonymousClass1O7 A04;
    public final /* synthetic */ String A05;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1O6(AnonymousClass1O7 r2, AnonymousClass1O1 r3, String str, int i, int i2, int i3) {
        super("updateDataTaskDownloadProgressCallback");
        this.A04 = r2;
        this.A03 = r3;
        this.A05 = str;
        this.A02 = i;
        this.A01 = i2;
        this.A00 = i3;
    }

    public final void run() {
        this.A03.updateDataTaskDownloadProgressCallback(this.A05, (long) this.A02, (long) this.A01, (long) this.A00);
    }
}
