package org.chromium.media;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaCodecBridgeBuilder {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.chromium.media.MediaCodecBridge createAudioDecoder(java.lang.String r11, android.media.MediaCrypto r12, int r13, int r14, byte[] r15, byte[] r16, byte[] r17, boolean r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 138
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaCodecBridgeBuilder.createAudioDecoder(java.lang.String, android.media.MediaCrypto, int, int, byte[], byte[], byte[], boolean, boolean):org.chromium.media.MediaCodecBridge");
    }

    public static MediaCodecBridge createVideoDecoder(String str, int i, MediaCrypto mediaCrypto, int i2, int i3, Surface surface, byte[] bArr, byte[] bArr2, HdrMetadata hdrMetadata, boolean z, boolean z2) {
        try {
            AbstractC1220Ua0.d("MediaCodecBridge", "create MediaCodec video decoder, mime %s", str);
            C5961zd0 b = MediaCodecUtil.b(str, i, mediaCrypto);
            MediaCodec mediaCodec = b.f11756a;
            if (mediaCodec == null) {
                return null;
            }
            MediaCodecBridge mediaCodecBridge = new MediaCodecBridge(mediaCodec, b.c, z2);
            if (!mediaCodecBridge.a(AbstractC3408ke0.b(str, i2, i3, new byte[][]{bArr, bArr2}, hdrMetadata, b.b && z), surface, mediaCrypto, 0)) {
                return null;
            }
            if (mediaCodecBridge.f()) {
                return mediaCodecBridge;
            }
            mediaCodecBridge.release();
            return null;
        } catch (Exception e) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to create MediaCodec video decoder: %s, codecType: %d", str, Integer.valueOf(i), e);
            return null;
        }
    }

    public static MediaCodecBridge createVideoEncoder(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        MediaCodecBridge mediaCodecBridge;
        C5961zd0 zd0 = new C5961zd0();
        int i7 = 0;
        try {
            AbstractC1220Ua0.d("MediaCodecBridge", "create MediaCodec video encoder, mime %s", str);
            zd0 = MediaCodecUtil.c(str);
        } catch (Exception e) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to create MediaCodec video encoder: %s", str, e);
        }
        if (zd0.f11756a == null) {
            return null;
        }
        if (str.equals("video/avc")) {
            mediaCodecBridge = new C5621xd0(zd0.f11756a, zd0.c);
        } else {
            mediaCodecBridge = new MediaCodecBridge(zd0.f11756a, zd0.c, false);
        }
        int i8 = zd0.c;
        if (i8 == 0) {
            i7 = Math.min(i4, 30);
        } else if (i8 == 1) {
            i7 = 30;
        }
        boolean z = zd0.b;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
        createVideoFormat.setInteger("bitrate", i3);
        createVideoFormat.setInteger("frame-rate", i7);
        createVideoFormat.setInteger("i-frame-interval", i5);
        createVideoFormat.setInteger("color-format", i6);
        AbstractC3408ke0.a(createVideoFormat, z);
        if (!mediaCodecBridge.a(createVideoFormat, null, null, 1)) {
            return null;
        }
        if (mediaCodecBridge.f()) {
            return mediaCodecBridge;
        }
        mediaCodecBridge.release();
        return null;
    }
}
