package com.oculus.panelapp.people.fetchers.fb;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC13251zE;
import X.AnonymousClass1y2;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher;
import com.oculus.panelapp.people.fetchers.PeopleUserDataObserver;
import com.oculus.panelapp.people.graphql.FBFriendListGraphQL;
import com.oculus.panelapp.people.graphql.FBFriendsListResult;
import com.oculus.panelapp.people.model.FBViewerSocialParty;
import com.oculus.panelapp.people.model.IViewerSocialParty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.OkHttpClient;

public class FBFriendsFetcher extends BaseFetcher implements IViewerSocialPartyFetcher {
    public static final String[] ORDER_BY = {"oculus_party_presence_status", "oculus_presence_status", "active_on_messenger_first", "has_oculus_account", "oculus_recent_presence_status", "recently_active_on_messenger_first", "name"};
    public static final int REFETCH_UPDATE_COUNT = 6;
    public static final String TAG = LoggingUtil.tag(FBFriendsFetcher.class);
    public Context mContext;
    public FBViewerSocialParty mCurrentParty;
    public FBFriendListGraphQL mFriendGraphQL;
    public List<SocialUser> mFriends;
    public OkHttpClient mOkHttpClient;
    public int mRefetchCount = 0;
    public AbstractC12271xB mTokenDisposable;
    public List<PeopleUserDataObserver> mUserObservers;

    @Override // com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher
    public void addUserToInvitedList(SocialUser socialUser) {
    }

    public /* synthetic */ void lambda$new$1$FBFriendsFetcher(Throwable th) throws Exception {
        this.mIsErrored = true;
    }

    private void loadFriends() {
        FBFriendListGraphQL fBFriendListGraphQL = this.mFriendGraphQL;
        if (fBFriendListGraphQL == null) {
            this.mIsErrored = true;
            return;
        }
        this.mEnoughDataFetched = false;
        long currentTimeMillis = System.currentTimeMillis();
        fBFriendListGraphQL.fetchFriends(new FBFriendListGraphQL.SuccessCallback(currentTimeMillis) {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendsFetcher$XOUEcIRcOyTAc2IPcBXfIjPW7KY2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.people.graphql.FBFriendListGraphQL.SuccessCallback
            public final void callback(FBFriendsListResult fBFriendsListResult) {
                FBFriendsFetcher.this.lambda$loadFriends$2$FBFriendsFetcher(this.f$1, fBFriendsListResult);
            }
        }, new FacebookGraphQLUtil.FailureCallback(currentTimeMillis) {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendsFetcher$TfcsM4o9q249kuGFCLuTXntcDo2 */
            public final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FBFriendsFetcher.this.lambda$loadFriends$3$FBFriendsFetcher(this.f$1, th);
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v7, resolved type: java.util.List<com.oculus.horizoncontent.social.SocialUser> */
    /* JADX WARN: Multi-variable type inference failed */
    private void mergeFriendList(List<FBSocialUser> list) {
        HashMap hashMap = new HashMap();
        for (FBSocialUser fBSocialUser : list) {
            hashMap.put(fBSocialUser.mID, fBSocialUser);
        }
        for (int i = 0; i < this.mFriends.size(); i++) {
            Object obj = hashMap.get(this.mFriends.get(i).mID);
            if (obj != null) {
                this.mFriends.set(i, obj);
            }
        }
    }

    private void notifyUserObservers(@Nullable String str) {
        for (PeopleUserDataObserver peopleUserDataObserver : this.mUserObservers) {
            peopleUserDataObserver.onUserUpdated(str);
        }
    }

    private List<SocialUser> reshuffleUsers(List<FBSocialUser> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (FBSocialUser fBSocialUser : list) {
            if (fBSocialUser.mIsVRUser) {
                if (fBSocialUser.getIsActive(this.mContext.getResources())) {
                    arrayList.add(fBSocialUser);
                } else if (!TextUtils.isEmpty(fBSocialUser.mLastActiveTime)) {
                    arrayList2.add(fBSocialUser);
                } else {
                    arrayList3.add(fBSocialUser);
                }
            }
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(arrayList);
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        return arrayList4;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFriends(List<FBSocialUser> list) {
        int i;
        if (this.mPeopleListObservers.size() == 0 || this.mFriends.size() == 0 || this.mRefetchCount >= 6) {
            this.mFriends = reshuffleUsers(list);
            i = 0;
        } else {
            mergeFriendList(list);
            i = this.mRefetchCount + 1;
        }
        this.mRefetchCount = i;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setParty(FBViewerSocialParty fBViewerSocialParty) {
        this.mCurrentParty = fBViewerSocialParty;
        notifyUserObservers(null);
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

    @Override // com.oculus.panelapp.people.fetchers.IViewerSocialPartyFetcher
    public IViewerSocialParty getViewerSocialParty() {
        return this.mCurrentParty;
    }

    public /* synthetic */ void lambda$loadFriends$2$FBFriendsFetcher(final long j, final FBFriendsListResult fBFriendsListResult) {
        fBFriendsListResult.mFriendsList.size();
        new Handler(this.mContext.getMainLooper()).post(new Runnable() {
            /* class com.oculus.panelapp.people.fetchers.fb.FBFriendsFetcher.AnonymousClass1 */

            public void run() {
                FBFriendsFetcher.this.logFetchSuccess(ActionId.FETCH_FB_FRIENDS, j);
                FBFriendsFetcher fBFriendsFetcher = FBFriendsFetcher.this;
                fBFriendsFetcher.mIsErrored = false;
                fBFriendsFetcher.setParty(fBFriendsListResult.mFBViewerSocialParty);
                FBFriendsFetcher.this.setFriends(fBFriendsListResult.mFriendsList);
            }
        });
    }

    public /* synthetic */ void lambda$loadFriends$3$FBFriendsFetcher(long j, Throwable th) {
        logFetchFailure(ActionId.FETCH_FB_FRIENDS, j);
        this.mIsErrored = true;
        Log.e(TAG, "Failed to load friends");
    }

    public /* synthetic */ void lambda$new$0$FBFriendsFetcher(Context context, String str) throws Exception {
        this.mFriendGraphQL = new FBFriendListGraphQL(context, this.mOkHttpClient, ORDER_BY, str);
        loadFriends();
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

    @SuppressLint({"CheckResult"})
    public FBFriendsFetcher(Context context, OkHttpClient okHttpClient, AbstractC13251zE<String> r6, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        this.mFriends = new ArrayList();
        this.mUserObservers = new ArrayList();
        this.mCurrentParty = new FBViewerSocialParty();
        this.mOkHttpClient = okHttpClient;
        this.mTokenDisposable = r6.A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(context) {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendsFetcher$BkJdx90UrHdBeIEZS6ebgpv1jpg2 */
            public final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBFriendsFetcher.this.lambda$new$0$FBFriendsFetcher(this.f$1, (String) obj);
            }
        }, new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendsFetcher$m4kyVb5JpYn6fh_yQmLSMS9SIM2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBFriendsFetcher.this.mIsErrored = true;
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mTokenDisposable.dispose();
        this.mFriends = ImmutableList.of();
        this.mUserObservers.clear();
        FBFriendListGraphQL fBFriendListGraphQL = this.mFriendGraphQL;
        if (fBFriendListGraphQL != null) {
            fBFriendListGraphQL.destroy();
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadFriends();
    }
}
