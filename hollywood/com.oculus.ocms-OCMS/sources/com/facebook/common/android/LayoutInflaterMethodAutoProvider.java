package com.facebook.common.android;

import android.view.LayoutInflater;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class LayoutInflaterMethodAutoProvider extends AbstractProvider<LayoutInflater> {
    @Override // javax.inject.Provider
    public LayoutInflater get() {
        return AndroidModule.provideLayoutInflater(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
