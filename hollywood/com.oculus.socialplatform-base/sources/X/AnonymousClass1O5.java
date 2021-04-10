package X;

/* renamed from: X.1O5  reason: invalid class name */
public class AnonymousClass1O5 extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.network.system.HttpUrlConnectionNetworkSessionListenerManager$3";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ AnonymousClass1O1 A02;
    public final /* synthetic */ AnonymousClass1O7 A03;
    public final /* synthetic */ String A04;
    public final /* synthetic */ byte[] A05;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1O5(AnonymousClass1O7 r2, AnonymousClass1O1 r3, String str, int i, int i2, byte[] bArr) {
        super("updateDataTaskUploadProgressCallback");
        this.A03 = r2;
        this.A02 = r3;
        this.A04 = str;
        this.A00 = i;
        this.A01 = i2;
        this.A05 = bArr;
    }

    public final void run() {
        this.A02.updateDataTaskUploadProgressCallback(this.A04, (long) this.A00, (long) this.A01, (long) this.A05.length);
    }
}
