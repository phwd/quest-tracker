package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher;
import com.oculus.panelapp.people.fetchers.PeopleUserDataObserver;
import com.oculus.panelapp.people.model.OCViewerSocialParty;
import java.util.ArrayList;
import java.util.List;

public class OCCurrentPartyFetcher extends BaseFetcher implements IViewerSocialPartyFetcher {
    public static final String TAG = LoggingUtil.tag(OCCurrentPartyFetcher.class);
    public Context mContext;
    @Nullable
    @VisibleForTesting
    public OCViewerSocialParty mCurrentParty;
    @Nullable
    public AsyncQueryHandle mFetchCurrentPartyAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchCurrentPartyInvitesAsyncQueryHandle;
    @VisibleForTesting
    public List<PeopleUserDataObserver> mUserObservers = new ArrayList();

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        return null;
    }

    @VisibleForTesting
    public void setParty(SocialParty socialParty) {
        String str;
        if (socialParty == null || (str = socialParty.mID) == null || str.isEmpty()) {
            socialParty = null;
        }
        maybeCreateViewerSocialParty();
        this.mCurrentParty.mCurrentParty = socialParty;
        notifyUserObservers(null);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchCurrentPartyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchCurrentPartyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchCurrentPartyAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchCurrentPartyInvitesAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchCurrentPartyInvitesAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchCurrentPartyInvitesAsyncQueryHandle = null;
        }
    }

    private void maybeCreateViewerSocialParty() {
        if (this.mCurrentParty == null) {
            this.mCurrentParty = new OCViewerSocialParty();
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher
    public void addUserToInvitedList(SocialUser socialUser) {
        SocialParty socialParty;
        OCViewerSocialParty oCViewerSocialParty = this.mCurrentParty;
        if (oCViewerSocialParty != null && (socialParty = oCViewerSocialParty.mCurrentParty) != null) {
            socialParty.addInvitedUser(socialUser);
            socialUser.mUserType = SocialUser.UserRowType.INVITED;
            notifyUserObservers(socialUser.mID);
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.HIGH;
    }

    @VisibleForTesting
    public void notifyUserObservers(@Nullable String str) {
        for (PeopleUserDataObserver peopleUserDataObserver : this.mUserObservers) {
            peopleUserDataObserver.onUserUpdated(str);
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher
    public void registerUserObserver(PeopleUserDataObserver peopleUserDataObserver) {
        if (!this.mUserObservers.contains(peopleUserDataObserver)) {
            this.mUserObservers.add(peopleUserDataObserver);
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher
    public void removeUserObserver(PeopleUserDataObserver peopleUserDataObserver) {
        if (this.mUserObservers.contains(peopleUserDataObserver)) {
            this.mUserObservers.remove(peopleUserDataObserver);
        }
    }

    public OCCurrentPartyFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        loadCurrentParty();
        loadCurrentPartyInvites();
    }

    private void loadCurrentParty() {
        clearFetchCurrentPartyAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchCurrentPartyAsyncQueryHandle = HorizonContentProviderHelper.fetchCurrentPartyV2(this.mContext, new HorizonContentProviderHelper.FetchCurrentPartyCallBack() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCCurrentPartyFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                OCCurrentPartyFetcher.this.logFetchFailure(ActionId.FETCH_OCULUS_VIEWER_PARTY_DATA, currentTimeMillis);
                OCCurrentPartyFetcher.this.clearFetchCurrentPartyAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchCurrentPartyCallBack
            public void onSuccess(SocialParty socialParty) {
                OCCurrentPartyFetcher.this.logFetchSuccess(ActionId.FETCH_OCULUS_VIEWER_PARTY_DATA, currentTimeMillis);
                OCCurrentPartyFetcher.this.setParty(socialParty);
                OCCurrentPartyFetcher.this.clearFetchCurrentPartyAsyncQueryHandle();
            }
        });
    }

    private void loadCurrentPartyInvites() {
        clearFetchCurrentPartyInvitesAsyncQueryHandle();
        this.mFetchCurrentPartyInvitesAsyncQueryHandle = HorizonContentProviderHelper.fetchPartyInvites(this.mContext, new HorizonContentProviderHelper.FetchPartyInvitesCallBack() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCCurrentPartyFetcher.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                OCCurrentPartyFetcher.this.clearFetchCurrentPartyInvitesAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPartyInvitesCallBack
            public void onSuccess(List<SocialParty> list) {
                list.size();
                OCCurrentPartyFetcher.this.setPartyInvites(list);
                OCCurrentPartyFetcher.this.clearFetchCurrentPartyInvitesAsyncQueryHandle();
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mUserObservers.clear();
        clearFetchCurrentPartyAsyncQueryHandle();
        clearFetchCurrentPartyInvitesAsyncQueryHandle();
        this.mCurrentParty = null;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadCurrentParty();
        loadCurrentPartyInvites();
    }

    @VisibleForTesting
    public void setPartyInvites(List<SocialParty> list) {
        maybeCreateViewerSocialParty();
        this.mCurrentParty.mInvitedParties = list;
        notifyUserObservers(null);
    }

    @Override // com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher
    @Nullable
    public OCViewerSocialParty getViewerSocialParty() {
        return this.mCurrentParty;
    }
}
