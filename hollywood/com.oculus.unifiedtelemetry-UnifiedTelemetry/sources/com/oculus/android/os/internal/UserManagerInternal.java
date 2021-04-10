package com.oculus.android.os.internal;

import android.content.pm.UserInfo;
import android.os.UserManager;
import javax.annotation.Nullable;

public final class UserManagerInternal {
    @Nullable
    public static UserInfo A00(UserManager userManager, int i) {
        return userManager.getUserInfo(i);
    }
}
