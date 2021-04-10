package X;

/* renamed from: X.1yi  reason: invalid class name and case insensitive filesystem */
public class C11361yi implements AnonymousClass1zF {
    public final /* synthetic */ C11291yb A00;

    public C11361yi(C11291yb r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r12) {
        C11291yb r1 = this.A00;
        C11281ya r0 = r1.A01;
        r0.release();
        r0.A0B.A5Q("start_recording_audio_failed", "AudioRecordingTrack", (long) r0.hashCode(), "", r12, "start", null);
        r1.A00.A62(r12);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        C11291yb r1 = this.A00;
        C11281ya r0 = r1.A01;
        r0.A0B.A5Q("start_recording_audio_finished", "AudioRecordingTrack", (long) r0.hashCode(), "", null, null, null);
        r1.A00.onSuccess();
    }
}
