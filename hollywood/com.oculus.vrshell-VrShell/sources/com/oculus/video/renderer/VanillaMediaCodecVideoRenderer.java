package com.oculus.video.renderer;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.drm.DrmSessionManager;
import com.oculus.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.oculus.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.oculus.android.exoplayer2.video.VideoRendererEventListener;

@TargetApi(16)
public class VanillaMediaCodecVideoRenderer extends MediaCodecVideoRenderer implements FrameReleaser {
    private volatile long lastPresentationTimeUs = C.TIME_UNSET;

    public VanillaMediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector, long j, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        super(context, mediaCodecSelector, j, drmSessionManager, z, handler, videoRendererEventListener, i);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.video.MediaCodecVideoRenderer
    public void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        this.lastPresentationTimeUs = j;
        super.renderOutputBuffer(mediaCodec, i, j);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.video.MediaCodecVideoRenderer
    @TargetApi(21)
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        this.lastPresentationTimeUs = j;
        super.renderOutputBufferV21(mediaCodec, i, j, j2);
    }

    @Override // com.oculus.video.renderer.FrameReleaser
    public long getLastPresentationTimeUs() {
        return this.lastPresentationTimeUs;
    }
}
