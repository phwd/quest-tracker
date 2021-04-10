package com.oculus.panelapp.anytimeui.v2.tablet.settings.modules;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GuardianModule {
    public static final int GUARDIAN_CONFIG_3DOF_MODE = 10;
    public static final int GUARDIAN_CONFIG_HAND_TRIGGER_DIST = 2;
    public static final int GUARDIAN_CONFIG_HAND_VELOCITY_TRIGGER = 7;
    public static final int GUARDIAN_CONFIG_HMD_TRIGGER_DIST = 1;
    public static final int GUARDIAN_CONFIG_HMD_VELOCITY_TRIGGER = 6;
    public static final int GUARDIAN_CONFIG_PARM_CUSTOMIZE_BOUNDARY_COLOR = 17;
    public static final int GUARDIAN_CONFIG_PARM_GLANCEABLE_BOUNDARIES = 16;
    public static final int GUARDIAN_CONFIG_PARM_INTRUSION_DETECTION = 19;
    public static final int GUARDIAN_CONFIG_PARM_IS_STAGE_ACTIVE = 22;
    public static final int GUARDIAN_CONFIG_PARM_PASSTHROUGH_PORTAL_CONTRAST = 18;
    public static final int GUARDIAN_CONFIG_PARM_QUICKBOOT = 23;
    public static final int GUARDIAN_CONFIG_PARM_STATIONARY_GUARDIAN_V2 = 21;
    public static final int GUARDIAN_CONFIG_SIMPLE_SETTING = 13;
    public static final int GUARDIAN_SETTINGS_CONFIG = 8;
    public static final float GUARDIAN_SLIDER_MAX_VALUE = 100.0f;
    public static final int GUARDIAN_SLIDER_MIN_VALUE = 0;
    private static final String TAG = LoggingUtil.tag(GuardianModule.class);
    private final Map<Integer, Map<GuardianConfigUpdateListener, WeakReference<GuardianConfigUpdateListener>>> mUpdateListeners = new HashMap();

    @FunctionalInterface
    public interface GuardianConfigUpdateListener {
        void onUpdate(float f);
    }

    public void updateGuardianSettings(int i, float f) {
        notifyUpdateListeners(i, f);
    }

    public void addGuardianUpdateListener(int i, GuardianConfigUpdateListener guardianConfigUpdateListener) {
        if (!this.mUpdateListeners.containsKey(Integer.valueOf(i))) {
            this.mUpdateListeners.put(Integer.valueOf(i), new HashMap());
        }
        Map<GuardianConfigUpdateListener, WeakReference<GuardianConfigUpdateListener>> map = this.mUpdateListeners.get(Integer.valueOf(i));
        if (map == null) {
            Log.e(TAG, "Unexpected null guardian update listeners.");
        } else {
            map.put(guardianConfigUpdateListener, new WeakReference<>(guardianConfigUpdateListener));
        }
    }

    private void notifyUpdateListeners(int i, float f) {
        Map<GuardianConfigUpdateListener, WeakReference<GuardianConfigUpdateListener>> map = this.mUpdateListeners.get(Integer.valueOf(i));
        if (map != null) {
            for (WeakReference<GuardianConfigUpdateListener> weakReference : map.values()) {
                weakReference.get().onUpdate(f);
            }
        }
    }

    public void removeUpdateListener(GuardianConfigUpdateListener guardianConfigUpdateListener) {
        for (Map<GuardianConfigUpdateListener, WeakReference<GuardianConfigUpdateListener>> map : this.mUpdateListeners.values()) {
            map.remove(guardianConfigUpdateListener);
        }
    }

    public static boolean getGuardianConfigValue(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, int i) {
        anytimeUIAndroidPanelAppV2.getCommandChannel().sendCommand(String.format(Locale.US, "guardianController getGuardianConfigValue %d", Integer.valueOf(i)));
        return false;
    }

    public static void setGuardianConfigValue(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, int i, float f) {
        anytimeUIAndroidPanelAppV2.getCommandChannel().sendCommand(String.format(Locale.US, "guardianController setGuardianConfigValue %d %f", Integer.valueOf(i), Float.valueOf(f)));
    }
}
