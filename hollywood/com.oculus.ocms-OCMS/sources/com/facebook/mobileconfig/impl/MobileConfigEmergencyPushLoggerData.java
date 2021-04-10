package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigEmergencyPushLoggerData {
    public static final String CONFIGS_CAUSING_RESTART = "configs_causing_restart";
    public static final String CONFIGS_FORCE_REFRESHED = "configs_force_refreshed";
    public static final String DEBUG_STRING = "debug_string";
    public static final String HANDLER_LANGUAGE = "handler_language";
    public static final String IS_BACKGROUND = "is_background";
    public static final String RELOGIN_ENABLED = "relogin_enabled";
    public static final String RESTART_DELAY = "restart_delay";
    public static final String RESTART_DELAY_INCLUDING_SHADOW = "restart_delay_including_shadow";
    public static final String RESTART_NEEDED = "restart_needed";
    public static final String RESTART_NEEDED_INCLUDING_SHADOW = "restart_needed_including_shadow";
    public static final String SHADOWING = "shadowing";
    public static final String TIME_IN_FOREGROUND = "time_since_foreground";
    public static final String TIME_SINCE_LAUNCH = "time_since_launch";
    public final String configsCausingRestart;
    public final String configsForceRefreshed;
    public final boolean reloginEnabled;
    public final int restartDelay;
    public final int restartDelayIncludingShadow;
    public final boolean restartNeeded;
    public final boolean restartNeededIncludingShadow;
    public final boolean shadowing;

    public MobileConfigEmergencyPushLoggerData(boolean z, int i, int i2, Set<String> set, Set<String> set2, boolean z2, boolean z3, boolean z4) {
        this.reloginEnabled = z;
        this.restartDelay = i;
        this.restartDelayIncludingShadow = i2;
        this.shadowing = z4;
        this.restartNeeded = z2;
        this.restartNeededIncludingShadow = z3;
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        int i4 = 0;
        for (String str : set) {
            sb.append(str);
            if (i4 < set.size() - 1) {
                sb.append(",");
            }
            i4++;
        }
        this.configsCausingRestart = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        for (String str2 : set2) {
            sb2.append(str2);
            if (i3 < set2.size() - 1) {
                sb2.append(",");
            }
            i3++;
        }
        this.configsForceRefreshed = sb2.toString();
    }
}
