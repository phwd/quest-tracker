package com.facebook.common.android;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Integer_com_facebook_common_android_AndroidSdkVersionMethodAutoProvider extends AbstractProvider<Integer> {
    @Override // javax.inject.Provider
    public Integer get() {
        return AndroidModule.provideAndroidSdkVersion();
    }
}
