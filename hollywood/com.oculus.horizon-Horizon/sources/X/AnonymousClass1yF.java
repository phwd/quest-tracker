package X;

/* renamed from: X.1yF  reason: invalid class name */
public class AnonymousClass1yF implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.recording.muxing.AvMuxer$1";
    public final /* synthetic */ AnonymousClass1xu A00;

    public AnonymousClass1yF(AnonymousClass1xu r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass1xu r4 = this.A00;
        AnonymousClass1yI r3 = r4.A04;
        if (!r4.A0F && r4.A0E) {
            r4.A0F = true;
            r4.A0E = false;
            if (r3 != null) {
                AnonymousClass1xu.A01(r4, r3, 21003, "Timeout while waiting for first samples for muxing", null);
            }
        }
    }
}
