package X;

/* renamed from: X.1yf  reason: invalid class name and case insensitive filesystem */
public class C11331yf implements AnonymousClass1zF {
    public final /* synthetic */ AnonymousClass1zF A00;
    public final /* synthetic */ C11281ya A01;

    public C11331yf(C11281ya r1, AnonymousClass1zF r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r2) {
        this.A01.release();
        this.A00.A5y(r2);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        C11281ya r2 = this.A01;
        AbstractC11491yy r3 = r2.A02;
        if (r3 != null) {
            r3.A9N(new C11341yg(this), r2.A09);
            return;
        }
        C11081xd r9 = null;
        if (!r2.A08) {
            r9 = new C11081xd(22000, "mAudioEncoder is null while stopping");
            r2.A0B.A5Q("stop_recording_audio_failed", "AudioRecordingTrack", (long) r2.hashCode(), "", r9, "stop", null);
        }
        r2.release();
        if (r9 != null) {
            this.A00.A5y(r9);
        } else {
            this.A00.onSuccess();
        }
    }
}
