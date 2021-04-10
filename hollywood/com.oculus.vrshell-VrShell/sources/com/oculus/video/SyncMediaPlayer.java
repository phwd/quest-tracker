package com.oculus.video;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.DefaultLoadControl;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.video.OculusExoPlayer;
import com.oculus.video.VideoPlayer;
import com.oculus.video.audio.AudioSpatializerController;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class SyncMediaPlayer extends OculusExoPlayer {
    public static final long SYNC_DELAY_UNSET_MS = -3600000;
    private static final String TAG = "SyncMediaPlayer";
    private final int FUTURE_SEEK_OFFSET_DEFAULT_MS = 2000;
    private final int FUTURE_SEEK_OFFSET_MAX_MS = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS;
    private final float FUTURE_SEEK_OFFSET_MULTIPLIER = 1.7f;
    private final int OFF_SYNC_THRESHOLD_MS = 500;
    private final int ONE_SEC_MS = 1000;
    volatile long curPlaylistDurationMillis = -1;
    volatile long curPlaylistPDTMillis = -1;
    private int curPlaylistSelected;
    private long firstSyncOffsetTimeMs = C.TIME_UNSET;
    private long futureSeekOffsetMS = 0;
    private long futureSeekTimeMS = -1;
    private boolean isFirstTimeSyncing = false;
    private boolean isFutureSeeking = false;
    private boolean isLive = false;
    private boolean isLoop = false;
    volatile boolean isMetaDataSkipped = false;
    private long lastReferenceSyncTimeMs = -1;
    private long lastSystemSyncTimeMs = -1;
    volatile int numOfTickToSkipSync = -1;
    private final ReentrantLock pdtLock = new ReentrantLock();
    private boolean useMediaTypeEvent = false;

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void pause() {
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void resume() {
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void seekTo(long j) {
    }

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

    protected class SyncVideoRendererEventListener extends OculusExoPlayer.OculusVideoRendererEventListener {
        protected SyncVideoRendererEventListener() {
            super();
        }

        @Override // com.oculus.video.OculusExoPlayer.OculusVideoRendererEventListener, com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onVideoInputFormatChanged(Format format) {
            super.onVideoInputFormatChanged(format);
            SyncMediaPlayer.this.curPlaylistSelected = format != null ? format.bitrate : 0;
            String str = SyncMediaPlayer.TAG;
            Log.i(str, "onVideoInputFormatChanged, curPlaylistSelected : " + SyncMediaPlayer.this.curPlaylistSelected);
        }
    }

    protected class SyncPrimaryHlsPlaylistListener extends OculusExoPlayer.PrimaryHlsPlaylistListener {
        protected SyncPrimaryHlsPlaylistListener() {
            super();
        }

        @Override // com.oculus.video.OculusExoPlayer.PrimaryHlsPlaylistListener, com.oculus.video.source.hls.OculusHlsPlaylistTracker.PrimaryPlaylistListener
        public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist, HlsMasterPlaylist.HlsUrl hlsUrl, HlsMasterPlaylist hlsMasterPlaylist) {
            if (hlsUrl != null && hlsUrl.format != null) {
                super.onPrimaryPlaylistRefreshed(hlsMediaPlaylist, hlsUrl, hlsMasterPlaylist);
                String profileLog = SyncMediaPlayer.this.getProfileLog(hlsUrl.format);
                List<HlsMasterPlaylist.HlsUrl> list = hlsMasterPlaylist.variants;
                String str = "";
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).format != null) {
                        str = str + SyncMediaPlayer.this.getProfileLog(list.get(i).format) + "; ";
                    }
                }
                Log.i(SyncMediaPlayer.TAG, "onPrimaryPlaylistChanged: selected profile:" + profileLog + "; " + "available profiles: " + str);
                SyncMediaPlayer.this.pdtLock.lock();
                try {
                    if (SyncMediaPlayer.this.isMetaDataSkipped) {
                        SyncMediaPlayer.this.curPlaylistSelected = hlsUrl.format.bitrate;
                        long j = SyncMediaPlayer.this.curPlaylistPDTMillis;
                        SyncMediaPlayer.this.curPlaylistPDTMillis = hlsMediaPlaylist.startTimeUs / 1000;
                        SyncMediaPlayer.this.curPlaylistDurationMillis = hlsMediaPlaylist.durationUs / 1000;
                        if (j > 0) {
                            SyncMediaPlayer.this.futureSeekTimeMS -= SyncMediaPlayer.this.curPlaylistPDTMillis - j;
                        }
                        SyncMediaPlayer.this.numOfTickToSkipSync = 5;
                        Log.i(SyncMediaPlayer.TAG, "prevPlaylistPDTMillis : " + j + ", curPlaylistPDTMillis : " + SyncMediaPlayer.this.curPlaylistPDTMillis + ", curPlaylistDurationMillis : " + SyncMediaPlayer.this.curPlaylistDurationMillis);
                    }
                    SyncMediaPlayer.this.isMetaDataSkipped = true;
                } finally {
                    SyncMediaPlayer.this.pdtLock.unlock();
                }
            }
        }
    }

    SyncMediaPlayer(Context context, AudioSpatializerController audioSpatializerController, Handler handler, Settings settings) {
        super(context, audioSpatializerController, handler, settings);
        this.primaryHlsPlaylistListener = new SyncPrimaryHlsPlaylistListener();
        this.videoRendererEventListener = new SyncVideoRendererEventListener();
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public void start(Video video, boolean z, Surface surface, VideoPlayer.EventListener eventListener) {
        super.start(video, z, surface, eventListener);
        setVolume(0.1f);
        resetSyncPlaybackProperty();
        this.exoPlayer2.setPlayWhenReady(this.isLive && !this.useMediaTypeEvent);
    }

    @Override // com.oculus.video.VideoPlayer, com.oculus.video.OculusExoPlayer
    public boolean isBuffering() {
        int playbackState = this.exoPlayer2 == null ? 1 : this.exoPlayer2.getPlaybackState();
        if (this.isFutureSeeking || playbackState == 2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setSyncReferenceTimeMs(long j, long j2) {
        if (j > 0 && j2 > 0) {
            this.lastReferenceSyncTimeMs = j;
            this.lastSystemSyncTimeMs = j2;
        }
    }

    /* access modifiers changed from: package-private */
    public long getSyncDelayMs() {
        if (this.exoPlayer2 == null || !(this.exoPlayer2.getCurrentManifest() instanceof DashManifest) || this.exoPlayer2.getCurrentTimeline().isEmpty() || this.lastReferenceSyncTimeMs < 0 || this.lastSystemSyncTimeMs < 0) {
            this.firstSyncOffsetTimeMs = C.TIME_UNSET;
            return SYNC_DELAY_UNSET_MS;
        }
        long currentTimeMillis = (this.lastReferenceSyncTimeMs + (System.currentTimeMillis() - this.lastSystemSyncTimeMs)) - getCurrentEpochTimePositionMs();
        long j = this.firstSyncOffsetTimeMs;
        if (j != C.TIME_UNSET) {
            return currentTimeMillis - j;
        }
        this.firstSyncOffsetTimeMs = currentTimeMillis;
        return SYNC_DELAY_UNSET_MS;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateSyncedPlaybackTime(int r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 516
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.SyncMediaPlayer.updateSyncedPlaybackTime(int, java.lang.String):void");
    }

    private void resync(int i) {
        initialFutureSeek(calculateFutureSeekTimeMs(i));
    }

    private void initialFutureSeek(long j) {
        if (this.exoPlayer2 != null) {
            this.exoPlayer2.seekTo(j);
            this.exoPlayer2.setPlayWhenReady(false);
            this.isFutureSeeking = true;
            String str = TAG;
            Log.d(str, "resync at (futureSeekTimeMS) : " + j);
        }
    }

    private void finishFutureSeek() {
        if (this.exoPlayer2 != null) {
            this.exoPlayer2.setPlayWhenReady(true);
            this.isFutureSeeking = false;
            this.futureSeekOffsetMS = 0;
        }
    }

    private void resetSyncPlaybackProperty() {
        this.futureSeekTimeMS = -1;
        this.futureSeekOffsetMS = 0;
        this.isLive = false;
        this.useMediaTypeEvent = false;
        this.isLoop = false;
        this.isFutureSeeking = false;
        this.isFirstTimeSyncing = true;
        this.curPlaylistSelected = -1;
        this.isMetaDataSkipped = false;
        this.numOfTickToSkipSync = -1;
        this.curPlaylistPDTMillis = -1;
        this.curPlaylistDurationMillis = -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getProfileLog(Format format) {
        return format.width + "x" + format.height + " Bitrate=" + format.bitrate + " FPS=" + format.frameRate;
    }

    private long calculateFutureSeekTimeMs(int i) {
        String str = TAG;
        Log.d(str, "resync with playback position " + i);
        long j = this.futureSeekOffsetMS;
        if (j > 0) {
            this.futureSeekOffsetMS = (long) Math.min(((float) j) * 1.7f, 15000.0f);
        } else {
            this.futureSeekOffsetMS = AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS;
        }
        this.futureSeekTimeMS = Math.max(((long) i) + this.futureSeekOffsetMS, 0L);
        if (this.isLive) {
            if (this.useMediaTypeEvent) {
                this.futureSeekTimeMS = Math.min(this.futureSeekTimeMS, this.exoPlayer2.getDuration());
            } else if (this.curPlaylistDurationMillis > 0) {
                this.futureSeekTimeMS = Math.min(this.futureSeekTimeMS, this.curPlaylistDurationMillis);
            }
        }
        return this.futureSeekTimeMS;
    }
}
