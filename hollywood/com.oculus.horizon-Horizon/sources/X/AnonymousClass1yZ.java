package X;

import android.media.MediaCodec;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: X.1yZ  reason: invalid class name */
public class AnonymousClass1yZ {
    @Nullable
    public AnonymousClass1yU A00;
    public volatile boolean A01 = false;
    public final /* synthetic */ C11281ya A02;

    public AnonymousClass1yZ(C11281ya r2) {
        this.A02 = r2;
    }

    public final void A00(Exception exc) {
        if (this.A00 != null) {
            C11081xd r7 = new C11081xd(22001, exc);
            C11281ya r2 = this.A02;
            C11311yd r0 = r2.A04;
            if (r0 != null) {
                r7.A01(r0.A00());
            }
            try {
                r7.A00("supported_configs", AnonymousClass1jp.A00(AnonymousClass1jp.A01()));
            } catch (Exception unused) {
            }
            r2.A0B.A5Q("inprogress_recording_audio_failure", "AudioRecordingTrack", (long) r2.hashCode(), "", r7, "AudioEncoderCallback", null);
            AnonymousClass1yU r22 = this.A00;
            C11211xt r1 = r22.A00;
            r1.A03(r7);
            r1.A05(new AnonymousClass1yV(r22));
            this.A00 = null;
        }
    }

    public final void A01(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (!this.A01) {
            C11281ya r2 = this.A02;
            if (!r2.A0C.A92() || (bufferInfo.flags & 4) == 0) {
                synchronized (r2) {
                    AnonymousClass1xu r1 = r2.A06;
                    if (r1 != null) {
                        r1.A03(r2.A4Z(), byteBuffer, bufferInfo);
                    }
                }
                return;
            }
            this.A01 = true;
        }
    }
}
