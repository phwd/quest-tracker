package com.facebook.common.android;

import android.content.ContentResolver;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ContentResolverMethodAutoProvider extends AbstractProvider<ContentResolver> {
    @Override // javax.inject.Provider
    public ContentResolver get() {
        return AndroidModule.provideContentResolver(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
