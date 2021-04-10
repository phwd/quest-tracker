package com.oculus.panelapp.social;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.logging.SectionTrackerEvent;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.tablet.view.BaseView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SocialView extends BaseView implements SocialDataObserver {
    private static final float SLIDER_THRESHOLD_OFF = 0.05f;
    private static final String TAG = LoggingUtil.tag(SocialView.class);
    private AnytimeTabletSocialViewV2Binding mBinding;
    private final Context mContext;
    @Nullable
    private AsyncQueryHandle mCreatePartyAsyncQueryHandle;
    private SocialViewType mCurrentView;
    private SocialAdapter mFriendsAdapter;
    @Nullable
    private AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    private SocialPanelApp mPanelApp;
    private SocialParty mParty;
    private SocialAdapter mPartyAdapter;
    @Nullable
    private String mPendingDeeplinkUri;
    private final Resources mResources;
    @Nullable
    private AsyncQueryHandle mSetPartyTypeAsyncQueryHandle;
    private SocialPartyFooter mSocialPartyFooter;
    private SocialViewModel mSocialViewModel;

    public enum SocialViewType {
        FRIENDS,
        PARTY
    }

    @Override // com.oculus.panelapp.social.SocialDataObserver
    public void updateSocialViewer() {
    }

    public SocialView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing SocialView");
        this.mContext = context;
        this.mResources = context.getResources();
        this.mSocialPartyFooter = new SocialPartyFooter(this.mContext, attributeSet);
    }

    public void initialize(SocialPanelApp socialPanelApp, ViewDataBinding viewDataBinding) {
        super.initialize(socialPanelApp.getAndroidPanelApp(), viewDataBinding);
        Log.d(TAG, "Initializing SocialView");
        this.mPanelApp = socialPanelApp;
        this.mBinding = (AnytimeTabletSocialViewV2Binding) viewDataBinding;
        initializeLists();
        initializePartyHeaderButtons();
        initializeFriendsHeaderButtons();
        this.mPanelApp.logSectionEntry(SectionTrackerEvent.SOCIAL_TABLET);
        this.mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
        setCurrentView(this.mSocialViewModel.getPartyData() != null ? SocialViewType.PARTY : SocialViewType.FRIENDS);
        updateFriendsList(this.mSocialViewModel.getFriends(), this.mSocialViewModel.getPYMKs(), this.mSocialViewModel.getJoinableParties(), this.mSocialViewModel.getPartyData());
        initPartyVolume();
        this.mSocialViewModel.registerObserver(this);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing SocialView");
        this.mPendingDeeplinkUri = str;
        if (this.mSocialViewModel.getEnoughDataFetched()) {
            onEnoughDataFetched();
        }
        this.mSocialViewModel.setTabletActive(true);
    }

    @Override // com.oculus.panelapp.social.SocialDataObserver
    public void onEnoughDataFetched() {
        if (this.mPendingDeeplinkUri != null) {
            String str = TAG;
            Log.d(str, "Deep linked to " + this.mPendingDeeplinkUri);
            if (this.mPendingDeeplinkUri.equals(TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTIES_URI) && this.mSocialViewModel.getPartyData() != null) {
                setCurrentView(SocialViewType.PARTY);
            } else if (this.mPendingDeeplinkUri.equals(TabletDeepLinkingUriUtil.AUI_SOCIAL_START_PARTY_URI)) {
                startParty();
            } else if (this.mPendingDeeplinkUri.equals(TabletDeepLinkingUriUtil.AUI_SOCIAL_END_PARTY_URI) && this.mSocialViewModel.getPartyData() != null) {
                leaveParty();
            } else if (this.mPendingDeeplinkUri.equals(TabletDeepLinkingUriUtil.AUI_SOCIAL_FRIENDS_URI)) {
                setCurrentView(SocialViewType.FRIENDS);
            } else if (this.mPendingDeeplinkUri.equals(TabletDeepLinkingUriUtil.AUI_SOCIAL_PARTY_VIEW_URI)) {
                setCurrentView(SocialViewType.PARTY);
            }
            this.mPendingDeeplinkUri = null;
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding SocialView");
        this.mSocialViewModel.removeMenu();
        this.mSocialViewModel.setTabletActive(false);
        this.mBinding.partyHeader.partyPrivacy.dismissDropdown();
    }

    @Override // com.oculus.panelapp.social.SocialDataObserver
    public void updateFriendsList(List<SocialUser> list, List<SocialUser> list2, List<SocialParty> list3, SocialParty socialParty) {
        Log.d(TAG, "updateFriendsList() - Updating friends list, friends: " + list.size() + ", pymks: " + list2.size() + ", joinableParties: " + list3.size() + ", party: " + socialParty);
        List<SocialAdapterItem> arrayList = new ArrayList<>();
        if (!this.mSocialViewModel.hasInternetConnection()) {
            arrayList.add(new SocialOfflineAdapter(this.mResources.getString(R.string.anytime_tablet_social_offline_title), this.mResources.getString(R.string.anytime_tablet_social_offline_subtitle)));
            setCurrentView(SocialViewType.FRIENDS);
            this.mFriendsAdapter.setData(arrayList);
            this.mBinding.setParty(null);
        } else if (!this.mSocialViewModel.getEnoughDataFetched()) {
            this.mBinding.setShowLoading(true);
        } else {
            boolean z = false;
            this.mBinding.setShowLoading(false);
            updatePartyList(socialParty);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            List<SocialAdapterItem> arrayList4 = new ArrayList<>();
            for (SocialUser socialUser : list) {
                if (socialUser.getIsActive(this.mResources) || socialUser.getLastActive() != null) {
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
            }
            if (!z3 && !z2 && arrayList4.isEmpty()) {
                z = true;
            }
            this.mBinding.setCanShowStartParty(!z);
            Log.d(TAG, "updateFriendsList() - Setting null state: " + z);
            if (z) {
                arrayList.add(new SocialAdapterNullRow(this.mSocialViewModel.getLinkedAccountsInfo()));
            }
            arrayList3.sort($$Lambda$SocialView$OlQT0MWCm9aZCQFAWElP0zCKjqg.INSTANCE);
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

    private void addPymkRowIfPermitted(List<SocialAdapterItem> list, List<SocialAdapterItem> list2) {
        if (!list.isEmpty() && this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_SHOW_PYMK)) {
            list2.add(new SocialAdapterHeader(this.mResources.getString(R.string.anytime_tablet_social_pymk_section_header)));
            list2.add(new SocialCardsRowAdapterItem(list));
        }
    }

    private void initPartyVolume() {
        int partyVolume = (int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f);
        String str = TAG;
        Log.i(str, "Initialize party volume to " + partyVolume);
        this.mBinding.partyHeader.partyVolumeSlider.seekbar.setProgress(partyVolume);
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateVolume(SeekBar seekBar, int i, boolean z) {
        float f = ((float) i) / 100.0f;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("party volume set to: ");
        sb.append(f);
        sb.append(z ? " from user" : "");
        Log.i(str, sb.toString());
        if (!z) {
            seekBar.setProgress((int) (HorizonContentProviderHelper.getPartyVolume(this.mContext) * 100.0f));
            return;
        }
        HorizonContentProviderHelper.setPartyVolume(this.mContext, f);
        if (f < SLIDER_THRESHOLD_OFF) {
            this.mBinding.partyHeader.setMutePartyVolume(true);
        } else {
            this.mBinding.partyHeader.setMutePartyVolume(false);
        }
    }

    public void updatePartyList(SocialParty socialParty) {
        ArrayList arrayList = new ArrayList();
        String localUserId = this.mPanelApp.getLocalUserId();
        if (socialParty == null) {
            this.mPartyAdapter.setData(arrayList);
            this.mBinding.partyHeader.partyPrivacy.setVisibility(8);
            return;
        }
        for (SocialUser socialUser : socialParty.getPartyMembers()) {
            if (socialUser.getID().equals(localUserId)) {
                socialUser.setUserType(SocialUser.UserRowType.PARTY_CONTROLS);
                arrayList.add(0, new SocialUserAdapterItem(socialUser, socialParty));
            } else {
                arrayList.add(new SocialUserAdapterItem(socialUser, socialParty));
            }
        }
        arrayList.add(0, new SocialGuidedActionAdapterItem(SocialGuidedAction.ADD_TO_PARTY));
        for (SocialUser socialUser2 : socialParty.getInvitedUsers()) {
            arrayList.add(new SocialUserAdapterItem(socialUser2, socialParty));
        }
        if (!socialParty.getPartyLeader().getID().equals(localUserId)) {
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
    private void clearSetPartyTypeAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mSetPartyTypeAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSetPartyTypeAsyncQueryHandle = null;
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

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mSocialViewModel.removeObserver(this);
        this.mSocialPartyFooter.destroy();
        clearCreatePartyAsyncQueryHandle();
        clearSetPartyTypeAsyncQueryHandle();
        clearLeavePartyAsyncQueryHandle();
    }

    private void initializeLists() {
        Log.d(TAG, "Initializing social RecyclerView");
        initializeRecyclerView(this.mBinding.friendsList);
        initializeRecyclerView(this.mBinding.partyList);
        this.mFriendsAdapter = new SocialAdapter(this.mPanelApp);
        this.mBinding.friendsList.setAdapter(this.mFriendsAdapter);
        this.mFriendsAdapter.setData(new ArrayList());
        this.mBinding.friendsList.setLayoutManager(new LinearLayoutManager(getContext()) {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass2 */

            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager, androidx.recyclerview.widget.LinearLayoutManager
            public boolean canScrollVertically() {
                return SocialView.this.mSocialViewModel.getOpenMenu() == null && super.canScrollVertically();
            }
        });
        this.mPartyAdapter = new SocialAdapter(this.mPanelApp);
        this.mBinding.partyList.setAdapter(this.mPartyAdapter);
        this.mPartyAdapter.setData(new ArrayList());
        Log.d(TAG, "Initialized social RecyclerView");
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentView(SocialViewType socialViewType) {
        String str = TAG;
        Log.d(str, "Switching view to " + socialViewType.toString());
        this.mCurrentView = socialViewType;
        this.mBinding.setView(socialViewType);
        if (socialViewType == SocialViewType.PARTY) {
            this.mBinding.partyList.scrollToPosition(0);
        } else {
            this.mBinding.setShowPartyFooter(false);
        }
    }

    private void initializePartyHeaderButtons() {
        initializePrivacyDropdown();
        this.mBinding.partyHeader.partyPrivacy.setIsCompact(true);
        this.mBinding.partyHeader.navigateBackButton.button.setEventHandler(this.mPanelApp);
        this.mBinding.partyHeader.navigateBackButton.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$bdcF2FrHcf3VgJiw5hAsRcOHdJM */

            public final void onClick(View view) {
                SocialView.this.lambda$initializePartyHeaderButtons$31$SocialView(view);
            }
        });
        $$Lambda$SocialView$pPLBZGCUQ97_yzB9yL5IOdhAxSY r0 = new DialogResultHandler() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$pPLBZGCUQ97_yzB9yL5IOdhAxSY */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialView.this.lambda$initializePartyHeaderButtons$32$SocialView(dialogResult);
            }
        };
        DialogDefinitionBase leaveInviteOnlyPartyDialog = SocialPartyDialogs.getLeaveInviteOnlyPartyDialog(this.mContext.getResources());
        leaveInviteOnlyPartyDialog.setDialogResultHandler(r0);
        DialogDefinitionBase leaveDirectJoinPartyDialog = SocialPartyDialogs.getLeaveDirectJoinPartyDialog(this.mContext.getResources());
        leaveDirectJoinPartyDialog.setDialogResultHandler(r0);
        this.mBinding.partyHeader.socialEndCallButton.setOnClickListener(new View.OnClickListener(leaveInviteOnlyPartyDialog, leaveDirectJoinPartyDialog) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$la1njG_yjpqci0Pf2JUF8T7LZaw */
            private final /* synthetic */ DialogDefinitionBase f$1;
            private final /* synthetic */ DialogDefinitionBase f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                SocialView.this.lambda$initializePartyHeaderButtons$33$SocialView(this.f$1, this.f$2, view);
            }
        });
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$31$SocialView(View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_NAVIGATE_TO_FRIENDS, this.mPanelApp);
        setCurrentView(SocialViewType.FRIENDS);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$initializePartyHeaderButtons$32$SocialView(com.oculus.systemdialog.DialogResult r5) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialView.lambda$initializePartyHeaderButtons$32$SocialView(com.oculus.systemdialog.DialogResult):boolean");
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$33$SocialView(DialogDefinitionBase dialogDefinitionBase, DialogDefinitionBase dialogDefinitionBase2, View view) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            if (socialParty.getJoinType() == SocialPartyType.JOINABLE_BY_FRIENDS) {
                dialogDefinitionBase = dialogDefinitionBase2;
            }
            this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase);
        }
    }

    private void updatePartyAvailability() {
        String str;
        SocialParty socialParty = this.mParty;
        int maxPartySize = socialParty == null ? 0 : socialParty.getMaxPartySize();
        SocialParty socialParty2 = this.mParty;
        int size = maxPartySize - (socialParty2 == null ? 0 : socialParty2.getSize());
        SocialParty socialParty3 = this.mParty;
        if (socialParty3 == null) {
            str = "";
        } else {
            str = this.mResources.getString(socialParty3.getJoinType().getStringId());
        }
        AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding = this.mBinding.partyHeader;
        anytimeTabletSocialPartyHeaderBinding.setPartySpotsAvailable(this.mResources.getQuantityString(R.plurals.anytime_tablet_social_party_party_spots_available, size, Integer.valueOf(size)) + " · " + str);
    }

    private void updatePartyHeader() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            if (socialParty.getJoinType() == SocialPartyType.OPEN) {
                String socialActivityApplicationDisplayName = this.mParty.getSocialActivityApplicationDisplayName();
                this.mBinding.partyHeader.partyHeaderTitle.setText(this.mResources.getString(R.string.anytime_tablet_social_party_header_title_for_public_party, socialActivityApplicationDisplayName));
                return;
            }
            this.mBinding.partyHeader.partyHeaderTitle.setText(this.mResources.getString(R.string.anytime_tablet_social_party_header_title));
        }
    }

    private void initializePrivacyDropdown() {
        DialogDefinitionBase setToInviteOnlyPartyDialog = SocialPartyDialogs.getSetToInviteOnlyPartyDialog(this.mResources);
        setToInviteOnlyPartyDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$LLycjznFYKB3_rg5wRhsEXeYR8 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialView.this.lambda$initializePrivacyDropdown$34$SocialView(dialogResult);
            }
        });
        DialogDefinitionBase setToFriendsOfParticipantsPartyDialog = SocialPartyDialogs.getSetToFriendsOfParticipantsPartyDialog(this.mResources);
        setToFriendsOfParticipantsPartyDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$xzd3VVW3lmfJ_ASko7QLxfIlO3E */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialView.this.lambda$initializePrivacyDropdown$35$SocialView(dialogResult);
            }
        });
        this.mBinding.partyHeader.partyPrivacy.setItems(new ArrayList(Arrays.asList(SocialPartyType.CLOSED, SocialPartyType.JOINABLE_BY_FRIENDS)));
        this.mBinding.partyHeader.partyPrivacy.setTitleMap(SocialPartyType.getLabelMap());
        this.mBinding.partyHeader.partyPrivacy.setIconMap(SocialPartyType.getIconMap());
        this.mBinding.partyHeader.partyPrivacy.setOnItemClick(new Function(setToInviteOnlyPartyDialog, setToFriendsOfParticipantsPartyDialog) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$qPM1lMgGJAw9fqTWPtxXi78yX7g */
            private final /* synthetic */ DialogDefinitionBase f$1;
            private final /* synthetic */ DialogDefinitionBase f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialView.this.lambda$initializePrivacyDropdown$36$SocialView(this.f$1, this.f$2, obj);
            }
        });
        this.mBinding.partyHeader.partyPrivacy.setEventHandler(this.mPanelApp);
    }

    public /* synthetic */ boolean lambda$initializePrivacyDropdown$34$SocialView(DialogResult dialogResult) {
        if (SocialPartyDialogs.SWITCH_ACTION.equals(dialogResult.getDialogAction())) {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UPDATE_PRIVACY_CLOSED, this.mPanelApp);
            setPartyJoinType(SocialPartyType.CLOSED);
            return true;
        }
        updateCurrentPartyPrivacySelection();
        return false;
    }

    public /* synthetic */ boolean lambda$initializePrivacyDropdown$35$SocialView(DialogResult dialogResult) {
        if (SocialPartyDialogs.SWITCH_ACTION.equals(dialogResult.getDialogAction())) {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_UPDATE_PRIVACY_OPEN, this.mPanelApp);
            setPartyJoinType(SocialPartyType.JOINABLE_BY_FRIENDS);
            return true;
        }
        updateCurrentPartyPrivacySelection();
        return false;
    }

    public /* synthetic */ Object lambda$initializePrivacyDropdown$36$SocialView(DialogDefinitionBase dialogDefinitionBase, DialogDefinitionBase dialogDefinitionBase2, Object obj) {
        SocialPartyType socialPartyType = (SocialPartyType) obj;
        this.mBinding.partyHeader.partyPrivacy.setSelectedItem(socialPartyType);
        SocialParty socialParty = this.mParty;
        if (socialParty != null && socialParty.getPartyLeader().getID().equals(this.mPanelApp.getLocalUserId())) {
            if (socialPartyType == SocialPartyType.CLOSED) {
                this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase);
            } else if (socialPartyType == SocialPartyType.JOINABLE_BY_FRIENDS) {
                this.mPanelApp.getDialogManager().showDialog(dialogDefinitionBase2);
            }
        }
        return null;
    }

    private void updateCurrentPartyPrivacySelection() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            if (socialParty.getJoinType() == SocialPartyType.OPEN) {
                this.mBinding.partyHeader.partyPrivacy.setItems(new ArrayList(Arrays.asList(SocialPartyType.CLOSED, SocialPartyType.JOINABLE_BY_FRIENDS, SocialPartyType.OPEN)));
            } else {
                this.mBinding.partyHeader.partyPrivacy.setItems(new ArrayList(Arrays.asList(SocialPartyType.CLOSED, SocialPartyType.JOINABLE_BY_FRIENDS)));
            }
            this.mBinding.partyHeader.partyPrivacy.setSelectedItem(this.mParty.getJoinType());
            this.mSocialViewModel.notifyPropertyChanged(BR.shouldShowSharePartyButton);
        }
    }

    private void setPartyJoinType(final SocialPartyType socialPartyType) {
        this.mSocialViewModel.ignoreNextPartyUpdate();
        clearSetPartyTypeAsyncQueryHandle();
        this.mSetPartyTypeAsyncQueryHandle = HorizonContentProviderHelper.setPartyType(this.mContext, this.mParty.getID(), socialPartyType.toTypeForMutation(), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialView.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialView.this.clearSetPartyTypeAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPanelApp socialPanelApp = SocialView.this.mPanelApp;
                String str = SocialView.TAG;
                SocialLogger.logError(socialPanelApp, "set_party_join_type", str, "Failed to update join privacy to " + socialPartyType.toString());
                SocialViewWarningToaster.showToast(SocialView.this.mContext, "oculus_mobile_social_party_join_privacy_error", SocialView.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_privacy_update_failed), SocialView.TAG);
                SocialView.this.clearSetPartyTypeAsyncQueryHandle();
            }
        });
    }

    private void leaveParty() {
        if (this.mSocialViewModel.getPartyData() != null) {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_LEAVE, this.mPanelApp);
            String id = this.mSocialViewModel.getPartyData().getID();
            this.mPanelApp.acquireSocialViewModel().removePartyOptimistically();
            setCurrentView(SocialViewType.FRIENDS);
            clearLeavePartyAsyncQueryHandle();
            this.mLeavePartyAsyncQueryHandle = HorizonContentProviderHelper.leaveParty(this.mContext, id, new HorizonContentProviderHelper.SingleIDCallback() {
                /* class com.oculus.panelapp.social.SocialView.AnonymousClass4 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
                public void onSuccess(String str) {
                    SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_PARTY_LEAVE_SUCCESS, SocialView.this.mPanelApp);
                    SocialView.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                    SocialView.this.clearLeavePartyAsyncQueryHandle();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(SocialView.this.mPanelApp, "leave_party", SocialView.TAG, "Failed to leave party");
                    SocialViewWarningToaster.showToast(SocialView.this.mContext, "oculus_mobile_social_party_leave_error", SocialView.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_leave_failed), SocialView.TAG);
                    SocialView.this.clearLeavePartyAsyncQueryHandle();
                }
            });
        }
    }

    private void initializeFriendsHeaderButtons() {
        this.mBinding.friendsHeader.buttonGotoParty.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$3Bdacg5S30iXzeYKe41VX9qL1s */

            public final void onClick(View view) {
                SocialView.this.lambda$initializeFriendsHeaderButtons$37$SocialView(view);
            }
        });
        this.mBinding.friendsHeader.buttonStartParty.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialView$q6bxxfsCt8OwTMMONUgBBNG5UUY */

            public final void onClick(View view) {
                SocialView.this.lambda$initializeFriendsHeaderButtons$38$SocialView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeFriendsHeaderButtons$37$SocialView(View view) {
        updatePartyAvailability();
        setCurrentView(SocialViewType.PARTY);
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_NAVIGATE_TO_PARTY, this.mPanelApp);
    }

    public /* synthetic */ void lambda$initializeFriendsHeaderButtons$38$SocialView(View view) {
        startParty();
    }

    private void startParty() {
        if (this.mSocialViewModel.getPartyData() == null && this.mSocialViewModel.hasInternetConnection()) {
            SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mSocialViewModel.getLinkedAccountsInfo(), new Runnable() {
                /* class com.oculus.panelapp.social.$$Lambda$SocialView$VK8uKugFefRV7hrr2Pg3D6AySs */

                public final void run() {
                    SocialView.this.lambda$startParty$39$SocialView();
                }
            }, new UpsellLoggingParameters("aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_SEND_PARTY_INVITE, "aui_v2_social_panel", SocialBundleConstants.FB_UPSELL_START_PARTY_HEADER_BUTTON, SocialBundleConstants.FB_UPSELL_MUST_INTERACT, SocialBundleConstants.FB_UPSELL_PARTIES_PRODUCT));
        }
    }

    public /* synthetic */ void lambda$startParty$39$SocialView() {
        setCurrentView(SocialViewType.PARTY);
        this.mPartyAdapter.setData(new ArrayList());
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_START_DIALOG_OPEN, this.mPanelApp);
        clearCreatePartyAsyncQueryHandle();
        this.mCreatePartyAsyncQueryHandle = HorizonContentProviderHelper.createParty(this.mContext, null, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialView.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_START_PARTY_SUCCESS, SocialView.this.mPanelApp);
                SocialView.this.mPanelApp.acquireSocialViewModel().notifyPartyCreated();
                SocialView.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialView.this.clearCreatePartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialLogger.logError(SocialView.this.mPanelApp, "start_party", SocialView.TAG, "Failed to start party");
                SocialView.this.setCurrentView(SocialViewType.FRIENDS);
                SocialViewWarningToaster.showToast(SocialView.this.mContext, "oculus_mobile_social_party_start_error", SocialView.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_start_failed), SocialView.TAG);
                SocialView.this.clearCreatePartyAsyncQueryHandle();
            }
        });
    }
}
