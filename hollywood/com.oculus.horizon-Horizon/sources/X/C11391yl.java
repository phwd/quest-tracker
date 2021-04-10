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
/* renamed from: X.1yl  reason: invalid class name and case insensitive filesystem */
public final class C11391yl implements AbstractC11491yy {
    public MediaCodec.BufferInfo A00;
    public MediaCodec A01;
    public MediaFormat A02;
    public final C11351yh A03;
    public final Handler A04;
    public final AnonymousClass1yZ A05;
    public volatile Integer A06 = AnonymousClass007.A00;

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 152
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:72)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        */
    public static void A01(X.C11391yl r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 195
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11391yl.A01(X.1yl, boolean):void");
    }

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
            A01(this, false);
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
        throw new UnsupportedOperationException("Method not decompiled: X.C11391yl.A00(X.1yh, boolean):android.media.MediaFormat");
    }

    @Override // X.AbstractC11491yy
    public final void A7R(AbstractC11131xk r3, Handler handler) {
        this.A00 = new MediaCodec.BufferInfo();
        this.A04.post(new RunnableC11411yn(this, r3, handler));
    }

    @Override // X.AbstractC11491yy
    public final void A9C(AbstractC11131xk r3, Handler handler) {
        this.A04.post(new RunnableC11431yq(this, r3, handler));
    }

    @Override // X.AbstractC11491yy
    public final void A9N(AbstractC11131xk r3, Handler handler) {
        this.A04.post(new RunnableC11421yp(this, r3, handler));
    }

    public C11391yl(C11351yh r2, AnonymousClass1yZ r3, Handler handler) {
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
