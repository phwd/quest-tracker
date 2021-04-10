package X;

/* renamed from: X.1ye  reason: invalid class name and case insensitive filesystem */
public class C11321ye implements AnonymousClass1zF {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ C11281ya A01;

    public C11321ye(C11281ya r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r11) {
        C11281ya r2 = this.A01;
        C11311yd r0 = r2.A04;
        if (r0 != null) {
            r11.A01(r0.A00());
        }
        r2.release();
        try {
            r11.A00("supported_configs", AnonymousClass1jp.A00(AnonymousClass1jp.A01()));
        } catch (Exception unused) {
        }
        r2.A0B.A5Q("prepare_recording_audio_failed", "AudioRecordingTrack", (long) r2.hashCode(), "", r11, "prepareAudioPipeline", null);
        this.A00.A62(r11);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        this.A00.onSuccess();
    }
}
