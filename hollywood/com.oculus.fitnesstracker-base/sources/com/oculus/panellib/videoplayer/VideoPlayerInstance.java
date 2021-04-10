package com.oculus.panellib.videoplayer;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import com.oculus.common.build.BuildConfig;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class VideoPlayerInstance implements SurfaceTexture.OnFrameAvailableListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {
    private AudioManager mAudioManager = null;
    private final long mCtxPtr;
    private boolean mFrameAvailable = false;
    private boolean mIsPrepareFinished = false;
    private boolean mIsPrepareStarted = false;
    private int mLastPosition = Integer.MIN_VALUE;
    private boolean mLooping = false;
    private MediaPlayer mMediaPlayer = null;
    private boolean mPlay = false;
    private int mPositionEventThrottle = 500;
    private Surface mSurface = null;
    private SurfaceTexture mSurfaceTexture = null;
    private final int mVideoHandle;
    private String mVideoSource = BuildConfig.PROVIDER_SUFFIX;
    private float mVolume = 1.0f;

    public VideoPlayerInstance(long j, int i) {
        this.mCtxPtr = j;
        this.mVideoHandle = i;
    }

    public synchronized void close() {
        Log.d("RCTVideo", "VideoPlayerInstance called close");
        this.mVideoSource = BuildConfig.PROVIDER_SUFFIX;
        this.mPlay = false;
        this.mIsPrepareStarted = false;
        this.mIsPrepareFinished = false;
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
        if (this.mSurface != null) {
            this.mSurface.release();
            this.mSurface = null;
        }
        if (this.mSurfaceTexture != null) {
            this.mSurfaceTexture.release();
            this.mSurfaceTexture = null;
        }
    }

    public synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.mMediaPlayer != null) {
            this.mFrameAvailable = true;
            int currentPosition = this.mMediaPlayer.getCurrentPosition();
            if (Math.abs(currentPosition - this.mLastPosition) >= this.mPositionEventThrottle) {
                sendOnPositionChangeEvent(currentPosition);
            }
        }
    }

    public synchronized void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("RCTVideo", "MediaPlayer finished prepare");
        this.mIsPrepareFinished = true;
        VideoPlayer.notifyOnDurationChange(this.mCtxPtr, this.mVideoHandle, mediaPlayer.getDuration());
        sendOnPositionChangeEvent(mediaPlayer.getCurrentPosition());
        syncPlayState();
    }

    public synchronized void onSeekComplete(MediaPlayer mediaPlayer) {
        Log.d("RCTVideo", "MediaPlayer completed seek");
        int currentPosition = mediaPlayer.getCurrentPosition();
        sendOnPositionChangeEvent(currentPosition);
        VideoPlayer.notifyOnSeek(this.mCtxPtr, this.mVideoHandle, currentPosition);
        syncPlayState();
    }

    public synchronized void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("RCTVideo", "MediaPlayer playback reached completion of " + this.mVideoSource);
        sendOnPositionChangeEvent(mediaPlayer.getCurrentPosition());
        VideoPlayer.notifyOnCompletion(this.mCtxPtr, this.mVideoHandle);
        if (this.mLooping) {
            syncPlayState();
        }
    }

    public synchronized void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d("RCTVideo", "MediaPlayer video size changed: " + i + ", " + i2);
        VideoPlayer.notifyOnVideoSizeChange(this.mCtxPtr, this.mVideoHandle, i, i2);
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

    public synchronized void sendOnPositionChangeEvent(int i) {
        VideoPlayer.notifyOnPositionChange(this.mCtxPtr, this.mVideoHandle, i);
        this.mLastPosition = i;
    }

    public static Application getApplicationContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getInstalledPackagePath(Context context, String str) {
        Log.d("RCTVideo", "Searching installed packages for '" + str + "'");
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo.sourceDir != null) {
                return applicationInfo.sourceDir;
            }
            return BuildConfig.PROVIDER_SUFFIX;
        } catch (Exception e) {
            Log.w("RCTVideo", "Package '" + str + "' not installed", e);
            return BuildConfig.PROVIDER_SUFFIX;
        }
    }

    private VideoPlayerDataSource createOptionalDataSource() {
        Uri parse = Uri.parse(this.mVideoSource);
        String scheme = parse.getScheme();
        String host = parse.getHost();
        String path = parse.getPath();
        Log.d("RCTVideo", "URI Scheme: " + scheme);
        Log.d("RCTVideo", "URI Host: " + host);
        Log.d("RCTVideo", "URI Path: " + path);
        boolean z = scheme == null && host == null;
        if (!z && !scheme.equals("apk")) {
            return null;
        }
        Application applicationContext = getApplicationContext();
        if (z) {
            host = applicationContext.getPackageName();
        }
        String installedPackagePath = getInstalledPackagePath(applicationContext, host);
        Log.d("RCTVideo", "Install path: " + installedPackagePath);
        if (installedPackagePath.equals(BuildConfig.PROVIDER_SUFFIX)) {
            return null;
        }
        if (path.startsWith("/assets/")) {
            path = path.substring(1);
        }
        try {
            ZipFile zipFile = new ZipFile(installedPackagePath);
            Log.d("RCTVideo", "Zip size: " + zipFile.size());
            ZipEntry entry = zipFile.getEntry(path);
            if (entry == null) {
                Log.w("RCTVideo", "Failed to find file in apk: " + path);
            }
            Log.d("RCTVideo", "ZipEntry size: " + entry.getSize());
            InputStream inputStream = zipFile.getInputStream(entry);
            Log.d("RCTVideo", "Stream available: " + inputStream.available());
            return new VideoPlayerDataSource(inputStream, (int) entry.getSize());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTexture(int i) {
        Log.d("RCTVideo", "VideoPlayerInstance called updateTexture: " + i);
        SurfaceTexture surfaceTexture = new SurfaceTexture(i);
        Surface surface = new Surface(surfaceTexture);
        surfaceTexture.setOnFrameAvailableListener(this);
        synchronized (this) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.setSurface(surface);
            }
            if (this.mSurface != null) {
                this.mSurface.release();
            }
            if (this.mSurfaceTexture != null) {
                this.mSurfaceTexture.release();
            }
            this.mSurfaceTexture = surfaceTexture;
            this.mSurface = surface;
        }
    }

    public void prepareVideo() {
        Log.d("RCTVideo", "VideoPlayerInstance called prepareVideo on " + this.mVideoSource);
        if (!this.mVideoSource.isEmpty()) {
            if (this.mIsPrepareStarted) {
                Log.d("RCTVideo", "VideoPlayerInstance has already prepared " + this.mVideoSource);
                return;
            }
            this.mIsPrepareStarted = true;
            this.mIsPrepareFinished = false;
            synchronized (this) {
                if (this.mMediaPlayer == null) {
                    this.mMediaPlayer = new MediaPlayer();
                    this.mMediaPlayer.setOnCompletionListener(this);
                    this.mMediaPlayer.setOnErrorListener(this);
                    this.mMediaPlayer.setOnPreparedListener(this);
                    this.mMediaPlayer.setOnSeekCompleteListener(this);
                    this.mMediaPlayer.setOnVideoSizeChangedListener(this);
                } else {
                    this.mMediaPlayer.reset();
                }
                this.mMediaPlayer.setSurface(this.mSurface);
            }
            this.mMediaPlayer.setLooping(false);
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            float f = this.mVolume;
            mediaPlayer.setVolume(f, f);
            try {
                VideoPlayerDataSource createOptionalDataSource = createOptionalDataSource();
                if (createOptionalDataSource != null) {
                    Log.d("RCTVideo", "Using dataSource");
                    this.mMediaPlayer.setDataSource(createOptionalDataSource);
                } else {
                    Log.d("RCTVideo", "Using url");
                    this.mMediaPlayer.setDataSource(this.mVideoSource);
                }
                Log.d("RCTVideo", "MediaPlayer start prepare");
                this.mMediaPlayer.prepareAsync();
            } catch (Exception e) {
                Log.e("RCTVideo", "MediaPlayer prepare failed");
                e.printStackTrace();
            }
            VideoPlayer.notifyOnDurationChange(this.mCtxPtr, this.mVideoHandle, -1);
        }
    }

    public void setLooping(boolean z) {
        Log.d("RCTVideo", "VideoPlayerInstance called setLooping: " + z);
        this.mLooping = z;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(z);
        }
    }

    public void setPlay(boolean z) {
        Log.d("RCTVideo", "VideoPlayerInstance called setPlay: " + z);
        this.mPlay = z;
        syncPlayState();
    }

    public void setVideoSource(String str) {
        Log.d("RCTVideo", "VideoPlayerInstance called setVideoSource: " + str);
        if (this.mVideoSource == str) {
            Log.d("RCTVideo", "VideoPlayerInstance is already using source " + this.mVideoSource);
            return;
        }
        this.mVideoSource = str;
        this.mIsPrepareStarted = false;
        this.mIsPrepareFinished = false;
    }

    public void setPositionEventThrottle(int i) {
        this.mPositionEventThrottle = i;
    }

    public void setVolume(float f) {
        this.mVolume = f;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    private boolean isVideoReady() {
        return this.mMediaPlayer != null && this.mIsPrepareStarted && this.mIsPrepareFinished && !this.mVideoSource.isEmpty();
    }

    private void syncPlayState() {
        if (isVideoReady()) {
            if (this.mPlay) {
                if (!this.mMediaPlayer.isPlaying()) {
                    this.mMediaPlayer.start();
                }
            } else if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
            }
        }
    }

    public void seekVideo(int i, boolean z) {
        Log.d("RCTVideo", "VideoPlayerInstance called seekVideo: " + i + ", " + z);
        if (isVideoReady()) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mMediaPlayer.seekTo((long) i, z ? 3 : 2);
                return;
            }
            if (z) {
                Log.d("RCTVideo", "Exact seek is unsupported, using SEEK_PREVIOUS_SYNC");
            }
            this.mMediaPlayer.seekTo(i);
        }
    }

    public int frame() {
        if (!this.mFrameAvailable) {
            return 0;
        }
        synchronized (this) {
            if (this.mSurfaceTexture != null) {
                if (!this.mSurfaceTexture.isReleased()) {
                    this.mSurfaceTexture.updateTexImage();
                    this.mFrameAvailable = false;
                    return 1;
                }
            }
            return 0;
        }
    }
}
