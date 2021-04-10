package com.oculus.panelapp.parties.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.social.PartyMicrophoneState;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.views.actions.Block;
import com.oculus.panelapp.parties.views.actions.CancelPartyInvite;
import com.oculus.panelapp.parties.views.actions.KickFromParty;
import com.oculus.panelapp.parties.views.actions.ManageVoice;
import com.oculus.panelapp.parties.views.actions.PartyAction;
import com.oculus.panelapp.parties.views.actions.PartyActionType;
import com.oculus.panelapp.parties.views.actions.Report;
import com.oculus.panelapp.parties.views.actions.SelfMute;
import com.oculus.panelapp.parties.views.actions.SwitchAudio;
import com.oculus.panelapp.parties.views.actions.TravelTo;
import com.oculus.socialplatform.R;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartyUserCardViewModel extends AnonymousClass1uc implements ISelfMuteStateListener, ISpeakerIndicatorListener {
    public static final String TAG = LoggingUtil.tag(PartyUserCardViewModel.class);
    public boolean mAsyncCtaActionInProgress;
    public Context mContext;
    public List<PartyAction> mCtaActions;
    public int mCurrentSelfMuteState;
    public SocialGroupLaunchResponse mGroupLaunchResponse;
    public boolean mIsHoverOverlayShowing;
    public boolean mIsLeader;
    public boolean mIsSpeaking;
    public boolean mMicSwitcherEnabled;
    @Nullable
    public List<PartyAction> mMicrophoneActions;
    @Nullable
    public List<PartyAction> mOverflowActions;
    public PartiesTabletPanelApp mPanelApp;
    public SocialUser mPartyUser;
    public PartyMicrophoneState mViewerMicState = PartyMicrophoneState.UNKNOWN;

    @Bindable
    public boolean getShouldShowGroupLaunch() {
        return false;
    }

    @Bindable
    public boolean getShouldShowSubtitleSeparator() {
        return false;
    }

    /* renamed from: com.oculus.panelapp.parties.views.PartyUserCardViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.horizoncontent.social.SocialUser$UserRowType[] r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.parties.views.PartyUserCardViewModel.AnonymousClass1.$SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType = r2
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.PARTY_CONTROLS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.INVITED     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.PARTY_MEMBER     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.BLOCKED_USER     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = com.oculus.horizoncontent.social.SocialUser.UserRowType.BLOCKED_INVITED     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.PartyUserCardViewModel.AnonymousClass1.<clinit>():void");
        }
    }

    private void createCtaActions(PartyUserCardAdapterItem partyUserCardAdapterItem) {
        ImmutableList A06;
        SocialUser socialUser = this.mPartyUser;
        if (socialUser != null) {
            switch (socialUser.mUserType.ordinal()) {
                case 3:
                    SocialParty socialParty = partyUserCardAdapterItem.mParty;
                    if (socialParty != null && socialParty.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId(), this.mPartyUser)) {
                        A06 = ImmutableList.A06(new CancelPartyInvite(partyUserCardAdapterItem, this.mPanelApp, ClickEventButtonId.PARTIES_PANEL_USER_CARD_CANCEL_INVITE, SurfaceType.PARTY_MEMBER_LIST));
                        this.mCtaActions = A06;
                        break;
                    }
                case 4:
                default:
                    A06 = ImmutableList.of();
                    this.mCtaActions = A06;
                    break;
                case 5:
                    TravelTo travelTo = new TravelTo(socialUser, this.mPanelApp);
                    if (travelTo.isRelevant()) {
                        A06 = ImmutableList.A06(travelTo);
                        this.mCtaActions = A06;
                        break;
                    }
                    break;
                case 6:
                    if (this.mMicSwitcherEnabled) {
                        A06 = ImmutableList.A06(new ManageVoice(partyUserCardAdapterItem));
                    } else {
                        A06 = ImmutableList.A06(new SelfMute(partyUserCardAdapterItem, this, this.mContext));
                    }
                    this.mCtaActions = A06;
                    break;
            }
            if (this.mCtaActions.size() > 2) {
                this.mCtaActions = this.mCtaActions.subList(0, 2);
            }
        }
    }

    public List<PartyAction> getCtaActions() {
        return this.mCtaActions;
    }

    @Bindable
    public Drawable getCtaIcon() {
        List<PartyAction> list = this.mCtaActions;
        if (list == null || list.size() != 1) {
            return null;
        }
        return this.mCtaActions.get(0).getType().getActionIcon(this.mContext.getResources());
    }

    @Bindable
    public String getGroupLaunchStatusText() {
        return null;
    }

    @Bindable
    public boolean getIsHoverOverlayShowing() {
        if (!this.mIsHoverOverlayShowing || this.mCtaActions.size() == 0) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getIsOnline() {
        SocialUser socialUser = this.mPartyUser;
        if (socialUser == null || !socialUser.getIsActive(this.mContext.getResources())) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getIsReady() {
        return false;
    }

    @Bindable
    public Drawable getLeftCtaIcon() {
        if (this.mCtaActions.size() == 2) {
            return this.mCtaActions.get(0).getType().getActionIcon(this.mContext.getResources());
        }
        return null;
    }

    @Nullable
    public List<PartyAction> getMicrophoneActions() {
        return this.mMicrophoneActions;
    }

    public PartyMicrophoneState getMicrophoneChannel() {
        SocialUser socialUser = this.mPartyUser;
        if (socialUser == null) {
            return null;
        }
        return socialUser.mMicrophoneChannel;
    }

    @Nullable
    public List<PartyAction> getOverflowActions() {
        return this.mOverflowActions;
    }

    @Bindable
    public Drawable getRightCtaIcon() {
        if (this.mCtaActions.size() == 2) {
            return this.mCtaActions.get(1).getType().getActionIcon(this.mContext.getResources());
        }
        return null;
    }

    public int getSelfMuteState() {
        return this.mCurrentSelfMuteState;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001c A[RETURN] */
    @androidx.databinding.Bindable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getShouldShowAudioConnected() {
        /*
            r3 = this;
            boolean r0 = r3.mMicSwitcherEnabled
            r2 = 0
            if (r0 == 0) goto L_0x0012
            com.oculus.horizoncontent.social.SocialUser r1 = r3.mPartyUser
            if (r1 == 0) goto L_0x0012
            com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = r1.mUserType
            int r0 = r0.ordinal()
            switch(r0) {
                case 5: goto L_0x0013;
                case 6: goto L_0x0016;
                default: goto L_0x0012;
            }
        L_0x0012:
            return r2
        L_0x0013:
            com.oculus.horizoncontent.social.PartyMicrophoneState r1 = r1.mMicrophoneChannel
            goto L_0x0018
        L_0x0016:
            com.oculus.horizoncontent.social.PartyMicrophoneState r1 = r3.mViewerMicState
        L_0x0018:
            com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.PARTY
            if (r1 != r0) goto L_0x0012
            r2 = 1
            return r2
            switch-data {5->0x0013, 6->0x0016, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.PartyUserCardViewModel.getShouldShowAudioConnected():boolean");
    }

    @Bindable
    public boolean getShouldShowCta() {
        if (!this.mIsHoverOverlayShowing || this.mAsyncCtaActionInProgress || this.mCtaActions.size() != 1) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowCtaSpinner() {
        if (!this.mIsHoverOverlayShowing || !this.mAsyncCtaActionInProgress) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowLeftCta() {
        if (!this.mIsHoverOverlayShowing || this.mAsyncCtaActionInProgress || this.mCtaActions.size() != 2) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowMuteOverlay() {
        List<PartyAction> list = this.mCtaActions;
        if (list == null || list.isEmpty() || this.mCtaActions.get(0).getType() != PartyActionType.SELF_UNMUTE) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowOverflowMenu() {
        List<PartyAction> list = this.mOverflowActions;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowRightCta() {
        if (!this.mIsHoverOverlayShowing || this.mAsyncCtaActionInProgress || this.mCtaActions.size() != 2) {
            return false;
        }
        return true;
    }

    @Bindable
    public boolean getShouldShowSpeakingIndicator() {
        return this.mIsSpeaking;
    }

    @Bindable
    public boolean getShouldUseActiveTextColor() {
        SocialUser socialUser = this.mPartyUser;
        if (socialUser == null || !socialUser.getIsActive(this.mContext.getResources()) || this.mIsLeader) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0010  */
    @androidx.databinding.Bindable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getSubtitleText() {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.PartyUserCardViewModel.getSubtitleText():java.lang.String");
    }

    public SocialUser.UserRowType getUserType() {
        SocialUser socialUser = this.mPartyUser;
        if (socialUser == null) {
            return null;
        }
        return socialUser.mUserType;
    }

    @Bindable
    public String getUsername() {
        SocialUser socialUser = this.mPartyUser;
        if (socialUser == null) {
            return null;
        }
        PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
        if (partiesTabletPanelApp != null) {
            String localUserId = partiesTabletPanelApp.getLocalUserId();
            socialUser = this.mPartyUser;
            if (localUserId.equals(socialUser.mID)) {
                return this.mContext.getResources().getString(R.string.parties_tablet_party_user_card_viewer_label);
            }
        }
        return socialUser.mAlias;
    }

    public void setIsAsyncCtaActionInProgress(boolean z) {
        this.mAsyncCtaActionInProgress = z;
        notifyPropertyChanged(17);
        notifyPropertyChanged(9);
        notifyPropertyChanged(50);
        notifyPropertyChanged(5);
    }

    public void setIsHoverOverlayShowing(boolean z) {
        this.mIsHoverOverlayShowing = z;
        notifyPropertyChanged(10);
        notifyPropertyChanged(17);
        notifyPropertyChanged(9);
        notifyPropertyChanged(50);
        notifyPropertyChanged(5);
    }

    public void setMicSwitcherEnabledAndCreateCtaActions(PartyUserCardAdapterItem partyUserCardAdapterItem, boolean z) {
        this.mMicSwitcherEnabled = z;
        createCtaActions(partyUserCardAdapterItem);
        updateCtaAction();
        updateMuteHoverOverlay();
        notifyPropertyChanged(3);
    }

    public void setPartyUserAdapterItem(PartyUserCardAdapterItem partyUserCardAdapterItem) {
        if (partyUserCardAdapterItem != null) {
            this.mPartyUser = partyUserCardAdapterItem.mUser;
            this.mGroupLaunchResponse = partyUserCardAdapterItem.getGroupLaunchResponse();
            this.mIsLeader = partyUserCardAdapterItem.isLeader();
        }
        notifyPropertyChanged(49);
        notifyPropertyChanged(31);
        notifyPropertyChanged(46);
        notifyPropertyChanged(37);
        notifyPropertyChanged(3);
        updateCtaAction();
        updateMuteHoverOverlay();
        SocialUser socialUser = partyUserCardAdapterItem.mUser;
        PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
        ClickEventButtonId clickEventButtonId = ClickEventButtonId.PARTIES_PANEL_USER_CARD_BLOCK;
        SurfaceType surfaceType = SurfaceType.PARTY_MEMBER_LIST;
        this.mOverflowActions = (List) Stream.of((Object[]) new PartyAction[]{new Block(socialUser, partiesTabletPanelApp, clickEventButtonId, surfaceType), new Report(partyUserCardAdapterItem, partiesTabletPanelApp), new KickFromParty(partyUserCardAdapterItem, partiesTabletPanelApp, ClickEventButtonId.PARTIES_PANEL_USER_CARD_KICK, surfaceType)}).filter($$Lambda$usiUxgdZu_Ozwi0YbSsXSrOHHQ2.INSTANCE).collect(Collectors.toList());
        notifyPropertyChanged(25);
        if (this.mPartyUser.mUserType == SocialUser.UserRowType.PARTY_CONTROLS) {
            this.mMicrophoneActions = ImmutableList.A08(new SwitchAudio(partyUserCardAdapterItem, PartyMicrophoneState.PARTY), new SwitchAudio(partyUserCardAdapterItem, PartyMicrophoneState.APP), new SwitchAudio(partyUserCardAdapterItem, PartyMicrophoneState.MUTE));
        }
    }

    public void setViewerMicState(@NonNull PartyMicrophoneState partyMicrophoneState) {
        this.mViewerMicState = partyMicrophoneState;
        notifyPropertyChanged(3);
    }

    public void subscribeToSelfMuteStateUpdates() {
        SocialUser socialUser;
        PartiesViewModel partiesViewModel = this.mPanelApp.mPartiesViewModel;
        if (partiesViewModel != null && (socialUser = this.mPartyUser) != null && socialUser.mUserType == SocialUser.UserRowType.PARTY_CONTROLS) {
            partiesViewModel.mSelfMuteStateListener = this;
        }
    }

    public void subscribeToSpeakingUpdates() {
        SocialUser socialUser;
        PartiesViewModel partiesViewModel = this.mPanelApp.mPartiesViewModel;
        if (partiesViewModel != null && (socialUser = this.mPartyUser) != null) {
            SocialUser.UserRowType userRowType = socialUser.mUserType;
            if (userRowType == SocialUser.UserRowType.PARTY_MEMBER || userRowType == SocialUser.UserRowType.PARTY_CONTROLS) {
                partiesViewModel.startSpeakingIndicatorSubscriptionForUserID(this, socialUser.mID);
            }
        }
    }

    public void unsubscribeFromSpeakingUpdates() {
        SocialUser socialUser;
        PartiesViewModel partiesViewModel = this.mPanelApp.mPartiesViewModel;
        if (partiesViewModel != null && (socialUser = this.mPartyUser) != null) {
            partiesViewModel.stopSpeakingIndicatorSubscriptionForUserID(this, socialUser.mID);
        }
    }

    public void updateCtaAction() {
        notifyPropertyChanged(47);
        notifyPropertyChanged(38);
        notifyPropertyChanged(12);
    }

    @Override // com.oculus.panelapp.parties.views.ISpeakerIndicatorListener
    public void updateIsSpeaking(boolean z) {
        this.mIsSpeaking = z;
        notifyPropertyChanged(41);
    }

    public void updateMuteHoverOverlay() {
        notifyPropertyChanged(15);
    }

    @Override // com.oculus.panelapp.parties.views.ISelfMuteStateListener
    public void updateSelfMuteState(int i) {
        if (this.mCurrentSelfMuteState != i) {
            this.mCurrentSelfMuteState = i;
            updateCtaAction();
            updateMuteHoverOverlay();
        }
    }

    public PartyUserCardViewModel(Context context, PartiesTabletPanelApp partiesTabletPanelApp) {
        this.mContext = context;
        this.mPanelApp = partiesTabletPanelApp;
        this.mCtaActions = ImmutableList.of();
    }
}
