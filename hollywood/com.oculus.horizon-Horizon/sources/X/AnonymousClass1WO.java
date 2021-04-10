package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1WO  reason: invalid class name */
public final class AnonymousClass1WO implements AnonymousClass1yB {
    public int A00;
    @Nullable
    public MediaMuxer A01;
    public volatile boolean A02;
    public volatile boolean A03;

    @Override // X.AnonymousClass1yB
    public final void A1o(String str) throws IOException {
        this.A01 = new MediaMuxer(str, 0);
        this.A02 = false;
    }

    @Override // X.AnonymousClass1yB
    public final void A8b(MediaFormat mediaFormat) {
        throw new RuntimeException("VideoOnlyMuxer does not accept an audio format.");
    }

    @Override // X.AnonymousClass1yB
    public final void A8h(int i) {
        MediaMuxer mediaMuxer = this.A01;
        if (mediaMuxer != null) {
            mediaMuxer.setOrientationHint(i);
        }
    }

    @Override // X.AnonymousClass1yB
    public final void A8t(MediaFormat mediaFormat) {
        MediaMuxer mediaMuxer = this.A01;
        if (mediaMuxer != null) {
            this.A00 = mediaMuxer.addTrack(mediaFormat);
            this.A03 = true;
        }
    }

    @Override // X.AnonymousClass1yB
    public final boolean A9Q() throws IOException, IllegalStateException {
        boolean z;
        if (this.A01 == null || (this.A03 && !this.A02)) {
            z = false;
        } else {
            z = true;
            this.A01.stop();
            this.A01.release();
        }
        this.A02 = false;
        this.A01 = null;
        this.A00 = 0;
        return z;
    }

    @Override // X.AnonymousClass1yB
    public final void AAB(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        throw new RuntimeException("VideoOnlyMuxer does not have audio to write.");
    }

    @Override // X.AnonymousClass1yB
    public final void AAJ(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        MediaMuxer mediaMuxer;
        if ((bufferInfo.flags & 2) == 0 && (mediaMuxer = this.A01) != null) {
            mediaMuxer.writeSampleData(this.A00, byteBuffer, bufferInfo);
            this.A02 = true;
        }
    }

    @Override // X.AnonymousClass1yB
    public final void start() throws IOException {
        MediaMuxer mediaMuxer = this.A01;
        if (mediaMuxer != null) {
            mediaMuxer.start();
        }
    }
}
