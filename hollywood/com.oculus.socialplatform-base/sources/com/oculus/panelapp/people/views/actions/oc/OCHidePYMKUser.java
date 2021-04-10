package com.oculus.panelapp.people.views.actions.oc;

import android.content.Context;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.OCPeopleTabletPanelApp;
import com.oculus.panelapp.people.fetchers.oc.OCPYMKFetcher;
import com.oculus.panelapp.people.views.PeopleUserAdapterItem;
import com.oculus.panelapp.people.views.PeopleViewToaster;
import com.oculus.panelapp.people.views.actions.PeopleUserActionHandler;
import com.oculus.panelapp.people.views.actions.PeopleUserActionType;
import com.oculus.socialplatform.R;

public class OCHidePYMKUser extends OCPeopleUserAction {
    public static final String TAG = LoggingUtil.tag(OCHidePYMKUser.class);
    @NonNull
    public PeopleUserAdapterItem mUser;

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public ClickEventButtonId getButtonId() {
        return ClickEventButtonId.PEOPLE_TAB_USER_CARD_HIDE_PYMK_USER;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public String getTargetUserId() {
        return this.mUser.getID();
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public PeopleUserActionType getType() {
        return PeopleUserActionType.HIDE_PYMK_USER;
    }

    @Override // com.oculus.panelapp.people.views.actions.PeopleUserAction
    public boolean isRelevant() {
        if (this.mUser.mUser.mUserType == SocialUser.UserRowType.SUGGESTED_FRIEND) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.panelapp.people.views.actions.oc.OCPeopleUserAction
    public void performAction(final OCPeopleTabletPanelApp oCPeopleTabletPanelApp, final Context context, final PeopleUserActionHandler peopleUserActionHandler) {
        HorizonContentProviderHelper.hidePYMKUser(context, this.mUser.getID(), new HorizonContentProviderHelper.HidePYMKUserCallback() {
            /* class com.oculus.panelapp.people.views.actions.oc.OCHidePYMKUser.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                peopleUserActionHandler.onError("");
                Context context = context;
                PeopleViewToaster.showErrorToast(context, "oculus_mobile_social_hide_suggestion_error", context.getResources().getString(R.string.people_tablet_hide_pymk_failed), OCHidePYMKUser.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.HidePYMKUserCallback
            public void onSuccess(String str) {
                OCPYMKFetcher oCPYMKFetcher = oCPeopleTabletPanelApp.mPYMKFetcher;
                oCPYMKFetcher.incrementRefCount();
                oCPYMKFetcher.removeUserFromPYMK(OCHidePYMKUser.this.mUser.mUser);
                oCPeopleTabletPanelApp.releasePYMKFetcher();
                peopleUserActionHandler.onSuccess();
            }
        });
    }

    public OCHidePYMKUser(PeopleUserAdapterItem peopleUserAdapterItem) {
        this.mUser = peopleUserAdapterItem;
    }
}
