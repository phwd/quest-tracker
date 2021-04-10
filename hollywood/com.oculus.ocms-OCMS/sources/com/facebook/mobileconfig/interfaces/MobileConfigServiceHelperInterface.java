package com.facebook.mobileconfig.interfaces;

import android.content.Context;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import javax.annotation.Nullable;

public interface MobileConfigServiceHelperInterface {
    MobileConfigManagerHolder createMobileConfigManagerHolder(String str, String str2);

    void enforcePermissions(Context context);

    @Nullable
    String getCallerPackageName();

    String getUserId();

    boolean isUserLoggedIn();

    void registerListenerToLoginEvent(Runnable runnable);

    void scheduleOnIdleExecutor(Runnable runnable);
}
