package com.facebook.common.android;

import android.media.MediaPlayer;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MediaPlayerMethodAutoProvider extends AbstractProvider<MediaPlayer> {
    @Override // javax.inject.Provider
    public MediaPlayer get() {
        return AndroidModule.provideMediaPlayer();
    }
}
