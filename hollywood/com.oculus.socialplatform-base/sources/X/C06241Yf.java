package X;

/* renamed from: X.1Yf  reason: invalid class name and case insensitive filesystem */
public class C06241Yf extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.config.LazyMailbox$2";
    public final /* synthetic */ C06251Yg A00;
    public final /* synthetic */ AnonymousClass1YZ A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C06241Yf(C06251Yg r2, AnonymousClass1YZ r3) {
        super("LazyMailbox-runWithMailbox");
        this.A00 = r2;
        this.A01 = r3;
    }

    public final void run() {
        this.A01.onCompletion(this.A00.A02);
    }
}
