package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import java.util.ArrayList;
import java.util.List;

public class OCFriendRequestFetcher extends BaseFetcher {
    public static final String TAG = LoggingUtil.tag(OCFriendRequestFetcher.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchFriendRequestsAsyncQueryHandle;
    public List<SocialUser> mFriendRequests = new ArrayList();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchFriendRequestsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchFriendRequestsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchFriendRequestsAsyncQueryHandle = null;
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        return this.mFriendRequests;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.HIGH;
    }

    public void removeUserFriendRequest(SocialUser socialUser) {
        this.mFriendRequests.remove(socialUser);
        notifyPeopleListObservers();
    }

    @VisibleForTesting(otherwise = 2)
    public void setFriendRequests(List<SocialUser> list) {
        this.mFriendRequests = list;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public OCFriendRequestFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        loadFriendRequests();
    }

    private void loadFriendRequests() {
        clearFetchFriendRequestsAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchFriendRequestsAsyncQueryHandle = HorizonContentProviderHelper.fetchFriendRequests(this.mContext, new HorizonContentProviderHelper.FetchFriendRequestsCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCFriendRequestFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                OCFriendRequestFetcher.this.logFetchFailure(ActionId.FETCH_OCULUS_FRIEND_REQUESTS, currentTimeMillis);
                OCFriendRequestFetcher oCFriendRequestFetcher = OCFriendRequestFetcher.this;
                oCFriendRequestFetcher.mIsErrored = true;
                oCFriendRequestFetcher.clearFetchFriendRequestsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchFriendRequestsCallback
            public void onSuccess(List<SocialUser> list) {
                OCFriendRequestFetcher.this.logFetchSuccess(ActionId.FETCH_OCULUS_FRIEND_REQUESTS, currentTimeMillis);
                OCFriendRequestFetcher oCFriendRequestFetcher = OCFriendRequestFetcher.this;
                oCFriendRequestFetcher.mIsErrored = false;
                oCFriendRequestFetcher.setFriendRequests(list);
                OCFriendRequestFetcher.this.clearFetchFriendRequestsAsyncQueryHandle();
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        clearFetchFriendRequestsAsyncQueryHandle();
        this.mFriendRequests.clear();
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadFriendRequests();
    }
}
