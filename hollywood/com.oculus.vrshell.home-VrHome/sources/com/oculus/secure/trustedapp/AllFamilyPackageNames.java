package com.oculus.secure.trustedapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AllFamilyPackageNames {
    public static final String ADSMANAGER = "com.facebook.adsmanager";
    public static final String AKIRA = "com.facebook.akira";
    public static final Set<String> ALL_FAMILY_PACKAGE_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(ADSMANAGER, AKIRA, DAYKIRA, DIRECT, GAMES, HELLO, HOME, HOME_DEV, INSTAGRAM, INSTAGRAM_LITE, KATANA, KOTOTORO, LASSO, MESSENGER, MESSENGER_KIDS, MESSENGER_KIDS_DEV, MESSENGER_LITE, "com.oculus.vrshell", "com.oculus.horizon", OCULUS_HORIZON_DEV, "com.oculus.vrshell.home", "com.oculus.ocms", OCULUS_TWILIGHT, PAGES, PARTIES, WAKIZASHI, WHATSAPP, WORK, WORKDEV, WORK_CHAT)));
    public static final Set<String> ALL_FB_PACKAGE_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(ADSMANAGER, HELLO, HOME, HOME_DEV, KATANA, MESSENGER, MESSENGER_KIDS, MESSENGER_KIDS_DEV, MESSENGER_LITE, PAGES, PARTIES, WAKIZASHI, WORK, WORKDEV, WORK_CHAT)));
    public static final Set<String> ALL_INHOUSE_PACKAGE_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(GS_CRISIS_MANAGEMENT)));
    public static final String BISHOP = "com.facebook.bishop";
    public static final String DAYKIRA = "com.facebook.daykira";
    public static final String DIRECT = "com.instagram.direct";
    public static final String GAMES = "com.facebook.games";
    public static final String GS_CRISIS_MANAGEMENT = "com.facebook.globalsecurity";
    public static final String HELLO = "com.facebook.phone";
    public static final String HOME = "com.facebook.home";
    public static final String HOME_DEV = "com.facebook.home.dev";
    public static final String INSTAGRAM = "com.instagram.android";
    public static final String INSTAGRAM_LITE = "com.instagram.lite";
    public static final Set<String> INSTAGRAM_PACKAGE_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(DIRECT, INSTAGRAM, INSTAGRAM_LITE)));
    public static final String KATANA = "com.facebook.katana";
    public static final String KOTOTORO = "com.facebook.kototoro";
    public static final String LASSO = "com.facebook.lasso";
    public static final String MESSENGER = "com.facebook.orca";
    public static final String MESSENGER_KIDS = "com.facebook.talk";
    public static final String MESSENGER_KIDS_DEV = "com.facebook.mk";
    public static final String MESSENGER_LITE = "com.facebook.mlite";
    public static final String OCULUS_BROWSER = "com.oculus.browser";
    public static final String OCULUS_FIRST_TIME_NUX = "com.oculus.firsttimenux";
    public static final String OCULUS_HOME_SHELL = "com.oculus.vrshell.home";
    public static final String OCULUS_HORIZON = "com.oculus.horizon";
    public static final String OCULUS_HORIZON_DEV = "com.oculus.horizon.dev";
    public static final String OCULUS_MEDIA_PLAYER = "com.oculus.mediaplayer";
    public static final String OCULUS_OCMS = "com.oculus.ocms";
    public static final Set<String> OCULUS_PACKAGE_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList("com.oculus.vrshell.home", "com.oculus.horizon", OCULUS_HORIZON_DEV, "com.oculus.ocms", OCULUS_TWILIGHT)));
    public static final String OCULUS_SHELL = "com.oculus.vrshell";
    public static final String OCULUS_SYSTEM_ACTIVITIES = "com.oculus.systemactivities";
    public static final String OCULUS_SYSTEM_DRIVER = "com.oculus.systemdriver";
    public static final String OCULUS_TWILIGHT = "com.oculus.twilight";
    public static final String OCULUS_VRDRIVER = "com.oculus.vrdriver";
    public static final String PAGES = "com.facebook.pages.app";
    public static final String PARTIES = "com.facebook.bonfire";
    public static final Set<String> PORTAL_PACKAGE_NAMES = Collections.unmodifiableSet(new HashSet(Arrays.asList(BISHOP)));
    public static final String WAKIZASHI = "com.facebook.wakizashi";
    public static final String WHATSAPP = "com.whatsapp";
    public static final String WORK = "com.facebook.work";
    public static final String WORKDEV = "com.facebook.workdev";
    public static final String WORK_CHAT = "com.facebook.workchat";

    public static boolean isFirstPartyPackageName(String packageName) {
        return ALL_FAMILY_PACKAGE_NAMES.contains(packageName);
    }
}
