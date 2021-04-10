package com.oculus.panelapp.social;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBinding;
import com.oculus.panelapp.social.profile.ProfileUtils;
import com.oculus.panelapp.social.utils.SocialBundleUtils;
import com.oculus.panelapp.social.utils.UpsellLoggingParameters;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SocialGuidedActionViewHolder extends RecyclerView.ViewHolder {
    private static final String FB_UPSELL_ACTION_PARAM = "send_friend_request";
    private static final String FB_UPSELL_CONTAINER_PARAM = "aui_v2_social_panel";
    private static final String FB_UPSELL_ENTRY_POINT = "aui_v2_friends_list";
    private static final String FB_UPSELL_MUST_INTERACT_PARAM = "true";
    private static final String FB_UPSELL_PRODUCT_PARAM = "friends";
    private static final String FB_UPSELL_SOURCE_PARAM = "aui_v2_friends_list";
    private static final String TAG = LoggingUtil.tag(SocialGuidedActionViewHolder.class);
    private SocialGuidedAction mAction;
    private AnytimeTabletSocialGuidedActionCardBinding mBinding;
    private Context mContext;
    private SocialPanelApp mPanelApp;
    private SocialViewModel mSocialViewModel = this.mPanelApp.acquireSocialViewModel();

    public SocialGuidedActionViewHolder(AnytimeTabletSocialGuidedActionCardBinding anytimeTabletSocialGuidedActionCardBinding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialGuidedActionCardBinding.socialSuggestedActionCard);
        this.mBinding = anytimeTabletSocialGuidedActionCardBinding;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
    }

    public void bindData(SocialGuidedActionAdapterItem socialGuidedActionAdapterItem) {
        this.mAction = socialGuidedActionAdapterItem.getAction();
        this.mBinding.setAction(this.mAction);
        initializeHoverOverlay();
        initializeCTA();
    }

    private void initializeHoverOverlay() {
        this.mBinding.actionCardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$YliK73yd6HYQcFhDoJGPpCgbnTk */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SocialGuidedActionViewHolder.this.lambda$initializeHoverOverlay$9$SocialGuidedActionViewHolder(view, motionEvent);
            }
        });
    }

    public /* synthetic */ boolean lambda$initializeHoverOverlay$9$SocialGuidedActionViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mBinding.setHoveredOverCard(true);
        } else if (motionEvent.getAction() == 10) {
            this.mBinding.setHoveredOverCard(false);
        }
        return false;
    }

    private void initializeCTA() {
        this.mBinding.actionCtaButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$fdOn4bpqe6EpM1FDaGyUIdCSHt8 */

            public final void onClick(View view) {
                SocialGuidedActionViewHolder.this.lambda$initializeCTA$13$SocialGuidedActionViewHolder(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCTA$13$SocialGuidedActionViewHolder(View view) {
        switch (this.mAction) {
            case FB_LINK:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_FB_LINK);
                SocialBundleUtils.ShowUpsell(this.mPanelApp, new UpsellLoggingParameters("aui_v2_friends_list", "send_friend_request", "aui_v2_social_panel", "aui_v2_friends_list", "true", "friends"));
                return;
            case ADD_FRIEND:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_ADD_FRIEND);
                SocialBundleUtils.PerformActionIfLinked(this.mPanelApp, this.mSocialViewModel.getLinkedAccountsInfo(), new Runnable() {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$1hc43jFgt06rDT71kf0mZSdCazw */

                    public final void run() {
                        SocialGuidedActionViewHolder.this.lambda$null$10$SocialGuidedActionViewHolder();
                    }
                }, new UpsellLoggingParameters("aui_v2_friends_list", "send_friend_request", "aui_v2_social_panel", "aui_v2_friends_list", "true", "friends"));
                return;
            case CREATE_AVATAR:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_AVATAR);
                this.mPanelApp.actionNavigate("systemux://avatareditor", "/");
                return;
            case SHARE_PROFILE:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_SHARE_PROFILE);
                ProfileUtils.navigateToProfileShareSheet(this.mContext, this.mPanelApp, this.mSocialViewModel.getAlias(), this.mSocialViewModel.getProfilePhotoUrl());
                return;
            case ADD_TO_PARTY:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_INVITE_DIALOG_OPEN);
                SocialParty partyData = this.mSocialViewModel.getPartyData();
                DialogDefinitionBase partyInviteDialog = SocialPartyDialogs.getPartyInviteDialog(this.mContext.getResources(), (List) this.mSocialViewModel.getFriends().stream().filter(new Predicate(partyData) {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$40qyqaiIGvNii98i2azIVxJOJ_8 */
                    private final /* synthetic */ SocialParty f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return SocialGuidedActionViewHolder.this.lambda$null$11$SocialGuidedActionViewHolder(this.f$1, (SocialUser) obj);
                    }
                }).collect(Collectors.toList()));
                partyInviteDialog.setDialogResultHandler(new DialogResultHandler() {
                    /* class com.oculus.panelapp.social.$$Lambda$SocialGuidedActionViewHolder$j86pClWLe8JYkH2mMFwbE98xUTU */

                    @Override // com.oculus.systemdialog.DialogResultHandler
                    public final boolean handleDialogResult(DialogResult dialogResult) {
                        return SocialGuidedActionViewHolder.this.lambda$null$12$SocialGuidedActionViewHolder(dialogResult);
                    }
                });
                this.mPanelApp.getDialogManager().showDialog(partyInviteDialog);
                return;
            case FIND_EVENTS:
                this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_GUIDED_ACTION_FIND_EVENTS);
                this.mPanelApp.actionNavigate(SystemUXRoute.ALL_EVENTS, "");
                return;
            default:
                return;
        }
    }

    public /* synthetic */ void lambda$null$10$SocialGuidedActionViewHolder() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_ADD_FRIENDS, "");
    }

    public /* synthetic */ boolean lambda$null$11$SocialGuidedActionViewHolder(SocialParty socialParty, SocialUser socialUser) {
        return socialUser != null && socialUser.getIsActive(this.mContext.getResources()) && socialParty.getUserPartyMembership(socialUser) == SocialParty.PartyMembership.NONE;
    }

    public /* synthetic */ boolean lambda$null$12$SocialGuidedActionViewHolder(DialogResult dialogResult) {
        dialogResult.getDialogAction();
        if (dialogResult.getDialogAction().equals(SocialPartyDialogs.INVITE_ACTION)) {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_INVITE_DIALOG_SEND);
            List<String> dialogSelectedListItemIds = dialogResult.getDialogSelectedListItemIds();
            if (dialogSelectedListItemIds != null && dialogSelectedListItemIds.size() >= 1) {
                HorizonContentProviderHelper.inviteUsersToParty(this.mContext, dialogResult.getDialogSelectedListItemIds(), this.mSocialViewModel.getPartyData().getID(), new HorizonContentProviderHelper.MultipleIDCallback() {
                    /* class com.oculus.panelapp.social.SocialGuidedActionViewHolder.AnonymousClass1 */

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
                    public void onSuccess(List<String> list) {
                        SocialGuidedActionViewHolder.this.mSocialViewModel.loadPartyData();
                    }

                    @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                    public void onError() {
                        SocialLogger.logError(SocialGuidedActionViewHolder.this.mPanelApp, "invite_users_to_party", SocialGuidedActionViewHolder.TAG, "Failed to invite users to party");
                        SocialGuidedActionViewHolder.this.mPanelApp.acquireSocialViewModel().loadPartyData();
                        SocialViewWarningToaster.showToast(SocialGuidedActionViewHolder.this.mContext, "oculus_mobile_social_party_invite_error", SocialGuidedActionViewHolder.this.mContext.getResources().getString(R.string.anytime_tablet_social_party_invite_failed), SocialGuidedActionViewHolder.TAG);
                    }
                });
            }
            return true;
        } else if (!dialogResult.getDialogAction().equals("cancel")) {
            return false;
        } else {
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PARTIES_INVITE_DIALOG_CANCEL);
            return true;
        }
    }
}
