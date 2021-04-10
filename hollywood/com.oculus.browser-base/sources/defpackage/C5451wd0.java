package defpackage;

import android.media.MediaCodec;
import android.media.MediaFormat;
import org.chromium.media.MediaCodecBridge;

/* renamed from: wd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5451wd0 extends MediaCodec.Callback {

    /* renamed from: a  reason: collision with root package name */
    public MediaCodecBridge f11554a;

    public C5451wd0(MediaCodecBridge mediaCodecBridge, MediaCodecBridge mediaCodecBridge2) {
        this.f11554a = mediaCodecBridge2;
    }

    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        AbstractC1220Ua0.a("MediaCodecBridge", "MediaCodec.onError: %s", codecException.getDiagnosticInfo());
        this.f11554a.d();
    }

    public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
        MediaCodecBridge mediaCodecBridge = this.f11554a;
        synchronized (mediaCodecBridge) {
            if (!mediaCodecBridge.i) {
                mediaCodecBridge.l.add(new MediaCodecBridge.DequeueInputResult(0, i, null));
                mediaCodecBridge.c();
            }
        }
    }

    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
        MediaCodecBridge mediaCodecBridge = this.f11554a;
        synchronized (mediaCodecBridge) {
            if (!mediaCodecBridge.i) {
                mediaCodecBridge.m.add(new MediaCodecBridge.DequeueOutputResult(0, i, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size, null));
                mediaCodecBridge.c();
            }
        }
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        MediaCodecBridge mediaCodecBridge = this.f11554a;
        synchronized (mediaCodecBridge) {
            mediaCodecBridge.m.add(new MediaCodecBridge.DequeueOutputResult(3, -1, 0, 0, 0, 0, null));
            mediaCodecBridge.f.add(new MediaCodecBridge.GetOutputFormatResult(0, mediaFormat, null));
            mediaCodecBridge.c();
        }
    }
}
