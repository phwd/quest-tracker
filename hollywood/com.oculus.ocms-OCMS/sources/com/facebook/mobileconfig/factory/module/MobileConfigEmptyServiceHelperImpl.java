package com.facebook.mobileconfig.factory.module;

import android.content.Context;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface;
import javax.annotation.Nullable;

public class MobileConfigEmptyServiceHelperImpl implements MobileConfigServiceHelperInterface {
    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    @Nullable
    public MobileConfigManagerHolder createMobileConfigManagerHolder(String str, String str2) {
        return null;
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    public void enforcePermissions(Context context) {
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    @Nullable
    public String getCallerPackageName() {
        return null;
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    public String getUserId() {
        return "";
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    public boolean isUserLoggedIn() {
        return false;
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    public void registerListenerToLoginEvent(Runnable runnable) {
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface
    public void scheduleOnIdleExecutor(Runnable runnable) {
    }
}
