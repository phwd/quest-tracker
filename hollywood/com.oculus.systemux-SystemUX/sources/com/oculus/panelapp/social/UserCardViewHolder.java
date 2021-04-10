package com.oculus.panelapp.social;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.social.SocialUserRecyclerViewHolder;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.panelapp.social.actions.AddFriend;
import com.oculus.panelapp.social.actions.AddToParty;
import com.oculus.panelapp.social.actions.Block;
import com.oculus.panelapp.social.actions.CancelPartyInvite;
import com.oculus.panelapp.social.actions.Chat;
import com.oculus.panelapp.social.actions.CreatePartyWith;
import com.oculus.panelapp.social.actions.Goto;
import com.oculus.panelapp.social.actions.JoinParty;
import com.oculus.panelapp.social.actions.KickFromParty;
import com.oculus.panelapp.social.actions.PerPersonMute;
import com.oculus.panelapp.social.actions.Report;
import com.oculus.panelapp.social.actions.SelfMute;
import com.oculus.panelapp.social.actions.SocialUserAction;
import com.oculus.panelapp.social.actions.SocialUserActionActionHandler;
import com.oculus.panelapp.social.actions.UnFriend;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public class UserCardViewHolder extends SocialUserRecyclerViewHolder implements ISpeakerIndicatorListener {
    private static final String TAG = LoggingUtil.tag(UserCardViewHolder.class);
    private AnytimeTabletSocialUserCardBinding mBinding;
    private Context mContext;
    private SocialPanelApp mPanelApp;
    @Nullable
    private SocialPopupMenu mPopUpmenu;
    private ProfilePictureHelper mProfilePictureHelper = new ProfilePictureHelper(this.mContext);
    private SocialUser mSocialUser;
    private SocialViewModel mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
    @Nullable
    private SocialUserAdapterItem mUserForRefresh;

    private boolean isGroupLaunchResponseShown() {
        return false;
    }

    public UserCardViewHolder(AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialUserCardBinding.socialUserCard);
        this.mBinding = anytimeTabletSocialUserCardBinding;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
    }

    public void bindUser(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUserForRefresh = socialUserAdapterItem;
        this.mSocialUser = socialUserAdapterItem.getUser();
        hideOverflowMenu();
        setCurrentAsyncAction(null);
        initializeCTA();
        initializeHoverOverlay();
        initializeSecondaryButton();
        initializeSubtitle(this.mUserForRefresh.getPartyMembership());
        initializeProfilePicture();
        initializeUsername();
        initializeMuteOverlay();
        subscribeToSpeakingUpdates();
    }

    public void subscribeToSpeakingUpdates() {
        SocialUser user = this.mUserForRefresh.getUser();
        if (user.getUserType() == SocialUser.UserRowType.PARTY_MEMBER || user.getUserType() == SocialUser.UserRowType.PARTY_CONTROLS) {
            this.mSocialViewModel.startSpeakingIndicatorSubscriptionForUserID(this, user.getID());
        }
    }

    @Override // com.oculus.panelapp.social.ISpeakerIndicatorListener
    public void updateIsSpeaking(boolean z) {
        this.mUserForRefresh.getUser();
        updateSpeakingIndicatorInView(z);
    }

    private void updateSpeakingIndicatorInView(boolean z) {
        this.mBinding.setIsSpeaking(z);
    }

    public void hideOverflowMenu() {
        SocialPopupMenu socialPopupMenu = this.mPopUpmenu;
        if (socialPopupMenu != null) {
            socialPopupMenu.dismissPopup();
            this.mSocialViewModel.setOpenMenu(null);
        }
    }

    private void initializeMuteOverlay() {
        if (this.mSocialUser.getUserType() != SocialUser.UserRowType.PARTY_CONTROLS) {
            SocialParty party = this.mUserForRefresh.getParty();
            if (party != null) {
                this.mBinding.setIsMuted(party.getIsUserMutedByViewer(this.mUserForRefresh.getID()));
                return;
            }
            return;
        }
        int partyLocalMute = HorizonContentProviderHelper.getPartyLocalMute(this.mContext);
        AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding = this.mBinding;
        boolean z = true;
        if (partyLocalMute != 1) {
            z = false;
        }
        anytimeTabletSocialUserCardBinding.setIsMuted(z);
    }

    private void initializeHoverOverlay() {
        this.mBinding.cardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$E1MHMvObwk2MvSqFoFLrRA82xEs */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return UserCardViewHolder.this.lambda$initializeHoverOverlay$40$UserCardViewHolder(view, motionEvent);
            }
        });
    }

    public /* synthetic */ boolean lambda$initializeHoverOverlay$40$UserCardViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mBinding.ctaButton.setVisibility(0);
            this.mBinding.username.setVisibility(8);
            this.mBinding.subtitle.setVisibility(8);
            if (this.mAsyncActionSource == SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON || this.mAsyncActionSource == SocialUserRecyclerViewHolder.AsyncSource.START_PARTY_BUTTON) {
                this.mBinding.ctaLoadingSpinner.setVisibility(0);
            }
        } else if (motionEvent.getAction() == 10) {
            this.mBinding.ctaButton.setVisibility(8);
            this.mBinding.ctaLoadingSpinner.setVisibility(8);
            this.mBinding.username.setVisibility(0);
            this.mBinding.subtitle.setVisibility(0);
        }
        return false;
    }

    private void hidePYMKUser(SocialUser socialUser) {
        if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON);
            HorizonContentProviderHelper.hidePYMKUser(this.mContext, socialUser.getID(), new HorizonContentProviderHelper.HidePYMKUserCallback() {
                /* class com.oculus.panelapp.social.UserCardViewHolder.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.HidePYMKUserCallback
                public void onSuccess(String str) {
                    UserCardViewHolder.this.mSocialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.PYMK);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(UserCardViewHolder.this.mPanelApp, "hide_PYMK_user", UserCardViewHolder.TAG, "Failed to hide PYMK");
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                    Log.d(UserCardViewHolder.TAG, "Hide PYMK user action failed");
                }
            });
        }
    }

    private void initializeSecondaryButton() {
        this.mBinding.secondaryButton.clearOnClickListener();
        if (this.mSocialUser.getUserType() == SocialUser.UserRowType.PARTY_CONTROLS) {
            this.mBinding.secondaryButton.setVisibility(8);
            return;
        }
        this.mBinding.secondaryButton.setVisibility(0);
        if (this.mSocialUser.getUserType() == SocialUser.UserRowType.SUGGESTED_FRIEND) {
            this.mBinding.secondaryButton.setBackground(this.mContext.getDrawable(R.drawable.ic_close));
            this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$q4w9ESgEFEopSLj6XvFNWsuBwmE */

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$initializeSecondaryButton$41$UserCardViewHolder(view);
                }
            });
        } else if (this.mSocialUser.getUserType() != SocialUser.UserRowType.INVITED || !this.mUserForRefresh.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId())) {
            this.mBinding.secondaryButton.setBackground(this.mContext.getDrawable(R.drawable.ic_more_vertical));
            this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$ohLvhdFaV9xOQRnqnr0hzTHTvt8 */

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$initializeSecondaryButton$43$UserCardViewHolder(view);
                }
            });
        } else {
            this.mBinding.secondaryButton.setBackground(this.mContext.getDrawable(R.drawable.ic_close));
            this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$COFVMmWKeElxlUIWZxYmAkzX9cw */

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$initializeSecondaryButton$42$UserCardViewHolder(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$41$UserCardViewHolder(View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_PYMK_CARD_DISMISS, this.mPanelApp);
        hidePYMKUser(this.mSocialUser);
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$42$UserCardViewHolder(View view) {
        if (this.mAsyncActionSource == null) {
            AnonymousClass2 r5 = new SocialUserActionActionHandler() {
                /* class com.oculus.panelapp.social.UserCardViewHolder.AnonymousClass2 */

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onSuccess() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onError() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }
            };
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON);
            new CancelPartyInvite(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, r5, null);
        }
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$43$UserCardViewHolder(View view) {
        this.mPopUpmenu = new SocialPopupMenu(this.mContext, this.mPanelApp, getOverflowActions(), view, SocialUserAction.Source.USER_CARD_MENU);
        this.mSocialViewModel.setOpenMenu(this.mPopUpmenu);
    }

    private List<SocialUserAction> getPossibleActions() {
        SocialParty joinableParty = getJoinableParty(this.mSocialUser);
        SocialUserAction[] socialUserActionArr = new SocialUserAction[12];
        socialUserActionArr[0] = new AddFriend(this.mUserForRefresh);
        socialUserActionArr[1] = new JoinParty(this.mUserForRefresh, joinableParty);
        socialUserActionArr[2] = new Goto(this.mUserForRefresh);
        socialUserActionArr[3] = this.mSocialUser.getIsActive(this.mContext.getResources()) ? new CreatePartyWith(this.mUserForRefresh) : new Chat(this.mUserForRefresh, this.mPanelApp);
        socialUserActionArr[4] = new AddToParty(this.mUserForRefresh);
        socialUserActionArr[5] = new CancelPartyInvite(this.mUserForRefresh);
        socialUserActionArr[6] = this.mSocialUser.getIsActive(this.mContext.getResources()) ? new Chat(this.mUserForRefresh, this.mPanelApp) : new CreatePartyWith(this.mUserForRefresh);
        socialUserActionArr[7] = new UnFriend(this.mUserForRefresh);
        socialUserActionArr[8] = new PerPersonMute(this.mUserForRefresh, this.mPanelApp);
        socialUserActionArr[9] = new Block(this.mUserForRefresh);
        socialUserActionArr[10] = new Report(this.mUserForRefresh);
        socialUserActionArr[11] = new KickFromParty(this.mUserForRefresh, this.mPanelApp);
        return (List) Arrays.asList(socialUserActionArr).stream().filter($$Lambda$UserCardViewHolder$jhfs2b9LmQ2OwUhRw47pQOqv5Q.INSTANCE).collect(Collectors.toList());
    }

    private SocialUserAction getCTAAction() {
        if (this.mSocialUser.getUserType() == SocialUser.UserRowType.PARTY_CONTROLS) {
            return new SelfMute(this.mUserForRefresh, this.mContext);
        }
        for (SocialUserAction socialUserAction : getPossibleActions()) {
            if (socialUserAction.isRelevant()) {
                return socialUserAction;
            }
        }
        return null;
    }

    private List<SocialUserAction> getOverflowActions() {
        return (List) getPossibleActions().stream().filter(new Predicate() {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$7aMJXCBLXXVIOtdFBX5HhjbCI0A */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return UserCardViewHolder.lambda$getOverflowActions$45(SocialUserAction.this, (SocialUserAction) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getOverflowActions$45(SocialUserAction socialUserAction, SocialUserAction socialUserAction2) {
        return socialUserAction.getType() != socialUserAction2.getType();
    }

    @Nullable
    private SocialParty getJoinableParty(SocialUser socialUser) {
        if (socialUser.getCurrentPartyID() == null) {
            return null;
        }
        for (SocialParty socialParty : this.mPanelApp.acquireSocialViewModel().getJoinableParties()) {
            if (socialParty.getID().equals(socialUser.getCurrentPartyID()) && !socialParty.isFull()) {
                return socialParty;
            }
        }
        return null;
    }

    private void initializeCTA() {
        SocialUserAction cTAAction = getCTAAction();
        this.mBinding.ctaButton.setText(cTAAction.getType().getActionString(this.mContext.getResources()));
        if (cTAAction.getType().hasActionIcon()) {
            this.mBinding.setCtaIcon(cTAAction.getType().getActionIcon(this.mContext.getResources()));
        }
        this.mBinding.ctaButton.clearOnClickListener();
        this.mBinding.ctaButton.setVisibility(8);
        this.mBinding.ctaButton.setOnClickListener(new View.OnClickListener(cTAAction) {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$NcbAIeEjW5QZrvz0yPuYNA3uN1Y */
            private final /* synthetic */ SocialUserAction f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                UserCardViewHolder.this.lambda$initializeCTA$46$UserCardViewHolder(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCTA$46$UserCardViewHolder(SocialUserAction socialUserAction, View view) {
        AnonymousClass3 r5;
        if (!socialUserAction.getType().isAsyncAction()) {
            r5 = null;
        } else if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON);
            r5 = new SocialUserActionActionHandler() {
                /* class com.oculus.panelapp.social.UserCardViewHolder.AnonymousClass3 */

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onError() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onSuccess() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }
            };
        } else {
            return;
        }
        socialUserAction.execute(this.mPanelApp, this.mContext, r5, SocialUserAction.Source.USER_CARD_MENU);
    }

    private void setPartyMemberSubtitle() {
        String str;
        String str2;
        String str3;
        boolean equals = this.mUserForRefresh.getParty().getPartyLeader().getID().equals(this.mUserForRefresh.getID());
        String string = this.mContext.getResources().getString(R.string.anytime_tablet_social_party_concat_symbol);
        if (equals) {
            AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding = this.mBinding;
            if (isGroupLaunchResponseShown()) {
                str3 = this.mContext.getResources().getString(R.string.anytime_tablet_social_party_leader_subtitle) + string;
            } else {
                str3 = this.mContext.getResources().getString(R.string.anytime_tablet_social_party_leader_subtitle);
            }
            anytimeTabletSocialUserCardBinding.setSubtitleText(str3);
            if (isGroupLaunchResponseShown()) {
                this.mBinding.setGroupLaunchStatusText(getGroupLaunchResponseString(this.mUserForRefresh.getGroupLaunchResponse()));
                setGroupLaunchResponseColor();
                return;
            }
            this.mBinding.groupLaunchStatus.setVisibility(8);
        } else if (this.mSocialUser.getIsActive(this.mContext.getResources())) {
            AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding2 = this.mBinding;
            if (isGroupLaunchResponseShown()) {
                str2 = this.mSocialUser.getPresenceString() + string;
            } else {
                str2 = this.mSocialUser.getPresenceString();
            }
            anytimeTabletSocialUserCardBinding2.setSubtitleText(str2);
            if (isGroupLaunchResponseShown()) {
                this.mBinding.setGroupLaunchStatusText(getGroupLaunchResponseString(this.mUserForRefresh.getGroupLaunchResponse()));
                setGroupLaunchResponseColor();
                return;
            }
            this.mBinding.groupLaunchStatus.setVisibility(8);
        } else {
            AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding3 = this.mBinding;
            if (isGroupLaunchResponseShown()) {
                str = this.mSocialUser.getPresenceString() + string;
            } else {
                str = this.mSocialUser.getLastActive();
            }
            anytimeTabletSocialUserCardBinding3.setSubtitleText(str);
            if (isGroupLaunchResponseShown()) {
                this.mBinding.setGroupLaunchStatusText(getGroupLaunchResponseString(this.mUserForRefresh.getGroupLaunchResponse()));
                setGroupLaunchResponseColor();
                return;
            }
            this.mBinding.groupLaunchStatus.setVisibility(8);
        }
    }

    private String getGroupLaunchResponseString(SocialGroupLaunchResponse socialGroupLaunchResponse) {
        if (socialGroupLaunchResponse == SocialGroupLaunchResponse.ACCEPT) {
            return this.mContext.getResources().getString(R.string.anytime_tablet_social_party_group_launch_response_accept_subtitle);
        }
        return this.mContext.getResources().getString(R.string.anytime_tablet_social_party_group_launch_response_pending_subtitle);
    }

    private void setGroupLaunchResponseColor() {
        if (this.mUserForRefresh.getGroupLaunchResponse() == SocialGroupLaunchResponse.ACCEPT) {
            this.mBinding.groupLaunchStatus.setTextColor(this.mContext.getColor(R.color.online_indicator));
        } else {
            this.mBinding.groupLaunchStatus.setTextColor(this.mContext.getColor(R.color.octypography_secondary_color));
        }
    }

    private void initializeSubtitle(SocialParty.PartyMembership partyMembership) {
        SocialParty joinableParty = getJoinableParty(this.mSocialUser);
        if (this.mSocialUser.getUserType() == SocialUser.UserRowType.SUGGESTED_FRIEND && this.mSocialUser.getMutualContext() != null) {
            this.mBinding.setSubtitleText(this.mSocialUser.getMutualContext());
        } else if (this.mSocialUser.getUserType() == SocialUser.UserRowType.INVITED) {
            this.mBinding.setSubtitleText(this.mContext.getResources().getString(R.string.anytime_tablet_social_party_card_invite_subtitle));
        } else if (this.mSocialUser.getUserType() == SocialUser.UserRowType.PARTY_MEMBER || this.mSocialUser.getUserType() == SocialUser.UserRowType.PARTY_CONTROLS) {
            setPartyMemberSubtitle();
        } else if ((partyMembership == null || !partyMembership.equals(SocialParty.PartyMembership.MEMBER)) && joinableParty != null) {
            this.mBinding.setSubtitleText(this.mContext.getResources().getString(R.string.anytime_tablet_social_party_in_joinable_party_subtitle));
        } else if (this.mSocialUser.getIsActive(this.mContext.getResources())) {
            this.mBinding.setSubtitleText(this.mSocialUser.getPresenceString());
        } else {
            this.mBinding.setSubtitleText(this.mSocialUser.getLastActive());
        }
    }

    private void initializeProfilePicture() {
        this.mProfilePictureHelper.setImageViewFromUrl(this.mBinding.profilePhoto, this.mSocialUser.getProfilePhotoURL(), this.mSocialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
        this.mBinding.profilePhoto.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$hu8WX9cvTFZzTHk1bYaouv47vo8 */

            public final void onClick(View view) {
                UserCardViewHolder.this.lambda$initializeProfilePicture$47$UserCardViewHolder(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeProfilePicture$47$UserCardViewHolder(View view) {
        SocialLogger.log(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_PROFILE_VIEW, this.mPanelApp);
        SocialPanelApp socialPanelApp = this.mPanelApp;
        SystemUXRoute systemUXRoute = SystemUXRoute.AUI_PROFILE;
        socialPanelApp.actionNavigate(systemUXRoute, "/vr/" + this.mSocialUser.getID());
    }

    private void initializeUsername() {
        this.mBinding.setUsernameText(this.mSocialUser.getAlias());
        this.mBinding.setIsFriend(this.mSocialUser.getFriendship() == SocialUserFriendshipStatus.ARE_FRIENDS);
    }

    /* access modifiers changed from: package-private */
    @Override // com.oculus.panelapp.social.SocialUserRecyclerViewHolder
    public void setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource asyncSource) {
        if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON || asyncSource == SocialUserRecyclerViewHolder.AsyncSource.START_PARTY_BUTTON) {
            this.mBinding.ctaLoadingSpinner.setVisibility(0);
            this.mBinding.ctaButton.setText("");
        } else if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON) {
            this.mBinding.secondaryButton.setVisibility(4);
            this.mBinding.secondaryButtonLoadingSpinner.setVisibility(0);
        } else if (asyncSource == null) {
            this.mBinding.ctaLoadingSpinner.setVisibility(8);
            this.mBinding.secondaryButtonLoadingSpinner.setVisibility(8);
            initializeCTA();
            initializeSecondaryButton();
            initializeMuteOverlay();
        }
        this.mAsyncActionSource = asyncSource;
    }
}
