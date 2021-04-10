package com.oculus.video;

import android.content.Context;
import android.graphics.Rect;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.view.Surface;
import com.oculus.android.exoplayer2.C;
import com.oculus.video.VideoPlayer;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class OculusMediaPlayer implements VideoPlayer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "OculusMediaPlayer";
    private final Context context;
    private VideoPlayer.EventListener eventListener;
    private long frameIntervalNs = C.TIME_UNSET;
    private boolean isBuffering;
    private boolean isMuted;
    private boolean isPlayerReady;
    private boolean isSeekable;
    private long lastPresentationTimeNs = C.TIME_UNSET;
    private MediaPlayer mediaPlayer = null;
    private boolean playWhenReady;
    private float volume = 1.0f;

    @Override // com.oculus.video.VideoPlayer
    public long getBufferedPositionMs() {
        return -1;
    }

    @Override // com.oculus.video.VideoPlayer
    public String getName() {
        return TAG;
    }

    @Override // com.oculus.video.VideoPlayer
    public void onBeforeRender(long j) {
    }

    @Override // com.oculus.video.VideoPlayer
    public void onRender(boolean z) {
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean selectSubtitle(String str) {
        return false;
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean selectVideoTrack(String str) {
        return false;
    }

    private class MediaPlayerListener implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        }

        private MediaPlayerListener() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            if (mediaPlayer == OculusMediaPlayer.this.mediaPlayer) {
                OculusMediaPlayer.this.eventListener.onFinish();
                OculusMediaPlayer.this.release();
            }
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            if (mediaPlayer != OculusMediaPlayer.this.mediaPlayer) {
                return false;
            }
            if (i2 == -1010) {
                OculusMediaPlayer.this.reportError(new Exception("MediaPlayer.onError: MEDIA_ERROR_UNSUPPORTED"));
                return true;
            } else if (i2 == -1007) {
                OculusMediaPlayer.this.reportError(new Exception("MediaPlayer.onError: MEDIA_ERROR_MALFORMED"));
                return true;
            } else if (i2 == -1004) {
                OculusMediaPlayer.this.reportError(new Exception("MediaPlayer.onError: MEDIA_ERROR_IO"));
                return true;
            } else if (i2 == -110) {
                OculusMediaPlayer.this.reportError(new Exception("MediaPlayer.onError: MEDIA_ERROR_TIMED_OUT"));
                return true;
            } else if (i == 1) {
                OculusMediaPlayer.this.reportError(new Exception("MediaPlayer.onError: MEDIA_ERROR_UNKNOWN"));
                return true;
            } else if (i != 100) {
                return false;
            } else {
                OculusMediaPlayer.this.reportError(new Exception("MediaPlayer.onError: MEDIA_ERROR_SERVER_DIED"));
                return true;
            }
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (mediaPlayer != OculusMediaPlayer.this.mediaPlayer) {
                return false;
            }
            if (i == 701) {
                OculusMediaPlayer.this.isBuffering = true;
                OculusMediaPlayer.this.eventListener.onBuffering();
            } else if (i == 702) {
                OculusMediaPlayer.this.isBuffering = false;
                OculusMediaPlayer.this.eventListener.onStopBuffering();
            } else if (i == 801) {
                OculusMediaPlayer.this.isSeekable = false;
            }
            return false;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            if (mediaPlayer == OculusMediaPlayer.this.mediaPlayer) {
                OculusMediaPlayer.this.isPlayerReady = true;
                OculusMediaPlayer oculusMediaPlayer = OculusMediaPlayer.this;
                oculusMediaPlayer.setMute(oculusMediaPlayer.isMuted);
                try {
                    if (OculusMediaPlayer.this.playWhenReady) {
                        OculusMediaPlayer.this.mediaPlayer.start();
                    }
                    OculusMediaPlayer.this.eventListener.onReady();
                } catch (Exception e) {
                    OculusMediaPlayer.this.reportError(e);
                }
            }
        }

        public void onSeekComplete(MediaPlayer mediaPlayer) {
            if (mediaPlayer == OculusMediaPlayer.this.mediaPlayer) {
                OculusMediaPlayer.this.eventListener.onSeekProcessed();
                OculusMediaPlayer.this.eventListener.onReady();
            }
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            if (mediaPlayer == OculusMediaPlayer.this.mediaPlayer) {
                if (OculusMediaPlayer.this.frameIntervalNs < 0) {
                    float frameRate = OculusMediaPlayer.this.getFrameRate();
                    if (frameRate > Float.MIN_NORMAL) {
                        OculusMediaPlayer.this.frameIntervalNs = (long) ((1.0d / ((double) frameRate)) * 1.0E9d);
                    }
                }
                OculusMediaPlayer.this.eventListener.onTimelineChanged((long) OculusMediaPlayer.this.mediaPlayer.getDuration(), (long) OculusMediaPlayer.this.mediaPlayer.getCurrentPosition(), OculusMediaPlayer.this.isSeekable);
                OculusMediaPlayer.this.eventListener.onVideoSizeChanged(i, i2, 1.0f, new Rect());
            }
        }
    }

    OculusMediaPlayer(Context context2) {
        this.context = context2;
        this.isPlayerReady = false;
        this.isBuffering = false;
    }

    @Override // com.oculus.video.VideoPlayer
    public void start(Video video, boolean z, Surface surface, VideoPlayer.EventListener eventListener2) {
        this.playWhenReady = z;
        this.eventListener = eventListener2;
        stop();
        eventListener2.onStartVideo();
        this.isBuffering = false;
        this.isPlayerReady = false;
        this.isSeekable = true;
        this.mediaPlayer = new MediaPlayer();
        MediaPlayerListener mediaPlayerListener = new MediaPlayerListener();
        this.mediaPlayer.setOnVideoSizeChangedListener(mediaPlayerListener);
        this.mediaPlayer.setOnErrorListener(mediaPlayerListener);
        this.mediaPlayer.setOnCompletionListener(mediaPlayerListener);
        this.mediaPlayer.setOnPreparedListener(mediaPlayerListener);
        this.mediaPlayer.setOnSeekCompleteListener(mediaPlayerListener);
        this.mediaPlayer.setOnBufferingUpdateListener(mediaPlayerListener);
        this.mediaPlayer.setOnInfoListener(mediaPlayerListener);
        try {
            this.mediaPlayer.setSurface(surface);
            JSONObject httpRequestHeaders = video.getHttpRequestHeaders();
            HashMap hashMap = new HashMap();
            if (httpRequestHeaders != null) {
                Iterator<String> keys = httpRequestHeaders.keys();
                while (keys.hasNext()) {
                    try {
                        String next = keys.next();
                        hashMap.put(next, httpRequestHeaders.getString(next));
                    } catch (JSONException unused) {
                    }
                }
            }
            this.mediaPlayer.setDataSource(this.context, video.getVideoUri(), hashMap);
            this.mediaPlayer.prepareAsync();
        } catch (Exception e) {
            reportError(e);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void stop() {
        if (!this.isPlayerReady) {
            release();
            return;
        }
        try {
            this.mediaPlayer.stop();
            release();
        } catch (Exception e) {
            reportError(e);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void release() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null) {
            try {
                mediaPlayer2.release();
            } catch (Exception unused) {
            }
            this.mediaPlayer = null;
            this.isPlayerReady = false;
            this.frameIntervalNs = C.TIME_UNSET;
            this.lastPresentationTimeNs = C.TIME_UNSET;
            this.eventListener.onRelease();
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void pause() {
        if (this.isPlayerReady) {
            try {
                this.mediaPlayer.pause();
            } catch (Exception e) {
                reportError(e);
            }
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void resume() {
        if (this.isPlayerReady) {
            try {
                this.mediaPlayer.start();
            } catch (Exception e) {
                reportError(e);
            }
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void seekTo(long j) {
        if (this.isPlayerReady) {
            try {
                this.mediaPlayer.seekTo((int) Math.min(Math.max(0L, j), (long) this.mediaPlayer.getDuration()));
            } catch (Exception e) {
                reportError(e);
            }
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public long getCurrentPositionMs() {
        if (!this.isPlayerReady) {
            return 0;
        }
        try {
            return (long) this.mediaPlayer.getCurrentPosition();
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public long getCurrentEpochTimePositionMs() {
        return getCurrentPositionMs();
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isPlaying() {
        if (!this.isPlayerReady) {
            return false;
        }
        try {
            return this.mediaPlayer.isPlaying();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isBuffering() {
        return this.isBuffering;
    }

    @Override // com.oculus.video.VideoPlayer
    public void setVolume(float f) {
        this.volume = f;
        setMute(this.isMuted);
    }

    @Override // com.oculus.video.VideoPlayer
    public float getVolume() {
        return this.volume;
    }

    @Override // com.oculus.video.VideoPlayer
    public void setMute(boolean z) {
        if (this.isPlayerReady) {
            this.isMuted = z;
            if (this.isMuted) {
                this.mediaPlayer.setVolume(0.0f, 0.0f);
                return;
            }
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            float f = this.volume;
            mediaPlayer2.setVolume(f, f);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isMuted() {
        return this.isMuted;
    }

    @Override // com.oculus.video.VideoPlayer
    public long getDurationMs() {
        if (!this.isPlayerReady) {
            return 0;
        }
        try {
            long duration = (long) this.mediaPlayer.getDuration();
            if (duration < 0) {
                return 0;
            }
            return duration;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void reportError(Exception exc) {
        this.eventListener.onError(exc);
        release();
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isSeekable() {
        return this.isPlayerReady && this.isSeekable;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if ((r0 - r6) < r2) goto L_0x001d;
     */
    @Override // com.oculus.video.VideoPlayer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getLastPresentationTimeUs() {
        /*
            r8 = this;
            long r0 = java.lang.System.nanoTime()
            long r2 = r8.frameIntervalNs
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0017
            long r6 = r8.lastPresentationTimeNs
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x0017
            long r0 = r0 - r6
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x001d
        L_0x0017:
            long r0 = java.lang.System.nanoTime()
            r8.lastPresentationTimeNs = r0
        L_0x001d:
            long r0 = r8.lastPresentationTimeNs
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.OculusMediaPlayer.getLastPresentationTimeUs():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        return (float) r2.getInteger("frame-rate");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0027 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float getFrameRate() {
        /*
            r6 = this;
            android.media.MediaPlayer r0 = r6.mediaPlayer
            android.media.MediaPlayer$TrackInfo[] r0 = r0.getTrackInfo()
            r1 = 0
        L_0x0007:
            int r2 = r0.length
            r3 = 0
            if (r1 >= r2) goto L_0x0031
            r2 = r0[r1]
            int r4 = r2.getTrackType()
            r5 = 1
            if (r4 != r5) goto L_0x002e
            android.media.MediaFormat r2 = r2.getFormat()
            if (r2 == 0) goto L_0x002e
            java.lang.String r4 = "frame-rate"
            boolean r5 = r2.containsKey(r4)
            if (r5 == 0) goto L_0x002e
            float r0 = r2.getFloat(r4)     // Catch:{ ClassCastException -> 0x0027 }
            return r0
        L_0x0027:
            int r0 = r2.getInteger(r4)     // Catch:{ ClassCastException -> 0x002d }
            float r0 = (float) r0
            return r0
        L_0x002d:
            return r3
        L_0x002e:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0031:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.OculusMediaPlayer.getFrameRate():float");
    }

    public int getRotationDegrees() {
        MediaFormat format;
        MediaPlayer.TrackInfo[] trackInfo = this.mediaPlayer.getTrackInfo();
        for (MediaPlayer.TrackInfo trackInfo2 : trackInfo) {
            if (trackInfo2.getTrackType() == 1 && (format = trackInfo2.getFormat()) != null && format.containsKey("rotation-degrees")) {
                return format.getInteger("rotation-degrees");
            }
        }
        return 0;
    }
}
