package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader;
import java.util.List;

public class OCSearchFetcher extends BaseFetcher implements ISearchFetcher {
    public static final int FRIEND_COUNT = 5;
    public static final String TAG = LoggingUtil.tag(OCSearchFetcher.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchSearchResultsAsyncQueryHandle;
    public OCFriendsLoader mFriendsLoader;
    @VisibleForTesting
    public List<SocialUser> mInitialUserList;
    @VisibleForTesting
    public List<SocialUser> mSearchResults;
    @VisibleForTesting
    public String mSearchString;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSearchResultsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchSearchResultsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchSearchResultsAsyncQueryHandle = null;
        }
    }

    public void fetchFriends() {
        this.mFriendsLoader.loadFriends(TAG, null, 5, new OCFriendsLoader.FriendLoadingCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCSearchFetcher.AnonymousClass2 */

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onError() {
                OCSearchFetcher.this.mIsErrored = true;
            }

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onSuccess(List<SocialUser> list) {
                OCSearchFetcher oCSearchFetcher = OCSearchFetcher.this;
                oCSearchFetcher.mIsErrored = false;
                oCSearchFetcher.setInitialData(list);
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        if (TextUtils.isEmpty(this.mSearchString)) {
            return this.mInitialUserList;
        }
        return this.mSearchResults;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.NONE;
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public String getSearchString() {
        return this.mSearchString;
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public void searchForUser(String str) {
        this.mSearchString = str;
        if (TextUtils.isEmpty(str)) {
            this.mEnoughDataFetched = true;
            notifyPeopleListObservers();
            return;
        }
        this.mEnoughDataFetched = false;
        notifyPeopleListObservers();
        clearFetchSearchResultsAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchSearchResultsAsyncQueryHandle = HorizonContentProviderHelper.searchUsers(str, "ALL", this.mContext, new HorizonContentProviderHelper.UserSearchCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCSearchFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                OCSearchFetcher.this.logFetchFailure(ActionId.FETCH_OCULUS_SEARCH_RESULTS, currentTimeMillis);
                OCSearchFetcher oCSearchFetcher = OCSearchFetcher.this;
                oCSearchFetcher.mIsErrored = true;
                oCSearchFetcher.clearFetchSearchResultsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.UserSearchCallback
            public void onSuccess(List<SocialUser> list) {
                OCSearchFetcher.this.logFetchSuccess(ActionId.FETCH_OCULUS_SEARCH_RESULTS, currentTimeMillis);
                OCSearchFetcher oCSearchFetcher = OCSearchFetcher.this;
                oCSearchFetcher.mIsErrored = false;
                oCSearchFetcher.setSearchData(list);
                OCSearchFetcher.this.clearFetchSearchResultsAsyncQueryHandle();
            }
        });
    }

    @VisibleForTesting
    public void setInitialData(List<SocialUser> list) {
        this.mInitialUserList = list;
        if (TextUtils.isEmpty(this.mSearchString)) {
            this.mEnoughDataFetched = true;
        }
        notifyPeopleListObservers();
    }

    @VisibleForTesting
    public void setSearchData(List<SocialUser> list) {
        this.mSearchResults = list;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public OCSearchFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        ImmutableList of = ImmutableList.of();
        this.mSearchResults = of;
        this.mInitialUserList = of;
        this.mFriendsLoader = new OCFriendsLoader(context, socialLogger);
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        ImmutableList of = ImmutableList.of();
        this.mSearchResults = of;
        this.mInitialUserList = of;
        this.mFriendsLoader.destroy();
        clearFetchSearchResultsAsyncQueryHandle();
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public int getCount() {
        if (getData() == null) {
            return 0;
        }
        return getData().size();
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        getData();
    }
}
