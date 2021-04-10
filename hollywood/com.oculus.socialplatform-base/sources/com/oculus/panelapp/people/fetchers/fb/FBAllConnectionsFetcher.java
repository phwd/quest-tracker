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
import com.google.common.collect.ImmutableList;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.fetchers.ISearchFetcher;
import com.oculus.panelapp.people.graphql.FBFriendListGraphQL;
import com.oculus.panelapp.people.graphql.FBFriendSearchGraphQL;
import com.oculus.panelapp.people.graphql.FBFriendsListResult;
import java.util.List;
import okhttp3.OkHttpClient;

public class FBAllConnectionsFetcher extends BaseFetcher implements ISearchFetcher {
    public static final String[] ORDER_BY = {"name", "oculus_presence_status", "active_on_messenger_first", "oculus_recent_presence_status", "recently_active_on_messenger_first"};
    public static final String TAG = LoggingUtil.tag(FBAllConnectionsFetcher.class);
    public Context mContext;
    public FBFriendListGraphQL mFriendGraphQL;
    public int mFriendsCount;
    public ImmutableList<SocialUser> mFullFriendsList;
    public Handler mHandler;
    public OkHttpClient mOkHttpClient;
    public FBFriendSearchGraphQL mSearchGraphQL;
    public ImmutableList<SocialUser> mSearchResults;
    public String mSearchString;
    public AbstractC12271xB mTokenDisposable;

    public /* synthetic */ void lambda$createFailureCallback$4$FBAllConnectionsFetcher(Throwable th) {
        this.mIsErrored = true;
        Log.e(TAG, "Failed to load friends");
    }

    public /* synthetic */ void lambda$new$1$FBAllConnectionsFetcher(Throwable th) throws Exception {
        this.mIsErrored = true;
    }

    private FacebookGraphQLUtil.FailureCallback createFailureCallback() {
        return new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$yc_ctzCjMAvNXTcHegHPG9QsI2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FBAllConnectionsFetcher.this.lambda$createFailureCallback$4$FBAllConnectionsFetcher(th);
            }
        };
    }

    private FBFriendListGraphQL.SuccessCallback createFriendsFetchingSuccessCallback() {
        return new FBFriendListGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$AkGuIvzNKw3JvHkdqiM1rtZ3LVE2 */

            @Override // com.oculus.panelapp.people.graphql.FBFriendListGraphQL.SuccessCallback
            public final void callback(FBFriendsListResult fBFriendsListResult) {
                FBAllConnectionsFetcher.this.lambda$createFriendsFetchingSuccessCallback$3$FBAllConnectionsFetcher(fBFriendsListResult);
            }
        };
    }

    private void loadFriends() {
        FBFriendListGraphQL fBFriendListGraphQL = this.mFriendGraphQL;
        if (fBFriendListGraphQL == null) {
            this.mIsErrored = true;
            return;
        }
        this.mFullFriendsList = ImmutableList.of();
        this.mEnoughDataFetched = false;
        fBFriendListGraphQL.fetchFriends(new FBFriendListGraphQL.SuccessCallback() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$AkGuIvzNKw3JvHkdqiM1rtZ3LVE2 */

            @Override // com.oculus.panelapp.people.graphql.FBFriendListGraphQL.SuccessCallback
            public final void callback(FBFriendsListResult fBFriendsListResult) {
                FBAllConnectionsFetcher.this.lambda$createFriendsFetchingSuccessCallback$3$FBAllConnectionsFetcher(fBFriendsListResult);
            }
        }, new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$yc_ctzCjMAvNXTcHegHPG9QsI2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FBAllConnectionsFetcher.this.lambda$createFailureCallback$4$FBAllConnectionsFetcher(th);
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public int getCount() {
        if (TextUtils.isEmpty(this.mSearchString)) {
            return this.mFriendsCount;
        }
        return this.mSearchResults.size();
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        if (TextUtils.isEmpty(this.mSearchString)) {
            return this.mFullFriendsList;
        }
        return this.mSearchResults;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.HIGH;
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public String getSearchString() {
        return this.mSearchString;
    }

    public boolean hasMore() {
        FBFriendListGraphQL fBFriendListGraphQL = this.mFriendGraphQL;
        if (fBFriendListGraphQL == null || !fBFriendListGraphQL.hasNextPage()) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$createFriendsFetchingSuccessCallback$3$FBAllConnectionsFetcher(final FBFriendsListResult fBFriendsListResult) {
        fBFriendsListResult.mFriendsList.size();
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.people.fetchers.fb.FBAllConnectionsFetcher.AnonymousClass2 */

            public void run() {
                FBAllConnectionsFetcher fBAllConnectionsFetcher = FBAllConnectionsFetcher.this;
                fBAllConnectionsFetcher.mIsErrored = false;
                FBFriendsListResult fBFriendsListResult = fBFriendsListResult;
                fBAllConnectionsFetcher.mFriendsCount = fBFriendsListResult.mFriendsCount;
                fBAllConnectionsFetcher.addFriends(fBFriendsListResult.mFriendsList);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$FBAllConnectionsFetcher(String str) throws Exception {
        Context context = this.mContext;
        OkHttpClient okHttpClient = this.mOkHttpClient;
        this.mFriendGraphQL = new FBFriendListGraphQL(context, okHttpClient, ORDER_BY, str);
        this.mSearchGraphQL = new FBFriendSearchGraphQL(context, okHttpClient, str);
        loadFriends();
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public void searchForUser(String str) {
        this.mSearchString = str;
        if (this.mSearchGraphQL == null) {
            this.mIsErrored = true;
        } else if (TextUtils.isEmpty(str)) {
            this.mEnoughDataFetched = true;
            notifyPeopleListObservers();
        } else {
            this.mEnoughDataFetched = false;
            this.mSearchGraphQL.searchFriends(str, new FBFriendSearchGraphQL.SuccessCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$sY7RzIXiKrOTOZXsBBtnIOdCaJc2 */

                @Override // com.oculus.panelapp.people.graphql.FBFriendSearchGraphQL.SuccessCallback
                public final void callback(List list) {
                    FBAllConnectionsFetcher.this.lambda$searchForUser$2$FBAllConnectionsFetcher(list);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$yc_ctzCjMAvNXTcHegHPG9QsI2 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBAllConnectionsFetcher.this.lambda$createFailureCallback$4$FBAllConnectionsFetcher(th);
                }
            });
        }
    }

    @SuppressLint({"CheckResult"})
    public FBAllConnectionsFetcher(Context context, OkHttpClient okHttpClient, AbstractC13251zE<String> r6, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        ImmutableList<SocialUser> of = ImmutableList.of();
        this.mFullFriendsList = of;
        this.mSearchResults = of;
        this.mOkHttpClient = okHttpClient;
        this.mTokenDisposable = r6.A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$pfCllhWx79zMy7su6OYqThuxhE2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBAllConnectionsFetcher.this.lambda$new$0$FBAllConnectionsFetcher((String) obj);
            }
        }, new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$fcqAwnjP_qhoAITrwiy_yPVJyQ2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBAllConnectionsFetcher.this.mIsErrored = true;
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addFriends(List<FBSocialUser> list) {
        ImmutableList.Builder A02 = ImmutableList.A02();
        A02.A03(this.mFullFriendsList);
        A02.A03(list);
        this.mFullFriendsList = A02.build();
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setSearchResults(List<FBSocialUser> list) {
        this.mSearchResults = ImmutableList.A0A(list);
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mTokenDisposable.dispose();
        ImmutableList<SocialUser> of = ImmutableList.of();
        this.mFullFriendsList = of;
        this.mSearchResults = of;
        FBFriendListGraphQL fBFriendListGraphQL = this.mFriendGraphQL;
        if (fBFriendListGraphQL != null) {
            fBFriendListGraphQL.destroy();
        }
        FBFriendSearchGraphQL fBFriendSearchGraphQL = this.mSearchGraphQL;
        if (fBFriendSearchGraphQL != null) {
            fBFriendSearchGraphQL.destroy();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
    }

    public void fetchMore() {
        if (hasMore()) {
            this.mFriendGraphQL.fetchNext(new FBFriendListGraphQL.SuccessCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$AkGuIvzNKw3JvHkdqiM1rtZ3LVE2 */

                @Override // com.oculus.panelapp.people.graphql.FBFriendListGraphQL.SuccessCallback
                public final void callback(FBFriendsListResult fBFriendsListResult) {
                    FBAllConnectionsFetcher.this.lambda$createFriendsFetchingSuccessCallback$3$FBAllConnectionsFetcher(fBFriendsListResult);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBAllConnectionsFetcher$yc_ctzCjMAvNXTcHegHPG9QsI2 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBAllConnectionsFetcher.this.lambda$createFailureCallback$4$FBAllConnectionsFetcher(th);
                }
            });
        }
    }

    public /* synthetic */ void lambda$searchForUser$2$FBAllConnectionsFetcher(final List list) {
        list.size();
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.people.fetchers.fb.FBAllConnectionsFetcher.AnonymousClass1 */

            public void run() {
                FBAllConnectionsFetcher fBAllConnectionsFetcher = FBAllConnectionsFetcher.this;
                fBAllConnectionsFetcher.mIsErrored = false;
                fBAllConnectionsFetcher.setSearchResults(list);
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadFriends();
    }
}
