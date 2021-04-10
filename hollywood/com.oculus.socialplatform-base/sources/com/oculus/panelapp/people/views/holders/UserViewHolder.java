package com.oculus.panelapp.people.views.holders;

import X.AnonymousClass1Ah;
import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleListItemBinding;
import com.oculus.panelapp.people.views.PeoplePopupMenu;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.UserViewModel;
import com.oculus.panelapp.people.views.actions.PeopleUserAction;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.tablet.utils.ProfilePictureHelper;
import java.util.ArrayList;
import java.util.List;

public class UserViewHolder extends AnonymousClass1Ah {
    public static final String TAG = LoggingUtil.tag(UserViewHolder.class);
    public List<OCButton> mActiveCtaButtons;
    public PeopleTabletPeopleListItemBinding mBinding;
    public Context mContext;
    public boolean mIsExecutingAction;
    public PeopleTabletPanelApp mPanelApp;
    @Nullable
    public PeoplePopupMenu mPopUpMenu;
    public ProfilePictureHelper mProfilePictureHelper;
    public PeopleUserAdapterItem mUser;
    public PeopleUserActionHandler mUserActionHandler = new PeopleUserActionHandler() {
        /* class com.oculus.panelapp.people.views.holders.UserViewHolder.AnonymousClass1 */

        @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
        public void onError(String str) {
            UserViewHolder.this.setIsExecutingAction(false);
        }

        @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
        public void onSuccess() {
            UserViewHolder.this.setIsExecutingAction(false);
            UserViewHolder userViewHolder = UserViewHolder.this;
            userViewHolder.bindUser(userViewHolder.mUser);
        }
    };
    public UserViewModel mUserViewModel;

    public UserViewHolder(PeopleTabletPeopleListItemBinding peopleTabletPeopleListItemBinding, Context context, PeopleTabletPanelApp peopleTabletPanelApp) {
        super(peopleTabletPeopleListItemBinding.peopleListItem);
        this.mBinding = peopleTabletPeopleListItemBinding;
        UserViewModel userViewModel = new UserViewModel(context);
        this.mUserViewModel = userViewModel;
        peopleTabletPeopleListItemBinding.setViewModel(userViewModel);
        this.mContext = context;
        this.mPanelApp = peopleTabletPanelApp;
        this.mProfilePictureHelper = new ProfilePictureHelper(context);
    }

    private void bindPrimaryCTA(List<PeopleUserAction> list) {
        PeopleUserAction peopleUserAction;
        PeopleUserAction peopleUserAction2;
        this.mActiveCtaButtons = new ArrayList();
        PeopleUserAction peopleUserAction3 = null;
        if (list.size() >= 2) {
            peopleUserAction3 = list.get(0);
            peopleUserAction2 = list.get(1);
        } else if (list.size() == 1) {
            peopleUserAction2 = list.get(0);
        } else {
            peopleUserAction = null;
            bindPrimaryCTAButton(peopleUserAction3, this.mBinding.ctaLeftButton);
            bindPrimaryCTAButton(peopleUserAction, this.mBinding.ctaMiddleButton);
        }
        peopleUserAction = peopleUserAction2;
        bindPrimaryCTAButton(peopleUserAction3, this.mBinding.ctaLeftButton);
        bindPrimaryCTAButton(peopleUserAction, this.mBinding.ctaMiddleButton);
    }

    private void bindPrimaryCTAButton(@Nullable PeopleUserAction peopleUserAction, OCButton oCButton) {
        if (peopleUserAction == null || !peopleUserAction.getType().hasActionIconWithCircularBackgroundOnHoverID()) {
            oCButton.setVisibility(8);
            return;
        }
        this.mActiveCtaButtons.add(oCButton);
        oCButton.setVisibility(0);
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setBackground(peopleUserAction.getType().getActionIconWithCircularBackgroundOnHoverID(this.mContext.getResources()));
        oCButton.setOnClickListener(new View.OnClickListener(peopleUserAction) {
            /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserViewHolder$kmRRVZ0qRQDExilKeqrHzlGczI2 */
            public final /* synthetic */ PeopleUserAction f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                UserViewHolder.this.lambda$bindPrimaryCTAButton$0$UserViewHolder(this.f$1, view);
            }
        });
    }

    private void bindProfilePicture(SocialUser socialUser) {
        this.mProfilePictureHelper.setImageViewFromUrl(this.mBinding.icon, socialUser.mProfilePhotoURL, socialUser.getIsActive(this.mContext.getResources()), ProfilePictureHelper.RenderFlags.V2, true);
    }

    private void bindSecondaryCTA(List<PeopleUserAction> list) {
        PeoplePopupMenu peoplePopupMenu = this.mPopUpMenu;
        if (peoplePopupMenu != null) {
            peoplePopupMenu.dismissPopup();
            this.mPopUpMenu = null;
        }
        List<PeopleUserAction> overflowActions = getOverflowActions(list);
        if (!overflowActions.isEmpty()) {
            this.mBinding.ctaOverflowButton.setVisibility(0);
            this.mActiveCtaButtons.add(this.mBinding.ctaOverflowButton);
            this.mBinding.ctaOverflowButton.setOnClickListener(new View.OnClickListener(overflowActions) {
                /* class com.oculus.panelapp.people.views.holders.$$Lambda$UserViewHolder$eKSzOSh4qW_t7ps8ihGBdmtoXQ2 */
                public final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    UserViewHolder.this.lambda$bindSecondaryCTA$1$UserViewHolder(this.f$1, view);
                }
            });
            return;
        }
        this.mBinding.ctaOverflowButton.setVisibility(8);
    }

    private List<PeopleUserAction> getOverflowActions(List<PeopleUserAction> list) {
        List<PeopleUserAction> possibleActions = this.mPanelApp.getPossibleActions(this.mUser);
        possibleActions.removeAll(list);
        return possibleActions;
    }

    private void setActiveCtaButtonVisibility(int i) {
        List<OCButton> list = this.mActiveCtaButtons;
        if (list != null) {
            for (OCButton oCButton : list) {
                oCButton.setVisibility(i);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setIsExecutingAction(boolean z) {
        this.mIsExecutingAction = z;
        if (z) {
            this.mBinding.ctaLoadingSpinner.setVisibility(0);
            setActiveCtaButtonVisibility(8);
            return;
        }
        this.mBinding.ctaLoadingSpinner.setVisibility(8);
        setActiveCtaButtonVisibility(0);
    }

    private PeopleUserActionHandler setUpMainActionHandler() {
        return new PeopleUserActionHandler() {
            /* class com.oculus.panelapp.people.views.holders.UserViewHolder.AnonymousClass1 */

            @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
            public void onError(String str) {
                UserViewHolder.this.setIsExecutingAction(false);
            }

            @Override // com.oculus.panelapp.people.views.actions.PeopleUserActionHandler
            public void onSuccess() {
                UserViewHolder.this.setIsExecutingAction(false);
                UserViewHolder userViewHolder = UserViewHolder.this;
                userViewHolder.bindUser(userViewHolder.mUser);
            }
        };
    }

    public void bindUser(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
        SocialUser socialUser = peopleUserAdapterItem.mUser;
        setIsExecutingAction(false);
        bindProfilePicture(socialUser);
        this.mUserViewModel.setUser(socialUser, this.mPanelApp.getPeopleTabletType());
        List<PeopleUserAction> primaryCTAAction = this.mPanelApp.getPrimaryCTAAction(this.mUser);
        bindPrimaryCTA(primaryCTAAction);
        bindSecondaryCTA(primaryCTAAction);
    }

    public /* synthetic */ void lambda$bindPrimaryCTAButton$0$UserViewHolder(PeopleUserAction peopleUserAction, View view) {
        if (!this.mIsExecutingAction) {
            setIsExecutingAction(true);
            this.mPanelApp.executeAction(peopleUserAction, this.mContext, this.mUserActionHandler);
        }
    }

    public /* synthetic */ void lambda$bindSecondaryCTA$1$UserViewHolder(List list, View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.PEOPLE_TAB_USER_OVERFLOW_MENU, LoggingConstants.TARGET_USERID, this.mUser.getID());
        PeoplePopupMenu peoplePopupMenu = this.mPopUpMenu;
        if (peoplePopupMenu == null) {
            peoplePopupMenu = new PeoplePopupMenu(this.mContext, this.mPanelApp, list);
            this.mPopUpMenu = peoplePopupMenu;
        }
        peoplePopupMenu.togglePopup(view);
    }
}
