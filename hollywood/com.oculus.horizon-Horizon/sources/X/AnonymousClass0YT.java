package X;

/* renamed from: X.0YT  reason: invalid class name */
public class AnonymousClass0YT implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$3";
    public final /* synthetic */ C06120mX A00;
    public final /* synthetic */ C02060Yx A01;

    public AnonymousClass0YT(C06120mX r1, C02060Yx r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        AnonymousClass0Yy A012 = this.A01.A04.A01();
        if (A012.equals(AnonymousClass0Yy.FAILED_CONNECTION_REFUSED_SERVER_SHEDDING_LOAD) || A012.equals(AnonymousClass0Yy.FAILED_CONNECTION_REFUSED_NOT_AUTHORIZED)) {
            C02270a7 r1 = this.A00.A01.A0N;
            synchronized (r1) {
                r1.A08 = true;
            }
        }
        C06120mX r0 = this.A00;
        AnonymousClass0YZ r2 = r0.A01;
        if (r2.A0l == r0.A00) {
            if (A012.equals(AnonymousClass0Yy.FAILED_CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD)) {
                r2.A0Q.A00();
            }
            AnonymousClass0YZ.A03(r2, AnonymousClass007.A00, new AnonymousClass0nZ(A012));
        }
        ((AnonymousClass0nJ) r2.A0B.A06(AnonymousClass0Ie.class)).A02(AnonymousClass0nK.LastConnectFailureReason, A012.name());
    }
}
