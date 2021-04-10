package X;

/* renamed from: X.1yt  reason: invalid class name */
public class AnonymousClass1yt implements AnonymousClass1zF {
    public final /* synthetic */ AnonymousClass1zF A00;
    public final /* synthetic */ AnonymousClass1yX A01;

    public AnonymousClass1yt(AnonymousClass1yX r1, AnonymousClass1zF r2) {
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
        C11461yv r11;
        AnonymousClass1yX r2 = this.A01;
        AbstractC11091xe r3 = r2.A0B;
        r3.A5O(19, "recording_stop_video_finished");
        long hashCode = (long) r2.hashCode();
        if (r2.A05 != null) {
            r11 = new C11461yv(this);
        } else {
            r11 = null;
        }
        r3.A5Q("stop_recording_video_finished", "AbstractVideoRecordingTrack", hashCode, "", null, null, r11);
        r2.release();
        this.A00.onSuccess();
    }
}
