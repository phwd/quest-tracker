package X;

/* renamed from: X.1yj  reason: invalid class name and case insensitive filesystem */
public class C11371yj implements AnonymousClass1zF {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ AnonymousClass1yX A01;

    public C11371yj(AnonymousClass1yX r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r12) {
        AnonymousClass1yX r1 = this.A01;
        r1.A0B.A5Q("prepare_recording_video_failed", "AbstractVideoRecordingTrack", (long) r1.hashCode(), "", r12, "prepareEncoder", null);
        r1.release();
        this.A00.A62(r12);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        AnonymousClass1yX r2 = this.A01;
        r2.A08 = true;
        r2.A09 = false;
        AbstractC11091xe r3 = r2.A0B;
        r3.A5O(19, "recording_prepare_video_finished");
        r3.A5Q("prepare_recording_video_finished", "AbstractVideoRecordingTrack", (long) r2.hashCode(), "", null, null, null);
        this.A00.onSuccess();
    }
}
