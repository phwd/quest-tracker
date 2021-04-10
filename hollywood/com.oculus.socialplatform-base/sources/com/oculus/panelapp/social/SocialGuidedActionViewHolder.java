package com.oculus.panelapp.social;

import X.AnonymousClass1Ah;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding;
import com.oculus.panelapp.social.profile.ProfileUtils;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SocialGuidedActionViewHolder extends AnonymousClass1Ah {
    public static final String FB_UPSELL_ACTION_PARAM = "send_friend_request";
    public static final String FB_UPSELL_CONTAINER_PARAM = "aui_v2_social_panel";
    public static final String FB_UPSELL_ENTRY_POINT = "aui_v2_friends_list";
    public static final String FB_UPSELL_MUST_INTERACT_PARAM = "true";
    public static final String FB_UPSELL_PRODUCT_PARAM = "friends";
    public static final String FB_UPSELL_SOURCE_PARAM = "aui_v2_friends_list";
    public static final String TAG = LoggingUtil.tag(SocialGuidedActionViewHolder.class);
    public SocialGuidedAction mAction;
    public AnytimeTabletSocialGuidedActionCardBinding mBinding;
    public Context mContext;
    public SocialPanelApp mPanelApp;
    public SocialViewModel mSocialViewModel;

    /* renamed from: com.oculus.panelapp.social.SocialGuidedActionViewHolder$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$social$SocialGuidedAction;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.panelapp.social.SocialGuidedAction[] r0 = com.oculus.panelapp.social.SocialGuidedAction.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.social.SocialGuidedActionViewHolder.AnonymousClass2.$SwitchMap$com$oculus$panelapp$social$SocialGuidedAction = r2
                com.oculus.panelapp.social.SocialGuidedAction r0 = com.oculus.panelapp.social.SocialGuidedAction.FB_LINK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.social.SocialGuidedAction r0 = com.oculus.panelapp.social.SocialGuidedAction.ADD_FRIEND     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.social.SocialGuidedAction r0 = com.oculus.panelapp.social.SocialGuidedAction.CREATE_AVATAR     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.social.SocialGuidedAction r0 = com.oculus.panelapp.social.SocialGuidedAction.SHARE_PROFILE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.panelapp.social.SocialGuidedAction r0 = com.oculus.panelapp.social.SocialGuidedAction.ADD_TO_PARTY     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.panelapp.social.SocialGuidedAction r0 = com.oculus.panelapp.social.SocialGuidedAction.FIND_EVENTS     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.SocialGuidedActionViewHolder.AnonymousClass2.<clinit>():void");
        }
    }

    public SocialGuidedActionViewHolder(AnytimeTabletSocialGuidedActionCardBinding anytimeTabletSocialGuidedActionCardBinding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialGuidedActionCardBinding.socialSuggestedActionCard);
        this.mBinding = anytimeTabletSocialGuidedActionCardBinding;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
        this.mSocialViewModel = socialPanelApp.acquireSocialViewModel();
    }

    private void initializeCTA() {
        this.mBinding.actionCtaButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$3Q7hhX34BbKuFLUGqgMc3xQieAw2 */

            public final void onClick(View view) {
                SocialGuidedActionViewHolder.this.lambda$initializeCTA$4$SocialGuidedActionViewHolder(view);
            }
        });
    }

    private void initializeHoverOverlay() {
        this.mBinding.actionCardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$1xhW8hzIG0qUC32ugmJLnZRv7Q2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SocialGuidedActionViewHolder.this.lambda$initializeHoverOverlay$0$SocialGuidedActionViewHolder(view, motionEvent);
            }
        });
    }

    public void bindData(SocialGuidedActionAdapterItem socialGuidedActionAdapterItem) {
        SocialGuidedAction socialGuidedAction = socialGuidedActionAdapterItem.mAction;
        this.mAction = socialGuidedAction;
        this.mBinding.setAction(socialGuidedAction);
        initializeHoverOverlay();
        initializeCTA();
    }

    public /* synthetic */ void lambda$initializeCTA$4$SocialGuidedActionViewHolder(View view) {
        switch (this.mAction.ordinal()) {
            case 0:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_FB_LINK);
                SocialBundleUtils.ShowUpsell(this.mPanelApp, new UpsellLoggingParameters("aui_v2_friends_list", "send_friend_request", "aui_v2_social_panel", "aui_v2_friends_list", "true", "friends"));
                return;
            case 1:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_AVATAR);
                this.mPanelApp.actionNavigate("systemux://avatareditor", "/");
                return;
            case 2:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_ADD_FRIEND);
                SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mSocialViewModel.mLinkedAccountsInfo, new Runnable() {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$1uBUZGwuVxv88K4yO3ikWix9Jw2 */

                    public final void run() {
                        SocialGuidedActionViewHolder.this.lambda$null$1$SocialGuidedActionViewHolder();
                    }
                }, new UpsellLoggingParameters("aui_v2_friends_list", "send_friend_request", "aui_v2_social_panel", "aui_v2_friends_list", "true", "friends"));
                return;
            case 3:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_SHARE_PROFILE);
                Context context = this.mContext;
                SocialPanelApp socialPanelApp = this.mPanelApp;
                SocialViewModel socialViewModel = this.mSocialViewModel;
                ProfileUtils.navigateToProfileShareSheet(context, socialPanelApp, socialViewModel.getAlias(), socialViewModel.getProfilePhotoUrl());
                return;
            case 4:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_FIND_EVENTS);
                this.mPanelApp.actionNavigate(SystemUXRoute.ALL_EVENTS, "");
                return;
            case 5:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_INVITE_DIALOG_OPEN);
                SocialViewModel socialViewModel2 = this.mSocialViewModel;
                SocialParty socialParty = socialViewModel2.mParty;
                DialogDefinitionBase partyInviteDialog = SocialPartyDialogs.getPartyInviteDialog(this.mContext.getResources(), (List) socialViewModel2.mFriends.stream().filter(new Predicate(socialParty) {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$uXBnNJACzKapOOjDQn188oeMVfg2 */
                    public final /* synthetic */ SocialParty f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return SocialGuidedActionViewHolder.this.lambda$null$2$SocialGuidedActionViewHolder(this.f$1, (SocialUser) obj);
                    }
                }).collect(Collectors.toList()));
                partyInviteDialog.mDialogResultHandler = new DialogResultHandler() {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$qPpHrUiDO_b1IyenPDOkqE1sro82 */

                    @Override // com.oculus.systemdialog.DialogResultHandler
                    public final boolean handleDialogResult(DialogResult dialogResult) {
                        return SocialGuidedActionViewHolder.this.lambda$null$3$SocialGuidedActionViewHolder(dialogResult);
                    }
                };
                this.mPanelApp.getDialogManager().showDialog(partyInviteDialog);
                return;
            default:
                return;
        }
    }

    public /* synthetic */ void lambda$null$1$SocialGuidedActionViewHolder() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_ADD_FRIENDS, "");
    }

    public /* synthetic */ boolean lambda$null$2$SocialGuidedActionViewHolder(SocialParty socialParty, SocialUser socialUser) {
        if (socialUser == null || !socialUser.getIsActive(this.mContext.getResources()) || socialParty.getUserPartyMembership(socialUser) != SocialParty.PartyMembership.NONE) {
            return false;
        }
        return true;
    }

    public /* synthetic */ boolean lambda$null$3$SocialGuidedActionViewHolder(DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        if (str.equals(SocialPartyDialogs.INVITE_ACTION)) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_INVITE_DIALOG_SEND);
            List<String> list = dialogResult.mDialogSelectedListItemIds;
            if (list != null && list.size() >= 1) {
                HorizonContentProviderHelper.inviteUsersToParty(this.mContext, dialogResult.mDialogSelectedListItemIds, this.mSocialViewModel.mParty.mID, new HorizonContentProviderHelper.MultipleIDCallback() {
                    /* class com.oculus.panelapp.social.SocialGuidedActionViewHolder.AnonymousClass1 */

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                    public void onError() {
                        SocialLogger.logError(SocialGuidedActionViewHolder.this.mContext, "invite_users_to_party", SocialGuidedActionViewHolder.TAG, "Failed to invite users to party");
                        SocialGuidedActionViewHolder.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                        Context context = SocialGuidedActionViewHolder.this.mContext;
                        SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_invite_error", context.getResources().getString(R.string.anytime_tablet_social_party_invite_failed), SocialGuidedActionViewHolder.TAG);
                    }

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
                    public void onSuccess(List<String> list) {
                        SocialGuidedActionViewHolder.this.mSocialViewModel.loadPartyData();
                    }
                });
            }
            return true;
        } else if (!str.equals("cancel")) {
            return false;
        } else {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_INVITE_DIALOG_CANCEL);
            return true;
        }
    }

    public /* synthetic */ boolean lambda$initializeHoverOverlay$0$SocialGuidedActionViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mBinding.setHoveredOverCard(true);
        } else if (motionEvent.getAction() == 10) {
            this.mBinding.setHoveredOverCard(false);
            return false;
        }
        return false;
    }
}
