package com.facebook.common.android;

import android.media.MediaRecorder;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MediaRecorderMethodAutoProvider extends AbstractProvider<MediaRecorder> {
    @Override // javax.inject.Provider
    public MediaRecorder get() {
        return AndroidModule.provideMediaRecorder();
    }
}
