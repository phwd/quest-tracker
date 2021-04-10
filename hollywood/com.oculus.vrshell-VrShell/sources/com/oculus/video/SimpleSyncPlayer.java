package com.oculus.video;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.oculus.video.VideoPlayer;
import com.oculus.video.audio.AudioSpatializerController;

public class SimpleSyncPlayer extends OculusExoPlayer {
    private static final String TAG = "SimpleSyncPlayer";
    private boolean isFutureSeeking = false;
    private boolean preFutureSeekPlayWhenReady = false;
    private long targetSyncTimestampMs = -1;

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ long getBufferedPositionMs() {
        return super.getBufferedPositionMs();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ long getCurrentEpochTimePositionMs() {
        return super.getCurrentEpochTimePositionMs();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ long getCurrentPositionMs() {
        return super.getCurrentPositionMs();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ long getDurationMs() {
        return super.getDurationMs();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ long getLastPresentationTimeUs() {
        return super.getLastPresentationTimeUs();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ float getVolume() {
        return super.getVolume();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ boolean isMuted() {
        return super.isMuted();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ boolean isPlaying() {
        return super.isPlaying();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ boolean isSeekable() {
        return super.isSeekable();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ void onBeforeRender(long j) {
        super.onBeforeRender(j);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ void onRender(boolean z) {
        super.onRender(z);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ boolean selectSubtitle(String str) {
        return super.selectSubtitle(str);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ boolean selectVideoTrack(String str) {
        return super.selectVideoTrack(str);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ void setMute(boolean z) {
        super.setMute(z);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ void setVolume(float f) {
        super.setVolume(f);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    SimpleSyncPlayer(Context context, AudioSpatializerController audioSpatializerController, Handler handler, Settings settings) {
        super(context, audioSpatializerController, handler, settings);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void start(Video video, boolean z, Surface surface, VideoPlayer.EventListener eventListener) {
        super.start(video, z, surface, eventListener);
        resetSyncPlaybackProperties();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void pause() {
        finishFutureSeek();
        super.pause();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void resume() {
        finishFutureSeek();
        super.resume();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void seekTo(long j) {
        finishFutureSeek();
        if (this.exoPlayer2 != null) {
            this.exoPlayer2.seekTo(j);
        }
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public boolean isBuffering() {
        if (this.exoPlayer2 == null) {
            return false;
        }
        if (this.isFutureSeeking || this.exoPlayer2.getPlaybackState() == 2) {
            return true;
        }
        return false;
    }

    public void futureSeekTo(long j, long j2) {
        if (this.exoPlayer2 != null) {
            if (!this.isFutureSeeking) {
                this.preFutureSeekPlayWhenReady = this.exoPlayer2.getPlayWhenReady();
            }
            this.targetSyncTimestampMs = j2;
            initialFutureSeek(j);
        }
    }

    public void updateSyncPlaybackState() {
        if (this.exoPlayer2 != null && this.isFutureSeeking && this.targetSyncTimestampMs <= System.currentTimeMillis() && this.exoPlayer2.getPlaybackState() == 3) {
            finishFutureSeek();
        }
    }

    private void initialFutureSeek(long j) {
        if (this.exoPlayer2 != null) {
            this.exoPlayer2.seekTo(j);
            this.exoPlayer2.setPlayWhenReady(false);
            this.isFutureSeeking = true;
            String str = TAG;
            Log.d(str, "Resync start at (futureSeekTimeMS): " + j);
        }
    }

    private void finishFutureSeek() {
        if (this.exoPlayer2 != null && this.isFutureSeeking) {
            this.exoPlayer2.setPlayWhenReady(this.preFutureSeekPlayWhenReady);
            String str = TAG;
            Log.d(str, "Resync completed at (futureSeekTimeMS): " + this.targetSyncTimestampMs + " with playWhenReady: " + this.preFutureSeekPlayWhenReady);
            resetSyncPlaybackProperties();
        }
    }

    private void resetSyncPlaybackProperties() {
        this.isFutureSeeking = false;
        this.preFutureSeekPlayWhenReady = false;
        this.targetSyncTimestampMs = -1;
    }
}
