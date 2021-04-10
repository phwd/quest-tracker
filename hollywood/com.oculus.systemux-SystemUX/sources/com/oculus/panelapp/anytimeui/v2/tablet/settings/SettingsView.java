package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.assistant.service.api.OculusAssistantContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCNotchedSlider;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeviceSection;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.util.PackageUtil;

public class SettingsView extends BaseView {
    private static final String TAG = LoggingUtil.tag(SettingsView.class);
    private final long NIGHT_SHIFT_RESET_DELAY_MILLISECONDS = 250;
    private final int NUM_REALITY_TUNER_SLIDER_STEPS = 3;
    private AnytimeTabletSettingsViewV2Binding mBinding;
    private SettingsObserverCallback mHandTrackingAutoTransitionSettingsObserverCallback;
    private SettingsObserverCallback mHandTrackingEnabledSettingsObserverCallback;
    private Handler mHandler;
    private long mLastNightShiftUpdateTimeMilliseconds;
    private long mOculusLinkButtonObserver;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private SettingsViewModel.SettingObserver mSettingObserver;
    private ContentObserver mSettingsContentObserver;
    private SettingsManager mSettingsManager;
    private SettingsViewModel mSettingsViewModel;
    private StatusViewModel mStatusViewModel;

    public SettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing SettingsView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing SettingsView");
        this.mSettingsViewModel = anytimeUIAndroidPanelAppV2.acquireSettingsViewModel();
        this.mStatusViewModel = anytimeUIAndroidPanelAppV2.acquireStatusViewModel();
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletSettingsViewV2Binding) viewDataBinding;
        this.mSettingsManager = new SettingsManager();
        this.mHandler = new Handler(getContext().getMainLooper());
        this.mBinding.setSettingsViewModel(this.mSettingsViewModel);
        this.mBinding.setStatusViewModel(this.mStatusViewModel);
        initializeVolumeSeekBar();
        initializeBrightnessSeekBar();
        initializeWifiButton();
        initializeGuardianButton();
        initializeLinkButton();
        initializeNightShiftButton();
        initializeResetViewButton();
        initializeDoNotDisturbButton();
        initializeMicrophoneButton();
        initializeAssistantButton();
        initializeRealityTunerSlider();
        initializeHandsButton();
        initializeBugReportButton();
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.SETTINGS_TABLET);
    }

    public void initializeTooltips() {
        this.mPanelApp.getSystemTooltipController().setTooltipText("anytime_tablet_settings_reality_tuner_tooltip", this.mSettingsViewModel.getRealityTunerTooltipText(this.mPanelApp.isInOverlayMode()));
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying SettingsView");
        this.mPanelApp.releaseSettingsViewModel();
        this.mPanelApp.releaseStatusViewModel();
        this.mPanelApp.actionSetVolumeLayerEnabled(true);
        getContext().getContentResolver().unregisterContentObserver(this.mSettingsContentObserver);
        this.mSettingsManager.unregisterSettingsObserver(SettingsManager.HAND_TRACKING_ENABLED, this.mHandTrackingEnabledSettingsObserverCallback);
        this.mSettingsManager.unregisterSettingsObserver(SettingsManager.AUTOTRANSITION_HANDS_CONTROLLERS, this.mHandTrackingAutoTransitionSettingsObserverCallback);
        long j = this.mOculusLinkButtonObserver;
        if (j != 0) {
            AnytimeUIAndroidPanelAppV2.unregisterShouldShowOculusLinkButton(j);
            this.mOculusLinkButtonObserver = 0;
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing SettingsView");
        this.mPanelApp.actionSetVolumeLayerEnabled(false);
        this.mBinding.buttonMicrophone.setSelected(this.mSettingsViewModel.getMicrophoneEnabled());
        this.mBinding.buttonDoNotDisturb.setSelected(this.mSettingsViewModel.getDoNotDisturbEnabled());
        this.mBinding.buttonNightShift.setSelected(this.mSettingsViewModel.getNightShiftEnabled());
        this.mBinding.buttonHands.setSelected(this.mSettingsViewModel.getHandsEnabled());
        if (!this.mSettingsViewModel.getBrightnessSeekbarVisible()) {
            this.mSettingsViewModel.onBrightnessProgressChanged(99);
        }
        initializeTooltips();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding SettingsView");
        this.mPanelApp.actionSetVolumeLayerEnabled(true);
    }

    private void initializeVolumeSeekBar() {
        Log.d(TAG, "Initializing volume seek bar");
        this.mSettingsContentObserver = new ContentObserver(new Handler(getContext().getMainLooper())) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView.AnonymousClass1 */

            public void onChange(boolean z) {
                super.onChange(z);
                SettingsView.this.mSettingsViewModel.updateVolume();
                SettingsView.this.mBinding.seekbarVolume.seekbar.setProgress(SettingsView.this.mSettingsViewModel.getVolume());
            }
        };
        getContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.mSettingsContentObserver);
        SeekBar seekBar = this.mBinding.seekbarVolume.seekbar;
        SettingsViewModel settingsViewModel = this.mSettingsViewModel;
        seekBar.setMax(255);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView.AnonymousClass2 */

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    SettingsView.this.mSettingsViewModel.onVolumeProgressChanged(i);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                SettingsView.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_VOLUME);
            }
        });
        this.mSettingsViewModel.updateVolume();
        Log.d(TAG, "Initialized volume seek bar");
    }

    private void initializeBrightnessSeekBar() {
        Log.d(TAG, "Initializing brightness seek bar");
        SeekBar seekBar = this.mBinding.seekbarBrightness.seekbar;
        SettingsViewModel settingsViewModel = this.mSettingsViewModel;
        seekBar.setMax(99);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView.AnonymousClass3 */

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    SettingsView.this.mSettingsViewModel.onBrightnessProgressChanged(i);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                SettingsView.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_BRIGHTNESS);
            }
        });
        Log.d(TAG, "Initialized brightness seek bar");
    }

    private void initializeMicrophoneButton() {
        Log.d(TAG, "Initializing microphone button");
        this.mBinding.buttonMicrophone.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$mrOxItr7DUX4vNcLu9XnWpzib1Q */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeMicrophoneButton$268$SettingsView(view);
            }
        });
        Log.d(TAG, "Initialized microphone button");
    }

    public /* synthetic */ void lambda$initializeMicrophoneButton$268$SettingsView(View view) {
        boolean microphoneEnabled = this.mSettingsViewModel.getMicrophoneEnabled();
        this.mSettingsViewModel.setMicrophoneEnabled(!microphoneEnabled);
        this.mBinding.buttonMicrophone.setSelected(!microphoneEnabled);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_MICROPHONE);
    }

    private void initializeDoNotDisturbButton() {
        Log.d(TAG, "Initializing do not disturb button");
        this.mBinding.buttonDoNotDisturb.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$s3RO7a2WMlKDaIVJgVr5l29ZHxg */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeDoNotDisturbButton$269$SettingsView(view);
            }
        });
        Log.d(TAG, "Initialized do not disturb button");
    }

    public /* synthetic */ void lambda$initializeDoNotDisturbButton$269$SettingsView(View view) {
        boolean doNotDisturbEnabled = this.mSettingsViewModel.getDoNotDisturbEnabled();
        this.mSettingsViewModel.setDoNotDisturbEnabled(!doNotDisturbEnabled);
        this.mBinding.buttonDoNotDisturb.setSelected(!doNotDisturbEnabled);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_DO_NO_DISTURB);
    }

    private void initializeNightShiftButton() {
        Log.d(TAG, "Initializing night shift button");
        this.mBinding.buttonNightShift.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$EJfEOG9aPKaztB2I_RhzUnnYNNA */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeNightShiftButton$271$SettingsView(view);
            }
        });
        Log.d(TAG, "Initialized night shift button");
    }

    public /* synthetic */ void lambda$initializeNightShiftButton$271$SettingsView(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastNightShiftUpdateTimeMilliseconds > 250) {
            new Handler().postDelayed(new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$uqlSAv1J96GosXKkZU8thcICGR4 */

                public final void run() {
                    SettingsView.this.lambda$null$270$SettingsView();
                }
            }, 250);
        }
        this.mLastNightShiftUpdateTimeMilliseconds = currentTimeMillis;
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_NIGHT_DISPLAY);
    }

    public /* synthetic */ void lambda$null$270$SettingsView() {
        boolean nightShiftEnabled = this.mSettingsViewModel.getNightShiftEnabled();
        this.mSettingsViewModel.setNightShiftEnabled(!nightShiftEnabled);
        this.mBinding.buttonNightShift.setSelected(!nightShiftEnabled);
    }

    private void initializeResetViewButton() {
        Log.d(TAG, "Initializing reset view button");
        this.mBinding.buttonResetView.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$s91nc4TVxlK1i6q57CZ80mMEpHQ */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeResetViewButton$272$SettingsView(view);
            }
        });
        Log.d(TAG, "Initialized reset view button");
    }

    public /* synthetic */ void lambda$initializeResetViewButton$272$SettingsView(View view) {
        this.mPanelApp.actionReorient();
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_RESET_VIEW);
    }

    private void initializeBugReportButton() {
        Log.d(TAG, "Initializing bug report button");
        this.mBinding.buttonBugReport.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$2q16XGDnG1wr9X6V_RvFHMJpiU8 */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeBugReportButton$273$SettingsView(view);
            }
        });
        Log.d(TAG, "Initialized bug report button");
    }

    public /* synthetic */ void lambda$initializeBugReportButton$273$SettingsView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.BUG_REPORT, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_BUG_REPORT);
    }

    private void initializeAssistantButton() {
        Log.d(TAG, "Initializing assistant button");
        this.mBinding.buttonActivateAssistant.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$BvwzXpxFXxlGJCT4bReP1nOQ */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeAssistantButton$274$SettingsView(view);
            }
        });
        Log.d(TAG, "Initialized assistant button");
    }

    public /* synthetic */ void lambda$initializeAssistantButton$274$SettingsView(View view) {
        Intent intent = new Intent();
        intent.setAction(OculusAssistantContract.ACTIVATE_ASSISTANT);
        intent.putExtra("source", "AUIv2");
        intent.putExtra("start_time", SystemClock.elapsedRealtime());
        getContext().sendBroadcast(intent);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_ASSISTANT);
    }

    private void initializeRealityTunerSlider() {
        Log.d(TAG, "Initializing Reality Tuner slider");
        if (this.mPanelApp.isInOverlayMode()) {
            this.mBinding.sliderRealityTuner.setEnabled(false);
        } else {
            this.mBinding.sliderRealityTuner.setOnValueChangedListener(new OCNotchedSlider.OnValueChangedListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$pEKO4_kIXSWwaJM1kehuI8vkZM */

                @Override // com.oculus.ocui.OCNotchedSlider.OnValueChangedListener
                public final void onValueChanged(int i) {
                    SettingsView.this.lambda$initializeRealityTunerSlider$275$SettingsView(i);
                }
            });
            this.mBinding.sliderRealityTuner.setNumSteps(3);
            this.mBinding.sliderRealityTuner.setEventHandler(this.mPanelApp);
        }
        Log.d(TAG, "Initialized Reality Tuner slider");
    }

    public /* synthetic */ void lambda$initializeRealityTunerSlider$275$SettingsView(int i) {
        this.mSettingsViewModel.setRealityTunerValue(i);
    }

    private void initializeHandsButton() {
        Log.d(TAG, "Initializing hands button");
        this.mBinding.buttonHands.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$xGOY9WH8cuxsf72fAqPrq4LnxI */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeHandsButton$276$SettingsView(view);
            }
        });
        this.mHandTrackingEnabledSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$ihx57dF7Vs4mmMgsYtT2GVfRXMg */

            @Override // com.oculus.os.SettingsObserverCallback
            public final void onSettingChange(String str) {
                SettingsView.this.lambda$initializeHandsButton$277$SettingsView(str);
            }
        };
        this.mSettingsManager.registerSettingsObserver(SettingsManager.HAND_TRACKING_ENABLED, this.mHandTrackingEnabledSettingsObserverCallback, this.mHandler);
        this.mHandTrackingAutoTransitionSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$u90D5CEooRiO6mVjzXhH9uAXiFQ */

            @Override // com.oculus.os.SettingsObserverCallback
            public final void onSettingChange(String str) {
                SettingsView.this.lambda$initializeHandsButton$278$SettingsView(str);
            }
        };
        this.mSettingsManager.registerSettingsObserver(SettingsManager.AUTOTRANSITION_HANDS_CONTROLLERS, this.mHandTrackingAutoTransitionSettingsObserverCallback, this.mHandler);
        Log.d(TAG, "Initialized hands button");
    }

    public /* synthetic */ void lambda$initializeHandsButton$276$SettingsView(View view) {
        boolean handsEnabled = this.mSettingsViewModel.getHandsEnabled();
        this.mSettingsViewModel.setHandsEnabled(!handsEnabled);
        this.mBinding.buttonHands.setSelected(!handsEnabled);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_HANDS);
    }

    public /* synthetic */ void lambda$initializeHandsButton$277$SettingsView(String str) {
        this.mBinding.buttonHands.setSelected(this.mSettingsViewModel.getHandsEnabled());
        this.mSettingsViewModel.notifyPropertyChanged(BR.handsButtonVisible);
    }

    public /* synthetic */ void lambda$initializeHandsButton$278$SettingsView(String str) {
        this.mSettingsViewModel.notifyPropertyChanged(BR.handsButtonVisible);
    }

    private void initializeWifiButton() {
        Log.d(TAG, "Initializing wifi button");
        this.mBinding.buttonWifi.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$BsCIgL0sURXmGmVA610Z_IDvcxE */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeWifiButton$279$SettingsView(view);
            }
        });
        this.mBinding.buttonWifi.setEventHandler(this.mPanelApp);
        Log.d(TAG, "Initialized wifi button");
    }

    public /* synthetic */ void lambda$initializeWifiButton$279$SettingsView(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.WIFI, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_WIFI);
    }

    private void initializeGuardianButton() {
        Log.d(TAG, "Initializing guardian button");
        this.mBinding.buttonGuardian.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$P0uVIfmPQxGSy3ZrsCnwEJcrTxE */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeGuardianButton$280$SettingsView(view);
            }
        });
        this.mSettingObserver = new SettingsViewModel.SettingObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView.AnonymousClass4 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel.SettingObserver
            public void onTrackingStatusChanged(boolean z) {
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel.SettingObserver
            public void onGuardianStateChange(boolean z, boolean z2) {
                SettingsView.this.mBinding.buttonGuardian.setEnabled(!z && !z2);
            }
        };
        this.mSettingsViewModel.addSettingObserver(this.mSettingObserver);
        this.mSettingObserver.onGuardianStateChange(this.mSettingsViewModel.getGuardianPaused(), this.mSettingsViewModel.getGuardianDisabled());
        this.mBinding.buttonGuardian.setEventHandler(this.mPanelApp);
        Log.d(TAG, "Initialized guardian button");
    }

    public /* synthetic */ void lambda$initializeGuardianButton$280$SettingsView(View view) {
        if (this.mSettingsViewModel.getIsTrackingIn3DOFMode()) {
            this.mPanelApp.actionNavigate(SystemUXRoute.TRACKING_OFF_DIALOG, "");
        } else {
            this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_ADJUST_DIALOG, "");
        }
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_GUARDIAN);
    }

    private void initializeLinkButton() {
        Log.d(TAG, "Initializing enable link button");
        this.mBinding.buttonEnableLink.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$KqGb8hG9eQNM57vakLdj8mEmLLM */

            public final void onClick(View view) {
                SettingsView.this.lambda$initializeLinkButton$281$SettingsView(view);
            }
        });
        this.mOculusLinkButtonObserver = AnytimeUIAndroidPanelAppV2.registerShouldShowOculusLinkButton(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$lmJEyKO8pddRSSYzcMbGQRwypg */

            public final void run() {
                SettingsView.this.lambda$initializeLinkButton$283$SettingsView();
            }
        });
        this.mBinding.buttonEnableLink.setEventHandler(this.mPanelApp);
        Log.d(TAG, "Initialized enable link button");
    }

    public /* synthetic */ void lambda$initializeLinkButton$281$SettingsView(View view) {
        if (AnytimeUIAndroidPanelAppV2.shouldShowOculusLinkButton()) {
            this.mPanelApp.actionNavigate(PackageUtil.PACKAGE_NAME_XRSTREAMING_CLIENT, "");
        } else {
            this.mPanelApp.getDialogManager().showDialog(SettingsDeviceSection.getLinkDisconnectedDialog(SettingsDeviceSection.DISCONNECT_REASON_PC_NOT_DETECTED));
        }
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SETTINGS_OCULUS_LINK);
    }

    public /* synthetic */ void lambda$initializeLinkButton$283$SettingsView() {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsView$dPz3rFmUS5Drj9_xQ9J4puDfE0 */

            public final void run() {
                SettingsView.this.lambda$null$282$SettingsView();
            }
        });
    }

    public /* synthetic */ void lambda$null$282$SettingsView() {
        this.mBinding.buttonEnableLink.setVisibility(AnytimeUIAndroidPanelAppV2.shouldShowOculusLinkButton() ? 0 : 8);
        Log.d(TAG, "Link button state changed");
    }
}
