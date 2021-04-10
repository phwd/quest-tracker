package X;

/* renamed from: X.0xI  reason: invalid class name and case insensitive filesystem */
public class RunnableC08470xI implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$3";
    public final /* synthetic */ C08520xN A00;
    public final /* synthetic */ C08730xj A01;

    public RunnableC08470xI(C08520xN r1, C08730xj r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        EnumC08860xw A012 = this.A01.A04.A01();
        if (A012.equals(EnumC08860xw.FAILED_CONNECTION_REFUSED_SERVER_SHEDDING_LOAD) || A012.equals(EnumC08860xw.FAILED_CONNECTION_REFUSED_NOT_AUTHORIZED)) {
            C08330x3 r1 = this.A00.A01.A0N;
            synchronized (r1) {
                r1.A08 = true;
            }
        }
        C08520xN r0 = this.A00;
        C08290wy r2 = r0.A01;
        if (r2.A0l == r0.A00) {
            if (A012.equals(EnumC08860xw.FAILED_CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD)) {
                r2.A0Q.A00();
            }
            C08290wy.A03(r2, AnonymousClass007.A00, new AnonymousClass0yR(A012));
        }
        ((AbstractC08430xE) r2.A0B.A06(C09660zz.class)).A02(AnonymousClass0y2.LastConnectFailureReason, A012.name());
    }
}
