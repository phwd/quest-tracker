package X;

/* renamed from: X.23F  reason: invalid class name */
public class AnonymousClass23F implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$6";
    public final /* synthetic */ AnonymousClass22H A00;

    public AnonymousClass23F(AnonymousClass22H r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass22H r3 = this.A00;
        AnonymousClass0MD.A05(r3.A0R, "ping unreceived");
        r3.A08(r3.A0n, EnumC141822g.PING_UNRECEIVED, AnonymousClass007.A01);
    }
}
