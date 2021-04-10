package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OCFriendsFetcher extends BaseFetcher {
    public static final int FRIEND_FETCH_COUNT = 20;
    public static final String[] ORDER_BY = {"self_last", "are_friends", "party_presence_first", "status", "last_online", "name_or_alias"};
    public static final int REFETCH_UPDATE_COUNT = 6;
    public static final String TAG = LoggingUtil.tag(OCFriendsFetcher.class);
    public List<SocialUser> mFriends = new ArrayList();
    @VisibleForTesting(otherwise = 2)
    public OCFriendsLoader mFriendsLoader;
    public int mRefetchCount = 0;

    /* JADX DEBUG: Multi-variable search result rejected for r0v7, resolved type: java.util.List<com.oculus.horizoncontent.social.SocialUser> */
    /* JADX WARN: Multi-variable type inference failed */
    private void mergeFriendList(List<SocialUser> list) {
        HashMap hashMap = new HashMap();
        for (SocialUser socialUser : list) {
            hashMap.put(socialUser.mID, socialUser);
        }
        for (int i = 0; i < this.mFriends.size(); i++) {
            Object obj = hashMap.get(this.mFriends.get(i).mID);
            if (obj != null) {
                this.mFriends.set(i, obj);
            }
        }
    }

    public void addUserToFriends(SocialUser socialUser) {
        this.mFriends.add(socialUser);
        notifyPeopleListObservers();
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        return this.mFriends;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.HIGH;
    }

    @VisibleForTesting(otherwise = 2)
    public void loadFriends() {
        this.mFriendsLoader.loadFriends(TAG, ORDER_BY, 20, new OCFriendsLoader.FriendLoadingCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCFriendsFetcher.AnonymousClass2 */

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onError() {
                OCFriendsFetcher.this.mIsErrored = true;
            }

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onSuccess(List<SocialUser> list) {
                OCFriendsFetcher oCFriendsFetcher = OCFriendsFetcher.this;
                oCFriendsFetcher.mIsErrored = false;
                oCFriendsFetcher.setFriends(list);
            }
        });
    }

    public void removeFriend(SocialUser socialUser) {
        this.mFriends.remove(socialUser);
        notifyPeopleListObservers();
    }

    @VisibleForTesting(otherwise = 2)
    public void setFriends(List<SocialUser> list) {
        int i;
        if (this.mPeopleListObservers.size() == 0 || this.mFriends.size() == 0 || this.mRefetchCount >= 6) {
            this.mFriends = list;
            i = 0;
        } else {
            mergeFriendList(list);
            i = this.mRefetchCount + 1;
        }
        this.mRefetchCount = i;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public OCFriendsFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        OCFriendsLoader oCFriendsLoader = new OCFriendsLoader(context, socialLogger);
        this.mFriendsLoader = oCFriendsLoader;
        oCFriendsLoader.loadFriendsWithoutRichPresence(TAG, ORDER_BY, 20, new OCFriendsLoader.FriendLoadingCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCFriendsFetcher.AnonymousClass1 */

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onError() {
            }

            @Override // com.oculus.panelapp.people.fetchers.oc.OCFriendsLoader.FriendLoadingCallback
            public void onSuccess(List<SocialUser> list) {
                OCFriendsFetcher.this.setFriends(list);
            }
        });
        loadFriends();
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mFriendsLoader.destroy();
        this.mFriends = ImmutableList.of();
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadFriends();
    }
}
