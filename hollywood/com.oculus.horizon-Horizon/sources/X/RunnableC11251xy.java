package X;

/* renamed from: X.1xy  reason: invalid class name and case insensitive filesystem */
public class RunnableC11251xy implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.recording.muxing.AvMuxer$2";
    public final /* synthetic */ AnonymousClass1yI A00;
    public final /* synthetic */ AnonymousClass1xu A01;

    public RunnableC11251xy(AnonymousClass1xu r1, AnonymousClass1yI r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1yI r6 = this.A00;
        C11211xt r5 = r6.A01;
        r5.A01 = AnonymousClass1y5.RECORDING;
        r5.A02.A5O(19, "recording_start_finished");
        AnonymousClass1xh r4 = r5.A06;
        if (r4 != null) {
            r5.A05.post(new AnonymousClass1xl(r5, r4, r4.now()));
        }
        r6.A00.A6B();
    }
}
