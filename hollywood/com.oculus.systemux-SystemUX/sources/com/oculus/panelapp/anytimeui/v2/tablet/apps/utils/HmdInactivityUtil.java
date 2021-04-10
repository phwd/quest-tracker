package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import com.oculus.common.logutilities.LoggingUtil;
import java.util.HashSet;
import java.util.Set;

public class HmdInactivityUtil {
    private static final String TAG = LoggingUtil.tag(HmdInactivityUtil.class);
    private static Set<HmdInactivityObserver> sHmdInactivityObservers = new HashSet();

    public interface HmdInactivityObserver {
        void onHmdInactivityEnd(long j);
    }

    public static void onHmdInactivityEnd(long j) {
        for (HmdInactivityObserver hmdInactivityObserver : sHmdInactivityObservers) {
            hmdInactivityObserver.onHmdInactivityEnd(j);
        }
    }

    public static void addHmdInactivityObserver(HmdInactivityObserver hmdInactivityObserver) {
        sHmdInactivityObservers.add(hmdInactivityObserver);
    }

    public static void removeHmdInactivityObserver(HmdInactivityObserver hmdInactivityObserver) {
        sHmdInactivityObservers.remove(hmdInactivityObserver);
    }
}
