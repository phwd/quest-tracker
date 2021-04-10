package X;

/* renamed from: X.1yc  reason: invalid class name and case insensitive filesystem */
public class C11301yc implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ C11281ya A01;

    public C11301yc(C11281ya r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        C11081xd r7 = new C11081xd(th);
        C11281ya r2 = this.A01;
        C11311yd r0 = r2.A04;
        if (r0 != null) {
            r7.A01(r0.A00());
        }
        r2.release();
        try {
            r7.A00("supported_configs", AnonymousClass1jp.A00(AnonymousClass1jp.A01()));
        } catch (Exception unused) {
        }
        r2.A0B.A5Q("prepare_recording_audio_failed", "AudioRecordingTrack", (long) r2.hashCode(), "", r7, "prepareEncoder", null);
        this.A00.A62(th);
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        this.A00.onSuccess();
    }
}
