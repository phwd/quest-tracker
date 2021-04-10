package X;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* renamed from: X.1zw  reason: invalid class name and case insensitive filesystem */
public class C11541zw extends MediaCodec.Callback {
    public final /* synthetic */ AnonymousClass1zt A00;

    public final void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
    }

    public C11541zw(AnonymousClass1zt r1) {
        this.A00 = r1;
    }

    public final void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        HashMap hashMap = new HashMap();
        AnonymousClass1zt r3 = this.A00;
        hashMap.put("current_state", AnonymousClass1z2.A00(r3.A0B));
        hashMap.put("method_invocation", r3.A04.toString());
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
        AnonymousClass1zt r22 = this.A00;
        AnonymousClass1zt.A02(r22, r22.A03, r22.A02);
    }

    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        this.A00.A01 = mediaFormat;
    }
}
