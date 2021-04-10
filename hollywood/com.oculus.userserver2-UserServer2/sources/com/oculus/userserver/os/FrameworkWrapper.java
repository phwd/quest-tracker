package com.oculus.userserver.os;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.os.UserHandle;
import android.os.UserManager;
import com.oculus.userserver.managerservice.OculusUserManagerImpl;
import java.util.List;

public class FrameworkWrapper {
    public static final UserHandle SYSTEM = UserHandle.SYSTEM;
    public static final int USER_SYSTEM = 0;

    public static UserInfo A02(UserManager userManager) {
        return userManager.getUserInfo(0);
    }

    public static List<UserInfo> A03(UserManager userManager) {
        return userManager.getUsers(true);
    }

    public static UserInfo A01(UserManager userManager) {
        return userManager.createUser(OculusUserManagerImpl.NEW_USER_NAME, 0);
    }

    public static void A07(UserManager userManager, UserHandle userHandle) {
        userManager.setUserRestriction("no_run_in_background", true, userHandle);
    }

    public static int A00(UserHandle userHandle) {
        return userHandle.getIdentifier();
    }

    public static boolean A08(ActivityManager activityManager, int i) {
        return activityManager.switchUser(i);
    }

    public static boolean A09(UserManager userManager) {
        return userManager.canAddMoreUsers();
    }

    public static boolean A0A(UserManager userManager, int i) {
        return userManager.removeUser(i);
    }

    public static void A04(Context context, Intent intent, UserHandle userHandle) {
        context.startServiceAsUser(intent, userHandle);
    }

    public static void A05(UserManager userManager, int i, Bitmap bitmap) {
        userManager.setUserIcon(i, bitmap);
    }

    public static void A06(UserManager userManager, int i, String str) {
        userManager.setUserName(i, str);
    }
}
