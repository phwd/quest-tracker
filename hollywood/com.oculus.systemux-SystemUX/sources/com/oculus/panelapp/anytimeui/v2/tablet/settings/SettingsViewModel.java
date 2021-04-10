package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.NightShiftController;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.os.VrApiManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.DoNotDisturbUtil;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.panels.FrameCommandChannel;
import com.oculus.vrshell.util.DeviceProperties;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel extends BaseObservable implements ViewModelLifecycle {
    private static final boolean DEFAULT_GUARDIAN_PAUSED = false;
    private static final boolean DEFAULT_HAND_TRACKING_STATUS = false;
    private static final boolean DEFAULT_MIC_MUTED_STATUS = false;
    private static final String GUARDIAN_DISABLE_SYSPROP = "persist.oculus.guardian_disable";
    private static final String GUARDIAN_TESTING_SETTINGS_SYSPROP = "persist.oculus.guardian_testing_settings";
    public static final int MAX_BRIGHTNESS = 99;
    private static final int MAX_SEEKBAR_VALUE = 255;
    public static final int MAX_VOLUME = 255;
    private static final float SLIDER_THRESHOLD_LOW = 0.33333334f;
    private static final float SLIDER_THRESHOLD_MID = 0.6666667f;
    private static final float SLIDER_THRESHOLD_OFF = 0.001f;
    private static final String TAG = LoggingUtil.tag(SettingsViewModel.class);
    private AudioManager mAudioManager = ((AudioManager) this.mContext.getSystemService("audio"));
    private int mBrightness;
    @Nullable
    private String mConnectedNetworkSSID;
    private Context mContext;
    private boolean mGuardianDisabled;
    private boolean mGuardianPaused;
    private SettingsObserverCallback mGuardianPausedSettingsObserverCallback;
    private Handler mHandler;
    private boolean mIsGuardianOn;
    private boolean mIsTrackingIn3DOFMode;
    private NightShiftController mNightShiftController;
    private List<WeakReference<SettingObserver>> mObservers;
    private AnytimeUIPanelAppBase mPanelApp;
    private boolean mRealityTunerEnabled;
    private int mRealityTunerValue;
    private boolean mRoomscaleEnabled;
    private SettingsManager mSettingsManager = new SettingsManager();
    private int mVolume;

    public interface SettingObserver {
        void onGuardianStateChange(boolean z, boolean z2);

        void onTrackingStatusChanged(boolean z);
    }

    public SettingsViewModel(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mNightShiftController = new NightShiftController(context);
        this.mObservers = new ArrayList();
        this.mHandler = new Handler(this.mContext.getMainLooper());
        boolean z = false;
        this.mGuardianPaused = this.mSettingsManager.getBoolean(SettingsManager.GUARDIAN_PAUSED, false);
        this.mGuardianPausedSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsViewModel$FfSClBVzlrhbZFLO4uToPn1kXbg */

            @Override // com.oculus.os.SettingsObserverCallback
            public final void onSettingChange(String str) {
                SettingsViewModel.this.lambda$new$73$SettingsViewModel(str);
            }
        };
        this.mSettingsManager.registerSettingsObserver(SettingsManager.GUARDIAN_PAUSED, this.mGuardianPausedSettingsObserverCallback, this.mHandler);
        if (isSystemPropertyEnabled(GUARDIAN_DISABLE_SYSPROP) && !isSystemPropertyEnabled(GUARDIAN_TESTING_SETTINGS_SYSPROP)) {
            z = true;
        }
        this.mGuardianDisabled = z;
    }

    public /* synthetic */ void lambda$new$73$SettingsViewModel(String str) {
        this.mGuardianPaused = this.mSettingsManager.getBoolean(SettingsManager.GUARDIAN_PAUSED, false);
        notifySettingObserversOfTrackingStatusChange();
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
        this.mObservers.clear();
        this.mSettingsManager.unregisterSettingsObserver(SettingsManager.GUARDIAN_PAUSED, this.mGuardianPausedSettingsObserverCallback);
    }

    public boolean oculusLinkButtonVisible() {
        return AnytimeUIAndroidPanelAppV2.shouldShowOculusLinkButton() && (!isEnterpriseMode() || isEnterpriseAdmin());
    }

    public boolean assistantButtonVisible() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.AUI_ASSISTANT) && !isEnterpriseMode();
    }

    public Drawable assistantButtonIcon() {
        return this.mContext.getResources().getDrawable(this.mPanelApp.isGKEnabled(Gatekeeper.AUI_ASSISTANT_QUICK_ACTIONS_COLOR) ? R.drawable.oc_icon_voice_assistant_color_24 : R.drawable.oc_icon_voice_assistant_filled_24_d2d2d2, null);
    }

    @Bindable
    public boolean getHandsButtonVisible() {
        if (this.mSettingsManager.getBoolean(SettingsManager.AUTOTRANSITION_HANDS_CONTROLLERS, false) || !this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN, false) || isEnterpriseMode()) {
            return false;
        }
        return true;
    }

    public boolean bugReportButtonVisible() {
        return !isEnterpriseMode();
    }

    public boolean isEnterpriseMode() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    public boolean isEnterpriseAdmin() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseAdminModeEnabled;
    }

    public boolean guardianButtonVisible() {
        return !isEnterpriseMode() || this.mPanelApp.isGuardianEnabled();
    }

    @Bindable
    public boolean getRealityTunerSliderVisible() {
        return this.mPanelApp.isGuardianEnabled() && this.mRealityTunerEnabled;
    }

    public String getRealityTunerTooltipText(boolean z) {
        return this.mContext.getResources().getString(z ? R.string.anytime_tablet_settings_reality_tuner_tooltip_disabled : R.string.anytime_tablet_settings_reality_tuner_tooltip);
    }

    @Bindable
    public Drawable getBrightnessIcon() {
        float f = ((float) this.mBrightness) / 99.0f;
        Resources resources = this.mContext.getResources();
        if (f < SLIDER_THRESHOLD_OFF) {
            return resources.getDrawable(R.drawable.oc_icon_screen_brightness_off_filled_24_d2d2d2, null);
        }
        if (f < SLIDER_THRESHOLD_LOW) {
            return resources.getDrawable(R.drawable.oc_icon_screen_brightness_low_filled_24_d2d2d2, null);
        }
        if (f < 0.6666667f) {
            return resources.getDrawable(R.drawable.oc_icon_screen_brightness_mid_filled_24_d2d2d2, null);
        }
        return resources.getDrawable(R.drawable.oc_icon_screen_brightness_on_filled_24_d2d2d2, null);
    }

    @Bindable
    public Drawable getVolumeIcon() {
        float f = ((float) this.mVolume) / 255.0f;
        Resources resources = this.mContext.getResources();
        if (f < SLIDER_THRESHOLD_OFF) {
            return resources.getDrawable(R.drawable.oc_icon_volume_off_filled_24_d2d2d2, null);
        }
        if (f < SLIDER_THRESHOLD_LOW) {
            return resources.getDrawable(R.drawable.oc_icon_volume_low_filled_24_d2d2d2, null);
        }
        if (f < 0.6666667f) {
            return resources.getDrawable(R.drawable.oc_icon_volume_mid_filled_24_d2d2d2, null);
        }
        return resources.getDrawable(R.drawable.oc_icon_volume_on_filled_24_d2d2d2, null);
    }

    public int getVolume() {
        return this.mVolume;
    }

    public void onVolumeProgressChanged(int i) {
        this.mVolume = i;
        this.mAudioManager.setStreamVolume(3, Math.round((((float) this.mVolume) / 255.0f) * ((float) this.mAudioManager.getStreamMaxVolume(3))), 0);
    }

    public void updateVolume() {
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
        this.mVolume = (int) ((streamMaxVolume != 0 ? ((float) streamVolume) / ((float) streamMaxVolume) : 0.0f) * 255.0f);
        notifyPropertyChanged(BR.volumeIcon);
    }

    @Bindable
    public int getBrightness() {
        this.mBrightness = VrApiManager.getInstance().getDisplayBrightness() - 1;
        return this.mBrightness;
    }

    @Bindable
    public boolean getBrightnessSeekbarVisible() {
        return DeviceProperties.supportsBrightness();
    }

    public void setBrightness(int i) {
        this.mBrightness = i;
        VrApiManager.getInstance().setDisplayBrightness(this.mBrightness + 1);
        notifyPropertyChanged(BR.brightness);
        notifyPropertyChanged(BR.brightnessIcon);
    }

    public void onBrightnessProgressChanged(int i) {
        if (i < 0) {
            Log.e(TAG, String.format("Brightness progress must be >= 0. %d was given, defaulting to 0.", Integer.valueOf(i)));
            i = 0;
        } else if (i > 99) {
            Log.e(TAG, String.format("Brightness progress exceeds maximum. %d was given, defaulting to the maximum %d.", Integer.valueOf(i), 99));
            i = 99;
        }
        setBrightness(i);
    }

    public boolean getMicrophoneEnabled() {
        return this.mSettingsManager.getBoolean(SettingsManager.MIC_MUTED, false);
    }

    public void setMicrophoneEnabled(boolean z) {
        this.mSettingsManager.setBoolean(SettingsManager.MIC_MUTED, z);
    }

    public boolean getDoNotDisturbEnabled() {
        return DoNotDisturbUtil.getDoNotDisturbEnabled();
    }

    public void setDoNotDisturbEnabled(boolean z) {
        DoNotDisturbUtil.setDoNotDisturbEnabled(z);
    }

    public boolean doNotDisturbButtonVisible() {
        return !isEnterpriseMode();
    }

    public void setRealityTunerEnabled(boolean z) {
        this.mRealityTunerEnabled = z;
        notifyPropertyChanged(BR.realityTunerSliderVisible);
    }

    @Bindable
    public int getRealityTunerValue() {
        return this.mRealityTunerValue;
    }

    public void initRealityTunerValue(int i) {
        if (this.mRealityTunerValue != i) {
            this.mRealityTunerValue = i;
            notifyPropertyChanged(BR.realityTunerValue);
        }
    }

    public void setRealityTunerValue(int i) {
        FrameCommandChannel commandChannel = this.mPanelApp.getCommandChannel();
        commandChannel.sendCommand("realityTunerValue " + i);
    }

    public boolean getNightShiftEnabled() {
        return this.mNightShiftController.isActivated();
    }

    public void setNightShiftEnabled(boolean z) {
        this.mNightShiftController.setActivated(z);
    }

    public boolean getHandsEnabled() {
        return this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_ENABLED, false);
    }

    public void setHandsEnabled(boolean z) {
        this.mSettingsManager.setBoolean(SettingsManager.HAND_TRACKING_ENABLED, z);
    }

    public void setRoomscaleEnabled(boolean z) {
        this.mRoomscaleEnabled = z;
        notifyPropertyChanged(BR.guardianCTA);
    }

    public void setIsGuardianOn(boolean z) {
        this.mIsGuardianOn = z;
        notifyPropertyChanged(BR.guardianCTA);
        notifyPropertyChanged(BR.guardianIcon);
    }

    public boolean getRoomscaleEnabled() {
        return this.mRoomscaleEnabled;
    }

    public void setIsTrackingIn3DOFMode(boolean z) {
        this.mIsTrackingIn3DOFMode = z;
        notifySettingObserversOfTrackingStatusChange();
    }

    public boolean getIsTrackingIn3DOFMode() {
        return this.mIsTrackingIn3DOFMode;
    }

    public boolean getGuardianPaused() {
        return this.mGuardianPaused;
    }

    public boolean getGuardianDisabled() {
        return this.mGuardianDisabled;
    }

    public boolean getGuardianTabEnabled() {
        return !getIsTrackingIn3DOFMode() && !getGuardianPaused() && !getGuardianDisabled() && (!isEnterpriseMode() || isEnterpriseAdmin());
    }

    @Bindable
    public String getGuardianCTA() {
        Resources resources = this.mContext.getResources();
        if (!this.mIsGuardianOn) {
            return resources.getString(R.string.anytime_tablet_settings_guardian_button_tracking_disabled);
        }
        if (this.mRoomscaleEnabled) {
            return resources.getString(R.string.anytime_tablet_settings_guardian_button_roomscale);
        }
        return resources.getString(R.string.anytime_tablet_settings_guardian_button_stationary);
    }

    @Bindable
    public Drawable getGuardianIcon() {
        Resources resources = this.mContext.getResources();
        if (this.mIsGuardianOn) {
            return resources.getDrawable(R.drawable.anytime_tablet_settings_icon_guardian_v2, null);
        }
        return resources.getDrawable(R.drawable.anytime_tablet_settings_icon_guardian_off_v2, null);
    }

    private String getSystemProperty(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod(MobileConfigServiceConstants.PATH_GET, String.class).invoke(null, str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Failed to fetch system property: '" + str + "'.", e);
            return null;
        }
    }

    private boolean isSystemPropertyEnabled(String str) {
        String systemProperty = getSystemProperty(str);
        if (systemProperty == null) {
            return false;
        }
        if (Boolean.parseBoolean(systemProperty) || "1".equals(systemProperty)) {
            return true;
        }
        return false;
    }

    public void notifySettingObserversOfTrackingStatusChange() {
        for (WeakReference<SettingObserver> weakReference : this.mObservers) {
            SettingObserver settingObserver = weakReference.get();
            if (settingObserver != null) {
                settingObserver.onTrackingStatusChanged(getGuardianTabEnabled());
                settingObserver.onGuardianStateChange(getGuardianPaused(), getGuardianDisabled());
            }
        }
    }

    public void addSettingObserver(SettingObserver settingObserver) {
        this.mObservers.add(new WeakReference<>(settingObserver));
    }

    public void removeSettingObserver(SettingObserver settingObserver) {
        int i = 0;
        for (WeakReference<SettingObserver> weakReference : this.mObservers) {
            if (weakReference.get() == settingObserver) {
                this.mObservers.remove(i);
                return;
            }
            i++;
        }
    }
}
