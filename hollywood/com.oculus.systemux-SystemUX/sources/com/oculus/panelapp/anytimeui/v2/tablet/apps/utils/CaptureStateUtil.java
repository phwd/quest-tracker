package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.broadcasts.AnytimeUIBroadcastReceiver;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CaptureStateUtil {
    private static final String TAG = LoggingUtil.tag(CaptureStateUtil.class);
    private static Set<CaptureStateObserver> sCaptureStateObservers = new HashSet();

    public interface CaptureStateObserver {
        void onCaptureStateChanged(boolean z, boolean z2, boolean z3, String str, Optional<Long> optional);

        void onLocalStreamStateUpdate(boolean z);
    }

    public static void onCaptureStateChanged(boolean z, boolean z2, boolean z3, String str, Optional<Long> optional) {
        for (CaptureStateObserver captureStateObserver : sCaptureStateObservers) {
            captureStateObserver.onCaptureStateChanged(z, z2, z3, str, optional);
        }
    }

    public static void onLocalStreamStateUpdate(boolean z) {
        for (CaptureStateObserver captureStateObserver : sCaptureStateObservers) {
            captureStateObserver.onLocalStreamStateUpdate(z);
        }
    }

    public static void addCaptureStateObserver(Context context, CaptureStateObserver captureStateObserver) {
        sCaptureStateObservers.add(captureStateObserver);
        AnytimeUIBroadcastReceiver.requestCaptureState(context);
    }

    public static void removeCaptureStateObserver(CaptureStateObserver captureStateObserver) {
        sCaptureStateObservers.remove(captureStateObserver);
    }
}
