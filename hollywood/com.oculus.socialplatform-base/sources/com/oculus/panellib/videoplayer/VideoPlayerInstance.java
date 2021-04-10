package com.oculus.panellib.videoplayer;

import X.AnonymousClass006;
import android.app.Application;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.Surface;

public class VideoPlayerInstance implements SurfaceTexture.OnFrameAvailableListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {
    public AudioManager mAudioManager = null;
    public final long mCtxPtr;
    public boolean mFrameAvailable = false;
    public boolean mIsPrepareFinished = false;
    public boolean mIsPrepareStarted = false;
    public int mLastPosition = Integer.MIN_VALUE;
    public boolean mLooping = false;
    public MediaPlayer mMediaPlayer = null;
    public boolean mPlay = false;
    public int mPositionEventThrottle = 500;
    public Surface mSurface = null;
    public SurfaceTexture mSurfaceTexture = null;
    public final int mVideoHandle;
    public String mVideoSource = "";
    public float mVolume = 1.0f;

    public static Application getApplicationContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized void close() {
        this.mVideoSource = "";
        this.mPlay = false;
        this.mIsPrepareStarted = false;
        this.mIsPrepareFinished = false;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mMediaPlayer = null;
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
    }

    public synchronized void onCompletion(MediaPlayer mediaPlayer) {
        sendOnPositionChangeEvent(mediaPlayer.getCurrentPosition());
        VideoPlayer.notifyOnCompletion(this.mCtxPtr, this.mVideoHandle);
        if (this.mLooping) {
            syncPlayState();
        }
    }

    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = "reason unknown";
        if (i2 == -1004) {
            str = "IO error";
        } else if (i2 == -1007) {
            str = "malformed bitstream";
        } else if (i2 == -1010) {
            str = "unsupported bitstream";
        } else if (i2 == -110) {
            str = "operation timed out";
        }
        Log.w("RCTVideo", String.format("MediaPlayer error: %s (what = %d, extra = %d)", str, Integer.valueOf(i), Integer.valueOf(i2)));
        return true;
    }

    public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            this.mFrameAvailable = true;
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (Math.abs(currentPosition - this.mLastPosition) >= this.mPositionEventThrottle) {
                sendOnPositionChangeEvent(currentPosition);
            }
        }
    }

    public synchronized void onPrepared(MediaPlayer mediaPlayer) {
        this.mIsPrepareFinished = true;
        VideoPlayer.notifyOnDurationChange(this.mCtxPtr, this.mVideoHandle, mediaPlayer.getDuration());
        sendOnPositionChangeEvent(mediaPlayer.getCurrentPosition());
        syncPlayState();
    }

    public synchronized void onSeekComplete(MediaPlayer mediaPlayer) {
        int currentPosition = mediaPlayer.getCurrentPosition();
        sendOnPositionChangeEvent(currentPosition);
        VideoPlayer.notifyOnSeek(this.mCtxPtr, this.mVideoHandle, currentPosition);
        syncPlayState();
    }

    public synchronized void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        VideoPlayer.notifyOnVideoSizeChange(this.mCtxPtr, this.mVideoHandle, i, i2);
    }

    public synchronized void sendOnPositionChangeEvent(int i) {
        VideoPlayer.notifyOnPositionChange(this.mCtxPtr, this.mVideoHandle, i);
        this.mLastPosition = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0018, code lost:
        if (r6 != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.oculus.panellib.videoplayer.VideoPlayerDataSource createOptionalDataSource() {
        /*
        // Method dump skipped, instructions count: 122
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.videoplayer.VideoPlayerInstance.createOptionalDataSource():com.oculus.panellib.videoplayer.VideoPlayerDataSource");
    }

    public static String getInstalledPackagePath(Context context, String str) {
        try {
            String str2 = context.getPackageManager().getApplicationInfo(str, 0).sourceDir;
            return str2 != null ? str2 : "";
        } catch (Exception e) {
            Log.w("RCTVideo", AnonymousClass006.A09("Package '", str, "' not installed"), e);
            return "";
        }
    }

    private boolean isVideoReady() {
        if (this.mMediaPlayer == null || !this.mIsPrepareStarted || !this.mIsPrepareFinished || this.mVideoSource.isEmpty()) {
            return false;
        }
        return true;
    }

    public int frame() {
        if (!this.mFrameAvailable) {
            return 0;
        }
        synchronized (this) {
            SurfaceTexture surfaceTexture = this.mSurfaceTexture;
            if (surfaceTexture == null || surfaceTexture.isReleased()) {
                return 0;
            }
            this.mSurfaceTexture.updateTexImage();
            this.mFrameAvailable = false;
            return 1;
        }
    }

    public void prepareVideo() {
        if (!this.mVideoSource.isEmpty() && !this.mIsPrepareStarted) {
            this.mIsPrepareStarted = true;
            this.mIsPrepareFinished = false;
            synchronized (this) {
                MediaPlayer mediaPlayer = this.mMediaPlayer;
                if (mediaPlayer == null) {
                    MediaPlayer mediaPlayer2 = new MediaPlayer();
                    this.mMediaPlayer = mediaPlayer2;
                    mediaPlayer2.setOnCompletionListener(this);
                    this.mMediaPlayer.setOnErrorListener(this);
                    this.mMediaPlayer.setOnPreparedListener(this);
                    this.mMediaPlayer.setOnSeekCompleteListener(this);
                    this.mMediaPlayer.setOnVideoSizeChangedListener(this);
                } else {
                    mediaPlayer.reset();
                }
                this.mMediaPlayer.setSurface(this.mSurface);
            }
            this.mMediaPlayer.setLooping(false);
            MediaPlayer mediaPlayer3 = this.mMediaPlayer;
            float f = this.mVolume;
            mediaPlayer3.setVolume(f, f);
            try {
                VideoPlayerDataSource createOptionalDataSource = createOptionalDataSource();
                if (createOptionalDataSource != null) {
                    this.mMediaPlayer.setDataSource(createOptionalDataSource);
                } else {
                    this.mMediaPlayer.setDataSource(this.mVideoSource);
                }
                this.mMediaPlayer.prepareAsync();
            } catch (Exception e) {
                Log.e("RCTVideo", "MediaPlayer prepare failed");
                e.printStackTrace();
            }
            VideoPlayer.notifyOnDurationChange(this.mCtxPtr, this.mVideoHandle, -1);
        }
    }

    public void setLooping(boolean z) {
        this.mLooping = z;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(z);
        }
    }

    public void setPlay(boolean z) {
        this.mPlay = z;
        syncPlayState();
    }

    public void setVideoSource(String str) {
        if (this.mVideoSource != str) {
            this.mVideoSource = str;
            this.mIsPrepareStarted = false;
            this.mIsPrepareFinished = false;
        }
    }

    public void setVolume(float f) {
        this.mVolume = f;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    public void updateTexture(int i) {
        SurfaceTexture surfaceTexture = new SurfaceTexture(i);
        Surface surface = new Surface(surfaceTexture);
        surfaceTexture.setOnFrameAvailableListener(this);
        synchronized (this) {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setSurface(surface);
            }
            Surface surface2 = this.mSurface;
            if (surface2 != null) {
                surface2.release();
            }
            SurfaceTexture surfaceTexture2 = this.mSurfaceTexture;
            if (surfaceTexture2 != null) {
                surfaceTexture2.release();
            }
            this.mSurfaceTexture = surfaceTexture;
            this.mSurface = surface;
        }
    }

    public VideoPlayerInstance(long j, int i) {
        this.mCtxPtr = j;
        this.mVideoHandle = i;
    }

    private void syncPlayState() {
        if (!isVideoReady()) {
            return;
        }
        if (this.mPlay) {
            if (!this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.start();
            }
        } else if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
        }
    }

    public void seekVideo(int i, boolean z) {
        if (!isVideoReady()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            int i2 = 2;
            if (z) {
                i2 = 3;
            }
            this.mMediaPlayer.seekTo((long) i, i2);
            return;
        }
        this.mMediaPlayer.seekTo(i);
    }

    public void setPositionEventThrottle(int i) {
        this.mPositionEventThrottle = i;
    }
}
