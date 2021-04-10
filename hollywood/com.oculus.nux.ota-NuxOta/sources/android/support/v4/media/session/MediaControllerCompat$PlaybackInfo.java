package android.support.v4.media.session;

public final class MediaControllerCompat$PlaybackInfo {
    private final int mAudioStream;
    private final int mCurrentVolume;
    private final int mMaxVolume;
    private final int mPlaybackType;
    private final int mVolumeControl;

    MediaControllerCompat$PlaybackInfo(int i, int i2, int i3, int i4, int i5) {
        this.mPlaybackType = i;
        this.mAudioStream = i2;
        this.mVolumeControl = i3;
        this.mMaxVolume = i4;
        this.mCurrentVolume = i5;
    }
}
