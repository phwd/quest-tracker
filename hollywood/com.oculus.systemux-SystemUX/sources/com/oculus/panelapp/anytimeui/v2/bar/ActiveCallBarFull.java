package com.oculus.panelapp.anytimeui.v2.bar;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.PartyMicrophoneInputLocation;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.notifications.NotificationConstants;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarActiveCallBarFullV2Binding;
import com.oculus.panelapp.anytimeui.tooltip.TooltipController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.SocialViewWarningToaster;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;

public final class ActiveCallBarFull extends ConstraintLayout {
    private static final String EXTRA_TYPE_ERROR = "active_call_bar_microphone_input_error";
    private static final String EXTRA_TYPE_SUCCESS = "active_call_bar_microphone_input";
    private static final String KEY_OCULUS_CATEGORY = "system";
    private static final int MICROPHONE_INPUT_TOAST_ID = -87117812;
    private static final String TAG = LoggingUtil.tag(ActiveCallBarFull.class);
    private static final String TOOLTIP_ID_MICROPHONE_INPUT_BUTTON = "anytime_bar_active_call_bar_microphone_input_button";
    private static final String TOOLTIP_ID_MUTE_MICROPHONE_BUTTON = "anytime_bar_active_call_bar_mute_microphone_button";
    private AnytimeBarActiveCallBarFullV2Binding mBinding;
    @Nullable
    private AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private SocialViewModel mSocialViewModel;
    private TooltipController mTooltipController;

    public ActiveCallBarFull(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Initializing bar lower section");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = AnytimeBarActiveCallBarFullV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
        this.mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
        this.mBinding.setViewModel(this.mSocialViewModel);
        this.mTooltipController = this.mPanelApp.getSystemTooltipController();
        initializeButtons();
        Log.d(TAG, "Initialized bar lower section");
    }

    private void initializeButtons() {
        initializeActiveCallButton();
        initializeEndCallButton();
        if (this.mSocialViewModel.getShouldShowActiveCallBarAdvancedControls()) {
            initializeCallVolumeButton();
            initializeMicrophoneInputButton();
            initializeMuteMicrophoneButton();
        }
    }

    private void initializeActiveCallButton() {
        this.mBinding.activeCallButton.setEventHandler(this.mPanelApp);
        this.mBinding.activeCallButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarFull$1MKIaeNrvM4Lb9jMN62mQoELqU */

            public final void onClick(View view) {
                ActiveCallBarFull.this.lambda$initializeActiveCallButton$86$ActiveCallBarFull(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeActiveCallButton$86$ActiveCallBarFull(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ACTIVE_CALL_BAR_FULL_ACTIVE_CALL_BUTTON);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
            Log.d(TAG, "Navigating to new parties");
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PARTIES, "");
            return;
        }
        Log.d(TAG, "Navigating to old parties");
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL, "");
    }

    private void initializeCallVolumeButton() {
        this.mBinding.callVolumeButton.setEventHandler(this.mPanelApp);
        this.mBinding.callVolumeButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarFull$6brgwfgDBhzhZwSMYwTB_ZQYmw */

            public final void onClick(View view) {
                ActiveCallBarFull.this.lambda$initializeCallVolumeButton$87$ActiveCallBarFull(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCallVolumeButton$87$ActiveCallBarFull(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ACTIVE_CALL_BAR_CALL_VOLUME);
    }

    private void initializeMicrophoneInputButton() {
        this.mBinding.muteMicrophoneButton.setSelected(this.mSocialViewModel.getPartyMicrophoneInputLocation() == PartyMicrophoneInputLocation.APP);
        this.mBinding.microphoneInputButton.setEventHandler(this.mPanelApp);
        this.mBinding.microphoneInputButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarFull$WJG51tckuPLnPIfuyDlqQ2aXVN4 */

            public final void onClick(View view) {
                ActiveCallBarFull.this.lambda$initializeMicrophoneInputButton$88$ActiveCallBarFull(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeMicrophoneInputButton$88$ActiveCallBarFull(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ACTIVE_CALL_BAR_MICROPHONE_INPUT);
        final PartyMicrophoneInputLocation partyMicrophoneInputLocation = this.mSocialViewModel.getPartyMicrophoneInputLocation() == PartyMicrophoneInputLocation.PARTY ? PartyMicrophoneInputLocation.APP : PartyMicrophoneInputLocation.PARTY;
        String str = TAG;
        Log.d(str, "Setting party microphone input to " + partyMicrophoneInputLocation.name());
        this.mSocialViewModel.setPartyMicrophoneInputLocation(partyMicrophoneInputLocation, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                String str2 = ActiveCallBarFull.TAG;
                Log.d(str2, "Set party microphone input to " + partyMicrophoneInputLocation.name());
                ActiveCallBarFull.this.showSetPartyMicrophoneInputSuccessNotification(partyMicrophoneInputLocation);
                ActiveCallBarFull.this.updateMicrophoneInputButton();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(ActiveCallBarFull.TAG, "Failed to set party microphone input location");
                ActiveCallBarFull.this.showSetPartyMicrophoneInputErrorNotification();
            }
        });
    }

    private Bundle getNotificationExtras(String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(NotificationConstants.KEY_AUI_PERSIST, false);
        bundle.putString(NotificationConstants.KEY_OCULUS_CATEGORY, "system");
        bundle.putString("oculus_notification_type", str);
        bundle.putString(NotificationConstants.KEY_LARGE_IMAGE_TYPE, NotificationConstants.LargeImageType.ICON.name());
        return bundle;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showSetPartyMicrophoneInputSuccessNotification(PartyMicrophoneInputLocation partyMicrophoneInputLocation) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setPriority(1).setSmallIcon(R.drawable.oc_icon_oculus_voice_filled_24_d2d2d2).setAutoCancel(true).addExtras(getNotificationExtras(EXTRA_TYPE_SUCCESS));
        int i = AnonymousClass4.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation[partyMicrophoneInputLocation.ordinal()];
        if (i == 1) {
            builder.setContentTitle(getResources().getString(R.string.anytime_bar_active_call_bar_microphone_input_app_toast_title)).setContentText(getResources().getString(R.string.anytime_bar_active_call_bar_microphone_input_app_toast_body));
        } else if (i != 2) {
            Log.e(TAG, "Invalid party microphone input location");
            return;
        } else {
            builder.setContentTitle(getResources().getString(R.string.anytime_bar_active_call_bar_microphone_input_party_toast_title)).setContentText(getResources().getString(R.string.anytime_bar_active_call_bar_microphone_input_party_toast_body));
        }
        ((NotificationManager) getContext().getSystemService("notification")).notify(MICROPHONE_INPUT_TOAST_ID, builder.build());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation = new int[PartyMicrophoneInputLocation.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation[] r0 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass4.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass4.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation r1 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.APP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass4.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneInputLocation     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.horizoncontent.social.PartyMicrophoneInputLocation r1 = com.oculus.horizoncontent.social.PartyMicrophoneInputLocation.PARTY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass4.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showSetPartyMicrophoneInputErrorNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setPriority(1).setSmallIcon(R.drawable.oc_icon_oculus_voice_filled_24_d2d2d2).setAutoCancel(true).addExtras(getNotificationExtras(EXTRA_TYPE_ERROR)).setContentTitle(getResources().getString(R.string.anytime_bar_active_call_bar_microphone_input_error_toast_title)).setContentText(getResources().getString(R.string.anytime_bar_active_call_bar_microphone_input_error_toast_body));
        ((NotificationManager) getContext().getSystemService("notification")).notify(MICROPHONE_INPUT_TOAST_ID, builder.build());
    }

    private void initializeMuteMicrophoneButton() {
        this.mBinding.muteMicrophoneButton.setSelected(this.mSocialViewModel.isPartyMuted());
        this.mBinding.muteMicrophoneButton.setEventHandler(this.mPanelApp);
        this.mBinding.muteMicrophoneButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarFull$o6PPpEf6NrJ0XtmMp1izpTGLOv4 */

            public final void onClick(View view) {
                ActiveCallBarFull.this.lambda$initializeMuteMicrophoneButton$89$ActiveCallBarFull(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeMuteMicrophoneButton$89$ActiveCallBarFull(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ACTIVE_CALL_BAR_MUTE_MICROPHONE);
        HorizonContentProviderHelper.setPartyLocalMuteAsync(getContext(), this.mSocialViewModel.isPartyMuted() ? 2 : 1, new HorizonContentProviderHelper.MuteStateCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MuteStateCallback
            public void onSuccess(int i) {
                ActiveCallBarFull.this.updateUI();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(ActiveCallBarFull.TAG, "Failed to mute microphone");
            }
        });
    }

    private void initializeEndCallButton() {
        this.mBinding.endCallButton.setEventHandler(this.mPanelApp);
        this.mBinding.endCallButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarFull$xSLao1mqkhw0QdJho8wdJHPMSP0 */

            public final void onClick(View view) {
                ActiveCallBarFull.this.lambda$initializeEndCallButton$91$ActiveCallBarFull(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeEndCallButton$91$ActiveCallBarFull(View view) {
        DialogDefinitionBase dialogDefinitionBase;
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_ACTIVE_CALL_BAR_END_CALL);
        SocialParty partyData = this.mSocialViewModel.getPartyData();
        if (partyData != null) {
            $$Lambda$ActiveCallBarFull$C5ExQBJIxRW_7OZniFwoTf6Wwl8 r0 = new DialogResultHandler() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$ActiveCallBarFull$C5ExQBJIxRW_7OZniFwoTf6Wwl8 */

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return ActiveCallBarFull.this.lambda$null$90$ActiveCallBarFull(dialogResult);
                }
            };
            if (partyData.getJoinType() == SocialPartyType.JOINABLE_BY_FRIENDS) {
                dialogDefinitionBase = SocialPartyDialogs.getLeaveDirectJoinPartyDialog(getResources());
            } else {
                dialogDefinitionBase = SocialPartyDialogs.getLeaveInviteOnlyPartyDialog(getResources());
            }
            dialogDefinitionBase.setDialogResultHandler(r0);
            this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$null$90$ActiveCallBarFull(com.oculus.systemdialog.DialogResult r5) {
        /*
            r4 = this;
            java.lang.String r5 = r5.getDialogAction()
            int r0 = r5.hashCode()
            r1 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001f
            r1 = 102846135(0x6214eb7, float:3.0338565E-35)
            if (r0 == r1) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r0 = "leave"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0029
            r5 = r2
            goto L_0x002a
        L_0x001f:
            java.lang.String r0 = "cancel"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0029
            r5 = r3
            goto L_0x002a
        L_0x0029:
            r5 = -1
        L_0x002a:
            if (r5 == 0) goto L_0x0030
            if (r5 == r3) goto L_0x002f
            goto L_0x0033
        L_0x002f:
            return r2
        L_0x0030:
            r4.leaveParty()
        L_0x0033:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.lambda$null$90$ActiveCallBarFull(com.oculus.systemdialog.DialogResult):boolean");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLeavePartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLeavePartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLeavePartyAsyncQueryHandle = null;
        }
    }

    private void leaveParty() {
        if (this.mSocialViewModel.getPartyData() != null) {
            String id = this.mSocialViewModel.getPartyData().getID();
            this.mSocialViewModel.removePartyOptimistically();
            clearLeavePartyAsyncQueryHandle();
            this.mLeavePartyAsyncQueryHandle = HorizonContentProviderHelper.leaveParty(getContext(), id, new HorizonContentProviderHelper.SingleIDCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.ActiveCallBarFull.AnonymousClass3 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
                public void onSuccess(String str) {
                    ActiveCallBarFull.this.mSocialViewModel.loadPartyData();
                    ActiveCallBarFull.this.clearLeavePartyAsyncQueryHandle();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialViewWarningToaster.showToast(ActiveCallBarFull.this.getContext(), "oculus_mobile_social_party_leave_error", ActiveCallBarFull.this.getResources().getString(R.string.anytime_tablet_social_party_leave_failed), ActiveCallBarFull.TAG);
                    ActiveCallBarFull.this.clearLeavePartyAsyncQueryHandle();
                }
            });
        }
    }

    public void updateUI() {
        this.mSocialViewModel.updateActiveCallBar();
        updateMuteMicrophoneButton();
        updateMicrophoneInputButton();
    }

    private void updateMuteMicrophoneButton() {
        boolean isPartyMuted = this.mSocialViewModel.isPartyMuted();
        this.mBinding.muteMicrophoneButton.setSelected(isPartyMuted);
        this.mTooltipController.setTooltipText(TOOLTIP_ID_MUTE_MICROPHONE_BUTTON, getResources().getString(isPartyMuted ? R.string.anytime_bar_active_call_bar_mute_microphone_button_unmute_tooltip : R.string.anytime_bar_active_call_bar_mute_microphone_button_mute_tooltip));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateMicrophoneInputButton() {
        boolean z = this.mSocialViewModel.getPartyMicrophoneInputLocation() == PartyMicrophoneInputLocation.APP;
        this.mBinding.muteMicrophoneButton.setSelected(z);
        this.mTooltipController.setTooltipText(TOOLTIP_ID_MICROPHONE_INPUT_BUTTON, getResources().getString(z ? R.string.anytime_bar_active_call_bar_microphone_input_button_party_tooltip : R.string.anytime_bar_active_call_bar_microphone_input_button_app_tooltip));
    }

    public void onShow() {
        initializeButtons();
    }

    public void onHide() {
        this.mBinding.activeCallButton.setEventHandler(null);
        this.mBinding.callVolumeButton.setEventHandler(null);
        this.mBinding.microphoneInputButton.setEventHandler(null);
        this.mBinding.muteMicrophoneButton.setEventHandler(null);
        this.mBinding.endCallButton.setEventHandler(null);
        this.mPanelApp.releaseSocialViewModel();
        clearLeavePartyAsyncQueryHandle();
    }
}
