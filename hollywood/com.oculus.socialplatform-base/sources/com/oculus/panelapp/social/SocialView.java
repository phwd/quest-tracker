package com.oculus.panelapp.social;

import X.AnonymousClass006;
import X.AnonymousClass1uW;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.view.BaseView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SocialView extends BaseView implements SocialDataObserver {
    public static final float SLIDER_THRESHOLD_OFF = 0.05f;
    public static final String TAG = LoggingUtil.tag(SocialView.class);
    public AnytimeTabletSocialViewV2Binding mBinding;
    public final Context mContext;
    @Nullable
    public AsyncQueryHandle mCreatePartyAsyncQueryHandle;
    public SocialViewType mCurrentView;
    public SocialAdapter mFriendsAdapter;
    @Nullable
    public AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    public SocialPanelApp mPanelApp;
    public SocialParty mParty;
    public SocialAdapter mPartyAdapter;
    @Nullable
    public String mPendingDeeplinkUri;
    public final Resources mResources;
    @Nullable
    public AsyncQueryHandle mSetPartyTypeAsyncQueryHandle;
    public SocialPartyFooter mSocialPartyFooter;
    public SocialViewModel mSocialViewModel;

    public enum SocialViewType {
        FRIENDS,
        PARTY
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        recyclerView.mHasFixedSize = true;
        recyclerView.setNestedScrollingEnabled(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateVolume(SeekBar seekBar, int i, boolean z) {
        AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding;
        boolean z2;
        float f = ((float) i) / 100.0f;
        if (z) {
            HorizonContentProviderHelper.setPartyVolume(this.mContext, f);
            if (f < 0.05f) {
                anytimeTabletSocialPartyHeaderBinding = this.mBinding.partyHeader;
                z2 = true;
            } else {
                anytimeTabletSocialPartyHeaderBinding = this.mBinding.partyHeader;
                z2 = false;
            }
            anytimeTabletSocialPartyHeaderBinding.setMutePartyVolume(z2);
            return;
        }
        seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
    }

    @Override // com.oculus.panelapp.social.SocialDataObserver
    public void updateSocialViewer() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearCreatePartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mCreatePartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mCreatePartyAsyncQueryHandle = null;
        }
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetPartyTypeAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mSetPartyTypeAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSetPartyTypeAsyncQueryHandle = null;
        }
    }

    private void initPartyVolume() {
        this.mBinding.partyHeader.partyVolumeSlider.seekbar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
        this.mBinding.partyHeader.partyVolumeSlider.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass1 */

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                SocialView.this.updateVolume(seekBar, i, z);
            }
        });
    }

    private void initializeFriendsHeaderButtons() {
        this.mBinding.friendsHeader.buttonGotoParty.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$hmdm2f0ZRqops59howEZ9IpKTgk2 */

            public final void onClick(View view) {
                SocialView.this.lambda$initializeFriendsHeaderButtons$7$SocialView(view);
            }
        });
        this.mBinding.friendsHeader.buttonStartParty.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$geG8fVnV88kLySSaPTk0o9SGvVw2 */

            public final void onClick(View view) {
                SocialView.this.lambda$initializeFriendsHeaderButtons$8$SocialView(view);
            }
        });
    }

    private void initializeLists() {
        initializeRecyclerView(this.mBinding.friendsList);
        initializeRecyclerView(this.mBinding.partyList);
        SocialAdapter socialAdapter = new SocialAdapter(this.mPanelApp);
        this.mFriendsAdapter = socialAdapter;
        this.mBinding.friendsList.setAdapter(socialAdapter);
        this.mFriendsAdapter.setData(new ArrayList());
        this.mBinding.friendsList.setLayoutManager(new LinearLayoutManager(getContext()) {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass2 */

            @Override // androidx.recyclerview.widget.LinearLayoutManager, X.AnonymousClass1Ag
            public boolean canScrollVertically() {
                if (SocialView.this.mSocialViewModel.mOpenMenu != null || !super.canScrollVertically()) {
                    return false;
                }
                return true;
            }
        });
        SocialAdapter socialAdapter2 = new SocialAdapter(this.mPanelApp);
        this.mPartyAdapter = socialAdapter2;
        this.mBinding.partyList.setAdapter(socialAdapter2);
        this.mPartyAdapter.setData(new ArrayList());
    }

    private void initializePrivacyDropdown() {
        DialogDefinitionBase setToInviteOnlyPartyDialog = SocialPartyDialogs.getSetToInviteOnlyPartyDialog(this.mResources);
        setToInviteOnlyPartyDialog.mDialogResultHandler = new DialogResultHandler() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$FGzfFmwB0pgm4j5LHOxrdya3ng2 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialView.this.lambda$initializePrivacyDropdown$4$SocialView(dialogResult);
            }
        };
        DialogDefinitionBase setToFriendsOfParticipantsPartyDialog = SocialPartyDialogs.getSetToFriendsOfParticipantsPartyDialog(this.mResources);
        setToFriendsOfParticipantsPartyDialog.mDialogResultHandler = new DialogResultHandler() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$bmHncuUlgk3GVoVTBNfQ6PoHcwg2 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialView.this.lambda$initializePrivacyDropdown$5$SocialView(dialogResult);
            }
        };
        this.mBinding.partyHeader.partyPrivacy.setItems(new ArrayList(Arrays.asList(SocialPartyType.CLOSED, SocialPartyType.JOINABLE_BY_FRIENDS)));
        this.mBinding.partyHeader.partyPrivacy.setTitleMap(SocialPartyType.getLabelMap());
        this.mBinding.partyHeader.partyPrivacy.setIconMap(SocialPartyType.getIconMap());
        OCSelect oCSelect = this.mBinding.partyHeader.partyPrivacy;
        oCSelect.setOnItemClick(new Function(setToInviteOnlyPartyDialog, setToFriendsOfParticipantsPartyDialog) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$MestZuwsoLYNVAM2t7qOsRyKXQ2 */
            public final /* synthetic */ DialogDefinitionBase f$1;
            public final /* synthetic */ DialogDefinitionBase f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialView.this.lambda$initializePrivacyDropdown$6$SocialView(this.f$1, this.f$2, obj);
            }
        });
        oCSelect.setEventHandler(this.mPanelApp);
    }

    private void leaveParty() {
        if (this.mSocialViewModel.mParty != null) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_LEAVE);
            String str = this.mSocialViewModel.mParty.mID;
            this.mPanelApp.acquireSocialViewModel().removePartyOptimistically();
            setCurrentView(SocialViewType.FRIENDS);
            clearLeavePartyAsyncQueryHandle();
            this.mLeavePartyAsyncQueryHandle = HorizonContentProviderHelper.leaveParty(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
                /* class com.oculus.panelapp.social.SocialView.AnonymousClass4 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(SocialView.this.mContext, "leave_party", SocialView.TAG, "Failed to leave party");
                    Context context = SocialView.this.mContext;
                    SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_leave_error", context.getResources().getString(R.string.anytime_tablet_social_party_leave_failed), SocialView.TAG);
                    SocialView.this.clearLeavePartyAsyncQueryHandle();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
                public void onSuccess(String str) {
                    SocialView.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PARTY_LEAVE_SUCCESS);
                    SocialView.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                    SocialView.this.clearLeavePartyAsyncQueryHandle();
                }
            });
        }
    }

    private void setPartyJoinType(final SocialPartyType socialPartyType) {
        this.mSocialViewModel.mIgnoreNextPartyUpdate = true;
        clearSetPartyTypeAsyncQueryHandle();
        this.mSetPartyTypeAsyncQueryHandle = HorizonContentProviderHelper.setPartyType(this.mContext, this.mParty.mID, socialPartyType.toTypeForMutation(), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialView.this.mContext, "set_party_join_type", SocialView.TAG, AnonymousClass006.A07("Failed to update join privacy to ", socialPartyType.toString()));
                Context context = SocialView.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_join_privacy_error", context.getResources().getString(R.string.anytime_tablet_social_party_privacy_update_failed), SocialView.TAG);
                SocialView.this.clearSetPartyTypeAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialView.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialView.this.clearSetPartyTypeAsyncQueryHandle();
            }
        });
    }

    private void startParty() {
        SocialViewModel socialViewModel = this.mSocialViewModel;
        if (socialViewModel.mParty == null && socialViewModel.mHasInternetConnection) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, socialViewModel.mLinkedAccountsInfo, new Runnable() {
                /* class com.oculus.panelapp.social.$$Lambda$SocialView$Iul0wT7wnXOjoC4s5LHSwNX8za82 */

                public final void run() {
                    SocialView.this.lambda$startParty$9$SocialView();
                }
            }, new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_START_PARTY_HEADER_BUTTON, "true", SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT));
        }
    }

    private void updateCurrentPartyPrivacySelection() {
        SocialPartyType[] socialPartyTypeArr;
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            SocialPartyType socialPartyType = socialParty.mJoinType;
            SocialPartyType socialPartyType2 = SocialPartyType.OPEN;
            OCSelect oCSelect = this.mBinding.partyHeader.partyPrivacy;
            if (socialPartyType == socialPartyType2) {
                socialPartyTypeArr = new SocialPartyType[]{SocialPartyType.CLOSED, SocialPartyType.JOINABLE_BY_FRIENDS, socialPartyType2};
            } else {
                socialPartyTypeArr = new SocialPartyType[]{SocialPartyType.CLOSED, SocialPartyType.JOINABLE_BY_FRIENDS};
            }
            oCSelect.setItems(new ArrayList(Arrays.asList(socialPartyTypeArr)));
            this.mBinding.partyHeader.partyPrivacy.setSelectedItem(this.mParty.mJoinType);
            this.mSocialViewModel.notifyPropertyChanged(110);
        }
    }

    private void updatePartyAvailability() {
        int i;
        int size;
        String string;
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            i = 0;
        } else {
            i = socialParty.mMaxPartySize;
        }
        if (socialParty == null) {
            size = 0;
        } else {
            size = socialParty.getSize();
        }
        int i2 = i - size;
        SocialParty socialParty2 = this.mParty;
        if (socialParty2 == null) {
            string = "";
        } else {
            string = this.mResources.getString(socialParty2.mJoinType.getStringId());
        }
        this.mBinding.partyHeader.setPartySpotsAvailable(AnonymousClass006.A09(this.mResources.getQuantityString(R.plurals.anytime_tablet_social_party_party_spots_available, i2, Integer.valueOf(i2)), " · ", string));
    }

    private void updatePartyHeader() {
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            return;
        }
        if (socialParty.mJoinType == SocialPartyType.OPEN) {
            this.mBinding.partyHeader.partyHeaderTitle.setText(this.mResources.getString(R.string.anytime_tablet_social_party_header_title_for_public_party, socialParty.mSocialActivityApplicationDisplayName));
            return;
        }
        this.mBinding.partyHeader.partyHeaderTitle.setText(this.mResources.getString(R.string.anytime_tablet_social_party_header_title));
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mSocialViewModel.removeObserver(this);
        this.mSocialPartyFooter.destroy();
        clearCreatePartyAsyncQueryHandle();
        clearSetPartyTypeAsyncQueryHandle();
        clearLeavePartyAsyncQueryHandle();
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$1$SocialView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_NAVIGATE_TO_FRIENDS);
        setCurrentView(SocialViewType.FRIENDS);
    }

    public /* synthetic */ boolean lambda$initializePartyHeaderButtons$2$SocialView(DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 102846135 && str.equals(SocialPartyDialogs.LEAVE_ACTION)) {
                leaveParty();
            }
        } else if (str.equals("cancel")) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$3$SocialView(DialogDefinitionBase dialogDefinitionBase, DialogDefinitionBase dialogDefinitionBase2, View view) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            if (socialParty.mJoinType == SocialPartyType.JOINABLE_BY_FRIENDS) {
                dialogDefinitionBase = dialogDefinitionBase2;
            }
            this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase);
        }
    }

    public /* synthetic */ boolean lambda$initializePrivacyDropdown$4$SocialView(DialogResult dialogResult) {
        if (SocialPartyDialogs.SWITCH_ACTION.equals(dialogResult.mDialogAction)) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UPDATE_PRIVACY_CLOSED);
            setPartyJoinType(SocialPartyType.CLOSED);
            return true;
        }
        updateCurrentPartyPrivacySelection();
        return false;
    }

    public /* synthetic */ boolean lambda$initializePrivacyDropdown$5$SocialView(DialogResult dialogResult) {
        if (SocialPartyDialogs.SWITCH_ACTION.equals(dialogResult.mDialogAction)) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UPDATE_PRIVACY_OPEN);
            setPartyJoinType(SocialPartyType.JOINABLE_BY_FRIENDS);
            return true;
        }
        updateCurrentPartyPrivacySelection();
        return false;
    }

    public /* synthetic */ Object lambda$initializePrivacyDropdown$6$SocialView(DialogDefinitionBase dialogDefinitionBase, DialogDefinitionBase dialogDefinitionBase2, Object obj) {
        this.mBinding.partyHeader.partyPrivacy.setSelectedItem(obj);
        SocialParty socialParty = this.mParty;
        if (socialParty != null && socialParty.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId())) {
            if (obj == SocialPartyType.CLOSED) {
                this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase);
            } else if (obj == SocialPartyType.JOINABLE_BY_FRIENDS) {
                this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase2);
                return null;
            }
        }
        return null;
    }

    public /* synthetic */ void lambda$startParty$9$SocialView() {
        setCurrentView(SocialViewType.PARTY);
        this.mPartyAdapter.setData(new ArrayList());
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_START_DIALOG_OPEN);
        clearCreatePartyAsyncQueryHandle();
        this.mCreatePartyAsyncQueryHandle = HorizonContentProviderHelper.createParty(this.mContext, null, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialView.this.mContext, "start_party", SocialView.TAG, "Failed to start party");
                SocialView.this.setCurrentView(SocialViewType.FRIENDS);
                Context context = SocialView.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_start_error", context.getResources().getString(R.string.anytime_tablet_social_party_start_failed), SocialView.TAG);
                SocialView.this.clearCreatePartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialView.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_START_PARTY_SUCCESS);
                SocialView.this.mPanelApp.acquireSocialViewModel().notifyPartyCreated();
                SocialView.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialView.this.clearCreatePartyAsyncQueryHandle();
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r1.equals(com.oculus.tablet.navigation.TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTY_VIEW_URI) != false) goto L_0x0012;
     */
    @Override // com.oculus.panelapp.social.SocialDataObserver
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onEnoughDataFetched() {
        /*
            r2 = this;
            java.lang.String r1 = r2.mPendingDeeplinkUri
            if (r1 == 0) goto L_0x001a
            java.lang.String r0 = "/social/parties"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x001b
            com.oculus.panelapp.social.SocialViewModel r0 = r2.mSocialViewModel
            com.oculus.horizoncontent.social.SocialParty r0 = r0.mParty
            if (r0 == 0) goto L_0x001b
        L_0x0012:
            com.oculus.panelapp.social.SocialView$SocialViewType r0 = com.oculus.panelapp.social.SocialView.SocialViewType.PARTY
        L_0x0014:
            r2.setCurrentView(r0)
        L_0x0017:
            r0 = 0
            r2.mPendingDeeplinkUri = r0
        L_0x001a:
            return
        L_0x001b:
            java.lang.String r0 = "/social/parties/start"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0027
            r2.startParty()
            goto L_0x0017
        L_0x0027:
            java.lang.String r0 = "/social/parties/end"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0039
            com.oculus.panelapp.social.SocialViewModel r0 = r2.mSocialViewModel
            com.oculus.horizoncontent.social.SocialParty r0 = r0.mParty
            if (r0 == 0) goto L_0x0039
            r2.leaveParty()
            goto L_0x0017
        L_0x0039:
            java.lang.String r0 = "/social_friends"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0044
            com.oculus.panelapp.social.SocialView$SocialViewType r0 = com.oculus.panelapp.social.SocialView.SocialViewType.FRIENDS
            goto L_0x0014
        L_0x0044:
            java.lang.String r0 = "/social/party_view"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0017
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialView.onEnoughDataFetched():void");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        this.mSocialViewModel.removeMenu();
        this.mSocialViewModel.setTabletActive(false);
        this.mBinding.partyHeader.partyPrivacy.dismissDropdown();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        this.mPendingDeeplinkUri = str;
        if (this.mSocialViewModel.getEnoughDataFetched()) {
            onEnoughDataFetched();
        }
        this.mSocialViewModel.setTabletActive(true);
    }

    public void updatePartyList(SocialParty socialParty) {
        ArrayList arrayList = new ArrayList();
        String localUserId = this.mPanelApp.getLocalUserId();
        if (socialParty == null) {
            this.mPartyAdapter.setData(arrayList);
            this.mBinding.partyHeader.partyPrivacy.setVisibility(8);
            return;
        }
        for (SocialUser socialUser : socialParty.mPartyMembers) {
            if (socialUser.mID.equals(localUserId)) {
                socialUser.mUserType = SocialUser.UserRowType.PARTY_CONTROLS;
                arrayList.add(0, new SocialUserAdapterItem(socialUser, socialParty));
            } else {
                arrayList.add(new SocialUserAdapterItem(socialUser, socialParty));
            }
        }
        arrayList.add(0, new SocialGuidedActionAdapterItem(SocialGuidedAction.ADD_TO_PARTY));
        for (SocialUser socialUser2 : socialParty.getInvitedUsers()) {
            arrayList.add(new SocialUserAdapterItem(socialUser2, socialParty));
        }
        if (!socialParty.mPartyLeader.mID.equals(localUserId)) {
            this.mBinding.partyHeader.partyPrivacy.setVisibility(8);
        } else {
            this.mBinding.partyHeader.partyPrivacy.setVisibility(0);
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SocialCardsRowAdapterItem(arrayList));
        if (this.mCurrentView == SocialViewType.PARTY) {
            this.mBinding.partyHeader.partyVolumeSlider.seekbar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
            this.mSocialPartyFooter.initialize(this.mPanelApp, this.mBinding);
        }
        this.mPartyAdapter.setData(arrayList2);
    }

    public SocialView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mResources = context.getResources();
        this.mSocialPartyFooter = new SocialPartyFooter(this.mContext, attributeSet);
    }

    private void addPymkRowIfPermitted(List<SocialAdapterItem> list, List<SocialAdapterItem> list2) {
        if (!list.isEmpty() && this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_SHOW_PYMK)) {
            list2.add(new SocialAdapterHeader(this.mResources.getString(R.string.anytime_tablet_social_pymk_section_header)));
            list2.add(new SocialCardsRowAdapterItem(list));
        }
    }

    private void initializePartyHeaderButtons() {
        initializePrivacyDropdown();
        AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding = this.mBinding.partyHeader;
        anytimeTabletSocialPartyHeaderBinding.partyPrivacy.mIsCompact = true;
        OCButton oCButton = anytimeTabletSocialPartyHeaderBinding.navigateBackButton.button;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$cudDtam37pbXSRfBaFbcCg01km42 */

            public final void onClick(View view) {
                SocialView.this.lambda$initializePartyHeaderButtons$1$SocialView(view);
            }
        });
        $$Lambda$SocialView$iS6id2AQho9EegAM1Cov63yRRfQ2 r1 = new DialogResultHandler() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$iS6id2AQho9EegAM1Cov63yRRfQ2 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialView.this.lambda$initializePartyHeaderButtons$2$SocialView(dialogResult);
            }
        };
        DialogDefinitionBase leaveInviteOnlyPartyDialog = SocialPartyDialogs.getLeaveInviteOnlyPartyDialog(this.mContext.getResources());
        leaveInviteOnlyPartyDialog.mDialogResultHandler = r1;
        DialogDefinitionBase leaveDirectJoinPartyDialog = SocialPartyDialogs.getLeaveDirectJoinPartyDialog(this.mContext.getResources());
        leaveDirectJoinPartyDialog.mDialogResultHandler = r1;
        this.mBinding.partyHeader.socialEndCallButton.setOnClickListener(new View.OnClickListener(leaveInviteOnlyPartyDialog, leaveDirectJoinPartyDialog) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$57DjBhMKP2CEAWpIW7WehPJKTOU2 */
            public final /* synthetic */ DialogDefinitionBase f$1;
            public final /* synthetic */ DialogDefinitionBase f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                SocialView.this.lambda$initializePartyHeaderButtons$3$SocialView(this.f$1, this.f$2, view);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentView(SocialViewType socialViewType) {
        socialViewType.toString();
        this.mCurrentView = socialViewType;
        this.mBinding.setView(socialViewType);
        if (socialViewType == SocialViewType.PARTY) {
            this.mBinding.partyList.scrollToPosition(0);
        } else {
            this.mBinding.setShowPartyFooter(false);
        }
    }

    public void initialize(SocialPanelApp socialPanelApp, AnonymousClass1uW r6) {
        SocialViewType socialViewType;
        super.initialize(socialPanelApp.getAndroidPanelApp(), r6);
        this.mPanelApp = socialPanelApp;
        this.mBinding = (AnytimeTabletSocialViewV2Binding) r6;
        initializeLists();
        initializePartyHeaderButtons();
        initializeFriendsHeaderButtons();
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.SOCIAL_TABLET);
        SocialViewModel acquireSocialViewModel = this.mPanelApp.acquireSocialViewModel();
        this.mSocialViewModel = acquireSocialViewModel;
        if (acquireSocialViewModel.mParty != null) {
            socialViewType = SocialViewType.PARTY;
        } else {
            socialViewType = SocialViewType.FRIENDS;
        }
        setCurrentView(socialViewType);
        SocialViewModel socialViewModel = this.mSocialViewModel;
        updateFriendsList(socialViewModel.mFriends, socialViewModel.mPYMKs, socialViewModel.getJoinableParties(), this.mSocialViewModel.mParty);
        initPartyVolume();
        this.mSocialViewModel.registerObserver(this);
    }

    public /* synthetic */ void lambda$initializeFriendsHeaderButtons$7$SocialView(View view) {
        updatePartyAvailability();
        setCurrentView(SocialViewType.PARTY);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_NAVIGATE_TO_PARTY);
    }

    @Override // com.oculus.panelapp.social.SocialDataObserver
    public void updateFriendsList(List<SocialUser> list, List<SocialUser> list2, List<SocialParty> list3, SocialParty socialParty) {
        list.size();
        list2.size();
        list3.size();
        ArrayList arrayList = new ArrayList();
        SocialViewModel socialViewModel = this.mSocialViewModel;
        if (!socialViewModel.mHasInternetConnection) {
            arrayList.add(new SocialOfflineAdapter(this.mResources.getString(R.string.anytime_tablet_social_offline_title), this.mResources.getString(R.string.anytime_tablet_social_offline_subtitle)));
            setCurrentView(SocialViewType.FRIENDS);
            this.mFriendsAdapter.setData(arrayList);
            this.mBinding.setParty(null);
        } else if (!socialViewModel.getEnoughDataFetched()) {
            this.mBinding.setShowLoading(true);
        } else {
            boolean z = false;
            this.mBinding.setShowLoading(false);
            updatePartyList(socialParty);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (SocialUser socialUser : list) {
                if (socialUser.getIsActive(this.mResources) || socialUser.mLastActive != null) {
                    arrayList2.add(new SocialUserAdapterItem(socialUser, this.mParty));
                }
                arrayList3.add(new SocialUserAdapterItem(socialUser, this.mParty));
            }
            boolean z2 = !arrayList3.isEmpty();
            ArrayList arrayList5 = new ArrayList(arrayList2);
            boolean z3 = !arrayList5.isEmpty();
            for (SocialUser socialUser2 : list2) {
                arrayList4.add(new SocialUserAdapterItem(socialUser2, this.mParty));
            }
            if (z3) {
                arrayList.add(new SocialAdapterHeader(this.mResources.getString(R.string.anytime_tablet_social_active_section_header)));
                arrayList.add(new SocialCardsRowAdapterItem(arrayList5));
            } else {
                addPymkRowIfPermitted(arrayList4, arrayList);
                if (!z2 && arrayList4.isEmpty()) {
                    z = true;
                }
            }
            this.mBinding.setCanShowStartParty(!z);
            if (z) {
                arrayList.add(new SocialAdapterNullRow(this.mSocialViewModel.mLinkedAccountsInfo));
            }
            arrayList3.sort($$Lambda$SocialView$4yPjs2WOOpYNLEFg1WFA5UxQO6A2.INSTANCE);
            if (z2) {
                arrayList.add(new SocialAdapterHeader(this.mResources.getString(R.string.anytime_tablet_social_all_section_header), true));
                arrayList.addAll(arrayList3);
            }
            if (z3) {
                addPymkRowIfPermitted(arrayList4, arrayList);
            }
            if (this.mParty != null && socialParty == null && this.mCurrentView == SocialViewType.PARTY) {
                this.mSocialViewModel.stopFetchingSpeakingIndicators();
                setCurrentView(SocialViewType.FRIENDS);
            }
            if (this.mParty == null && socialParty != null && this.mCurrentView == SocialViewType.FRIENDS) {
                setCurrentView(SocialViewType.PARTY);
            }
            this.mBinding.setParty(socialParty);
            this.mParty = socialParty;
            updatePartyAvailability();
            updatePartyHeader();
            updateCurrentPartyPrivacySelection();
            this.mFriendsAdapter.setData(arrayList);
        }
    }

    public /* synthetic */ void lambda$initializeFriendsHeaderButtons$8$SocialView(View view) {
        startParty();
    }
}
