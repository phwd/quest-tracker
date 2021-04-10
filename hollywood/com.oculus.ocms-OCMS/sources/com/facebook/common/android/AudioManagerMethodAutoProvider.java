package com.facebook.common.android;

import android.media.AudioManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class AudioManagerMethodAutoProvider extends AbstractProvider<AudioManager> {
    @Override // javax.inject.Provider
    public AudioManager get() {
        return AndroidModule.provideAudioManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
