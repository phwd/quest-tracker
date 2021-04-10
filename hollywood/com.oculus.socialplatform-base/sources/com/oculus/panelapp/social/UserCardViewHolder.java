package com.oculus.panelapp.social;

import X.AnonymousClass006;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
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
import com.oculus.panelapp.social.actions.SocialUserActionType;
import com.oculus.panelapp.social.actions.UnFriend;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBinding;
import com.oculus.socialplatform.R;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.views.ShellButton;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public class UserCardViewHolder extends SocialUserRecyclerViewHolder implements ISpeakerIndicatorListener {
    public static final String TAG = LoggingUtil.tag(UserCardViewHolder.class);
    public AnytimeTabletSocialUserCardBinding mBinding;
    public Context mContext;
    public SocialPanelApp mPanelApp;
    @Nullable
    public SocialPopupMenu mPopUpmenu;
    public ProfilePictureHelper mProfilePictureHelper = new ProfilePictureHelper(this.mContext);
    public SocialUser mSocialUser;
    public SocialViewModel mSocialViewModel;
    @Nullable
    public SocialUserAdapterItem mUserForRefresh;

    private boolean isGroupLaunchResponseShown() {
        return false;
    }

    public UserCardViewHolder(AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding, Context context, SocialPanelApp socialPanelApp) {
        super(anytimeTabletSocialUserCardBinding.socialUserCard);
        this.mBinding = anytimeTabletSocialUserCardBinding;
        this.mContext = context;
        this.mPanelApp = socialPanelApp;
        this.mSocialViewModel = socialPanelApp.acquireSocialViewModel();
    }

    private SocialUserAction getCTAAction() {
        if (this.mSocialUser.mUserType == SocialUser.UserRowType.PARTY_CONTROLS) {
            return new SelfMute(this.mUserForRefresh, this.mContext);
        }
        for (SocialUserAction socialUserAction : getPossibleActions()) {
            if (socialUserAction.isRelevant()) {
                return socialUserAction;
            }
        }
        return null;
    }

    private String getGroupLaunchResponseString(SocialGroupLaunchResponse socialGroupLaunchResponse) {
        Resources resources;
        int i;
        if (socialGroupLaunchResponse == SocialGroupLaunchResponse.ACCEPT) {
            resources = this.mContext.getResources();
            i = R.string.anytime_tablet_social_party_group_launch_response_accept_subtitle;
        } else {
            resources = this.mContext.getResources();
            i = R.string.anytime_tablet_social_party_group_launch_response_pending_subtitle;
        }
        return resources.getString(i);
    }

    @Nullable
    private SocialParty getJoinableParty(SocialUser socialUser) {
        if (socialUser.mCurrentPartyID != null) {
            for (SocialParty socialParty : this.mPanelApp.acquireSocialViewModel().getJoinableParties()) {
                if (socialParty.mID.equals(socialUser.mCurrentPartyID) && !socialParty.isFull()) {
                    return socialParty;
                }
            }
        }
        return null;
    }

    private List<SocialUserAction> getPossibleActions() {
        SocialUserAction chat;
        SocialUserAction createPartyWith;
        SocialParty joinableParty = getJoinableParty(this.mSocialUser);
        SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
        AddFriend addFriend = new AddFriend(socialUserAdapterItem);
        JoinParty joinParty = new JoinParty(socialUserAdapterItem, joinableParty);
        Goto r4 = new Goto(socialUserAdapterItem);
        boolean isActive = this.mSocialUser.getIsActive(this.mContext.getResources());
        SocialUserAdapterItem socialUserAdapterItem2 = this.mUserForRefresh;
        if (isActive) {
            chat = new CreatePartyWith(socialUserAdapterItem2);
        } else {
            chat = new Chat(socialUserAdapterItem2, this.mPanelApp);
        }
        AddToParty addToParty = new AddToParty(socialUserAdapterItem2);
        CancelPartyInvite cancelPartyInvite = new CancelPartyInvite(socialUserAdapterItem2);
        boolean isActive2 = this.mSocialUser.getIsActive(this.mContext.getResources());
        SocialUserAdapterItem socialUserAdapterItem3 = this.mUserForRefresh;
        if (isActive2) {
            createPartyWith = new Chat(socialUserAdapterItem3, this.mPanelApp);
        } else {
            createPartyWith = new CreatePartyWith(socialUserAdapterItem3);
        }
        UnFriend unFriend = new UnFriend(socialUserAdapterItem3);
        PerPersonMute perPersonMute = new PerPersonMute(socialUserAdapterItem3, this.mPanelApp);
        SocialUserAdapterItem socialUserAdapterItem4 = this.mUserForRefresh;
        return (List) Arrays.asList(addFriend, joinParty, r4, chat, addToParty, cancelPartyInvite, createPartyWith, unFriend, perPersonMute, new Block(socialUserAdapterItem4), new Report(socialUserAdapterItem4), new KickFromParty(socialUserAdapterItem4, this.mPanelApp)).stream().filter($$Lambda$UserCardViewHolder$_p17d8J4HtpoFDlBg5RoiGCBwj02.INSTANCE).collect(Collectors.toList());
    }

    private void hidePYMKUser(SocialUser socialUser) {
        if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON);
            HorizonContentProviderHelper.hidePYMKUser(this.mContext, socialUser.mID, new HorizonContentProviderHelper.HidePYMKUserCallback() {
                /* class com.oculus.panelapp.social.UserCardViewHolder.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    SocialLogger.logError(UserCardViewHolder.this.mContext, "hide_PYMK_user", UserCardViewHolder.TAG, "Failed to hide PYMK");
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.HidePYMKUserCallback
                public void onSuccess(String str) {
                    UserCardViewHolder.this.mSocialViewModel.removeUserOptimisticAndRefetch(str, SocialViewModel.SocialList.PYMK);
                }
            });
        }
    }

    private void initializeHoverOverlay() {
        this.mBinding.cardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$LZHTcP8Sfe36SQtr7vQkAmxjdM2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return UserCardViewHolder.this.lambda$initializeHoverOverlay$0$UserCardViewHolder(view, motionEvent);
            }
        });
    }

    private void initializeMuteOverlay() {
        if (this.mSocialUser.mUserType != SocialUser.UserRowType.PARTY_CONTROLS) {
            SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
            SocialParty socialParty = socialUserAdapterItem.mParty;
            if (socialParty != null) {
                this.mBinding.setIsMuted(socialParty.mUsersMutedByViewer.contains(socialUserAdapterItem.getID()));
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

    private void initializeProfilePicture() {
        ProfilePictureHelper profilePictureHelper = this.mProfilePictureHelper;
        ImageView imageView = this.mBinding.profilePhoto;
        SocialUser socialUser = this.mSocialUser;
        profilePictureHelper.setImageViewFromUrl(imageView, socialUser.mProfilePhotoURL, socialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
        this.mBinding.profilePhoto.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$WENAYi609v8m9Q2vX1iA4fYIuI2 */

            public final void onClick(View view) {
                UserCardViewHolder.this.lambda$initializeProfilePicture$7$UserCardViewHolder(view);
            }
        });
    }

    private void initializeSecondaryButton() {
        this.mBinding.secondaryButton.clearOnClickListener();
        SocialUser.UserRowType userRowType = this.mSocialUser.mUserType;
        SocialUser.UserRowType userRowType2 = SocialUser.UserRowType.PARTY_CONTROLS;
        ShellButton shellButton = this.mBinding.secondaryButton;
        if (userRowType == userRowType2) {
            shellButton.setVisibility(8);
            return;
        }
        shellButton.setVisibility(0);
        SocialUser.UserRowType userRowType3 = this.mSocialUser.mUserType;
        if (userRowType3 == SocialUser.UserRowType.SUGGESTED_FRIEND) {
            this.mBinding.secondaryButton.setBackground(this.mContext.getDrawable(R.drawable.oc_icon_close_filled_24_d2d2d2));
            this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$DqRZzwfVBuriZuMBBGZpsptwRaA2 */

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$initializeSecondaryButton$1$UserCardViewHolder(view);
                }
            });
        } else if (userRowType3 != SocialUser.UserRowType.INVITED || !this.mUserForRefresh.getIsUserInvitedByLocalUserToParty(this.mPanelApp.getLocalUserId())) {
            this.mBinding.secondaryButton.setBackground(this.mContext.getDrawable(R.drawable.oc_icon_more_vertical_filled_24_d2d2d2));
            this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$Qlq8K6JbiBWi5gJhwitieJBwiU2 */

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$initializeSecondaryButton$3$UserCardViewHolder(view);
                }
            });
        } else {
            this.mBinding.secondaryButton.setBackground(this.mContext.getDrawable(R.drawable.oc_icon_close_filled_24_d2d2d2));
            this.mBinding.secondaryButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$4hA0BXsDw3a8kbDV80XKgFN69bY2 */

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$initializeSecondaryButton$2$UserCardViewHolder(view);
                }
            });
        }
    }

    private void initializeSubtitle(SocialParty.PartyMembership partyMembership) {
        AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding;
        String str;
        AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding2;
        Resources resources;
        int i;
        SocialParty joinableParty = getJoinableParty(this.mSocialUser);
        SocialUser socialUser = this.mSocialUser;
        SocialUser.UserRowType userRowType = socialUser.mUserType;
        if (userRowType != SocialUser.UserRowType.SUGGESTED_FRIEND || (str = socialUser.mMutualContext) == null) {
            if (userRowType == SocialUser.UserRowType.INVITED) {
                anytimeTabletSocialUserCardBinding2 = this.mBinding;
                resources = this.mContext.getResources();
                i = R.string.anytime_tablet_social_party_card_invite_subtitle;
            } else if (userRowType == SocialUser.UserRowType.PARTY_MEMBER || userRowType == SocialUser.UserRowType.PARTY_CONTROLS) {
                setPartyMemberSubtitle();
                return;
            } else if ((partyMembership == null || !partyMembership.equals(SocialParty.PartyMembership.MEMBER)) && joinableParty != null) {
                anytimeTabletSocialUserCardBinding2 = this.mBinding;
                resources = this.mContext.getResources();
                i = R.string.anytime_tablet_social_party_in_joinable_party_subtitle;
            } else if (socialUser.getIsActive(this.mContext.getResources())) {
                anytimeTabletSocialUserCardBinding = this.mBinding;
                str = this.mSocialUser.mPresenceString;
            } else {
                anytimeTabletSocialUserCardBinding = this.mBinding;
                str = this.mSocialUser.mLastActive;
            }
            anytimeTabletSocialUserCardBinding2.setSubtitleText(resources.getString(i));
            return;
        }
        anytimeTabletSocialUserCardBinding = this.mBinding;
        anytimeTabletSocialUserCardBinding.setSubtitleText(str);
    }

    private void initializeUsername() {
        this.mBinding.setUsernameText(this.mSocialUser.mAlias);
        AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding = this.mBinding;
        boolean z = false;
        if (this.mSocialUser.getFriendship() == SocialUserFriendshipStatus.ARE_FRIENDS) {
            z = true;
        }
        anytimeTabletSocialUserCardBinding.setIsFriend(z);
    }

    private void setGroupLaunchResponseColor() {
        Resources.Theme theme;
        int i;
        TypedValue typedValue = new TypedValue();
        if (this.mUserForRefresh.getGroupLaunchResponse() == SocialGroupLaunchResponse.ACCEPT) {
            theme = this.mContext.getTheme();
            i = R.attr.ocPositive;
        } else {
            theme = this.mContext.getTheme();
            i = R.attr.ocSecondaryText;
        }
        theme.resolveAttribute(i, typedValue, true);
        this.mBinding.groupLaunchStatus.setTextColor(typedValue.data);
    }

    private void setPartyMemberSubtitle() {
        AnytimeTabletSocialUserCardBinding anytimeTabletSocialUserCardBinding;
        String str;
        SocialUserAdapterItem socialUserAdapterItem = this.mUserForRefresh;
        boolean equals = socialUserAdapterItem.mParty.mPartyLeader.mID.equals(socialUserAdapterItem.getID());
        this.mContext.getResources().getString(R.string.anytime_tablet_social_party_concat_symbol);
        if (equals) {
            this.mBinding.setSubtitleText(this.mContext.getResources().getString(R.string.anytime_tablet_social_party_leader_subtitle));
        } else {
            if (this.mSocialUser.getIsActive(this.mContext.getResources())) {
                anytimeTabletSocialUserCardBinding = this.mBinding;
                str = this.mSocialUser.mPresenceString;
            } else {
                anytimeTabletSocialUserCardBinding = this.mBinding;
                str = this.mSocialUser.mLastActive;
            }
            anytimeTabletSocialUserCardBinding.setSubtitleText(str);
        }
        this.mBinding.groupLaunchStatus.setVisibility(8);
    }

    private void updateSpeakingIndicatorInView(boolean z) {
        this.mBinding.setIsSpeaking(z);
    }

    public void bindUser(SocialUserAdapterItem socialUserAdapterItem) {
        this.mUserForRefresh = socialUserAdapterItem;
        this.mSocialUser = socialUserAdapterItem.mUser;
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

    public void hideOverflowMenu() {
        SocialPopupMenu socialPopupMenu = this.mPopUpmenu;
        if (socialPopupMenu != null) {
            socialPopupMenu.dismissPopup();
            this.mSocialViewModel.setOpenMenu(null);
        }
    }

    public /* synthetic */ void lambda$initializeProfilePicture$7$UserCardViewHolder(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_FRIENDS_CARD_PROFILE_VIEW);
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PROFILE, AnonymousClass006.A07("/vr/", this.mSocialUser.mID));
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$1$UserCardViewHolder(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_SOCIAL_PYMK_CARD_DISMISS);
        hidePYMKUser(this.mSocialUser);
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$2$UserCardViewHolder(View view) {
        if (this.mAsyncActionSource == null) {
            AnonymousClass2 r4 = new SocialUserActionActionHandler() {
                /* class com.oculus.panelapp.social.UserCardViewHolder.AnonymousClass2 */

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onError() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.social.actions.SocialUserActionActionHandler
                public void onSuccess() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }
            };
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON);
            new CancelPartyInvite(this.mUserForRefresh).execute(this.mPanelApp, this.mContext, r4, null);
        }
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$3$UserCardViewHolder(View view) {
        SocialPopupMenu socialPopupMenu = new SocialPopupMenu(this.mContext, this.mPanelApp, getOverflowActions(), view, SocialUserAction.Source.USER_CARD_MENU);
        this.mPopUpmenu = socialPopupMenu;
        this.mSocialViewModel.setOpenMenu(socialPopupMenu);
    }

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

    public void subscribeToSpeakingUpdates() {
        SocialUser socialUser = this.mUserForRefresh.mUser;
        SocialUser.UserRowType userRowType = socialUser.mUserType;
        if (userRowType == SocialUser.UserRowType.PARTY_MEMBER || userRowType == SocialUser.UserRowType.PARTY_CONTROLS) {
            this.mSocialViewModel.startSpeakingIndicatorSubscriptionForUserID(this, socialUser.mID);
        }
    }

    private List<SocialUserAction> getOverflowActions() {
        return (List) getPossibleActions().stream().filter(new Predicate() {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$aKQZDImhdoRoPnH_296_DP86jxM2 */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return UserCardViewHolder.lambda$getOverflowActions$5(SocialUserAction.this, (SocialUserAction) obj);
            }
        }).collect(Collectors.toList());
    }

    private void initializeCTA() {
        SocialUserAction cTAAction = getCTAAction();
        this.mBinding.ctaButton.setText(cTAAction.getType().getActionString(this.mContext.getResources()));
        SocialUserActionType type = cTAAction.getType();
        if (type.hasActionIcon()) {
            this.mBinding.setCtaIcon(type.getActionIcon(this.mContext.getResources()));
        }
        this.mBinding.ctaButton.clearOnClickListener();
        this.mBinding.ctaButton.setVisibility(8);
        this.mBinding.ctaButton.setOnClickListener(new View.OnClickListener(cTAAction) {
            /* class com.oculus.panelapp.social.$$Lambda$UserCardViewHolder$nEMvbLU0HkB7_e2FRKgDqVVQoXw2 */
            public final /* synthetic */ SocialUserAction f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                UserCardViewHolder.this.lambda$initializeCTA$6$UserCardViewHolder(this.f$1, view);
            }
        });
    }

    public static /* synthetic */ boolean lambda$getOverflowActions$5(SocialUserAction socialUserAction, SocialUserAction socialUserAction2) {
        if (socialUserAction.getType() != socialUserAction2.getType()) {
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$initializeCTA$6$UserCardViewHolder(SocialUserAction socialUserAction, View view) {
        AnonymousClass3 r3;
        if (!socialUserAction.getType().isAsyncAction()) {
            r3 = null;
        } else if (this.mAsyncActionSource == null) {
            setCurrentAsyncAction(SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON);
            r3 = new SocialUserActionActionHandler() {
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
        socialUserAction.execute(this.mPanelApp, this.mContext, r3, SocialUserAction.Source.USER_CARD_MENU);
    }

    public /* synthetic */ boolean lambda$initializeHoverOverlay$0$UserCardViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mBinding.ctaButton.setVisibility(0);
            this.mBinding.username.setVisibility(8);
            this.mBinding.subtitle.setVisibility(8);
            SocialUserRecyclerViewHolder.AsyncSource asyncSource = this.mAsyncActionSource;
            if (asyncSource == SocialUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON || asyncSource == SocialUserRecyclerViewHolder.AsyncSource.START_PARTY_BUTTON) {
                this.mBinding.ctaLoadingSpinner.setVisibility(0);
            }
        } else if (motionEvent.getAction() == 10) {
            this.mBinding.ctaButton.setVisibility(8);
            this.mBinding.ctaLoadingSpinner.setVisibility(8);
            this.mBinding.username.setVisibility(0);
            this.mBinding.subtitle.setVisibility(0);
            return false;
        }
        return false;
    }

    @Override // com.oculus.panelapp.social.ISpeakerIndicatorListener
    public void updateIsSpeaking(boolean z) {
        updateSpeakingIndicatorInView(z);
    }
}
