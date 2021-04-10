package com.oculus.horizon.service_media;

import android.media.MediaPlayer;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(trustOnly = @Nullsafe.TrustList({}), value = Nullsafe.Mode.LOCAL)
public class SoundPlayer {
    public static final String CHORDED_LONG_PRESS_START_SOUND = "file:///system/media/audio/ui/Lock.ogg";
    @Nullable
    public static MediaPlayer mMediaPlayer;
}
