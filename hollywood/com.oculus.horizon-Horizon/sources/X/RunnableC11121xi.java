package X;

/* renamed from: X.1xi  reason: invalid class name and case insensitive filesystem */
public class RunnableC11121xi implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.recording.controller.RecordingThreadController$18";
    public final /* synthetic */ AnonymousClass1lF A00;
    public final /* synthetic */ AnonymousClass1xh A01;
    public final /* synthetic */ C11211xt A02;

    public RunnableC11121xi(C11211xt r1, AnonymousClass1lF r2, AnonymousClass1xh r3) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = r3;
    }

    public final void run() {
        AnonymousClass1lF r2 = this.A00;
        if (r2 instanceof C11081xd) {
            this.A01.A5o((C11081xd) r2);
        } else {
            this.A01.A5o(new C11081xd(r2));
        }
    }
}
