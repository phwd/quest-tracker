package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(18)
/* renamed from: X.1Wc  reason: invalid class name */
public final class AnonymousClass1Wc implements AnonymousClass1yB {
    public int A00;
    public int A01;
    public MediaMuxer A02;
    public volatile boolean A03;
    public volatile boolean A04;
    public volatile boolean A05;
    public volatile boolean A06;

    @Override // X.AnonymousClass1yB
    public final void A1o(String str) throws IOException {
        this.A02 = new MediaMuxer(str, 0);
        this.A03 = false;
        this.A05 = false;
    }

    @Override // X.AnonymousClass1yB
    public final boolean A9Q() throws IOException, IllegalStateException {
        boolean z;
        try {
            if (this.A02 != null) {
                if (!this.A04 || this.A03) {
                    if (!this.A06 || this.A05) {
                        z = true;
                        this.A02.stop();
                        this.A02.release();
                        return z;
                    }
                }
            }
            z = false;
            return z;
        } finally {
            this.A03 = false;
            this.A05 = false;
            this.A02 = null;
            this.A00 = 0;
            this.A01 = 0;
        }
    }

    @Override // X.AnonymousClass1yB
    public final void A8b(MediaFormat mediaFormat) {
        this.A00 = this.A02.addTrack(mediaFormat);
        this.A04 = true;
    }

    @Override // X.AnonymousClass1yB
    public final void A8h(int i) {
        this.A02.setOrientationHint(i);
    }

    @Override // X.AnonymousClass1yB
    public final void A8t(MediaFormat mediaFormat) {
        this.A01 = this.A02.addTrack(mediaFormat);
        this.A06 = true;
    }

    @Override // X.AnonymousClass1yB
    public final void AAB(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        this.A02.writeSampleData(this.A00, byteBuffer, bufferInfo);
        this.A03 = true;
    }

    @Override // X.AnonymousClass1yB
    public final void AAJ(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        if ((bufferInfo.flags & 2) == 0) {
            this.A02.writeSampleData(this.A01, byteBuffer, bufferInfo);
            this.A05 = true;
        }
    }

    @Override // X.AnonymousClass1yB
    public final void start() throws IOException {
        this.A02.start();
    }
}
