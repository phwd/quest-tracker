package X;

/* renamed from: X.1yg  reason: invalid class name and case insensitive filesystem */
public class C11341yg implements AbstractC11131xk {
    public final /* synthetic */ C11331yf A00;

    public C11341yg(C11331yf r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        C11331yf r1 = this.A00;
        r1.A01.release();
        r1.A00.A5y(new C11081xd(th));
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        C11331yf r3 = this.A00;
        C11281ya r2 = r3.A01;
        r2.release();
        AbstractC11091xe r4 = r2.A0B;
        r4.A5O(19, "recording_stop_audio_finished");
        r4.A5Q("stop_recording_audio_finished", "AudioRecordingTrack", (long) r2.hashCode(), "", null, null, null);
        r3.A00.onSuccess();
    }
}
