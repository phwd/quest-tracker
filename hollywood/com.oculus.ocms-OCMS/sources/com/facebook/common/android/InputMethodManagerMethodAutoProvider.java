package com.facebook.common.android;

import android.view.inputmethod.InputMethodManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class InputMethodManagerMethodAutoProvider extends AbstractProvider<InputMethodManager> {
    @Override // javax.inject.Provider
    public InputMethodManager get() {
        return AndroidModule.provideInputMethodManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
