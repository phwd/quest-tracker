package X;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* renamed from: X.1zv  reason: invalid class name and case insensitive filesystem */
public class C11531zv extends MediaCodec.Callback {
    public final /* synthetic */ AnonymousClass1zu A00;

    public final void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
    }

    public C11531zv(AnonymousClass1zu r1) {
        this.A00 = r1;
    }

    public final void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        HashMap hashMap = new HashMap();
        AnonymousClass1zu r3 = this.A00;
        hashMap.put("current_state", AnonymousClass1z2.A00(r3.A0B));
        hashMap.put("method_invocation", r3.A05.toString());
        hashMap.put("isRecoverable", String.valueOf(codecException.isRecoverable()));
        hashMap.put("isTransient", String.valueOf(codecException.isTransient()));
        r3.A06.A00(codecException, hashMap);
    }

    public final void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        AnonymousClass1yW r2;
        Object[] objArr;
        String str;
        if ((bufferInfo.flags & 4) == 0 || bufferInfo.size > 0) {
            if (i < 0) {
                r2 = this.A00.A06;
                objArr = new Object[]{Integer.valueOf(i)};
                str = "Unexpected result from encoder.dequeueOutputBuffer: %d";
            } else {
                ByteBuffer outputBuffer = mediaCodec.getOutputBuffer(i);
                if (outputBuffer == null) {
                    r2 = this.A00.A06;
                    objArr = new Object[]{Integer.valueOf(i)};
                    str = "onOutputBufferAvailable ByteBuffer %d was null";
                } else {
                    if ((bufferInfo.flags & 2) != 0) {
                        bufferInfo.flags = 2;
                    }
                    if (bufferInfo.size > 0) {
                        this.A00.A06.A01(outputBuffer, bufferInfo);
                    }
                    mediaCodec.releaseOutputBuffer(i, false);
                    if ((bufferInfo.flags & 4) == 0 || bufferInfo.size <= 0) {
                        return;
                    }
                }
            }
            r2.A00(new IOException(String.format(null, str, objArr)), null);
            return;
        }
        AnonymousClass1zu r5 = this.A00;
        AnonymousClass1zF r4 = r5.A04;
        Handler handler = r5.A02;
        r5.A05.append("handleFinishedEncoding, ");
        r5.A04 = null;
        r5.A02 = null;
        if (r4 != null && handler != null) {
            try {
                Surface surface = r5.A03;
                if (surface != null) {
                    surface.release();
                }
                MediaCodec mediaCodec2 = r5.A00;
                if (mediaCodec2 != null) {
                    mediaCodec2.stop();
                    r5.A00.release();
                }
                r5.A0B = AnonymousClass007.A0D;
                r5.A00 = null;
                r5.A03 = null;
                r5.A01 = null;
                r5.A05.append("asyncStop end, ");
                AnonymousClass1z7.A00(r4, handler);
            } catch (Exception e) {
                AnonymousClass205 r1 = new AnonymousClass205(e);
                AnonymousClass1zu.A01(r5, r1, e);
                MediaCodec mediaCodec3 = r5.A00;
                if (mediaCodec3 != null) {
                    try {
                        mediaCodec3.release();
                    } catch (Exception unused) {
                    }
                }
                r5.A0B = AnonymousClass007.A0D;
                r5.A00 = null;
                r5.A03 = null;
                r5.A01 = null;
                AnonymousClass1z7.A01(r4, handler, r1);
            }
        }
    }

    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        this.A00.A01 = mediaFormat;
    }
}
