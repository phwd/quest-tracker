package X;

import java.util.HashMap;

/* renamed from: X.1yk  reason: invalid class name and case insensitive filesystem */
public class C11381yk implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ C11281ya A01;
    public final /* synthetic */ HashMap A02;

    public C11381yk(C11281ya r1, HashMap hashMap, AbstractC11131xk r3) {
        this.A01 = r1;
        this.A02 = hashMap;
        this.A00 = r3;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        this.A00.A62(th);
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        C11281ya r2 = this.A01;
        AbstractC11091xe r3 = r2.A0B;
        r3.A5O(19, "recording_prepare_audio_finished");
        r3.A5Q("prepare_recording_audio_finished", "AudioRecordingTrack", (long) r2.hashCode(), "", null, null, this.A02);
        r2.A07 = true;
        r2.A08 = false;
        this.A00.onSuccess();
    }
}
