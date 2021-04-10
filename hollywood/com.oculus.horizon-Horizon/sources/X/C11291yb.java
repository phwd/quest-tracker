package X;

import android.os.Handler;
import com.oculus.horizon.capture.VideoCapture;

/* renamed from: X.1yb  reason: invalid class name and case insensitive filesystem */
public class C11291yb implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ C11281ya A01;

    public C11291yb(C11281ya r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        C11281ya r0 = this.A01;
        r0.release();
        r0.A0B.A5Q("start_recording_audio_failed", "AudioRecordingTrack", (long) r0.hashCode(), "", new C11081xd(th), "start", null);
        this.A00.A62(th);
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        C11281ya r1 = this.A01;
        r1.A0F = false;
        VideoCapture.AnonymousClass7 r3 = r1.A0D;
        AbstractC11041xS r12 = r1.A0A;
        C11361yi r2 = new C11361yi(this);
        VideoCapture.this.mAudioPipelineRecorderOutput = r12;
        VideoCapture videoCapture = VideoCapture.this;
        Handler handler = videoCapture.mAudioHandler;
        if (handler != null) {
            handler.post(videoCapture.pollRunnable);
        }
        r2.onSuccess();
    }
}
