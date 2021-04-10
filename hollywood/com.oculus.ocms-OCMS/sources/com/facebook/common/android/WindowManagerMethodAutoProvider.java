package com.facebook.common.android;

import android.view.WindowManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class WindowManagerMethodAutoProvider extends AbstractProvider<WindowManager> {
    @Override // javax.inject.Provider
    public WindowManager get() {
        return AndroidModule.provideWindowManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
