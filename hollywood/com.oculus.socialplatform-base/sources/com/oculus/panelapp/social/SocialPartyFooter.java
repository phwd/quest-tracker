package com.oculus.panelapp.social;

import X.AnonymousClass006;
import X.AnonymousClass1uW;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizon.notifications.core.NotificationsIntentCreationUtils;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
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
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.SocialPartyFooter;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding;
import com.oculus.panelapp.social.logging.SocialPartyEvent;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.socialplatform.R;
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
    public static final long GROUP_LAUNCH_DELAY_MS = 10000;
    public static final int PARTY_LIST_HEIGHT_WITHOUT_FOOTER = 276;
    public static final int PARTY_LIST_HEIGHT_WITH_FOOTER = 204;
    public static final String TAG = LoggingUtil.tag(SocialPartyFooter.class);
    @Nullable
    public AsyncSource mAsyncActionSource = null;
    public AnytimeTabletSocialViewV2Binding mBinding;
    public final Context mContext;
    @Nullable
    public AsyncQueryHandle mLaunchGroupLaunchAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mLaunchGroupLaunchSoloAsyncQueryHandle;
    public Timer mLaunchTimer;
    @Nullable
    public AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    public SocialPanelApp mPanelApp;
    public SocialParty mParty;
    public SocialUser mPartyLeader;
    @Nullable
    public AsyncQueryHandle mPrepareGroupLaunchAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mRemovePartyGroupLaunchDestinationAsyncQueryHandle;
    public final Resources mResources;
    @Nullable
    public AsyncQueryHandle mSetGroupLaunchResponseAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mSetPartyGroupLaunchDestinationAsyncQueryHandle;
    public SocialViewModel mSocialViewModel;

    public enum AsyncSource {
        PARTY_FOOTER_PRIMARY_BUTTON,
        PARTY_FOOTER_SECONDARY_BUTTON
    }

    private void cancelGroupLaunch() {
        Timer timer = this.mLaunchTimer;
        if (timer != null) {
            timer.cancel();
            this.mLaunchTimer = null;
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
    private void clearLeavePartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mLeavePartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mLeavePartyAsyncQueryHandle = null;
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
    private void clearRemovePartyGroupLaunchDestinationAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mRemovePartyGroupLaunchDestinationAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mRemovePartyGroupLaunchDestinationAsyncQueryHandle = null;
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

    private void disablePrimaryActionButton(String str) {
        this.mBinding.partyFooter.setPrimaryActionButton(str);
        this.mBinding.partyFooter.primaryActionButton.button.setVisibility(0);
        this.mBinding.partyFooter.primaryActionButton.button.setEnabled(false);
    }

    private void goToUser(SocialUser socialUser) {
        SocialDeeplinkPresence socialDeeplinkPresence = socialUser.mDeepLink;
        SocialPanelApp socialPanelApp = this.mPanelApp;
        SocialPartyEvent socialPartyEvent = SocialPartyEvent.PARTY_FOOTER_JOIN_LEADER;
        SocialParty socialParty = this.mParty;
        socialPanelApp.logSocialPartyEvent(socialPartyEvent, "deeplink_package_name", socialParty.mSocialActivityApplicationPackageName, "deeplink_app_id", socialParty.mSocialActivityApplicationId, "deeplink_launch_params", socialDeeplinkPresence.mDeepLinkLaunchParams);
        this.mPanelApp.actionNavigate(this.mParty.mSocialActivityApplicationPackageName, socialDeeplinkPresence.mDeepLinkLaunchParams);
        setCurrentAsyncAction(null);
    }

    private void hideButton(ShellButton shellButton) {
        shellButton.setVisibility(8);
    }

    private void initializeChangeDestinationButton() {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination;
        if (!this.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId()) || isPublicParty() || socialGroupLaunchAppDestination == null) {
            this.mBinding.partyFooter.changeDestinationButton.setVisibility(8);
            return;
        }
        this.mBinding.partyFooter.changeDestinationButton.setVisibility(0);
        OCButton oCButton = this.mBinding.partyFooter.changeDestinationButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$CGqf15mG6HMmfkRE3hITvDFe3io2 */

            public final void onClick(View view) {
                SocialPartyFooter.this.lambda$initializeChangeDestinationButton$6$SocialPartyFooter(view);
            }
        });
    }

    private void initializeDestinationInfo() {
        OCTextView oCTextView;
        String str;
        SocialParty socialParty = this.mParty;
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination;
        if (socialGroupLaunchAppDestination != null) {
            this.mBinding.partyFooter.applicationName.setText(socialGroupLaunchAppDestination.mAppDisplayName);
            oCTextView = this.mBinding.partyFooter.destinationName;
            str = socialGroupLaunchAppDestination.mDestinationDisplayName;
        } else if (!socialParty.mSocialActivityId.isEmpty()) {
            this.mBinding.partyFooter.applicationName.setText(socialParty.mSocialActivityApplicationDisplayName);
            oCTextView = this.mBinding.partyFooter.destinationName;
            str = this.mParty.mSocialActivityTitle;
        } else {
            shouldShowDestinationInfo(false);
            return;
        }
        oCTextView.setText(str);
        shouldShowDestinationInfo(true);
    }

    private void initializeIcon() {
        String str;
        SocialParty socialParty = this.mParty;
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination;
        if (socialGroupLaunchAppDestination != null) {
            str = socialGroupLaunchAppDestination.mImageUrl;
        } else {
            str = socialParty.mSocialActivityIconUrl;
            if (str.isEmpty()) {
                setGroupLaunchIcon(null);
                return;
            }
        }
        setGroupLaunchIcon(str);
    }

    private void initializePrimaryActionButton() {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination;
        if (socialGroupLaunchAppDestination == null || getIsAppInstalled(socialGroupLaunchAppDestination.mAppID)) {
            SocialParty socialParty = this.mParty;
            if (socialParty.mSocialActivityId.isEmpty() || getIsAppInstalled(socialParty.mSocialActivityApplicationId)) {
                if (this.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId())) {
                    if (socialGroupLaunchAppDestination != null) {
                        setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable(socialGroupLaunchAppDestination) {
                            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$VC6IypLKjQUiWtFkf2UxIl2hmVo2 */
                            public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                SocialPartyFooter.this.lambda$initializePrimaryActionButton$3$SocialPartyFooter(this.f$1);
                            }
                        });
                        return;
                    } else if (!this.mParty.mSocialActivityId.isEmpty()) {
                        setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable() {
                            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$zdAazqQeWZ_ERtzgbqgDSRYliR82 */

                            public final void run() {
                                SocialPartyFooter.this.lambda$initializePrimaryActionButton$1$SocialPartyFooter();
                            }
                        });
                        return;
                    } else {
                        setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_propose_button), new Runnable() {
                            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$CtecRb_CklGhdGECAyG90t8x28g2 */

                            public final void run() {
                                SocialPartyFooter.this.lambda$initializePrimaryActionButton$2$SocialPartyFooter();
                            }
                        });
                        return;
                    }
                } else if (socialGroupLaunchAppDestination != null) {
                    setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable(socialGroupLaunchAppDestination) {
                        /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$kq5Spv9lueGLhIiGxPlgsQE4E2 */
                        public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            SocialPartyFooter.this.lambda$initializePrimaryActionButton$5$SocialPartyFooter(this.f$1);
                        }
                    });
                    return;
                } else if (!this.mParty.mSocialActivityId.isEmpty()) {
                    setPrimaryActionButton(true, this.mResources.getString(R.string.anytime_tablet_social_party_travel_together_launch_solo_button), new Runnable() {
                        /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$qmXIEagPZQSoypuwe43z7E3sAw2 */

                        public final void run() {
                            SocialPartyFooter.this.lambda$initializePrimaryActionButton$4$SocialPartyFooter();
                        }
                    });
                    return;
                } else {
                    hideButton(this.mBinding.partyFooter.primaryActionButton.button);
                    return;
                }
            }
        }
        setPrimaryActionButton(false, this.mResources.getString(R.string.anytime_tablet_social_party_group_launch_view_details_button), new Runnable(socialGroupLaunchAppDestination) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$HNEybD5TkCihtOEQi8wciRpm4Ko2 */
            public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SocialPartyFooter.this.lambda$initializePrimaryActionButton$0$SocialPartyFooter(this.f$1);
            }
        });
    }

    private void initializeSharePartyButton() {
        String str = this.mSocialViewModel.mParty.mID;
        this.mBinding.partyFooter.sharePartyButton.button.setBackground(this.mContext.getDrawable(R.drawable.ocbutton_secondary));
        this.mBinding.partyFooter.sharePartyButton.button.clearOnClickListener();
        this.mBinding.partyFooter.sharePartyButton.button.setOnClickListener(new View.OnClickListener(str) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$4gRlwCxIHb2sq2g_9Bmr42xHnI2 */
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialPartyFooter.this.lambda$initializeSharePartyButton$7$SocialPartyFooter(this.f$1, view);
            }
        });
    }

    private void initializeStatusText() {
        AnytimeTabletSocialPartyFooterBinding anytimeTabletSocialPartyFooterBinding;
        Resources resources;
        int i;
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination;
        if (!this.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId())) {
            if (socialGroupLaunchAppDestination == null) {
                if (this.mParty.mSocialActivityId.isEmpty()) {
                    anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
                    resources = this.mResources;
                    i = R.string.anytime_tablet_social_party_group_launch_member_status_text_destinationless;
                }
                anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
                resources = this.mResources;
                i = R.string.anytime_tablet_social_party_travel_to_status_text;
            }
            anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
            resources = this.mResources;
            i = R.string.anytime_tablet_social_party_group_launch_status_text_with_others;
        } else if (socialGroupLaunchAppDestination == null) {
            if (this.mParty.mSocialActivityId.isEmpty()) {
                anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
                resources = this.mResources;
                i = R.string.anytime_tablet_social_party_group_launch_status_text_destinationless;
            }
            anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
            resources = this.mResources;
            i = R.string.anytime_tablet_social_party_travel_to_status_text;
        } else {
            if (this.mParty.getSize() == 1) {
                anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
                resources = this.mResources;
                i = R.string.anytime_tablet_social_party_group_launch_status_text_leader_alone;
            }
            anytimeTabletSocialPartyFooterBinding = this.mBinding.partyFooter;
            resources = this.mResources;
            i = R.string.anytime_tablet_social_party_group_launch_status_text_with_others;
        }
        anytimeTabletSocialPartyFooterBinding.setStatusText(resources.getString(i));
    }

    private boolean isPartyLeaderInAssociatedApp() {
        SocialDeeplinkPresence socialDeeplinkPresence = this.mPartyLeader.mDeepLink;
        if (socialDeeplinkPresence == null || !socialDeeplinkPresence.mAppID.equals(this.mParty.mSocialActivityApplicationId)) {
            return false;
        }
        return true;
    }

    private boolean isPublicParty() {
        SocialParty socialParty = this.mParty;
        String str = socialParty.mSocialActivityId;
        if (str == null || str.isEmpty() || !socialParty.mJoinType.equals(SocialPartyType.OPEN)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: onClickLaunchSocialActivity */
    public void lambda$initializePrimaryActionButton$4$SocialPartyFooter() {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_TRAVEL_TO_LAUNCH_APP);
        SocialParty socialParty = this.mParty;
        String A09 = AnonymousClass006.A09("{\"ovr_social_launch\":", socialParty.mSocialActivityDeeplink, "}");
        this.mPanelApp.logSocialPartyEvent(SocialPartyEvent.PARTY_FOOTER_GO_TO_APP_LAUNCH, "deeplink_package_name", socialParty.mSocialActivityApplicationPackageName, "deeplink_app_id", socialParty.mSocialActivityApplicationId, "deeplink_launch_params", A09);
        this.mPanelApp.actionNavigate(this.mParty.mSocialActivityApplicationPackageName, A09);
        setCurrentAsyncAction(null);
    }

    private void onClickLeavePartyButton(String str) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LEAVE_PARTY);
        if (this.mParty != null) {
            $$Lambda$SocialPartyFooter$97Mq6kXQNfoNY8nCjuNN8tN3Y2 r1 = new DialogResultHandler(str) {
                /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$97Mq6kXQNfoNY8nCjuNN8tN3Y2 */
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SocialPartyFooter.this.lambda$onClickLeavePartyButton$9$SocialPartyFooter(this.f$1, dialogResult);
                }
            };
            DialogDefinitionBase leaveInviteOnlyPartyDialog = SocialPartyDialogs.getLeaveInviteOnlyPartyDialog(this.mContext.getResources());
            leaveInviteOnlyPartyDialog.mDialogResultHandler = r1;
            DialogDefinitionBase leaveDirectJoinPartyDialog = SocialPartyDialogs.getLeaveDirectJoinPartyDialog(this.mContext.getResources());
            leaveDirectJoinPartyDialog.mDialogResultHandler = r1;
            if (this.mParty.mJoinType == SocialPartyType.JOINABLE_BY_FRIENDS) {
                leaveInviteOnlyPartyDialog = leaveDirectJoinPartyDialog;
            }
            this.mPanelApp.getDialogManager().showDialog(leaveInviteOnlyPartyDialog);
            setCurrentAsyncAction(null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreateGroupLaunchButtonClick */
    public void lambda$initializePrimaryActionButton$2$SocialPartyFooter() {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_CREATE);
        if (isPublicParty()) {
            showGroupLaunchDestinationSelectionDialog(this.mParty.mSocialActivityApplicationId);
        } else {
            showGroupLaunchAppSelectionDialog();
        }
        setCurrentAsyncAction(null);
    }

    private void onLaunchButtonClick(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LAUNCH);
        prepareGroupLaunch(socialGroupLaunchAppDestination);
        showGroupLaunchAppLaunchDialog(socialGroupLaunchAppDestination);
    }

    /* access modifiers changed from: private */
    /* renamed from: onLaunchSoloButtonClicked */
    public void lambda$initializePrimaryActionButton$5$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LAUNCH_SOLO);
        launchGroupLaunchSolo(socialGroupLaunchAppDestination.mGroupLaunchID);
    }

    private void onSelectNewAppButtonClick() {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_SELECT_NEW_DESTINATION);
        showGroupLaunchAppSelectionDialog();
    }

    private void onViewAppDetailsButtonClick(String str) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_VIEW_APP_DETAILS);
        this.mPanelApp.actionNavigate(SystemUXRoute.STORE, AnonymousClass006.A07(DeeplinkIntentUtils.HOME_LINK_PDP, str));
        setCurrentAsyncAction(null);
    }

    private void removePartyGroupLaunchDestination() {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_CANCEL);
        clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
        this.mRemovePartyGroupLaunchDestinationAsyncQueryHandle = HorizonContentProviderHelper.removePartyGroupLaunchDestination(this.mContext, this.mParty.mID, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter socialPartyFooter = SocialPartyFooter.this;
                SocialLogger.logError(socialPartyFooter.mContext, "clear_party_group_launch", SocialPartyFooter.TAG, AnonymousClass006.A07("Failed to clear group launch destination for party ", socialPartyFooter.mParty.mID));
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_clear_destination_error", context.getResources().getString(R.string.anytime_tablet_social_group_launch_destination_removal_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.clearRemovePartyGroupLaunchDestinationAsyncQueryHandle();
            }
        });
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
            public void onFailure(Throwable th) {
                Log.e(SocialPartyFooter.TAG, AnonymousClass006.A07("Could not load application icon with url: ", str));
            }

            @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
            public void onSuccess(Bitmap bitmap) {
                SocialPartyFooter.this.mBinding.partyFooter.icon.setImageBitmap(bitmap);
            }
        });
    }

    private void setPrimaryActionButton(boolean z, String str, Runnable runnable) {
        ShellButton shellButton;
        Context context;
        int i;
        if (z) {
            shellButton = this.mBinding.partyFooter.primaryActionButton.button;
            context = this.mContext;
            i = R.drawable.ocbutton_primary;
        } else {
            shellButton = this.mBinding.partyFooter.primaryActionButton.button;
            context = this.mContext;
            i = R.drawable.ocbutton_secondary;
        }
        shellButton.setBackground(context.getDrawable(i));
        this.mBinding.partyFooter.setPrimaryActionButton(str);
        this.mBinding.partyFooter.primaryActionButton.button.clearOnClickListener();
        this.mBinding.partyFooter.primaryActionButton.button.setOnClickListener(new View.OnClickListener(runnable) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$Q3ABDpH7TnE8C0bFy36L1tqWWIY2 */
            public final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialPartyFooter.this.lambda$setPrimaryActionButton$8$SocialPartyFooter(this.f$1, view);
            }
        });
        this.mBinding.partyFooter.primaryActionButton.button.setEnabled(true);
        this.mBinding.partyFooter.primaryActionButton.button.setVisibility(0);
    }

    private void shouldShowDestinationInfo(boolean z) {
        OCTextView oCTextView = this.mBinding.partyFooter.applicationName;
        int i = 0;
        int i2 = 8;
        if (z) {
            i2 = 0;
        }
        oCTextView.setVisibility(i2);
        OCTextView oCTextView2 = this.mBinding.partyFooter.destinationInfoSeparator;
        int i3 = 8;
        if (z) {
            i3 = 0;
        }
        oCTextView2.setVisibility(i3);
        OCTextView oCTextView3 = this.mBinding.partyFooter.destinationName;
        if (!z) {
            i = 8;
        }
        oCTextView3.setVisibility(i);
    }

    private void showGroupLaunchAppLaunchDialog(final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        DialogDefinitionBase appLaunchDialog = SocialGroupLaunchDialogs.getAppLaunchDialog(this.mContext.getResources(), socialGroupLaunchAppDestination);
        appLaunchDialog.mDialogResultHandler = new DialogResultHandler(socialGroupLaunchAppDestination) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$I_lfeXn_EbLUS8VtK3UA0cV2HaI2 */
            public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialPartyFooter.this.lambda$showGroupLaunchAppLaunchDialog$10$SocialPartyFooter(this.f$1, dialogResult);
            }
        };
        this.mPanelApp.getDialogManager().showDialog(appLaunchDialog);
        if (this.mParty.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId())) {
            Timer timer = new Timer();
            this.mLaunchTimer = timer;
            timer.schedule(new TimerTask() {
                /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass2 */

                public /* synthetic */ void lambda$run$0$SocialPartyFooter$2(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
                    SocialPartyFooter.this.launchGroupLaunch(socialGroupLaunchAppDestination.mGroupLaunchID);
                }

                public void run() {
                    UiThreadExecutor.getInstance().execute(new Runnable(socialGroupLaunchAppDestination) {
                        /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$2$ZlYibJ4dXb2GTZZd3FKFfOkF82 */
                        public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            SocialPartyFooter.AnonymousClass2.this.lambda$run$0$SocialPartyFooter$2(this.f$1);
                        }
                    });
                }
            }, 10000);
        }
    }

    private void showGroupLaunchAppSelectionDialog() {
        this.mPanelApp.actionNavigate(SystemUXRoute.GROUP_LAUNCH_APP_SELECTOR, "");
    }

    private void showGroupLaunchDestinationSelectionDialog(String str) {
        this.mPanelApp.actionNavigate(SystemUXRoute.GROUP_LAUNCH_DESTINATION_SELECTOR, StringFormatUtil.formatStrLocaleSafe("?app_id=%s", str));
    }

    private void showPartyFooter() {
        ViewGroup.LayoutParams layoutParams = this.mBinding.partyList.getLayoutParams();
        layoutParams.height = 204;
        this.mBinding.partyList.setLayoutParams(layoutParams);
        this.mBinding.setShowPartyFooter(true);
    }

    private void showRiftUsersNotSupportedDialog(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        DialogDefinitionBase riftUsersNotSupportedDialog = SocialGroupLaunchDialogs.getRiftUsersNotSupportedDialog(this.mContext.getResources());
        riftUsersNotSupportedDialog.mDialogResultHandler = new DialogResultHandler(socialGroupLaunchAppDestination) {
            /* class com.oculus.panelapp.social.$$Lambda$SocialPartyFooter$h0T5RvOJduhDmnFJMKc80WHA9Y2 */
            public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialPartyFooter.this.lambda$showRiftUsersNotSupportedDialog$11$SocialPartyFooter(this.f$1, dialogResult);
            }
        };
        this.mPanelApp.getDialogManager().showDialog(riftUsersNotSupportedDialog);
    }

    public boolean getIsAppInstalled(String str) {
        for (App app : HorizonUtil.getApplications(this.mContext)) {
            if (app.id.equals(str) && app.status.equals(AppStatus.INSTALLED)) {
                return true;
            }
        }
        return false;
    }

    public void hidePartyFooter() {
        ViewGroup.LayoutParams layoutParams = this.mBinding.partyList.getLayoutParams();
        layoutParams.height = PARTY_LIST_HEIGHT_WITHOUT_FOOTER;
        this.mBinding.partyList.setLayoutParams(layoutParams);
        this.mBinding.setShowPartyFooter(false);
    }

    public void initialize(SocialPanelApp socialPanelApp, AnonymousClass1uW r6) {
        SocialParty socialParty;
        this.mPanelApp = socialPanelApp;
        this.mBinding = (AnytimeTabletSocialViewV2Binding) r6;
        SocialViewModel acquireSocialViewModel = socialPanelApp.acquireSocialViewModel();
        this.mSocialViewModel = acquireSocialViewModel;
        this.mParty = acquireSocialViewModel.mParty;
        this.mBinding.partyFooter.setSocialViewModel(acquireSocialViewModel);
        this.mPanelApp.getLocalUserId();
        if (!this.mPanelApp.isGKEnabled(Gatekeeper.OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY) || (socialParty = this.mParty) == null) {
            hidePartyFooter();
            return;
        }
        ListIterator<SocialUser> listIterator = socialParty.mPartyMembers.listIterator();
        while (listIterator.hasNext()) {
            SocialUser next = listIterator.next();
            if (next.mID.equals(this.mParty.mPartyLeader.mID)) {
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

    public /* synthetic */ void lambda$initializePrimaryActionButton$0$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        String str;
        if (socialGroupLaunchAppDestination != null) {
            str = socialGroupLaunchAppDestination.mAppID;
        } else {
            str = this.mParty.mSocialActivityApplicationId;
        }
        onViewAppDetailsButtonClick(str);
    }

    public /* synthetic */ void lambda$initializeSharePartyButton$7$SocialPartyFooter(String str, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_SHARE_PARTY);
        SocialPartyUtils.navigateToSocialPartyShareSheet(this.mPanelApp, str);
    }

    public /* synthetic */ boolean lambda$onClickLeavePartyButton$9$SocialPartyFooter(String str, DialogResult dialogResult) {
        String str2 = dialogResult.mDialogAction;
        int hashCode = str2.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 102846135 && str2.equals(SocialPartyDialogs.LEAVE_ACTION)) {
                leaveParty(str);
            }
        } else if (str2.equals("cancel")) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$setPrimaryActionButton$8$SocialPartyFooter(Runnable runnable, View view) {
        setCurrentAsyncAction(AsyncSource.PARTY_FOOTER_PRIMARY_BUTTON);
        runnable.run();
    }

    public /* synthetic */ boolean lambda$showGroupLaunchAppLaunchDialog$10$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, DialogResult dialogResult) {
        if (!"cancel".equals(dialogResult.mDialogAction)) {
            return true;
        }
        if (this.mParty.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId())) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_APP_LAUNCH_DIALOG_LEADER_VIEW_CANCEL);
            cancelGroupLaunch();
        } else {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_APP_LAUNCH_DIALOG_MEMBER_VIEW_CANCEL);
            setGroupLaunchUserResponse(socialGroupLaunchAppDestination.mGroupLaunchID, SocialGroupLaunchResponse.PENDING_RESPONSE);
        }
        setCurrentAsyncAction(null);
        return true;
    }

    public /* synthetic */ boolean lambda$showRiftUsersNotSupportedDialog$11$SocialPartyFooter(SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        if (NotificationsIntentCreationUtils.LAUNCH_CTA.equals(str)) {
            launchGroupLaunch(socialGroupLaunchAppDestination.mGroupLaunchID);
        } else if (!"cancel".equals(str)) {
            return true;
        }
        setCurrentAsyncAction(null);
        return true;
    }

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

    public SocialPartyFooter(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mResources = context.getResources();
    }

    private void createGroupLaunch(String str) {
        clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
        this.mSetPartyGroupLaunchDestinationAsyncQueryHandle = HorizonContentProviderHelper.setPartyGroupLaunchDestination(this.mContext, this.mParty.mID, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_set_destination_error", context.getResources().getString(R.string.anytime_tablet_social_group_launch_destination_select_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.clearSetPartyGroupLaunchDestinationAsyncQueryHandle();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void launchGroupLaunch(final String str) {
        clearLaunchGroupLaunchAsyncQueryHandle();
        this.mLaunchGroupLaunchAsyncQueryHandle = HorizonContentProviderHelper.launchGroupLaunch(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass8 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialLogger.logError(SocialPartyFooter.this.mContext, "launch_group_launch", SocialPartyFooter.TAG, AnonymousClass006.A07("Failed to prepare group launch the group launch launch: ", str));
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_launch_group_launch_error", context.getResources().getString(R.string.anytime_tablet_social_launch_group_launch_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearLaunchGroupLaunchAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearLaunchGroupLaunchAsyncQueryHandle();
            }
        });
    }

    private void launchGroupLaunchSolo(final String str) {
        clearLaunchGroupLaunchSoloAsyncQueryHandle();
        this.mLaunchGroupLaunchSoloAsyncQueryHandle = HorizonContentProviderHelper.launchGroupLaunchSolo(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass7 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialLogger.logError(SocialPartyFooter.this.mContext, "launch_group_launch_solo", SocialPartyFooter.TAG, AnonymousClass006.A07("Failed to launch group launch solo: ", str));
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_launch_group_launch_error", context.getResources().getString(R.string.anytime_tablet_social_launch_group_launch_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearLaunchGroupLaunchSoloAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearLaunchGroupLaunchSoloAsyncQueryHandle();
            }
        });
    }

    private void leaveParty(String str) {
        clearLeavePartyAsyncQueryHandle();
        this.mLeavePartyAsyncQueryHandle = HorizonContentProviderHelper.leaveParty(this.mContext, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass9 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialLogger.logError(SocialPartyFooter.this.mContext, "leave_party", SocialPartyFooter.TAG, "Failed to leave party");
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_leave_error", context.getResources().getString(R.string.anytime_tablet_social_party_leave_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearLeavePartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_GROUP_LAUNCH_LEAVE_PARTY);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearLeavePartyAsyncQueryHandle();
            }
        });
    }

    private void prepareGroupLaunch(final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        clearPrepareGroupLaunchAsyncQueryHandle();
        this.mPrepareGroupLaunchAsyncQueryHandle = HorizonContentProviderHelper.prepareGroupLaunch(this.mContext, socialGroupLaunchAppDestination.mGroupLaunchID, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass6 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialLogger.logError(SocialPartyFooter.this.mContext, "prepare_group_launch", SocialPartyFooter.TAG, AnonymousClass006.A07("Failed to prepare group launch for group launch: ", socialGroupLaunchAppDestination.mGroupLaunchID));
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_prepare_group_launch_error", context.getResources().getString(R.string.anytime_tablet_social_prepare_group_launch_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearPrepareGroupLaunchAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.clearPrepareGroupLaunchAsyncQueryHandle();
            }
        });
    }

    private void setGroupLaunchUserResponse(final String str, SocialGroupLaunchResponse socialGroupLaunchResponse) {
        clearSetGroupLaunchResponseAsyncQueryHandle();
        this.mSetGroupLaunchResponseAsyncQueryHandle = HorizonContentProviderHelper.setGroupLaunchResponse(this.mContext, str, socialGroupLaunchResponse, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.social.SocialPartyFooter.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialLogger.logError(SocialPartyFooter.this.mContext, "set_group_launch_response", SocialPartyFooter.TAG, AnonymousClass006.A07("Failed to set group launch response for group launch:  ", str));
                Context context = SocialPartyFooter.this.mContext;
                SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_set_group_launch_user_response_error", context.getResources().getString(R.string.anytime_tablet_social_group_launch_set_user_response_failed), SocialPartyFooter.TAG);
                SocialPartyFooter.this.clearSetGroupLaunchResponseAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                SocialPartyFooter.this.setCurrentAsyncAction(null);
                SocialPartyFooter.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                SocialPartyFooter.this.clearSetGroupLaunchResponseAsyncQueryHandle();
            }
        });
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

    public /* synthetic */ void lambda$initializeChangeDestinationButton$6$SocialPartyFooter(View view) {
        onSelectNewAppButtonClick();
    }
}
