package com.oculus.panelapp.parties.views;

import X.AnonymousClass1Ah;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.PartyMicrophoneState;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTooltip;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.databinding.PartyUserCardBinding;
import com.oculus.panelapp.parties.views.actions.PartyAction;
import com.oculus.panelapp.parties.views.actions.PartyActionHandler;
import com.oculus.panelapp.parties.views.actions.PartyActionType;
import com.oculus.panelapp.parties.views.actions.SwitchAudio;
import com.oculus.partiescontent.PartiesCallbackObserver;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.ProfilePictureHelper;
import java.util.List;

public class PartyUserCardViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(PartyUserCardViewHolder.class);
    @NonNull
    public PartyUserCardBinding mBinding;
    @NonNull
    public Context mContext;
    public boolean mMicSwitcherEnabled;
    @Nullable
    public OCTooltip mMicrophoneTooltip;
    @NonNull
    public PartiesTabletPanelApp mPanelApp;
    @Nullable
    public PartiesCallbackObserver mPartyObserver;
    public PartyUserCardViewModel mPartyUserCardViewModel;
    @Nullable
    public PartyActionMenu mPopUpMenu;
    public ProfilePictureHelper mProfilePictureHelper;

    public static /* synthetic */ void lambda$createOrUpdateTooltip$0() {
    }

    /* renamed from: com.oculus.panelapp.parties.views.PartyUserCardViewHolder$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneState;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.horizoncontent.social.PartyMicrophoneState[] r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.parties.views.PartyUserCardViewHolder.AnonymousClass5.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneState = r2
                com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.PARTY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.APP     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.MUTE     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.PartyUserCardViewHolder.AnonymousClass5.<clinit>():void");
        }
    }

    public PartyUserCardViewHolder(@NonNull PartyUserCardBinding partyUserCardBinding, @NonNull Context context, @NonNull PartiesTabletPanelApp partiesTabletPanelApp) {
        super(partyUserCardBinding.partyUserCard);
        this.mBinding = partyUserCardBinding;
        this.mContext = context;
        this.mPanelApp = partiesTabletPanelApp;
        PartyUserCardViewModel partyUserCardViewModel = new PartyUserCardViewModel(context, partiesTabletPanelApp);
        this.mPartyUserCardViewModel = partyUserCardViewModel;
        partyUserCardBinding.setPartyUserCardViewModel(partyUserCardViewModel);
        this.mProfilePictureHelper = new ProfilePictureHelper(context);
        initializeHoverOverlay();
        PartyUserCardBinding partyUserCardBinding2 = this.mBinding;
        OCButton oCButton = partyUserCardBinding2.ctaButton;
        PartiesTabletPanelApp partiesTabletPanelApp2 = this.mPanelApp;
        oCButton.mEventHandler = partiesTabletPanelApp2;
        partyUserCardBinding2.ctaLeftButton.mEventHandler = partiesTabletPanelApp2;
        partyUserCardBinding2.ctaRightButton.mEventHandler = partiesTabletPanelApp2;
    }

    private void bindOverflowMenu() {
        PartyActionMenu partyActionMenu = this.mPopUpMenu;
        if (partyActionMenu != null) {
            partyActionMenu.dismissPopup();
            this.mPopUpMenu = null;
        }
        List<PartyAction> list = this.mPartyUserCardViewModel.mOverflowActions;
        if (!list.isEmpty()) {
            OCButton oCButton = this.mBinding.overflowMenuButton;
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener(list) {
                /* class com.oculus.panelapp.parties.views.$$Lambda$PartyUserCardViewHolder$nv6CyeZYRcgNyiLY3kiD0u1yz02 */
                public final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PartyUserCardViewHolder.this.lambda$bindOverflowMenu$3$PartyUserCardViewHolder(this.f$1, view);
                }
            });
        }
    }

    private void bindPrimaryCTA() {
        PartyAction partyAction;
        OCButton oCButton;
        List<PartyAction> list = this.mPartyUserCardViewModel.mCtaActions;
        if (list.size() == 1) {
            partyAction = list.get(0);
            oCButton = this.mBinding.ctaButton;
        } else {
            if (list.size() == 2) {
                partyAction = list.get(1);
                bindPrimaryCtaButton(this.mBinding.ctaLeftButton, list.get(0));
                oCButton = this.mBinding.ctaRightButton;
            }
            this.mPartyUserCardViewModel.updateCtaAction();
        }
        bindPrimaryCtaButton(oCButton, partyAction);
        this.mPartyUserCardViewModel.updateCtaAction();
    }

    private void bindProfilePicture(SocialUser socialUser) {
        this.mProfilePictureHelper.setImageViewFromUrl(this.mBinding.profilePhoto, socialUser.mProfilePhotoURL, socialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void createOrUpdateTooltip(@NonNull PartyMicrophoneState partyMicrophoneState) {
        Context context;
        int i;
        if (this.mMicSwitcherEnabled) {
            switch (partyMicrophoneState.ordinal()) {
                case 1:
                    context = this.mContext;
                    i = R.string.parties_tablet_microphone_mute_tooltip;
                    break;
                case 2:
                    context = this.mContext;
                    i = R.string.parties_tablet_microphone_party_chat_tooltip;
                    break;
                case 3:
                    context = this.mContext;
                    i = R.string.parties_tablet_microphone_app_chat_tooltip;
                    break;
                default:
                    return;
            }
            String string = context.getString(i);
            if (this.mMicrophoneTooltip == null) {
                OCTooltip createTooltip = this.mPanelApp.createTooltip();
                this.mMicrophoneTooltip = createTooltip;
                createTooltip.setDismissButtonVisibility(false);
            }
            this.mMicrophoneTooltip.configureTooltip(string, OCTooltip.OCTooltipPosition.TOP, $$Lambda$PartyUserCardViewHolder$D_fFrPbyFkN3PXDE04RXMyqQ42g2.INSTANCE, this.mPanelApp, this.mBinding.cardHoverOverlay);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchAndUpdateMicrophoneSelection() {
        this.mPanelApp.mRequestHelper.fetchMicrophoneSelection(new HorizonContentProviderHelper.GetPartyMicrophoneStateCallback() {
            /* class com.oculus.panelapp.parties.views.PartyUserCardViewHolder.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(PartyUserCardViewHolder.TAG, "Error fetching microphone state");
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.GetPartyMicrophoneStateCallback
            public void onSuccess(PartyMicrophoneState partyMicrophoneState) {
                partyMicrophoneState.toString();
                PartyUserCardViewHolder.this.updateMicrophoneChannelDropdownSelection(partyMicrophoneState);
                PartyUserCardViewHolder.this.createOrUpdateTooltip(partyMicrophoneState);
                PartyUserCardViewHolder.this.mPartyUserCardViewModel.setViewerMicState(partyMicrophoneState);
            }
        });
    }

    private void initializeHoverOverlay() {
        this.mBinding.cardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartyUserCardViewHolder$xA7o8JBv6n5uyoSc76fhFsi5sc2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return PartyUserCardViewHolder.this.lambda$initializeHoverOverlay$1$PartyUserCardViewHolder(view, motionEvent);
            }
        });
    }

    private void onHoverEnter() {
        this.mPartyUserCardViewModel.setIsHoverOverlayShowing(true);
        OCTooltip oCTooltip = this.mMicrophoneTooltip;
        if (oCTooltip != null) {
            oCTooltip.show();
        }
    }

    private void onHoverExit() {
        this.mPartyUserCardViewModel.setIsHoverOverlayShowing(false);
        OCTooltip oCTooltip = this.mMicrophoneTooltip;
        if (oCTooltip != null) {
            oCTooltip.dismiss();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onMicSwitcherGKFetched(PartyUserCardAdapterItem partyUserCardAdapterItem) {
        this.mPartyUserCardViewModel.setMicSwitcherEnabledAndCreateCtaActions(partyUserCardAdapterItem, this.mMicSwitcherEnabled);
        bindPrimaryCTA();
        if (this.mMicSwitcherEnabled) {
            PartyUserCardViewModel partyUserCardViewModel = this.mPartyUserCardViewModel;
            if (partyUserCardViewModel.getUserType() == SocialUser.UserRowType.PARTY_MEMBER) {
                createOrUpdateTooltip(partyUserCardViewModel.getMicrophoneChannel());
            }
        }
    }

    private void registerPartyObserver() {
        AnonymousClass3 r1 = new PartiesCallbackObserver(new Handler(this.mContext.getMainLooper())) {
            /* class com.oculus.panelapp.parties.views.PartyUserCardViewHolder.AnonymousClass3 */

            @Override // com.oculus.partiescontent.PartiesCallbackObserver
            public void onAppChatAvailabilityChanged() {
            }

            @Override // com.oculus.partiescontent.PartiesCallbackObserver
            public void onPartyVoipConnectionStatusChanged() {
            }

            @Override // com.oculus.partiescontent.PartiesCallbackObserver
            public void onPartyMicrophoneStateChanged() {
                PartyUserCardViewHolder.this.fetchAndUpdateMicrophoneSelection();
            }
        };
        this.mPartyObserver = r1;
        this.mPanelApp.mRequestHelper.registerPartyObserver(r1);
    }

    public void bindUser(final PartyUserCardAdapterItem partyUserCardAdapterItem) {
        this.mPartyUserCardViewModel.setPartyUserAdapterItem(partyUserCardAdapterItem);
        SocialUser socialUser = partyUserCardAdapterItem.mUser;
        bindProfilePicture(socialUser);
        bindOverflowMenu();
        this.mPanelApp.getDeviceConfigSynchronizedAsync(DeviceConfigSocialPlatformMC.OCULUS_PARTIES_ENABLE_AUI_MIC_SWITCHER_CONTROLS, new DeviceConfigHelper.GetDeviceBooleanCallback() {
            /* class com.oculus.panelapp.parties.views.PartyUserCardViewHolder.AnonymousClass1 */

            @Override // com.oculus.common.deviceconfig.DeviceConfigHelper.GetDeviceBooleanCallback
            public void onValue(boolean z) {
                PartyUserCardViewHolder partyUserCardViewHolder = PartyUserCardViewHolder.this;
                partyUserCardViewHolder.mMicSwitcherEnabled = z;
                partyUserCardViewHolder.onMicSwitcherGKFetched(partyUserCardAdapterItem);
            }
        });
        if (socialUser.mUserType.equals(SocialUser.UserRowType.PARTY_MEMBER)) {
            createOrUpdateTooltip(socialUser.mMicrophoneChannel);
        }
    }

    public /* synthetic */ void lambda$bindOverflowMenu$3$PartyUserCardViewHolder(List list, View view) {
        PartyActionMenu partyActionMenu = this.mPopUpMenu;
        if (partyActionMenu == null) {
            partyActionMenu = this.mPanelApp.createActionMenu(list);
            this.mPopUpMenu = partyActionMenu;
        }
        partyActionMenu.togglePopup(view);
    }

    public void subscribeToSpeakingUpdates() {
        this.mPartyUserCardViewModel.subscribeToSpeakingUpdates();
    }

    public void unregisterPartyObserver() {
        PartiesCallbackObserver partiesCallbackObserver = this.mPartyObserver;
        if (partiesCallbackObserver != null) {
            this.mPanelApp.mRequestHelper.unregisterPartyObserver(partiesCallbackObserver);
        }
    }

    public void unsubscribeFromSpeakingUpdates() {
        this.mPartyUserCardViewModel.unsubscribeFromSpeakingUpdates();
    }

    private void bindPrimaryCtaButton(OCButton oCButton, PartyAction partyAction) {
        maybeCreateManageVoiceMenu(partyAction);
        oCButton.setText(partyAction.getType().getActionString(this.mContext.getResources()));
        oCButton.setOnClickListener(new View.OnClickListener(partyAction) {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartyUserCardViewHolder$2IkROKhGV7GtxXvG8kjX6pMAmY02 */
            public final /* synthetic */ PartyAction f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                PartyUserCardViewHolder.this.lambda$bindPrimaryCtaButton$2$PartyUserCardViewHolder(this.f$1, view);
            }
        });
    }

    private void maybeCreateManageVoiceMenu(PartyAction partyAction) {
        if (partyAction.getType().equals(PartyActionType.MANAGE_VOICE)) {
            PartyActionMenu partyActionMenu = this.mPopUpMenu;
            if (partyActionMenu != null) {
                partyActionMenu.dismissPopup();
                this.mPopUpMenu = null;
            }
            List<PartyAction> list = this.mPartyUserCardViewModel.mMicrophoneActions;
            if (list != null && !list.isEmpty()) {
                this.mPopUpMenu = this.mPanelApp.createActionMenu(list);
                fetchAndUpdateMicrophoneSelection();
                registerPartyObserver();
            }
        }
    }

    private void maybeToggleManageVoiceMenu(PartyAction partyAction, View view) {
        PartyActionMenu partyActionMenu;
        if (partyAction.getType().equals(PartyActionType.MANAGE_VOICE) && (partyActionMenu = this.mPopUpMenu) != null) {
            partyActionMenu.togglePopup(view);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateMicrophoneChannelDropdownSelection(PartyMicrophoneState partyMicrophoneState) {
        PartyActionType typeFromPartyMicrophoneState = SwitchAudio.getTypeFromPartyMicrophoneState(partyMicrophoneState);
        if (typeFromPartyMicrophoneState == null) {
            this.mPopUpMenu.setSelectedItem(null);
            return;
        }
        for (PartyAction partyAction : this.mPopUpMenu.getActions()) {
            if (partyAction.getType().equals(typeFromPartyMicrophoneState)) {
                this.mPopUpMenu.setSelectedItem(partyAction);
            }
        }
    }

    public /* synthetic */ void lambda$bindPrimaryCtaButton$2$PartyUserCardViewHolder(PartyAction partyAction, View view) {
        AnonymousClass2 r1;
        if (partyAction.getType().isAsyncAction()) {
            this.mPartyUserCardViewModel.setIsAsyncCtaActionInProgress(true);
            r1 = new PartyActionHandler() {
                /* class com.oculus.panelapp.parties.views.PartyUserCardViewHolder.AnonymousClass2 */

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onError() {
                    PartyUserCardViewHolder.this.mPartyUserCardViewModel.setIsAsyncCtaActionInProgress(false);
                }

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onSuccess() {
                    PartyUserCardViewHolder.this.mPartyUserCardViewModel.setIsAsyncCtaActionInProgress(false);
                    PartyUserCardViewHolder.this.mPartyUserCardViewModel.updateCtaAction();
                    PartyUserCardViewHolder.this.mPartyUserCardViewModel.updateMuteHoverOverlay();
                    PartiesViewModel partiesViewModel = PartyUserCardViewHolder.this.mPanelApp.mPartiesViewModel;
                    if (partiesViewModel != null) {
                        partiesViewModel.loadPartyData();
                    }
                }
            };
        } else {
            r1 = null;
        }
        partyAction.execute(this.mContext, r1);
        maybeToggleManageVoiceMenu(partyAction, view);
    }

    public /* synthetic */ boolean lambda$initializeHoverOverlay$1$PartyUserCardViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            onHoverEnter();
            return false;
        } else if (motionEvent.getAction() != 10) {
            return false;
        } else {
            onHoverExit();
            return false;
        }
    }
}
