package com.oculus.panelapp.people.views.holders;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleUserCardBinding;
import com.oculus.panelapp.people.fetchers.PeopleUserDataObserver;
import com.oculus.panelapp.people.views.PeoplePopupMenu;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleUserCardViewModel;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.panelapp.people.views.actions.oc.OCHidePYMKUser;
import com.oculus.panelapp.people.views.holders.PeopleUserRecyclerViewHolder;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.ProfilePictureHelper;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;

public class UserCardViewHolder extends PeopleUserRecyclerViewHolder implements PeopleUserDataObserver {
    public static final String TAG = LoggingUtil.tag(UserCardViewHolder.class);
    public PeopleTabletPeopleUserCardBinding mBinding;
    public Context mContext;
    public boolean mCurrentlyHovered = false;
    public PeopleTabletPanelApp mPanelApp;
    @Nullable
    public PeoplePopupMenu mPopUpMenu;
    public ProfilePictureHelper mProfilePictureHelper = new ProfilePictureHelper(this.mContext);
    public SocialUser mSocialUser;
    @NonNull
    public PeopleUserAdapterItem mUserForRefresh;
    public PeopleUserCardViewModel mViewModel;

    public UserCardViewHolder(PeopleTabletPeopleUserCardBinding peopleTabletPeopleUserCardBinding, Context context, PeopleTabletPanelApp peopleTabletPanelApp) {
        super(peopleTabletPeopleUserCardBinding.peopleUserCard);
        this.mBinding = peopleTabletPeopleUserCardBinding;
        this.mContext = context;
        this.mPanelApp = peopleTabletPanelApp;
        PeopleUserCardViewModel peopleUserCardViewModel = new PeopleUserCardViewModel(context);
        this.mViewModel = peopleUserCardViewModel;
        peopleTabletPeopleUserCardBinding.setViewModel(peopleUserCardViewModel);
        PeopleTabletPeopleUserCardBinding peopleTabletPeopleUserCardBinding2 = this.mBinding;
        OCButton oCButton = peopleTabletPeopleUserCardBinding2.ctaButton;
        PeopleTabletPanelApp peopleTabletPanelApp2 = this.mPanelApp;
        oCButton.mEventHandler = peopleTabletPanelApp2;
        peopleTabletPeopleUserCardBinding2.secondaryCtaButton.mEventHandler = peopleTabletPanelApp2;
        peopleTabletPeopleUserCardBinding2.ctaLeftButton.mEventHandler = peopleTabletPanelApp2;
        peopleTabletPeopleUserCardBinding2.ctaRightButton.mEventHandler = peopleTabletPanelApp2;
    }

    private void bindHoverOverlay() {
        this.mBinding.cardHoverOverlay.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$d5IPiEGkE1xD7xKpt0FD9qgDeXc2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return UserCardViewHolder.this.lambda$bindHoverOverlay$2$UserCardViewHolder(view, motionEvent);
            }
        });
    }

    private void bindHoverVisibility() {
        if (this.mCurrentlyHovered) {
            this.mViewModel.setIsHovered(true);
            if (this.mAsyncActionSource == PeopleUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON) {
                this.mBinding.ctaLoadingSpinner.setVisibility(0);
            } else {
                setCtaButtonVisibility(0);
            }
        } else {
            setCtaButtonVisibility(8);
            this.mBinding.ctaLoadingSpinner.setVisibility(8);
            this.mViewModel.setIsHovered(false);
        }
    }

    private void bindPrimaryCTA() {
        OCButton oCButton;
        List<PeopleUserAction> primaryCTAAction = this.mPanelApp.getPrimaryCTAAction(this.mUserForRefresh);
        if (primaryCTAAction.size() == 1) {
            PeopleUserAction peopleUserAction = primaryCTAAction.get(0);
            this.mViewModel.setCtaIcon(peopleUserAction.getType().getActionIcon(this.mContext.getResources()));
            bindPrimaryCtaButton(this.mBinding.ctaButton, peopleUserAction);
            this.mBinding.ctaLeftButton.setVisibility(8);
            oCButton = this.mBinding.ctaRightButton;
        } else if (primaryCTAAction.size() == 2) {
            PeopleUserAction peopleUserAction2 = primaryCTAAction.get(0);
            PeopleUserAction peopleUserAction3 = primaryCTAAction.get(1);
            bindPrimaryCtaButton(this.mBinding.ctaLeftButton, peopleUserAction2);
            bindPrimaryCtaButton(this.mBinding.ctaRightButton, peopleUserAction3);
            this.mViewModel.setLeftCtaIcon(peopleUserAction2.getType().getActionIcon(this.mContext.getResources()));
            this.mViewModel.setRightCtaIcon(peopleUserAction3.getType().getActionIcon(this.mContext.getResources()));
            oCButton = this.mBinding.ctaButton;
        } else {
            return;
        }
        oCButton.setVisibility(8);
    }

    private void bindProfilePicture() {
        ProfilePictureHelper profilePictureHelper = this.mProfilePictureHelper;
        ImageView imageView = this.mBinding.profilePhoto;
        SocialUser socialUser = this.mSocialUser;
        profilePictureHelper.setImageViewFromUrl(imageView, socialUser.mProfilePhotoURL, socialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
        bindUserLastActiveTime();
        this.mBinding.profilePhoto.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$omgyf45W30ddhFPZqNCNReUUnSk2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return UserCardViewHolder.this.lambda$bindProfilePicture$0$UserCardViewHolder(view, motionEvent);
            }
        });
        this.mBinding.profilePhoto.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$zdK4rLTdHGkihXCTUe2ZlIW5h9g2 */

            public final void onClick(View view) {
                UserCardViewHolder.this.lambda$bindProfilePicture$1$UserCardViewHolder(view);
            }
        });
    }

    private void bindSecondaryCTA() {
        PeoplePopupMenu peoplePopupMenu = this.mPopUpMenu;
        if (peoplePopupMenu != null) {
            peoplePopupMenu.dismissPopup();
            this.mPopUpMenu = null;
        }
        this.mBinding.secondaryCtaButton.setVisibility(0);
        if (this.mSocialUser.mUserType == SocialUser.UserRowType.SUGGESTED_FRIEND) {
            OCHidePYMKUser oCHidePYMKUser = new OCHidePYMKUser(this.mUserForRefresh);
            this.mBinding.secondaryCtaButton.setBackground(oCHidePYMKUser.getType().getActionIcon(this.mContext.getResources()));
            this.mBinding.secondaryCtaButton.setOnClickListener(new View.OnClickListener(oCHidePYMKUser) {
                /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$G4YnVjkmhbZDPrcZXyLFXzHFcko2 */
                public final /* synthetic */ PeopleUserAction f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$bindSecondaryCTA$5$UserCardViewHolder(this.f$1, view);
                }
            });
            return;
        }
        List<PeopleUserAction> overflowActions = getOverflowActions();
        if (overflowActions.isEmpty()) {
            this.mBinding.secondaryCtaButton.setVisibility(8);
            return;
        }
        this.mBinding.secondaryCtaButton.setBackground(this.mContext.getDrawable(R.drawable.people_tablet_more_actions_button));
        this.mBinding.secondaryCtaButton.setOnClickListener(new View.OnClickListener(overflowActions) {
            /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$X6c5vpEfrrI5FeWnoRPEGcca7I2 */
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                UserCardViewHolder.this.lambda$bindSecondaryCTA$6$UserCardViewHolder(this.f$1, view);
            }
        });
    }

    private void bindSubtitle() {
        PeopleUserCardViewModel peopleUserCardViewModel;
        String str;
        PeopleUserCardViewModel peopleUserCardViewModel2;
        String str2;
        String partyPresenceOverride = this.mPanelApp.getPartyPresenceOverride(this.mContext, this.mUserForRefresh);
        boolean z = true;
        if (!partyPresenceOverride.isEmpty()) {
            peopleUserCardViewModel2 = this.mViewModel;
        } else {
            SocialUser socialUser = this.mSocialUser;
            if (socialUser.mUserType != SocialUser.UserRowType.SUGGESTED_FRIEND || (str2 = socialUser.mMutualContext) == null) {
                if (socialUser.getFriendship() == SocialUserFriendshipStatus.INCOMING_REQUEST && this.mPanelApp.getPeopleTabletType() == PeopleTabletType.FACEBOOK) {
                    Integer num = socialUser.mNumMutualFriends;
                    if (num == null || num.intValue() == 0) {
                        peopleUserCardViewModel = this.mViewModel;
                        str = "";
                    } else {
                        peopleUserCardViewModel2 = this.mViewModel;
                        partyPresenceOverride = this.mContext.getResources().getQuantityString(R.plurals.people_tablet_search_result_mutual_friends_subtitle, num.intValue(), num);
                    }
                } else {
                    peopleUserCardViewModel = this.mViewModel;
                    str = ViewHolderUtil.getPresenceSubtitle(socialUser, this.mContext.getResources());
                }
                peopleUserCardViewModel.setSubtitleText(str);
                this.mViewModel.setPresenceIconType(this.mSocialUser.getPresenceIconType());
                PeopleUserCardViewModel peopleUserCardViewModel3 = this.mViewModel;
                if (!this.mSocialUser.getIsActive(this.mContext.getResources()) || this.mSocialUser.mUserType == SocialUser.UserRowType.SUGGESTED_FRIEND) {
                    z = false;
                }
                peopleUserCardViewModel3.setShowOnlineSubtitle(z);
            }
            this.mViewModel.setSubtitleText(str2);
            this.mViewModel.setPresenceIconType(this.mSocialUser.getPresenceIconType());
            PeopleUserCardViewModel peopleUserCardViewModel32 = this.mViewModel;
            z = false;
            peopleUserCardViewModel32.setShowOnlineSubtitle(z);
        }
        peopleUserCardViewModel2.setSubtitleText(partyPresenceOverride);
        this.mViewModel.setPresenceIconType(this.mSocialUser.getPresenceIconType());
        PeopleUserCardViewModel peopleUserCardViewModel322 = this.mViewModel;
        z = false;
        peopleUserCardViewModel322.setShowOnlineSubtitle(z);
    }

    private void bindUserLastActiveTime() {
        SocialUser socialUser = this.mSocialUser;
        if ((socialUser instanceof FBSocialUser) && !socialUser.getIsActive(this.mContext.getResources())) {
            SocialUser socialUser2 = this.mSocialUser;
            if (socialUser2.getFriendship() == SocialUserFriendshipStatus.ARE_FRIENDS) {
                String str = ((FBSocialUser) socialUser2).mLastActiveTime;
                if (!TextUtils.isEmpty(str)) {
                    this.mViewModel.setLastActiveTime(str);
                    return;
                }
            }
        }
        this.mViewModel.setLastActiveTime(null);
    }

    private void bindUsername() {
        PeopleUserCardViewModel peopleUserCardViewModel;
        String str;
        if (this.mPanelApp.getPeopleTabletType() == PeopleTabletType.FACEBOOK) {
            peopleUserCardViewModel = this.mViewModel;
            str = this.mSocialUser.mName;
        } else {
            peopleUserCardViewModel = this.mViewModel;
            str = this.mSocialUser.mAlias;
        }
        peopleUserCardViewModel.setUsernameText(str);
    }

    private List<PeopleUserAction> getOverflowActions() {
        List<PeopleUserAction> possibleActions = this.mPanelApp.getPossibleActions(this.mUserForRefresh);
        possibleActions.removeAll(this.mPanelApp.getPrimaryCTAAction(this.mUserForRefresh));
        return possibleActions;
    }

    private void setCtaButtonVisibility(int i) {
        OCButton oCButton;
        List<PeopleUserAction> primaryCTAAction = this.mPanelApp.getPrimaryCTAAction(this.mUserForRefresh);
        if (primaryCTAAction.size() == 1) {
            oCButton = this.mBinding.ctaButton;
        } else if (primaryCTAAction.size() == 2) {
            this.mBinding.ctaLeftButton.setVisibility(i);
            oCButton = this.mBinding.ctaRightButton;
        } else {
            return;
        }
        oCButton.setVisibility(i);
    }

    private PeopleUserActionHandler setUpMainActionHandler() {
        setCurrentAsyncAction(PeopleUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON);
        return new PeopleUserActionHandler() {
            /* class com.oculus.panelapp.people.views.holders.UserCardViewHolder.AnonymousClass1 */

            @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
            public void onError(String str) {
                UserCardViewHolder.this.setCurrentAsyncAction(null);
            }

            @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
            public void onSuccess() {
                UserCardViewHolder.this.setCurrentAsyncAction(null);
            }
        };
    }

    public void bindUser(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUserForRefresh = peopleUserAdapterItem;
        this.mSocialUser = peopleUserAdapterItem.mUser;
        setCurrentAsyncAction(null);
        bindSubtitle();
        bindProfilePicture();
        bindUsername();
        bindHoverOverlay();
        bindHoverVisibility();
        bindPrimaryCTA();
    }

    public void hideOverflowMenu() {
        PeoplePopupMenu peoplePopupMenu = this.mPopUpMenu;
        if (peoplePopupMenu != null) {
            peoplePopupMenu.dismissPopup();
        }
    }

    public /* synthetic */ void lambda$bindPrimaryCtaButton$3$UserCardViewHolder(PeopleUserAction peopleUserAction, View view) {
        this.mPanelApp.logButtonClick(peopleUserAction.getButtonId(), new String[0]);
        this.mPanelApp.getQuickMessagePopup(view.getContext()).toggle(this.mUserForRefresh.getID(), view);
    }

    public /* synthetic */ void lambda$bindPrimaryCtaButton$4$UserCardViewHolder(PeopleUserAction peopleUserAction, View view) {
        if (this.mAsyncActionSource == null) {
            this.mPanelApp.logButtonClick(peopleUserAction.getButtonId(), new String[0]);
            this.mPanelApp.executeAction(peopleUserAction, this.mContext, setUpMainActionHandler());
        }
    }

    public /* synthetic */ boolean lambda$bindProfilePicture$0$UserCardViewHolder(View view, MotionEvent motionEvent) {
        ImageView imageView;
        float f;
        if (this.mPanelApp != null && motionEvent.getAction() == 9) {
            this.mPanelApp.onButtonEnter();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            imageView = this.mBinding.profilePhoto;
            f = 0.6f;
        } else if (actionMasked != 10) {
            return false;
        } else {
            imageView = this.mBinding.profilePhoto;
            f = 1.0f;
        }
        imageView.setAlpha(f);
        return false;
    }

    public /* synthetic */ void lambda$bindProfilePicture$1$UserCardViewHolder(View view) {
        SystemUXRoute systemUXRoute;
        this.mPanelApp.onButtonClick();
        SocialUser socialUser = this.mSocialUser;
        if (socialUser.mUserType == SocialUser.UserRowType.FRIEND) {
            Uri.Builder appendPath = new Uri.Builder().encodedPath("").appendPath("mailbox").appendPath(this.mPanelApp.getViewerID()).appendPath("thread").appendPath(this.mSocialUser.mID);
            PeopleTabletPanelApp peopleTabletPanelApp = this.mPanelApp;
            PeopleTabletType peopleTabletType = peopleTabletPanelApp.getPeopleTabletType();
            if (peopleTabletType == PeopleTabletType.OCULUS) {
                systemUXRoute = SystemUXRoute.AUI_CHATS;
            } else if (peopleTabletType == PeopleTabletType.FACEBOOK) {
                systemUXRoute = SystemUXRoute.AUI_MESSENGER;
            }
            peopleTabletPanelApp.actionNavigate(systemUXRoute, appendPath.toString());
        } else {
            this.mPanelApp.actionNavigateToProfile(socialUser.mID);
        }
        this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_USER_CARD_PROFILE_PICTURE, new String[0]);
    }

    public /* synthetic */ void lambda$bindSecondaryCTA$5$UserCardViewHolder(PeopleUserAction peopleUserAction, View view) {
        if (this.mAsyncActionSource == null) {
            AnonymousClass2 r2 = new PeopleUserActionHandler() {
                /* class com.oculus.panelapp.people.views.holders.UserCardViewHolder.AnonymousClass2 */

                @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
                public void onError(String str) {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }

                @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
                public void onSuccess() {
                    UserCardViewHolder.this.setCurrentAsyncAction(null);
                }
            };
            setCurrentAsyncAction(PeopleUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON);
            this.mPanelApp.executeAction(peopleUserAction, this.mContext, r2);
        }
    }

    public /* synthetic */ void lambda$bindSecondaryCTA$6$UserCardViewHolder(List list, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_USER_OVERFLOW_MENU, LoggingConstants.TARGET_USERID, this.mSocialUser.mID);
        PeoplePopupMenu peoplePopupMenu = this.mPopUpMenu;
        if (peoplePopupMenu == null) {
            peoplePopupMenu = new PeoplePopupMenu(this.mContext, this.mPanelApp, list);
            this.mPopUpMenu = peoplePopupMenu;
        }
        peoplePopupMenu.togglePopup(view);
    }

    @Override // com.oculus.panelapp.people.fetchers.PeopleUserDataObserver
    public void onUserUpdated(@Nullable String str) {
        if (str == null || str.equals(this.mSocialUser.mID)) {
            bindPrimaryCTA();
            bindSubtitle();
            bindHoverVisibility();
        }
    }

    public void onViewAttachedToWindow() {
        this.mPanelApp.getCurrentPartyFetcher().registerUserObserver(this);
    }

    public void onViewDetachedFromWindow() {
        this.mPanelApp.getCurrentPartyFetcher().removeUserObserver(this);
    }

    @Override // com.oculus.panelapp.people.views.holders.PeopleUserRecyclerViewHolder
    public void setCurrentAsyncAction(PeopleUserRecyclerViewHolder.AsyncSource asyncSource) {
        if (asyncSource == PeopleUserRecyclerViewHolder.AsyncSource.MAIN_BUTTON) {
            this.mBinding.ctaLoadingSpinner.setVisibility(0);
            setCtaButtonVisibility(8);
        } else if (asyncSource == PeopleUserRecyclerViewHolder.AsyncSource.SECONDARY_BUTTON) {
            this.mBinding.secondaryCtaButton.setVisibility(4);
            this.mBinding.secondaryCtaButtonLoadingSpinner.setVisibility(0);
        } else if (asyncSource == null) {
            this.mBinding.ctaLoadingSpinner.setVisibility(8);
            this.mBinding.secondaryCtaButtonLoadingSpinner.setVisibility(8);
            bindPrimaryCTA();
            bindSecondaryCTA();
            if (this.mCurrentlyHovered) {
                setCtaButtonVisibility(0);
            }
        }
        this.mAsyncActionSource = asyncSource;
    }

    private void bindPrimaryCtaButton(OCButton oCButton, PeopleUserAction peopleUserAction) {
        oCButton.setText(peopleUserAction.getType().getActionString(this.mContext.getResources()));
        if (peopleUserAction.getType() != PeopleUserActionType.CHAT || DeviceConfigHelper.getBoolean(this.mContext, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_QUICK_MESSAGE_KILL_SWITCH)) {
            oCButton.setOnClickListener(new View.OnClickListener(peopleUserAction) {
                /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$C9raKrJErfTjvu05odnB1806bmc2 */
                public final /* synthetic */ PeopleUserAction f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$bindPrimaryCtaButton$4$UserCardViewHolder(this.f$1, view);
                }
            });
        } else {
            oCButton.setOnClickListener(new View.OnClickListener(peopleUserAction) {
                /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserCardViewHolder$MZIB1n4tOVIBArcnO9OjPWggbA02 */
                public final /* synthetic */ PeopleUserAction f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    UserCardViewHolder.this.lambda$bindPrimaryCtaButton$3$UserCardViewHolder(this.f$1, view);
                }
            });
        }
    }

    public /* synthetic */ boolean lambda$bindHoverOverlay$2$UserCardViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mCurrentlyHovered = true;
        } else {
            if (motionEvent.getAction() == 10) {
                this.mCurrentlyHovered = false;
            }
            return false;
        }
        bindHoverVisibility();
        return false;
    }
}
