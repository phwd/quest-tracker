package X;

import android.media.MediaCodec;
import java.nio.ByteBuffer;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1yW  reason: invalid class name */
public class AnonymousClass1yW {
    @Nullable
    public AnonymousClass1yU A00;
    public volatile boolean A01 = false;
    public final /* synthetic */ AnonymousClass1yX A02;

    public AnonymousClass1yW(AnonymousClass1yX r2) {
        this.A02 = r2;
    }

    public final void A00(Exception exc, @Nullable Map<String, String> map) {
        if (this.A00 != null) {
            C11081xd r7 = new C11081xd(23001, exc);
            r7.A01(map);
            AnonymousClass1yX r0 = this.A02;
            r0.A0B.A5Q("inprogress_recording_video_failure", "AbstractVideoRecordingTrack", (long) r0.hashCode(), "", r7, "VideoEncoderCallback", null);
            AnonymousClass1yU r2 = this.A00;
            C11211xt r1 = r2.A00;
            r1.A03(r7);
            r1.A05(new AnonymousClass1yV(r2));
        }
    }

    public final void A01(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (!this.A01) {
            AnonymousClass1yX r2 = this.A02;
            if (!r2.A0C.A92() || (bufferInfo.flags & 4) == 0) {
                synchronized (r2) {
                    AnonymousClass1xu r1 = r2.A03;
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
