package com.oculus.panelapp.keyboardv2;

import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;

public class PhysicalKeyboardSettingsHelper {
    private static final String TAG = LoggingUtil.tag(PhysicalKeyboardSettingsHelper.class);
    private boolean mDidSaveUserSettingsState = false;
    private boolean mHandTrackingOptedIn = this.mSettingsManager.getBoolean("hand_tracking_opt_in", false);
    private boolean mIsKeyboardTrackingOptedIn;
    private SettingsManager mSettingsManager = new SettingsManager();
    private boolean mWasAutoTransitionModeOn;
    private boolean mWasHandTrackingModeOn;

    public PhysicalKeyboardSettingsHelper(boolean z) {
        this.mIsKeyboardTrackingOptedIn = z;
    }

    public void turnOnHandTracking() {
        if (this.mIsKeyboardTrackingOptedIn && this.mHandTrackingOptedIn) {
            boolean z = this.mSettingsManager.getBoolean("hand_tracking_enabled", false);
            boolean z2 = this.mSettingsManager.getBoolean("autotransition_hands_controllers", false);
            if (z2 || !z) {
                this.mWasAutoTransitionModeOn = z2;
                if (z2) {
                    this.mSettingsManager.setBoolean("autotransition_hands_controllers", false);
                }
                this.mWasHandTrackingModeOn = z;
                if (!z) {
                    this.mSettingsManager.setBoolean("hand_tracking_enabled", true);
                }
                this.mDidSaveUserSettingsState = true;
            }
        }
    }

    public void turnOffHandTracking() {
        if ((this.mIsKeyboardTrackingOptedIn || this.mHandTrackingOptedIn) && this.mDidSaveUserSettingsState) {
            if (!this.mWasAutoTransitionModeOn) {
                this.mSettingsManager.setBoolean("hand_tracking_enabled", this.mWasHandTrackingModeOn);
            }
            this.mSettingsManager.setBoolean("autotransition_hands_controllers", this.mWasAutoTransitionModeOn);
        }
    }
}
