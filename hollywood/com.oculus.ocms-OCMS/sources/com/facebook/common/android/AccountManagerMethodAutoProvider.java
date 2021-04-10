package com.facebook.common.android;

import android.accounts.AccountManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class AccountManagerMethodAutoProvider extends AbstractProvider<AccountManager> {
    @Override // javax.inject.Provider
    public AccountManager get() {
        return AndroidModule.provideAccountManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
