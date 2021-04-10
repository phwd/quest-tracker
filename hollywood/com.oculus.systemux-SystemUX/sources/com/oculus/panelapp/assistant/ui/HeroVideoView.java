package com.oculus.panelapp.assistant.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.IOException;

public class HeroVideoView extends FrameLayout implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final String TAG = "HeroVideoView";
    private OnErrorListener mErrorListener;
    private MediaPlayer mMediaPlayer;
    private String mPendingUri;
    private TextureView mTextureView;

    public interface OnErrorListener {
        void onError(Exception exc);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public HeroVideoView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HeroVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public HeroVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void init(Context context) {
        this.mTextureView = new TextureView(context);
        this.mTextureView.setSurfaceTextureListener(this);
        this.mTextureView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.mTextureView);
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnPreparedListener(this);
    }

    public void play(String str) {
        postDelayed(new Runnable(str) {
            /* class com.oculus.panelapp.assistant.ui.$$Lambda$HeroVideoView$Y0Zx152lXSEofeSNNsl4uBL80b4 */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                HeroVideoView.this.lambda$play$35$HeroVideoView(this.f$1);
            }
        }, 75);
    }

    public void setErrorListener(OnErrorListener onErrorListener) {
        this.mErrorListener = onErrorListener;
    }

    @RequiresApi(api = 24)
    /* renamed from: onPlay */
    public void lambda$play$35$HeroVideoView(String str) {
        if (this.mMediaPlayer == null) {
            this.mPendingUri = str;
            return;
        }
        Log.d(TAG, "Preparing media for playback.");
        try {
            this.mMediaPlayer.setDataSource(getFdFromUri(str));
            this.mMediaPlayer.prepare();
        } catch (IOException | IllegalStateException e) {
            onError(e);
        }
    }

    public AssetFileDescriptor getFdFromUri(String str) throws IOException {
        Uri parse = Uri.parse(str);
        return getFdFromPackage(parse.getAuthority(), parse.getLastPathSegment());
    }

    public AssetFileDescriptor getFdFromPackage(String str, String str2) throws IOException {
        try {
            return getContext().getPackageManager().getResourcesForApplication(str).getAssets().openFd(str2);
        } catch (PackageManager.NameNotFoundException e) {
            onError(e);
            return null;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        prepMediaPlayer(surfaceTexture);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return false;
        }
        mediaPlayer.release();
        this.mMediaPlayer = null;
        return false;
    }

    private void prepMediaPlayer(SurfaceTexture surfaceTexture) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mMediaPlayer = null;
        }
        Log.d(TAG, "Preparing media player.");
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setSurface(new Surface(surfaceTexture));
        this.mMediaPlayer.setLooping(true);
        this.mMediaPlayer.setOnErrorListener(this);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this);
        String str = this.mPendingUri;
        if (str != null) {
            play(str);
            this.mPendingUri = null;
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "Media prepared, playing...");
        mediaPlayer.start();
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        onError(new Exception("Error playing back media. Error: " + i));
        return false;
    }

    /* access modifiers changed from: protected */
    public void onError(Exception exc) {
        OnErrorListener onErrorListener = this.mErrorListener;
        if (onErrorListener != null) {
            onErrorListener.onError(exc);
        } else {
            Log.e(TAG, exc.getMessage(), exc);
        }
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        String str = TAG;
        Log.d(str, "Video size changed: " + i + "x" + i2);
    }
}
