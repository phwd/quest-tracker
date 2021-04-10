package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@TargetApi(18)
/* renamed from: X.1ym  reason: invalid class name and case insensitive filesystem */
public final class C11401ym implements AbstractC11491yy {
    public MediaCodec.BufferInfo A00;
    public MediaCodec A01;
    public MediaFormat A02;
    public final C11351yh A03;
    public final Handler A04;
    public final AnonymousClass1yZ A05;
    public volatile Integer A06 = AnonymousClass007.A00;

    @Override // X.AbstractC11491yy
    public final void A4p(byte[] bArr, int i, long j) {
        int i2 = i;
        if (Looper.myLooper() != this.A04.getLooper()) {
            throw new IllegalStateException("inputData must be invoked on the same thread as the other methods");
        } else if (this.A06 == AnonymousClass007.A0C) {
            Exception e = null;
            if (i < 0) {
                try {
                    e = new IOException(String.format(null, "Failure to read input data, bytesRead=%d", Integer.valueOf(i)));
                    i2 = 0;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            ByteBuffer[] inputBuffers = this.A01.getInputBuffers();
            int dequeueInputBuffer = this.A01.dequeueInputBuffer((long) this.A03.A01);
            if (dequeueInputBuffer >= 0) {
                ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
                byteBuffer.clear();
                byteBuffer.put(bArr, 0, i2);
                this.A01.queueInputBuffer(dequeueInputBuffer, 0, i2, j, 0);
            }
            A01(this);
            if (e == null) {
                return;
            }
            this.A05.A00(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0029, code lost:
        if (r0 > 0) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.media.MediaFormat A00(X.C11351yh r3, boolean r4) {
        /*
            int r2 = r3.A05
            r1 = 1
            java.lang.String r0 = "audio/mp4a-latm"
            android.media.MediaFormat r2 = android.media.MediaFormat.createAudioFormat(r0, r2, r1)
            java.lang.String r1 = "aac-profile"
            r0 = 1
            r2.setInteger(r1, r0)
            r1 = 64000(0xfa00, float:8.9683E-41)
            java.lang.String r0 = "bitrate"
            r2.setInteger(r0, r1)
            java.lang.String r1 = "max-input-size"
            if (r4 == 0) goto L_0x0027
            r0 = 0
        L_0x001c:
            r2.setInteger(r1, r0)
        L_0x001f:
            int r1 = r3.A04
            java.lang.String r0 = "pcm-encoding"
            r2.setInteger(r0, r1)
            return r2
        L_0x0027:
            int r0 = r3.A00
            if (r0 <= 0) goto L_0x001f
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11401ym.A00(X.1yh, boolean):android.media.MediaFormat");
    }

    public static void A01(C11401ym r7) {
        AnonymousClass1yZ r4;
        IOException iOException;
        try {
            ByteBuffer[] outputBuffers = r7.A01.getOutputBuffers();
            while (true) {
                int dequeueOutputBuffer = r7.A01.dequeueOutputBuffer(r7.A00, 1000);
                if (dequeueOutputBuffer == -1) {
                    return;
                }
                if (dequeueOutputBuffer == -3) {
                    outputBuffers = r7.A01.getOutputBuffers();
                } else if (dequeueOutputBuffer == -2) {
                    r7.A02 = r7.A01.getOutputFormat();
                } else if (dequeueOutputBuffer < 0) {
                    r4 = r7.A05;
                    iOException = new IOException(String.format(null, "unexpected result from encoder.dequeueOutputBuffer: %d", Integer.valueOf(dequeueOutputBuffer)));
                    break;
                } else {
                    ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                    if (byteBuffer == null) {
                        r4 = r7.A05;
                        iOException = new IOException(String.format(null, "encoderOutputBuffer : %d was null", Integer.valueOf(dequeueOutputBuffer)));
                        break;
                    }
                    byteBuffer.position(r7.A00.offset).limit(r7.A00.size);
                    r7.A05.A01(byteBuffer, r7.A00);
                    r7.A01.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((r7.A00.flags & 4) != 0) {
                        return;
                    }
                }
            }
            r4.A00(iOException);
        } catch (Exception e) {
            r7.A05.A00(e);
        }
    }

    @Override // X.AbstractC11491yy
    public final void A7R(AbstractC11131xk r3, Handler handler) {
        this.A00 = new MediaCodec.BufferInfo();
        this.A04.post(new AnonymousClass1yo(this, r3, handler));
    }

    @Override // X.AbstractC11491yy
    public final void A9C(AbstractC11131xk r3, Handler handler) {
        this.A04.post(new RunnableC11441yr(this, r3, handler));
    }

    @Override // X.AbstractC11491yy
    public final void A9N(AbstractC11131xk r3, Handler handler) {
        this.A04.post(new AnonymousClass1ys(this, r3, handler));
    }

    public C11401ym(C11351yh r2, AnonymousClass1yZ r3, Handler handler) {
        this.A03 = r2;
        this.A05 = r3;
        this.A04 = handler;
    }

    @Override // X.AnonymousClass1yQ
    @Nullable
    public final MediaFormat A41() {
        return this.A02;
    }
}
