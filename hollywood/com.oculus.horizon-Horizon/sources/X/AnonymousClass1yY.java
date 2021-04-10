package X;

import android.view.Surface;

/* renamed from: X.1yY  reason: invalid class name */
public class AnonymousClass1yY implements AnonymousClass1zF {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ AnonymousClass1yX A01;

    public AnonymousClass1yY(AnonymousClass1yX r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r12) {
        AnonymousClass1yX r1 = this.A01;
        r1.A0B.A5Q("start_recording_video_failed", "AbstractVideoRecordingTrack", (long) r1.hashCode(), "", r12, "start", null);
        r1.release();
        this.A00.A62(r12);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        String str;
        AnonymousClass1yX r0 = this.A01;
        AbstractC11131xk r1 = this.A00;
        AbstractC11031xR<T> r5 = r0.A0D.get();
        if (r5 == null) {
            str = "VideoOutputProvider is null while adding to Mediapipeline";
        } else {
            AbstractC11501yz r3 = r0.A06;
            if (r3 == null || r0.A05 == null) {
                str = "mVideoEncoder or mConfig are null while adding to Mediapipeline";
            } else {
                Surface A3c = r3.A3c();
                r0.A01 = A3c;
                if (A3c == null) {
                    str = "Recording Surface is null";
                } else {
                    T t = (T) new AnonymousClass1xN(A3c);
                    r0.A07 = t;
                    T t2 = t;
                    if (t2 != null) {
                        t2.A8f(false);
                    }
                    r5.A19(r0.A07);
                    AbstractC11091xe r4 = r0.A0B;
                    r4.A5O(19, "recording_start_video_finished");
                    r4.A5Q("start_recording_video_finished", "AbstractVideoRecordingTrack", (long) r0.hashCode(), "", null, null, null);
                    r1.onSuccess();
                    C11241xx r12 = r0.A02;
                    if (r12 != null) {
                        r12.A00();
                        r0.A02 = null;
                        return;
                    }
                    return;
                }
            }
        }
        C11081xd r9 = new C11081xd(23000, str);
        r0.A0B.A5Q("start_recording_video_failed", "AbstractVideoRecordingTrack", (long) r0.hashCode(), "", r9, "addVideoOutputToMediapipeline", null);
        r0.release();
        r1.A62(r9);
    }
}
