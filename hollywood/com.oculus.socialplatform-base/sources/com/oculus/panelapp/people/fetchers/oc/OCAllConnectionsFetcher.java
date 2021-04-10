package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader;
import java.util.ArrayList;
import java.util.List;

public class OCAllConnectionsFetcher extends BaseFetcher implements ISearchFetcher {
    public static final String[] ORDER_BY = {"self_last", "alias_a_to_z"};
    public static final String TAG = LoggingUtil.tag(OCAllConnectionsFetcher.class);
    public OCFriendsLoader mFriendsLoader;
    public List<SocialUser> mSearchResults;
    public String mSearchString;
    public List<SocialUser> mUserFullList;

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return null;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserFullList(List<SocialUser> list) {
        this.mUserFullList = list;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public void fetchFriends() {
        this.mFriendsLoader.loadFriends(TAG, ORDER_BY, null, new OCFriendsLoader.FriendLoadingCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCAllConnectionsFetcher.AnonymousClass1 */

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onError() {
                OCAllConnectionsFetcher.this.mIsErrored = true;
            }

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onSuccess(List<SocialUser> list) {
                OCAllConnectionsFetcher oCAllConnectionsFetcher = OCAllConnectionsFetcher.this;
                oCAllConnectionsFetcher.mIsErrored = false;
                oCAllConnectionsFetcher.setUserFullList(list);
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        if (TextUtils.isEmpty(this.mSearchString)) {
            return this.mUserFullList;
        }
        return this.mSearchResults;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public String getSearchString() {
        return this.mSearchString;
    }

    public void removeFriend(SocialUser socialUser) {
        this.mUserFullList.remove(socialUser);
        notifyPeopleListObservers();
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public void searchForUser(String str) {
        this.mSearchString = str;
        if (!TextUtils.isEmpty(str)) {
            this.mSearchResults = new ArrayList();
            for (SocialUser socialUser : this.mUserFullList) {
                if (socialUser.mAlias.indexOf(str) != -1) {
                    this.mSearchResults.add(socialUser);
                }
            }
        }
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public OCAllConnectionsFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        ImmutableList of = ImmutableList.of();
        this.mUserFullList = of;
        this.mSearchResults = of;
        this.mFriendsLoader = new OCFriendsLoader(context, socialLogger);
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        ImmutableList of = ImmutableList.of();
        this.mUserFullList = of;
        this.mSearchResults = of;
        this.mFriendsLoader.destroy();
        super.destroy();
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public int getCount() {
        if (getData() == null) {
            return 0;
        }
        return getData().size();
    }
}
