package com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.databinding.ViewDataBinding;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCTextView;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseAdminKeypadButtonBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileViewV2Binding;
import com.oculus.panelapp.anytimeui.tooltip.TooltipController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.DensityUtils;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

public class EnterpriseProfileView extends BaseView {
    private static final int CHECKING_FOR_UPDATES_DURATION_MS = 2000;
    private static final String CONFIG_CHANGED_ACTION = "com.oculus.alpenglow.config.CONFIG_CHANGED";
    private static final String ENVIRONMENT_ARG_ENTERPRISE_CONFIG = "_oc_shell_enterprise_marshalled_configuration";
    private static final String FETCH_CONFIGURATION_ACTION = "com.oculus.alpenglow.config.FETCH_CONFIGURATION";
    private static final float KEYPAD_THROTTLE_ANIMATION_START_OFFSET_DIP = 12.0f;
    private static final int KEYPAD_THROTTLE_DURATION_MS = 2000;
    private static final int NO_UPDATES_FOUND_DURATION_MS = 3000;
    private static final String TAG = LoggingUtil.tag(EnterpriseProfileView.class);
    private static final String TOOLTIP_ID_KEYPAD_TITLE = "admin_keypad_title_tooltip";
    private AnytimeTabletEnterpriseProfileViewV2Binding mBinding;
    private final Handler mHandler;
    private boolean mIsDestroyed = false;
    private TimerTask mKeypadThrottleTask;
    private Timer mKeypadThrottleTimer;
    private final ReentrantLock mKeypadThrottleTimerLock;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private EnterpriseProfileBroadcastReceiver mReceiver;
    private TooltipController mTooltipController;
    private TimerTask mUpdateCheckTask;
    private Timer mUpdateCheckTimer;
    private final ReentrantLock mUpdateCheckTimerLock;
    private EnterpriseProfileViewModel mViewModel;

    public EnterpriseProfileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing EnterpriseProfileView");
        this.mUpdateCheckTimerLock = new ReentrantLock();
        this.mKeypadThrottleTimerLock = new ReentrantLock();
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding) {
        super.initialize((AndroidPanelApp) anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing EnterpriseProfileView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = (AnytimeTabletEnterpriseProfileViewV2Binding) viewDataBinding;
        this.mViewModel = anytimeUIAndroidPanelAppV2.acquireEnterpriseProfileViewModel();
        this.mTooltipController = this.mPanelApp.getSystemTooltipController();
        this.mBinding.setViewModel(this.mViewModel);
        initializeShowKeypadButton();
        initializeKeypadTitle();
        initializeKeypadButtons();
        initializeExitAdminModeButton();
        initializeCheckForUpdatesButton();
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.PROFILE_TABLET);
        this.mReceiver = new EnterpriseProfileBroadcastReceiver();
        getContext().registerReceiver(this.mReceiver, new IntentFilter(CONFIG_CHANGED_ACTION));
        this.mIsDestroyed = false;
    }

    /* JADX INFO: finally extract failed */
    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying EnterpriseProfileView");
        this.mIsDestroyed = true;
        getContext().unregisterReceiver(this.mReceiver);
        this.mReceiver = null;
        this.mPanelApp.releaseEnterpriseProfileViewModel();
        this.mUpdateCheckTimerLock.lock();
        try {
            cleanUpUpdateCheckTimer();
            this.mUpdateCheckTimerLock.unlock();
            this.mKeypadThrottleTimerLock.lock();
            try {
                cleanUpKeypadThrottleTimer();
            } finally {
                this.mKeypadThrottleTimerLock.unlock();
            }
        } catch (Throwable th) {
            this.mUpdateCheckTimerLock.unlock();
            throw th;
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing EnterpriseProfileView");
        this.mViewModel.clearKeypadDigits();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding EnterpriseProfileView");
    }

    private int getAdminModeIndex() {
        if (this.mViewModel.getConfigurationDefaultModeIndex() == 0) {
            return 1;
        }
        Log.w(TAG, "Current implementation expected current mode to be 0 and dest mode be 1. Guessing destination mode 0");
        return 0;
    }

    private void initializeShowKeypadButton() {
        this.mBinding.enterpriseProfileEnterAdminModeButton.setEventHandler(this.mPanelApp);
        this.mBinding.enterpriseProfileEnterAdminModeButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$oW_5X14Ykw5VKIqpL05RWFrgVtw */

            public final void onClick(View view) {
                EnterpriseProfileView.this.lambda$initializeShowKeypadButton$240$EnterpriseProfileView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeShowKeypadButton$240$EnterpriseProfileView(View view) {
        if (this.mViewModel.getIsAdminPinRequired()) {
            Log.w(TAG, "Keypad button should not be shown.");
        } else {
            launchAdminMode();
        }
    }

    private void launchAdminMode() {
        this.mPanelApp.getCommandChannel().sendCommand(String.format(Locale.ROOT, "enterprise setConfigurationModeIndex %d", Integer.valueOf(getAdminModeIndex())));
    }

    private void onClickKeypadNumberButton(int i) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ENTERPRISE_PROFILE_ADMIN_KEYPAD_NUMBER);
        this.mViewModel.appendKeypadDigit(i);
        if (!this.mViewModel.getIsKeypadEntryReadyToBeChecked()) {
            return;
        }
        if (this.mViewModel.isKeypadEntryCorrect()) {
            launchAdminMode();
        } else {
            handleIncorrectKeypadEntry();
        }
    }

    private void onClickKeypadBackspaceButton() {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ENTERPRISE_PROFILE_ADMIN_KEYPAD_BACKSPACE);
        this.mViewModel.eraseLastKeypadDigit();
    }

    private void initializeKeypadTitle() {
        final OCTextView oCTextView = this.mBinding.enterpriseProfileAdminKeypad.adminKeypadTitle;
        oCTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView.AnonymousClass1 */

            public void onGlobalLayout() {
                Layout layout = oCTextView.getLayout();
                boolean z = false;
                if (!(layout == null || layout.getEllipsisCount(0) == 0)) {
                    z = true;
                }
                EnterpriseProfileView.this.mTooltipController.setTooltipEnabled(EnterpriseProfileView.TOOLTIP_ID_KEYPAD_TITLE, z);
            }
        });
    }

    private void initializeKeypadButtons() {
        Log.d(TAG, "Initializing keypad buttons");
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad0, 0);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad1, 1);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad2, 2);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad3, 3);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad4, 4);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad5, 5);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad6, 6);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad7, 7);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad8, 8);
        initializeKeypadButton(this.mBinding.enterpriseProfileAdminKeypad.adminKeypad9, 9);
        this.mBinding.enterpriseProfileAdminKeypad.adminKeypadBackspace.adminKeypadButton.setEventHandler(this.mPanelApp);
        this.mBinding.enterpriseProfileAdminKeypad.adminKeypadBackspace.adminKeypadButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$cbH5kaTD3ZtDkGQu6NvofrAQjnE */

            public final void onClick(View view) {
                EnterpriseProfileView.this.lambda$initializeKeypadButtons$241$EnterpriseProfileView(view);
            }
        });
        Log.d(TAG, "Initialized keypad buttons");
    }

    public /* synthetic */ void lambda$initializeKeypadButtons$241$EnterpriseProfileView(View view) {
        onClickKeypadBackspaceButton();
    }

    private void initializeKeypadButton(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        anytimeTabletEnterpriseAdminKeypadButtonBinding.adminKeypadButton.setEventHandler(this.mPanelApp);
        anytimeTabletEnterpriseAdminKeypadButtonBinding.adminKeypadButton.setOnClickListener(new View.OnClickListener(i) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$1GL1lwSXBQxsg786fx3ruvJoHI */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                EnterpriseProfileView.this.lambda$initializeKeypadButton$242$EnterpriseProfileView(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeKeypadButton$242$EnterpriseProfileView(int i, View view) {
        onClickKeypadNumberButton(i);
    }

    private void initializeExitAdminModeButton() {
        Log.d(TAG, "Initializing exit admin mode button");
        this.mBinding.enterpriseProfileExitAdminModeButton.setEventHandler(this.mPanelApp);
        this.mBinding.enterpriseProfileExitAdminModeButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$v7zH8s4jRwEHaIRG8mnjC2obxGw */

            public final void onClick(View view) {
                EnterpriseProfileView.this.lambda$initializeExitAdminModeButton$243$EnterpriseProfileView(view);
            }
        });
        Log.d(TAG, "Initialized exit admin mode button");
    }

    public /* synthetic */ void lambda$initializeExitAdminModeButton$243$EnterpriseProfileView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ENTERPRISE_PROFILE_EXIT_ADMIN);
        this.mPanelApp.getCommandChannel().sendCommand(String.format("enterprise resetConfigurationModeIndex", new Object[0]));
    }

    private void initializeCheckForUpdatesButton() {
        Log.d(TAG, "Initializing check for updates button");
        this.mBinding.enterpriseProfileCheckForUpdatesButton.setEventHandler(this.mPanelApp);
        this.mBinding.enterpriseProfileCheckForUpdatesButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$3pJXyUN4Efp8ZHhFZPjQxzWS9z8 */

            public final void onClick(View view) {
                EnterpriseProfileView.this.lambda$initializeCheckForUpdatesButton$244$EnterpriseProfileView(view);
            }
        });
        Log.d(TAG, "Initialized check for updates button");
    }

    public /* synthetic */ void lambda$initializeCheckForUpdatesButton$244$EnterpriseProfileView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ENTERPRISE_PROFILE_CHECK_FOR_UPDATES);
        showCheckingForUpdates();
        Intent intent = new Intent();
        intent.setAction(FETCH_CONFIGURATION_ACTION);
        getContext().sendBroadcast(intent);
    }

    private void showApplyUpdatesDialog() {
        String string = getResources().getString(R.string.anytime_tablet_enterprise_profile_apply_updates_title);
        String string2 = getResources().getString(R.string.anytime_tablet_enterprise_profile_apply_updates_body);
        String string3 = getResources().getString(R.string.anytime_tablet_enterprise_profile_apply_updates_update_now);
        String string4 = getResources().getString(R.string.anytime_tablet_enterprise_profile_apply_updates_update_later);
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("enterprise_settings_apply_update_dialog", string, string2);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(MobileConfigServiceConstants.PATH_UPDATE_CONFIGS, string3));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", string4));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler(MobileConfigServiceConstants.PATH_UPDATE_CONFIGS) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$qO3xzqt5Px552zqRdckojr4pZsI */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return EnterpriseProfileView.this.lambda$showApplyUpdatesDialog$245$EnterpriseProfileView(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    public /* synthetic */ boolean lambda$showApplyUpdatesDialog$245$EnterpriseProfileView(String str, DialogResult dialogResult) {
        if (!dialogResult.getDialogAction().equals(str)) {
            return true;
        }
        this.mPanelApp.getCommandChannel().sendCommand("enterprise shutdown");
        return true;
    }

    private void showCheckingForUpdates() {
        Log.d(TAG, "Showing CheckingForUpdates");
        this.mUpdateCheckTimerLock.lock();
        try {
            if (!this.mIsDestroyed) {
                cleanUpUpdateCheckTimer();
                this.mViewModel.setUpdateCheckState(EnterpriseProfileViewModel.UpdateCheckState.CheckingForUpdates);
                this.mUpdateCheckTimer = new Timer("EnterpriseProfileView: Checking for updates", true);
                this.mUpdateCheckTask = new TimerTask() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView.AnonymousClass2 */

                    public void run() {
                        EnterpriseProfileView.this.mViewModel.refreshSubtitleText();
                        EnterpriseProfileView.this.showNoUpdatesFound();
                    }
                };
                this.mUpdateCheckTimer.schedule(this.mUpdateCheckTask, 2000);
                this.mUpdateCheckTimerLock.unlock();
            }
        } finally {
            this.mUpdateCheckTimerLock.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showNoUpdatesFound() {
        Log.d(TAG, "Showing NoUpdatesFound");
        this.mUpdateCheckTimerLock.lock();
        try {
            if (!this.mIsDestroyed) {
                cleanUpUpdateCheckTimer();
                this.mViewModel.setUpdateCheckState(EnterpriseProfileViewModel.UpdateCheckState.NoUpdatesFound);
                this.mUpdateCheckTimer = new Timer("EnterpriseProfileView: No updates found", true);
                this.mUpdateCheckTask = new TimerTask() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView.AnonymousClass3 */

                    public void run() {
                        EnterpriseProfileView.this.showReadyToCheck();
                    }
                };
                this.mUpdateCheckTimer.schedule(this.mUpdateCheckTask, 3000);
                this.mUpdateCheckTimerLock.unlock();
            }
        } finally {
            this.mUpdateCheckTimerLock.unlock();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showReadyToCheck() {
        Log.d(TAG, "Showing ReadyToCheck");
        this.mUpdateCheckTimerLock.lock();
        try {
            if (!this.mIsDestroyed) {
                cleanUpUpdateCheckTimer();
                this.mViewModel.setUpdateCheckState(EnterpriseProfileViewModel.UpdateCheckState.ReadyToCheck);
                this.mUpdateCheckTimerLock.unlock();
            }
        } finally {
            this.mUpdateCheckTimerLock.unlock();
        }
    }

    private void cleanUpUpdateCheckTimer() {
        String str = TAG;
        Log.d(str, "cleaning up UpdateCheck timer: " + this.mUpdateCheckTimer);
        if (this.mUpdateCheckTimer != null) {
            this.mUpdateCheckTask.cancel();
            this.mUpdateCheckTimer.purge();
            this.mUpdateCheckTask = null;
            this.mUpdateCheckTimer = null;
        }
    }

    private void cleanUpKeypadThrottleTimer() {
        String str = TAG;
        Log.d(str, "cleaning up KeypadThrottle timer: " + this.mKeypadThrottleTimer);
        if (this.mKeypadThrottleTimer != null) {
            this.mKeypadThrottleTask.cancel();
            this.mKeypadThrottleTimer.purge();
            this.mKeypadThrottleTask = null;
            this.mKeypadThrottleTimer = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isNewConfigAvailable() {
        return this.mPanelApp.getConfig().getTimestamp().getTime() != EnterpriseServer.getConfiguration(this.mPanelApp.getContext()).getTimestamp().getTime();
    }

    private void handleIncorrectKeypadEntry() {
        this.mViewModel.throttleKeypad(true);
        animateFilledBubbles();
        this.mKeypadThrottleTimerLock.lock();
        try {
            this.mKeypadThrottleTimer = new Timer("EnterpriseProfileView: Throttling keypad", true);
            this.mKeypadThrottleTask = new TimerTask() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView.AnonymousClass4 */

                public void run() {
                    EnterpriseProfileView.this.mViewModel.clearKeypadDigits();
                    EnterpriseProfileView.this.mViewModel.throttleKeypad(false);
                }
            };
            this.mKeypadThrottleTimer.schedule(this.mKeypadThrottleTask, 2000);
        } finally {
            this.mKeypadThrottleTimerLock.unlock();
        }
    }

    private void animateFilledBubbles() {
        this.mHandler.post(new Runnable((float) DensityUtils.dipToPixelsInt(KEYPAD_THROTTLE_ANIMATION_START_OFFSET_DIP, getContext().getResources().getDisplayMetrics())) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.$$Lambda$EnterpriseProfileView$wpSkNm3NUC6famGWzRAiInCdEI */
            private final /* synthetic */ float f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                EnterpriseProfileView.this.lambda$animateFilledBubbles$246$EnterpriseProfileView(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$animateFilledBubbles$246$EnterpriseProfileView(float f) {
        View[] viewArr = {this.mBinding.enterpriseProfileAdminKeypad.adminKeypadFilledBubble0, this.mBinding.enterpriseProfileAdminKeypad.adminKeypadFilledBubble1, this.mBinding.enterpriseProfileAdminKeypad.adminKeypadFilledBubble2, this.mBinding.enterpriseProfileAdminKeypad.adminKeypadFilledBubble3};
        for (View view : viewArr) {
            float translationX = view.getTranslationX();
            SpringAnimation springAnimation = new SpringAnimation(view, DynamicAnimation.TRANSLATION_X, translationX);
            springAnimation.getSpring().setDampingRatio(0.2f);
            springAnimation.getSpring().setStiffness(1500.0f);
            springAnimation.setStartValue(translationX + f);
            springAnimation.start();
        }
    }

    private class EnterpriseProfileBroadcastReceiver extends BroadcastReceiver {
        private EnterpriseProfileBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String str = EnterpriseProfileView.TAG;
            Log.d(str, "Enterprise profile received an intent: " + action);
            if (!action.equals(EnterpriseProfileView.CONFIG_CHANGED_ACTION)) {
                String str2 = EnterpriseProfileView.TAG;
                Log.w(str2, "Received an unknown intent: " + action);
                return;
            }
            EnterpriseProfileView.this.mViewModel.refreshSubtitleText();
            if (EnterpriseProfileView.this.isNewConfigAvailable()) {
                EnterpriseProfileView.this.mUpdateCheckTimerLock.lock();
                try {
                    if (!EnterpriseProfileView.this.mIsDestroyed) {
                        EnterpriseProfileView.this.showReadyToCheck();
                        EnterpriseProfileView.this.mUpdateCheckTimerLock.unlock();
                    }
                } finally {
                    EnterpriseProfileView.this.mUpdateCheckTimerLock.unlock();
                }
            }
        }
    }
}
