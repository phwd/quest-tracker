package com.oculus.android.exoplayer2.source;

import android.os.Handler;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;

public interface MediaSourceEventListener {
    void onDownstreamFormatChanged(int i, Format format, int i2, Object obj, long j);

    void onLoadCanceled(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5);

    void onLoadCompleted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5);

    void onLoadError(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z);

    void onLoadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3);

    void onUpstreamDiscarded(int i, long j, long j2);

    public static final class EventDispatcher {
        @Nullable
        private final Handler handler;
        @Nullable
        private final MediaSourceEventListener listener;
        private final long mediaTimeOffsetMs;

        public EventDispatcher(@Nullable Handler handler2, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            this(handler2, mediaSourceEventListener, 0);
        }

        public EventDispatcher(@Nullable Handler handler2, @Nullable MediaSourceEventListener mediaSourceEventListener, long j) {
            this.handler = mediaSourceEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = mediaSourceEventListener;
            this.mediaTimeOffsetMs = j;
        }

        public EventDispatcher copyWithMediaTimeOffsetMs(long j) {
            return new EventDispatcher(this.handler, this.listener, j);
        }

        public void loadStarted(DataSpec dataSpec, int i, long j) {
            loadStarted(dataSpec, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j);
        }

        public void loadStarted(final DataSpec dataSpec, final int i, final int i2, final Format format, final int i3, final Object obj, final long j, final long j2, final long j3) {
            Handler handler2;
            if (this.listener != null && (handler2 = this.handler) != null) {
                handler2.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.AnonymousClass1 */

                    public void run() {
                        EventDispatcher.this.listener.onLoadStarted(dataSpec, i, i2, format, i3, obj, EventDispatcher.this.adjustMediaTime(j), EventDispatcher.this.adjustMediaTime(j2), j3);
                    }
                });
            }
        }

        public void loadCompleted(DataSpec dataSpec, int i, long j, long j2, long j3) {
            loadCompleted(dataSpec, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3);
        }

        public void loadCompleted(final DataSpec dataSpec, final int i, final int i2, final Format format, final int i3, final Object obj, final long j, final long j2, final long j3, final long j4, final long j5) {
            Handler handler2;
            if (this.listener != null && (handler2 = this.handler) != null) {
                handler2.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.AnonymousClass2 */

                    public void run() {
                        EventDispatcher.this.listener.onLoadCompleted(dataSpec, i, i2, format, i3, obj, EventDispatcher.this.adjustMediaTime(j), EventDispatcher.this.adjustMediaTime(j2), j3, j4, j5);
                    }
                });
            }
        }

        public void loadCanceled(DataSpec dataSpec, int i, long j, long j2, long j3) {
            loadCanceled(dataSpec, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3);
        }

        public void loadCanceled(final DataSpec dataSpec, final int i, final int i2, final Format format, final int i3, final Object obj, final long j, final long j2, final long j3, final long j4, final long j5) {
            Handler handler2;
            if (this.listener != null && (handler2 = this.handler) != null) {
                handler2.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.AnonymousClass3 */

                    public void run() {
                        EventDispatcher.this.listener.onLoadCanceled(dataSpec, i, i2, format, i3, obj, EventDispatcher.this.adjustMediaTime(j), EventDispatcher.this.adjustMediaTime(j2), j3, j4, j5);
                    }
                });
            }
        }

        public void loadError(DataSpec dataSpec, int i, long j, long j2, long j3, IOException iOException, boolean z) {
            loadError(dataSpec, i, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3, iOException, z);
        }

        public void loadError(final DataSpec dataSpec, final int i, final int i2, final Format format, final int i3, final Object obj, final long j, final long j2, final long j3, final long j4, final long j5, final IOException iOException, final boolean z) {
            Handler handler2;
            if (this.listener != null && (handler2 = this.handler) != null) {
                handler2.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.AnonymousClass4 */

                    public void run() {
                        EventDispatcher.this.listener.onLoadError(dataSpec, i, i2, format, i3, obj, EventDispatcher.this.adjustMediaTime(j), EventDispatcher.this.adjustMediaTime(j2), j3, j4, j5, iOException, z);
                    }
                });
            }
        }

        public void upstreamDiscarded(final int i, final long j, final long j2) {
            Handler handler2;
            if (this.listener != null && (handler2 = this.handler) != null) {
                handler2.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.AnonymousClass5 */

                    public void run() {
                        EventDispatcher.this.listener.onUpstreamDiscarded(i, EventDispatcher.this.adjustMediaTime(j), EventDispatcher.this.adjustMediaTime(j2));
                    }
                });
            }
        }

        public void downstreamFormatChanged(final int i, final Format format, final int i2, final Object obj, final long j) {
            Handler handler2;
            if (this.listener != null && (handler2 = this.handler) != null) {
                handler2.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher.AnonymousClass6 */

                    public void run() {
                        EventDispatcher.this.listener.onDownstreamFormatChanged(i, format, i2, obj, EventDispatcher.this.adjustMediaTime(j));
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private long adjustMediaTime(long j) {
            long usToMs = C.usToMs(j);
            if (usToMs == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            return this.mediaTimeOffsetMs + usToMs;
        }
    }
}
