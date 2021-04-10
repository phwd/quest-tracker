package com.oculus.vrshell.util;

import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;

public final class PackageUtil {
    public static final String INTENT_ACTION_PANEL = "com.oculus.vrshell.SHELL_MAIN";
    public static final String PACKAGE_NAME_360_PHOTOS = "com.oculus.oculus360photos";
    public static final String PACKAGE_NAME_ASSISTANT = "com.oculus.assistant";
    public static final String PACKAGE_NAME_BROWSER = "com.oculus.browser";
    public static final String PACKAGE_NAME_CINEMA = "com.oculus.cinema";
    public static final String PACKAGE_NAME_COMPANION_SERVER = "com.oculus.companion.server";
    public static final String PACKAGE_NAME_FIRST_CONTACT = "com.Oculus.FirstContact";
    public static final String PACKAGE_NAME_FIRST_STEPS = "com.oculus.MontereySetup";
    public static final String PACKAGE_NAME_FIRST_STEPS_MIRAMAR = "com.oculus.MiramarSetupRetail";
    public static final String PACKAGE_NAME_GUARDIAN = "com.oculus.guardian";
    public static final String PACKAGE_NAME_HORIZON = "com.oculus.horizon";
    public static final String PACKAGE_NAME_MEDIA_GALLERY = "com.oculus.mediagallery";
    public static final String PACKAGE_NAME_MEDIA_PLAYER = "com.oculus.mediaplayer";
    public static final String PACKAGE_NAME_MOVE = "com.oculus.fitnesstracker";
    public static final String PACKAGE_NAME_NOTIFICATION_PROXY = "com.oculus.notification_proxy";
    public static final String PACKAGE_NAME_OCMS = "com.oculus.ocms";
    public static final String PACKAGE_NAME_QUILL_PLAYER = "com.facebook.arvr.quillplayer";
    public static final String PACKAGE_NAME_SCOREBOARDS = "com.oculus.gamingactivity";
    public static final String PACKAGE_NAME_SHELL = "com.oculus.vrshell";
    public static final String PACKAGE_NAME_SHELL_ENV = "com.oculus.shellenv";
    public static final String PACKAGE_NAME_SHELL_HOME = "com.oculus.vrshell.home";
    public static final String PACKAGE_NAME_SOCIAL_PLATFORM = "com.oculus.socialplatform";
    public static final String PACKAGE_NAME_SYSTEM_ACTIVITIES = "com.oculus.systemactivities";
    public static final String PACKAGE_NAME_SYSTEM_DRIVER = "com.oculus.systemdriver";
    public static final String PACKAGE_NAME_SYSTEM_UTILITIES = "com.oculus.systemutilities";
    public static final String PACKAGE_NAME_SYSTEM_UX = "com.oculus.systemux";
    public static final String PACKAGE_NAME_TIMER = "com.oculus.timer";
    public static final String PACKAGE_NAME_TV = "com.oculus.tv";
    public static final String PACKAGE_NAME_VENUES = "com.oculus.venues";
    public static final String PACKAGE_NAME_XRSTREAMING_CLIENT = "com.oculus.xrstreamingclient";
    private static String TAG = LoggingUtil.tag(PackageUtil.class);

    private PackageUtil() {
    }

    public static boolean isShellApp(@Nullable String str) {
        return "com.oculus.vrshell".equals(str) || "com.oculus.shellenv".equals(str);
    }

    public static boolean isFirstPartySystem(@Nullable String str) {
        return "com.oculus.assistant".equals(str) || "com.oculus.browser".equals(str) || "com.oculus.companion.server".equals(str) || PACKAGE_NAME_GUARDIAN.equals(str) || "com.oculus.horizon".equals(str) || "com.oculus.mediaplayer".equals(str) || PACKAGE_NAME_NOTIFICATION_PROXY.equals(str) || "com.oculus.ocms".equals(str) || "com.oculus.vrshell".equals(str) || "com.oculus.vrshell.home".equals(str) || "com.oculus.shellenv".equals(str) || "com.oculus.socialplatform".equals(str) || "com.oculus.systemactivities".equals(str) || "com.oculus.systemdriver".equals(str) || "com.oculus.systemutilities".equals(str) || "com.oculus.systemux".equals(str);
    }
}
