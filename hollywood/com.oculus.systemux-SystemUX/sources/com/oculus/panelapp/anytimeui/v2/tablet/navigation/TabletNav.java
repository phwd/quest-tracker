package com.oculus.panelapp.anytimeui.v2.tablet.navigation;

import com.oculus.panelapp.anytimeui.R;

public enum TabletNav {
    INTERNAL_SETTINGS,
    NOTIFICATIONS,
    SETTINGS,
    SOCIAL;

    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav = new int[TabletNav.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.INTERNAL_SETTINGS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.NOTIFICATIONS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.SETTINGS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.SOCIAL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.AnonymousClass1.<clinit>():void");
        }
    }

    public static int getTitleForTabletNav(TabletNav tabletNav) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav[tabletNav.ordinal()];
        if (i == 1) {
            return R.string.anytime_tablet_nav_internal_settings_v2;
        }
        if (i == 2) {
            return R.string.anytime_tablet_nav_notifications_v2;
        }
        if (i == 3) {
            return R.string.anytime_tablet_nav_settings_v2;
        }
        if (i == 4) {
            return R.string.anytime_tablet_nav_social_v2;
        }
        throw new IllegalArgumentException("getTitleForTabletNav does not handle: " + tabletNav);
    }

    public static TabletNav getTabletNavForAttribute(int i) {
        if (i == 1) {
            return INTERNAL_SETTINGS;
        }
        if (i == 2) {
            return NOTIFICATIONS;
        }
        if (i == 3) {
            return SETTINGS;
        }
        if (i == 4) {
            return SOCIAL;
        }
        throw new IllegalArgumentException("getTabletNavForAttribute does not handle: " + i);
    }
}
