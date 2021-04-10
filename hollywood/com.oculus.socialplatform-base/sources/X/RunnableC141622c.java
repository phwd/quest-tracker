package X;

/* renamed from: X.22c  reason: invalid class name and case insensitive filesystem */
public class RunnableC141622c implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$CallbackHandler$3";
    public final /* synthetic */ C142322l A00;
    public final /* synthetic */ C142022i A01;

    public RunnableC141622c(C142322l r1, C142022i r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        EnumC142522n A012 = this.A01.A04.A01();
        if (A012.equals(EnumC142522n.FAILED_CONNECTION_REFUSED_SERVER_SHEDDING_LOAD) || A012.equals(EnumC142522n.FAILED_CONNECTION_REFUSED_NOT_AUTHORIZED)) {
            AnonymousClass22S r1 = this.A00.A01.A0Q;
            synchronized (r1) {
                r1.A07 = true;
            }
        }
        C142322l r0 = this.A00;
        AnonymousClass22H r2 = r0.A01;
        if (r2.A0n == r0.A00) {
            AnonymousClass22H.A04(r2, AnonymousClass007.A00, new AnonymousClass1QL(A012));
        }
        ((AnonymousClass1Q3) r2.A0F.A04(AnonymousClass1QB.class)).A02(AnonymousClass1QC.LastConnectFailureReason, A012.name());
    }
}
