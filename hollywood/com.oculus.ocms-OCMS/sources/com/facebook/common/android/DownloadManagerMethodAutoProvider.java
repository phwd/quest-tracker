package com.facebook.common.android;

import android.app.DownloadManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class DownloadManagerMethodAutoProvider extends AbstractProvider<DownloadManager> {
    @Override // javax.inject.Provider
    public DownloadManager get() {
        return AndroidModule.provideDownloadManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
