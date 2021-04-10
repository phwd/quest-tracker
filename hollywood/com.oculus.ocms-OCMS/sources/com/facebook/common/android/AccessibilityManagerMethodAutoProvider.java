package com.facebook.common.android;

import android.view.accessibility.AccessibilityManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class AccessibilityManagerMethodAutoProvider extends AbstractProvider<AccessibilityManager> {
    @Override // javax.inject.Provider
    public AccessibilityManager get() {
        return AndroidModule.provideAccessibilityManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
