package com.facebook.common.android;

import androidx.fragment.app.FragmentActivity;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class FragmentActivityMethodAutoProvider extends AbstractProvider<FragmentActivity> {
    @Override // javax.inject.Provider
    public FragmentActivity get() {
        return AndroidModule.provideFragmentActivity(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
