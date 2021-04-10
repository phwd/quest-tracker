package com.oculus.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.decoder.DecoderCounters;
import com.oculus.android.exoplayer2.util.Assertions;

public interface VideoRendererEventListener {
    void onDroppedFrames(int i, long j);

    void onRenderedFirstFrame(Surface surface);

    void onVideoDecoderInitialized(String str, long j, long j2);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoInputFormatChanged(Format format);

    void onVideoSizeChanged(int i, int i2, int i3, float f);

    public static final class EventDispatcher {
        @Nullable
        private final Handler handler;
        @Nullable
        private final VideoRendererEventListener listener;

        public EventDispatcher(@Nullable Handler handler2, @Nullable VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = videoRendererEventListener;
        }

        public void enabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass1 */

                    public void run() {
                        EventDispatcher.this.listener.onVideoEnabled(decoderCounters);
                    }
                });
            }
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass2 */

                    public void run() {
                        EventDispatcher.this.listener.onVideoDecoderInitialized(str, j, j2);
                    }
                });
            }
        }

        public void inputFormatChanged(final Format format) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass3 */

                    public void run() {
                        EventDispatcher.this.listener.onVideoInputFormatChanged(format);
                    }
                });
            }
        }

        public void droppedFrames(final int i, final long j) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass4 */

                    public void run() {
                        EventDispatcher.this.listener.onDroppedFrames(i, j);
                    }
                });
            }
        }

        public void videoSizeChanged(final int i, final int i2, final int i3, final float f) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass5 */

                    public void run() {
                        EventDispatcher.this.listener.onVideoSizeChanged(i, i2, i3, f);
                    }
                });
            }
        }

        public void renderedFirstFrame(final Surface surface) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass6 */

                    public void run() {
                        EventDispatcher.this.listener.onRenderedFirstFrame(surface);
                    }
                });
            }
        }

        public void disabled(final DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher.AnonymousClass7 */

                    public void run() {
                        decoderCounters.ensureUpdated();
                        EventDispatcher.this.listener.onVideoDisabled(decoderCounters);
                    }
                });
            }
        }
    }
}
