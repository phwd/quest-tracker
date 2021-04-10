package com.oculus.panelapp.social;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialDeeplinkPresence;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.SocialPartyFooter;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.utils.ImageLoader;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.views.ShellButton;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

public class SocialPartyFooter extends ConstraintLayout {
    private static final long GROUP_LAUNCH_DELAY_MS = 10000;
    private static final int PARTY_LIST_HEIGHT_WITHOUT_FOOTER = 276;
    private static final int PARTY_LIST_HEIGHT_WITH_FOOTER = 204;
    private static final String TAG = LoggingUtil.tag(SocialPartyFooter.class);
    @Nullable
    AsyncSource mAsyncActionSource = null;
    private AnytimeTabletSocialViewV2Binding mBinding;
    private final Context mContext;
    private Handler mHandler = new Handler();
    @Nullable
    private AsyncQueryHandle mLaunchGroupLaunchAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mLaunchGroupLaunchSoloAsyncQueryHandle;
    private Timer mLaunchTimer;
    @Nullable
    private AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    private SocialPanelApp mPanelApp;
    private SocialParty mParty;
    private SocialUser mPartyLeader;
    @Nullable
    private AsyncQueryHandle mPrepareGroupLaunchAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mRemovePartyGroupLaunchDestinationAsyncQueryHandle;
    private final Resources mResources;
    @Nullable
    private AsyncQueryHandle mSetGroupLaunchResponseAsyncQueryHandle;
    @Nullable
    private AsyncQueryHandle mSetPartyGroupLaunchDestinationAsyncQueryHandle;
    private SocialViewModel mSocialViewModel;

    /* access modifiers changed from: private */
    public enum AsyncSource {
        PARTY_FOOTER_PRIMARY_BUTTON,
        PARTY_FOOTER_SECONDARY_BUTTON
    }

    public SocialPartyFooter(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing SocialPartyFooter");
        this.mContext = context;
        this.mResources = context.getResources();
    }

    public void initialize(SocialPanelApp socialPanelApp, ViewDataBinding viewDataBinding) {
        SocialParty socialParty;
        Log.d(TAG, "Initializing SocialPartyFooter");
        this.mPanelApp = socialPanelApp;
        this.mBinding = (AnytimeTabletSocialViewV2Binding) viewDataBinding;
        this.mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
        this.mParty = this.mSocialViewModel.getPartyData();
        this.mParty.getProposedGroupLaunchAppDestination();
        this.mBinding.partyFooter.setSocialViewModel(this.mSocialViewModel);
        this.mPanelApp.getLocalUserId();
        if (!this.mPanelApp.isGKEnabled(Gatekeeper.OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY) || (socialParty = this.mParty) == null) {
            hidePartyFooter();
            return;
        }
        ListIterator<SocialUser> listIterator = socialParty.getPartyMembers().listIterator();
        while (listIterator.hasNext()) {
            SocialUser next = listIterator.next();
            if (next.getID().equals(this.mParty.getPartyLeader().getID())) {
                this.mPartyLeader = next;
            }
        }
        initializeIcon();
        initializeDestinationInfo();
        initializeStatusText();
        initializePrimaryActionButton();
        initializeChangeDestinationButton();
        initializeSharePartyButton();
        showPartyFooter();
    }

    public void destroy() {
        clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
        clearLaunchGroupLaunchAsyncQueryHandle();
        clearLaunchGroupLaunchSoloAsyncQueryHandle();
        clearPrepareGroupLaunchAsyncQueryHandle();
        clearSetGroupLaunchResponseAsyncQueryHandle();
        clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
        clearLeavePartyAsyncQueryHandle();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearRemovePartyGroupLaunchDestinationAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mRemovePartyGroupLaunchDestinationAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mRemovePartyGroupLaunchDestinationAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLaunchGroupLaunchAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLaunchGroupLaunchAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLaunchGroupLaunchAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearLaunchGroupLaunchSoloAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLaunchGroupLaunchSoloAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLaunchGroupLaunchSoloAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearPrepareGroupLaunchAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mPrepareGroupLaunchAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mPrepareGroupLaunchAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetGroupLaunchResponseAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mSetGroupLaunchResponseAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSetGroupLaunchResponseAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSetPartyGroupLaunchDestinationAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mSetPartyGroupLaunchDestinationAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSetPartyGroupLaunchDestinationAsyncQueryHandle = null;
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

    private boolean isPublicParty() {
        return this.mParty.getSocialActivityId() != null && !this.mParty.getSocialActivityId().isEmpty() && this.mParty.getJoinType().equals(SocialPartyType.OPEN);
    }

    private boolean isPartyLeaderInAssociatedApp() {
        return this.mPartyLeader.getDeepLink() != null && this.mPartyLeader.getDeepLink().getAppID().equals(this.mParty.getSocialActivityApplicationId());
    }

    private void initializeIcon() {
        if (this.mParty.getProposedGroupLaunchAppDestination() != null) {
            setGroupLaunchIcon(this.mParty.getProposedGroupLaunchAppDestination().getImageUrl());
        } else if (!this.mParty.getSocialActivityIconUrl().isEmpty()) {
            setGroupLaunchIcon(this.mParty.getSocialActivityIconUrl());
        } else {
            setGroupLaunchIcon(null);
        }
    }

    private void initializeDestinationInfo() {
        SocialGroupLaunchAppDestination proposedGroupLaunchAppDestination = this.mParty.getProposedGroupLaunchAppDestination();
        if (proposedGroupLaunchAppDestination != null) {
            this.mBinding.partyFooter.applicationName.setText(proposedGroupLaunchAppDestination.getAppDisplayName());
            this.mBinding.partyFooter.destinationName.setText(proposedGroupLaunchAppDestination.getDestinationDisplayName());
            shouldShowDestinationInfo(true);
        } else if (!this.mParty.getSocialActivityId().isEmpty()) {
            this.mBinding.partyFooter.applicationName.setText(this.mParty.getSocialActivityApplicationDisplayName());
            this.mBinding.partyFooter.destinationName.setText(this.mParty.getSocialActivityTitle());
            shouldShowDestinationInfo(true);
        } else {
            shouldShowDestinationInfo(false);
        }
    }

    private void shouldShowDestinationInfo(boolean z) {
        int i = 0;
        this.mBinding.partyFooter.applicationName.setVisibility(z ? 0 : 8);
        this.mBinding.partyFooter.destinationInfoSeparator.setVisibility(z ? 0 : 8);
        OCTextView oCTextView = this.mBinding.partyFooter.destinationName;
        if (!z) {
            i = 8;
        }
        oCTextView.setVisibility(i);
    }

    private void initializeStatusText() {
        SocialGroupLaunchAppDestination proposedGroupLaunchAppDestination = this.mParty.getProposedGroupLaunchAppDestination();
        if (this.mPartyLeader.getID().equals(this.mPanelApp.getLocalUserId())) {
            if (proposedGroupLaunchAppDestination == null) {
                if (!this.mParty.getSocialActivityId().isEmpty()) {
                    this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_travel_to_status_text));
                } else {
                    this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_status_text_destinationless));
                }
            } else if (this.mParty.getSize() == 1) {
                this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_status_text_leader_alone));
            } else {
                this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_status_text_with_others));
            }
        } else if (proposedGroupLaunchAppDestination != null) {
            this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_status_text_with_others));
        } else if (!this.mParty.getSocialActivityId().isEmpty()) {
            this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_travel_to_status_text));
        } else {
            this.mBinding.partyFooter.setStatusText(this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_member_status_text_destinationless));
        }
    }

    private void initializePrimaryActionButton() {
        SocialGroupLaunchAppDestination proposedGroupLaunchAppDestination = this.mParty.getProposedGroupLaunchAppDestination();
        if ((proposedGroupLaunchAppDestination != null && !getIsAppInstalled(proposedGroupLaunchAppDestination.getAppID())) || (!this.mParty.getSocialActivityId().isEmpty() && !getIsAppInstalled(this.mParty.getSocialActivityApplicationId()))) {
            setPrimaryActionButton(false, this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_view_details_button), new Runnable(proposedGroupLaunchAppDestination) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$77NCqZdVPPqf6SdmrbBkTYcq_Y8 */
                private final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SocialPartyFooter.this.lambda$initializePrimaryActionButton$17$SocialPartyFooter(this.f$1);
                }
            });
        } else if (this.mPartyLeader.getID().equals(this.mPanelApp.getLocalUserId())) {
            if (proposedGroupLaunchAppDestination != null) {
                setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable(proposedGroupLaunchAppDestination) {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$su9ZuiWHTn3itC_kRHLAWmuQ51g */
                    private final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        SocialPartyFooter.this.lambda$initializePrimaryActionButton$20$SocialPartyFooter(this.f$1);
                    }
                });
            } else if (!this.mParty.getSocialActivityId().isEmpty()) {
                setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable() {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$hMbrOgzX4RpU211DODdqAcMpDsg */

                    public final void run() {
                        SocialPartyFooter.this.lambda$initializePrimaryActionButton$18$SocialPartyFooter();
                    }
                });
            } else {
                setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_propose_button), new Runnable() {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$717VzR0c2I3DrqSZyXnLHiOYYAg */

                    public final void run() {
                        SocialPartyFooter.this.lambda$initializePrimaryActionButton$19$SocialPartyFooter();
                    }
                });
            }
        } else if (proposedGroupLaunchAppDestination != null) {
            setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable(proposedGroupLaunchAppDestination) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$qLsBfd0TMkaq2sCWPOrvsLPrFQQ */
                private final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SocialPartyFooter.this.lambda$initializePrimaryActionButton$22$SocialPartyFooter(this.f$1);
                }
            });
        } else if (!this.mParty.getSocialActivityId().isEmpty()) {
            setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable() {
                /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$E1qvFG3cb5zQIxs1SzbYRpQF18 */

                public final void run() {
                    SocialPartyFooter.this.lambda$initializePrimaryActionButton$21$SocialPartyFooter();
                }
            });
        } else {
            hideButton(this.mBinding.partyFooter.primaryActionButton.button);
        }
    }

    public /* synthetic */ void lambda$initializePrimaryActionButton$17$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        String str;
        if (socialGroupLaunchAppDestination != null) {
            str = socialGroupLaunchAppDestination.getAppID();
        } else {
            str = this.mParty.getSocialActivityApplicationId();
        }
        onViewAppDetailsButtonClick(str);
    }

    private void initializeChangeDestinationButton() {
        SocialGroupLaunchAppDestination proposedGroupLaunchAppDestination = this.mParty.getProposedGroupLaunchAppDestination();
        if (!this.mPartyLeader.getID().equals(this.mPanelApp.getLocalUserId()) || isPublicParty() || proposedGroupLaunchAppDestination == null) {
            this.mBinding.partyFooter.changeDestinationButton.setVisibility(8);
            return;
        }
        this.mBinding.partyFooter.changeDestinationButton.setVisibility(0);
        this.mBinding.partyFooter.changeDestinationButton.setEventHandler(this.mPanelApp);
        this.mBinding.partyFooter.changeDestinationButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$Guu2jiJURhnzQG2cWAEDIMm1f0k */

            public final void onClick(View view) {
                SocialPartyFooter.this.lambda$initializeChangeDestinationButton$23$SocialPartyFooter(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeChangeDestinationButton$23$SocialPartyFooter(View view) {
        onSelectNewAppButtonClick();
    }

    private void initializeSharePartyButton() {
        String id = this.mSocialViewModel.getPartyData().getID();
        this.mBinding.partyFooter.sharePartyButton.button.setBackground(this.mContext.getDrawable(R.drawable.ocbutton_secondary));
        this.mBinding.partyFooter.sharePartyButton.button.clearOnClickListener();
        this.mBinding.partyFooter.sharePartyButton.button.setOnClickListener(new View.OnClickListener(id) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$GvvOhS9NcjgMWt7qvgFuzybb5Yo */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialPartyFooter.this.lambda$initializeSharePartyButton$24$SocialPartyFooter(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeSharePartyButton$24$SocialPartyFooter(String str, View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_SHARE_PARTY, this.mPanelApp);
        SocialPartyUtils.navigateToSocialPartyShareSheet(this.mPanelApp, str);
    }

    public boolean getIsAppInstalled(String str) {
        for (App app : HorizonUtil.getApplications(this.mContext)) {
            if (app.id.equals(str) && app.status.equals(AppStatus.INSTALLED)) {
                return true;
            }
        }
        return false;
    }

    private void onViewAppDetailsButtonClick(String str) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_VIEW_APP_DETAILS, this.mPanelApp);
        SocialPanelApp socialPanelApp = this.mPanelApp;
        SystemUXRoute systemUXRoute = SystemUXRoute.STORE;
        socialPanelApp.actionNavigate(systemUXRoute, "/item/" + str);
        setCurrentAsyncAction(null);
    }

    private void setPrimaryActionButton(boolean z, String str, Runnable runnable) {
        if (z) {
            this.mBinding.partyFooter.primaryActionButton.button.setBackground(this.mContext.getDrawable(R.drawable.ocbutton_primary));
        } else {
            this.mBinding.partyFooter.primaryActionButton.button.setBackground(this.mContext.getDrawable(R.drawable.ocbutton_secondary));
        }
        this.mBinding.partyFooter.setPrimaryActionButton(str);
        this.mBinding.partyFooter.primaryActionButton.button.clearOnClickListener();
        this.mBinding.partyFooter.primaryActionButton.button.setOnClickListener(new View.OnClickListener(runnable) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$VEdITzcyNn5VCsxzbJxz5u1ts64 */
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialPartyFooter.this.lambda$setPrimaryActionButton$25$SocialPartyFooter(this.f$1, view);
            }
        });
        this.mBinding.partyFooter.primaryActionButton.button.setEnabled(true);
        this.mBinding.partyFooter.primaryActionButton.button.setVisibility(0);
    }

    public /* synthetic */ void lambda$setPrimaryActionButton$25$SocialPartyFooter(Runnable runnable, View view) {
        setCurrentAsyncAction(AsyncSource.PARTY_FOOTER_PRIMARY_BUTTON);
        runnable.run();
    }

    private void hideButton(ShellButton shellButton) {
        shellButton.setVisibility(8);
    }

    private void disablePrimaryActionButton(String str) {
        this.mBinding.partyFooter.setPrimaryActionButton(str);
        this.mBinding.partyFooter.primaryActionButton.button.setVisibility(0);
        this.mBinding.partyFooter.primaryActionButton.button.setEnabled(false);
    }

    private void onClickLeavePartyButton(String str) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LEAVE_PARTY, this.mPanelApp);
        if (this.mParty != null) {
            $$Lambda$SocialPartyFooter$Yi9RAUH8wIWGT2jsW92iVgpc8 r0 = new DialogResultHandler(str) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$Yi9RAUH8wIWGT2jsW92iVgpc8 */
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SocialPartyFooter.this.lambda$onClickLeavePartyButton$26$SocialPartyFooter(this.f$1, dialogResult);
                }
            };
            DialogDefinitionBase leaveInviteOnlyPartyDialog = SocialPartyDialogs.getLeaveInviteOnlyPartyDialog(this.mContext.getResources());
            leaveInviteOnlyPartyDialog.setDialogResultHandler(r0);
            DialogDefinitionBase leaveDirectJoinPartyDialog = SocialPartyDialogs.getLeaveDirectJoinPartyDialog(this.mContext.getResources());
            leaveDirectJoinPartyDialog.setDialogResultHandler(r0);
            if (this.mParty.getJoinType() == SocialPartyType.JOINABLE_BY_FRIENDS) {
                leaveInviteOnlyPartyDialog = leaveDirectJoinPartyDialog;
            }
            this.mPanelApp.getDialogManager().showDialog(leaveInviteOnlyPartyDialog);
            setCurrentAsyncAction(null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$onClickLeavePartyButton$26$SocialPartyFooter(java.lang.String r5, com.oculus.systemdialog.DialogResult r6) {
        /*
            r4 = this;
            java.lang.String r6 = r6.getDialogAction()
            int r0 = r6.hashCode()
            r1 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001f
            r1 = 102846135(0x6214eb7, float:3.0338565E-35)
            if (r0 == r1) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r0 = "leave"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0029
            r6 = r2
            goto L_0x002a
        L_0x001f:
            java.lang.String r0 = "cancel"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0029
            r6 = r3
            goto L_0x002a
        L_0x0029:
            r6 = -1
        L_0x002a:
            if (r6 == 0) goto L_0x0030
            if (r6 == r3) goto L_0x002f
            goto L_0x0033
        L_0x002f:
            return r2
        L_0x0030:
            r4.leaveParty(r5)
        L_0x0033:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialPartyFooter.lambda$onClickLeavePartyButton$26$SocialPartyFooter(java.lang.String, com.oculus.systemdialog.DialogResult):boolean");
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreateGroupLaunchButtonClick */
    public void lambda$initializePrimaryActionButton$19$SocialPartyFooter() {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_CREATE, this.mPanelApp);
        if (isPublicParty()) {
            showGroupLaunchDestinationSelectionDialog(this.mParty.getSocialActivityApplicationId());
        } else {
            showGroupLaunchAppSelectionDialog();
        }
        setCurrentAsyncAction(null);
    }

    private void onSelectNewAppButtonClick() {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_SELECT_NEW_DESTINATION, this.mPanelApp);
        showGroupLaunchAppSelectionDialog();
    }

    private void onLaunchButtonClick(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LAUNCH, this.mPanelApp);
        prepareGroupLaunch(socialGroupLaunchAppDestination);
        showGroupLaunchAppLaunchDialog(socialGroupLaunchAppDestination);
    }

    /* access modifiers changed from: private */
    /* renamed from: onLaunchSoloButtonClicked */
    public void lambda$initializePrimaryActionButton$22$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LAUNCH_SOLO, this.mPanelApp);
        launchGroupLaunchSolo(socialGroupLaunchAppDestination.getGroupLaunchID());
    }

    /* access modifiers changed from: private */
    /* renamed from: onClickLaunchSocialActivity */
    public void lambda$initializePrimaryActionButton$21$SocialPartyFooter() {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_TRAVEL_TO_LAUNCH_APP, this.mPanelApp);
        String str = "{\"ovr_social_launch\":" + this.mParty.getSocialActivityDeeplink() + "}";
        this.mPanelApp.logSocialPartyEvent(SocialPartyEvent.PARTY_FOOTER_GO_TO_APP_LAUNCH, "deeplink_package_name", this.mParty.getSocialActivityApplicationPackageName(), "deeplink_app_id", this.mParty.getSocialActivityApplicationId(), "deeplink_launch_params", str);
        this.mPanelApp.actionNavigate(this.mParty.getSocialActivityApplicationPackageName(), str);
        setCurrentAsyncAction(null);
    }

    private void setGroupLaunchIcon(final String str) {
        if (str == null) {
            this.mBinding.partyFooter.icon.setPadding(6, 6, 6, 6);
            this.mBinding.partyFooter.icon.setImageDrawable(this.mContext.getDrawable(R.drawable.oc_icon_destination_filled_24_d2d2d2));
            return;
        }
        this.mBinding.partyFooter.icon.setPadding(0, 0, 0, 0);
        ImageLoader.getInstance(getContext()).loadImage(str, new ImageLoader.ImageCallbacks() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass1 */

            @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
            public void onSuccess(Bitmap bitmap) {
                SocialPartyFooter.this.mBinding.partyFooter.icon.setImageBitmap(bitmap);
            }

            @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
            public void onFailure(Throwable th) {
                String str = SocialPartyFooter.TAG;
                Log.e(str, "Could not load application icon with url: " + str);
            }
        });
    }

    private void showGroupLaunchAppSelectionDialog() {
        String str = TAG;
        Log.i(str, "getting group launch supported applications for party: " + this.mParty.getID());
        this.mPanelApp.actionNavigate(SystemUXRoute.GROUP_LAUNCH_APP_SELECTOR, "");
    }

    private void showGroupLaunchDestinationSelectionDialog(String str) {
        String str2 = TAG;
        Log.i(str2, "getting group launch supported destinations for party: " + this.mParty.getID());
        this.mPanelApp.actionNavigate(SystemUXRoute.GROUP_LAUNCH_DESTINATION_SELECTOR, StringFormatUtil.formatStrLocaleSafe("?app_id=%s", str));
    }

    private void showGroupLaunchAppLaunchDialog(final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        Log.i(TAG, "launching group launch application dialog");
        DialogDefinitionBase appLaunchDialog = SocialGroupLaunchDialogs.getAppLaunchDialog(this.mContext.getResources(), socialGroupLaunchAppDestination);
        appLaunchDialog.setDialogResultHandler(new DialogResultHandler(socialGroupLaunchAppDestination) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$n0rKl8Vg6TJtGHJjNvwX06pQLA */
            private final /* synthetic */ SocialGroupLaunchAppDestination f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialPartyFooter.this.lambda$showGroupLaunchAppLaunchDialog$27$SocialPartyFooter(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(appLaunchDialog);
        if (this.mParty.getPartyLeader().getID().equals(this.mPanelApp.getLocalUserId())) {
            this.mLaunchTimer = new Timer();
            this.mLaunchTimer.schedule(new TimerTask() {
                /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass2 */

                public void run() {
                    UiThreadExecutor.getInstance().execute(new Runnable(socialGroupLaunchAppDestination) {
                        /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$2$EaLpLWpm4DnFxD0rHP97RhhMbts */
                        private final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            SocialPartyFooter.AnonymousClass2.this.lambda$run$28$SocialPartyFooter$2(this.f$1);
                        }
                    });
                }

                public /* synthetic */ void lambda$run$28$SocialPartyFooter$2(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
                    SocialPartyFooter.this.launchGroupLaunch(socialGroupLaunchAppDestination.getGroupLaunchID());
                }
            }, GROUP_LAUNCH_DELAY_MS);
        }
    }

    public /* synthetic */ boolean lambda$showGroupLaunchAppLaunchDialog$27$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, DialogResult dialogResult) {
        if (!"cancel".equals(dialogResult.getDialogAction())) {
            return true;
        }
        if (this.mParty.getPartyLeader().getID().equals(this.mPanelApp.getLocalUserId())) {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_APP_LAUNCH_DIALOG_LEADER_VIEW_CANCEL, this.mPanelApp);
            cancelGroupLaunch();
        } else {
            SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_APP_LAUNCH_DIALOG_MEMBER_VIEW_CANCEL, this.mPanelApp);
            setGroupLaunchUserResponse(socialGroupLaunchAppDestination.getGroupLaunchID(), SocialGroupLaunchResponse.PENDING_RESPONSE);
        }
        setCurrentAsyncAction(null);
        return true;
    }

    private void cancelGroupLaunch() {
        Timer timer = this.mLaunchTimer;
        if (timer != null) {
            timer.cancel();
            this.mLaunchTimer = null;
        }
    }

    private void showRiftUsersNotSupportedDialog(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        Log.i(TAG, "launching group launch application dialog");
        DialogDefinitionBase riftUsersNotSupportedDialog = SocialGroupLaunchDialogs.getRiftUsersNotSupportedDialog(this.mContext.getResources());
        riftUsersNotSupportedDialog.setDialogResultHandler(new DialogResultHandler(socialGroupLaunchAppDestination) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$rBw9kWT2JUGgW2xyFeGFvO2VFNI */
            private final /* synthetic */ SocialGroupLaunchAppDestination f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialPartyFooter.this.lambda$showRiftUsersNotSupportedDialog$29$SocialPartyFooter(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(riftUsersNotSupportedDialog);
    }

    public /* synthetic */ boolean lambda$showRiftUsersNotSupportedDialog$29$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, DialogResult dialogResult) {
        String dialogAction = dialogResult.getDialogAction();
        if ("launch".equals(dialogAction)) {
            launchGroupLaunch(socialGroupLaunchAppDestination.getGroupLaunchID());
            setCurrentAsyncAction(null);
            return true;
        } else if (!"cancel".equals(dialogAction)) {
            return true;
        } else {
            setCurrentAsyncAction(null);
            return true;
        }
    }

    private void setGroupLaunchUserResponse(final String str, SocialGroupLaunchResponse socialGroupLaunchResponse) {
        String str2 = TAG;
        Log.d(str2, "updating viewer response for: " + str + " to: " + socialGroupLaunchResponse);
        clearSetGroupLaunchResponseAsyncQueryHandle();
        this.mSetGroupLaunchResponseAsyncQueryHandle = HorizonContentProviderHelper.setGroupLaunchResponse(this.mContext, str, socialGroupLaunchResponse, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.clearSetGroupLaunchResponseAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPanelApp socialPanelApp = SocialPartyFooter.this.mPanelApp;
                String str = SocialPartyFooter.TAG;
                SocialLogger.logError(socialPanelApp, "set_group_launch_response", str, "Failed to set group launch response for group launch:  " + str);
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_set_group_launch_user_response_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_group_launch_set_user_response_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearSetGroupLaunchResponseAsyncQueryHandle();
            }
        });
    }

    private void removePartyGroupLaunchDestination() {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_CANCEL, this.mPanelApp);
        Log.d(TAG, "removing party group launch destination");
        clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
        this.mRemovePartyGroupLaunchDestinationAsyncQueryHandle = HorizonContentProviderHelper.removePartyGroupLaunchDestination(this.mContext, this.mParty.getID(), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPanelApp socialPanelApp = SocialPartyFooter.this.mPanelApp;
                String str = SocialPartyFooter.TAG;
                SocialLogger.logError(socialPanelApp, "clear_party_group_launch", str, "Failed to clear group launch destination for party " + SocialPartyFooter.this.mParty.getID());
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_clear_destination_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_group_launch_destination_removal_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
            }
        });
    }

    private void createGroupLaunch(String str) {
        String str2 = TAG;
        Log.d(str2, "updating party group launch destination id to " + str);
        clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
        this.mSetPartyGroupLaunchDestinationAsyncQueryHandle = HorizonContentProviderHelper.setPartyGroupLaunchDestination(this.mContext, this.mParty.getID(), str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_set_destination_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_group_launch_destination_select_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
            }
        });
    }

    private void prepareGroupLaunch(final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        String str = TAG;
        Log.d(str, "prepare group launch for: " + socialGroupLaunchAppDestination.getGroupLaunchID());
        clearPrepareGroupLaunchAsyncQueryHandle();
        this.mPrepareGroupLaunchAsyncQueryHandle = HorizonContentProviderHelper.prepareGroupLaunch(this.mContext, socialGroupLaunchAppDestination.getGroupLaunchID(), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass6 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearPrepareGroupLaunchAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPanelApp socialPanelApp = SocialPartyFooter.this.mPanelApp;
                String str = SocialPartyFooter.TAG;
                SocialLogger.logError(socialPanelApp, "prepare_group_launch", str, "Failed to prepare group launch for group launch: " + socialGroupLaunchAppDestination.getGroupLaunchID());
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_prepare_group_launch_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_prepare_group_launch_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearPrepareGroupLaunchAsyncQueryHandle();
            }
        });
    }

    private void launchGroupLaunchSolo(final String str) {
        String str2 = TAG;
        Log.d(str2, "launching group launch solo for: " + str);
        clearLaunchGroupLaunchSoloAsyncQueryHandle();
        this.mLaunchGroupLaunchSoloAsyncQueryHandle = HorizonContentProviderHelper.launchGroupLaunchSolo(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass7 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearLaunchGroupLaunchSoloAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPanelApp socialPanelApp = SocialPartyFooter.this.mPanelApp;
                String str = SocialPartyFooter.TAG;
                SocialLogger.logError(socialPanelApp, "launch_group_launch_solo", str, "Failed to launch group launch solo: " + str);
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_launch_group_launch_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_launch_group_launch_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearLaunchGroupLaunchSoloAsyncQueryHandle();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void launchGroupLaunch(final String str) {
        String str2 = TAG;
        Log.d(str2, "launching group launch for: " + str);
        clearLaunchGroupLaunchAsyncQueryHandle();
        this.mLaunchGroupLaunchAsyncQueryHandle = HorizonContentProviderHelper.launchGroupLaunch(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass8 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearLaunchGroupLaunchAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPanelApp socialPanelApp = SocialPartyFooter.this.mPanelApp;
                String str = SocialPartyFooter.TAG;
                SocialLogger.logError(socialPanelApp, "launch_group_launch", str, "Failed to prepare group launch the group launch launch: " + str);
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_launch_group_launch_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_launch_group_launch_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearLaunchGroupLaunchAsyncQueryHandle();
            }
        });
    }

    private void leaveParty(String str) {
        String str2 = TAG;
        Log.d(str2, "leaving party: " + str);
        clearLeavePartyAsyncQueryHandle();
        this.mLeavePartyAsyncQueryHandle = HorizonContentProviderHelper.leaveParty(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass9 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LEAVE_PARTY, SocialPartyFooter.this.mPanelApp);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearLeavePartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialLogger.logError(SocialPartyFooter.this.mPanelApp, "leave_party", SocialPartyFooter.TAG, "Failed to leave party");
                SocialViewWarningToaster.showToast(SocialPartyFooter.this.mContext, "oculus_mobile_social_party_leave_error", SocialPartyFooter.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_leave_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearLeavePartyAsyncQueryHandle();
            }
        });
    }

    private void goToUser(SocialUser socialUser) {
        SocialDeeplinkPresence deepLink = socialUser.getDeepLink();
        this.mPanelApp.logSocialPartyEvent(SocialPartyEvent.PARTY_FOOTER_JOIN_LEADER, "deeplink_package_name", this.mParty.getSocialActivityApplicationPackageName(), "deeplink_app_id", this.mParty.getSocialActivityApplicationId(), "deeplink_launch_params", deepLink.getDeepLinkLaunchParams());
        this.mPanelApp.actionNavigate(this.mParty.getSocialActivityApplicationPackageName(), deepLink.getDeepLinkLaunchParams());
        setCurrentAsyncAction(null);
    }

    private void showPartyFooter() {
        ViewGroup.LayoutParams layoutParams = this.mBinding.partyList.getLayoutParams();
        layoutParams.height = 204;
        this.mBinding.partyList.setLayoutParams(layoutParams);
        this.mBinding.setShowPartyFooter(true);
    }

    public void hidePartyFooter() {
        ViewGroup.LayoutParams layoutParams = this.mBinding.partyList.getLayoutParams();
        layoutParams.height = 276;
        this.mBinding.partyList.setLayoutParams(layoutParams);
        this.mBinding.setShowPartyFooter(false);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentAsyncAction(AsyncSource asyncSource) {
        if (asyncSource == AsyncSource.PARTY_FOOTER_PRIMARY_BUTTON) {
            this.mBinding.partyFooter.primaryActionButton.setText("");
            this.mBinding.partyFooter.primaryActionButtonSpinner.setVisibility(0);
        } else if (asyncSource == null) {
            this.mBinding.partyFooter.primaryActionButtonSpinner.setVisibility(8);
            initialize(this.mPanelApp, this.mBinding);
        }
        this.mAsyncActionSource = asyncSource;
    }
}
