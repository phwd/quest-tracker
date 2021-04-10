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
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
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
    private int mPositionEventThrottle = ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS;
    private Surface mSurface = null;
    private SurfaceTexture mSurfaceTexture = null;
    private final int mVideoHandle;
    private String mVideoSource = "";
    private float mVolume = 1.0f;

    public VideoPlayerInstance(long ctxPtr, int videoHandle) {
        this.mCtxPtr = ctxPtr;
        this.mVideoHandle = videoHandle;
    }

    public synchronized void close() {
        Log.d("RCTVideo", "VideoPlayerInstance called close");
        this.mVideoSource = "";
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

    public synchronized void onFrameAvailable(SurfaceTexture surface) {
        if (this.mMediaPlayer != null) {
            this.mFrameAvailable = true;
            int currentPosition = this.mMediaPlayer.getCurrentPosition();
            if (Math.abs(currentPosition - this.mLastPosition) >= this.mPositionEventThrottle) {
                sendOnPositionChangeEvent(currentPosition);
            }
        }
    }

    public synchronized void onPrepared(MediaPlayer mp) {
        Log.d("RCTVideo", "MediaPlayer finished prepare");
        this.mIsPrepareFinished = true;
        VideoPlayer.notifyOnDurationChange(this.mCtxPtr, this.mVideoHandle, mp.getDuration());
        sendOnPositionChangeEvent(mp.getCurrentPosition());
        syncPlayState();
    }

    public synchronized void onSeekComplete(MediaPlayer mp) {
        Log.d("RCTVideo", "MediaPlayer completed seek");
        int currentPosition = mp.getCurrentPosition();
        sendOnPositionChangeEvent(currentPosition);
        VideoPlayer.notifyOnSeek(this.mCtxPtr, this.mVideoHandle, currentPosition);
        syncPlayState();
    }

    public synchronized void onCompletion(MediaPlayer mp) {
        Log.d("RCTVideo", "MediaPlayer playback reached completion of " + this.mVideoSource);
        sendOnPositionChangeEvent(mp.getCurrentPosition());
        VideoPlayer.notifyOnCompletion(this.mCtxPtr, this.mVideoHandle);
        if (this.mLooping) {
            syncPlayState();
        }
    }

    public synchronized void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.d("RCTVideo", "MediaPlayer video size changed: " + width + ", " + height);
        VideoPlayer.notifyOnVideoSizeChange(this.mCtxPtr, this.mVideoHandle, width, height);
    }

    public synchronized boolean onError(MediaPlayer mp, int what, int extra) {
        String reason = "reason unknown";
        if (extra == -1004) {
            reason = "IO error";
        } else if (extra == -1007) {
            reason = "malformed bitstream";
        } else if (extra == -1010) {
            reason = "unsupported bitstream";
        } else if (extra == -110) {
            reason = "operation timed out";
        }
        Log.w("RCTVideo", String.format("MediaPlayer error: %s (what = %d, extra = %d)", reason, Integer.valueOf(what), Integer.valueOf(extra)));
        return true;
    }

    public synchronized void sendOnPositionChangeEvent(int currentPosition) {
        VideoPlayer.notifyOnPositionChange(this.mCtxPtr, this.mVideoHandle, currentPosition);
        this.mLastPosition = currentPosition;
    }

    public static Application getApplicationContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getInstalledPackagePath(Context context, String packageName) {
        Log.d("RCTVideo", "Searching installed packages for '" + packageName + "'");
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, 0);
            return info.sourceDir != null ? info.sourceDir : "";
        } catch (Exception e) {
            Log.w("RCTVideo", "Package '" + packageName + "' not installed", e);
            return "";
        }
    }

    private VideoPlayerDataSource createOptionalDataSource() {
        String resolvedHost;
        Uri parsedUri = Uri.parse(this.mVideoSource);
        String scheme = parsedUri.getScheme();
        String host = parsedUri.getHost();
        String filePath = parsedUri.getPath();
        Log.d("RCTVideo", "URI Scheme: " + scheme);
        Log.d("RCTVideo", "URI Host: " + host);
        Log.d("RCTVideo", "URI Path: " + filePath);
        boolean isRelativePath = scheme == null && host == null;
        if (!isRelativePath && !scheme.equals("apk")) {
            return null;
        }
        Context appContext = getApplicationContext();
        if (isRelativePath) {
            resolvedHost = appContext.getPackageName();
        } else {
            resolvedHost = host;
        }
        String installPath = getInstalledPackagePath(appContext, resolvedHost);
        Log.d("RCTVideo", "Install path: " + installPath);
        if (installPath.equals("")) {
            return null;
        }
        if (filePath.startsWith("/assets/")) {
            filePath = filePath.substring(1);
        }
        try {
            ZipFile apkZipFile = new ZipFile(installPath);
            Log.d("RCTVideo", "Zip size: " + apkZipFile.size());
            ZipEntry fileEntry = apkZipFile.getEntry(filePath);
            if (fileEntry == null) {
                Log.w("RCTVideo", "Failed to find file in apk: " + filePath);
            }
            Log.d("RCTVideo", "ZipEntry size: " + fileEntry.getSize());
            InputStream stream = apkZipFile.getInputStream(fileEntry);
            Log.d("RCTVideo", "Stream available: " + stream.available());
            return new VideoPlayerDataSource(stream, (int) fileEntry.getSize());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateTexture(int textureIndex) {
        Log.d("RCTVideo", "VideoPlayerInstance called updateTexture: " + textureIndex);
        SurfaceTexture newSurfaceTexture = new SurfaceTexture(textureIndex);
        Surface newSurface = new Surface(newSurfaceTexture);
        newSurfaceTexture.setOnFrameAvailableListener(this);
        synchronized (this) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.setSurface(newSurface);
            }
            if (this.mSurface != null) {
                this.mSurface.release();
            }
            if (this.mSurfaceTexture != null) {
                this.mSurfaceTexture.release();
            }
            this.mSurfaceTexture = newSurfaceTexture;
            this.mSurface = newSurface;
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
            this.mMediaPlayer.setVolume(this.mVolume, this.mVolume);
            try {
                VideoPlayerDataSource dataSource = createOptionalDataSource();
                if (dataSource != null) {
                    Log.d("RCTVideo", "Using dataSource");
                    this.mMediaPlayer.setDataSource(dataSource);
                } else {
                    Log.d("RCTVideo", "Using url");
                    this.mMediaPlayer.setDataSource(this.mVideoSource);
                }
                Log.d("RCTVideo", "MediaPlayer start prepare");
                this.mMediaPlayer.prepareAsync();
            } catch (Exception ex) {
                Log.e("RCTVideo", "MediaPlayer prepare failed");
                ex.printStackTrace();
            }
            VideoPlayer.notifyOnDurationChange(this.mCtxPtr, this.mVideoHandle, -1);
        }
    }

    public void setLooping(boolean looping) {
        Log.d("RCTVideo", "VideoPlayerInstance called setLooping: " + looping);
        this.mLooping = looping;
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setLooping(looping);
        }
    }

    public void setPlay(boolean play) {
        Log.d("RCTVideo", "VideoPlayerInstance called setPlay: " + play);
        this.mPlay = play;
        syncPlayState();
    }

    public void setVideoSource(String url) {
        Log.d("RCTVideo", "VideoPlayerInstance called setVideoSource: " + url);
        if (this.mVideoSource == url) {
            Log.d("RCTVideo", "VideoPlayerInstance is already using source " + this.mVideoSource);
            return;
        }
        this.mVideoSource = url;
        this.mIsPrepareStarted = false;
        this.mIsPrepareFinished = false;
    }

    public void setPositionEventThrottle(int positionEventThrottle) {
        this.mPositionEventThrottle = positionEventThrottle;
    }

    public void setVolume(float volume) {
        this.mVolume = volume;
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setVolume(volume, volume);
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

    public void seekVideo(int positionInMs, boolean isExact) {
        Log.d("RCTVideo", "VideoPlayerInstance called seekVideo: " + positionInMs + ", " + isExact);
        if (isVideoReady()) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mMediaPlayer.seekTo((long) positionInMs, isExact ? 3 : 2);
                return;
            }
            if (isExact) {
                Log.d("RCTVideo", "Exact seek is unsupported, using SEEK_PREVIOUS_SYNC");
            }
            this.mMediaPlayer.seekTo(positionInMs);
        }
    }

    public int frame() {
        if (!this.mFrameAvailable) {
            return 0;
        }
        synchronized (this) {
            if (this.mSurfaceTexture == null || this.mSurfaceTexture.isReleased()) {
                return 0;
            }
            this.mSurfaceTexture.updateTexImage();
            this.mFrameAvailable = false;
            return 1;
        }
    }
}
