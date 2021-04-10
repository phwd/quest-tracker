package com.oculus.panelapp.people.fetchers.fb;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC13251zE;
import X.AnonymousClass1y2;
import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.graphql.fb.FacebookGraphQLUtil;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import com.oculus.panelapp.people.graphql.FBFriendRequestsGraphQL;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;

public class FBFriendRequestFetcher extends BaseFetcher {
    public static final Integer NUM_INITIAL_FETCH = 20;
    public static final String TAG = LoggingUtil.tag(FBFriendRequestFetcher.class);
    public Context mContext;
    public FBFriendRequestsGraphQL mFriendRequestGraphQL;
    public List<SocialUser> mFriendRequests = new ArrayList();
    public OkHttpClient mOkHttpClient;
    public AbstractC12271xB mTokenDisposable;

    public /* synthetic */ void lambda$null$2$FBFriendRequestFetcher(List list) {
        this.mIsErrored = false;
        setFriendRequests(list);
    }

    public /* synthetic */ void lambda$null$4$FBFriendRequestFetcher() {
        this.mIsErrored = true;
    }

    private FacebookGraphQLUtil.FailureCallback createFailureCallback() {
        return new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$ykMxYW3B1VlanEAmuy3eAjV2cQM2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FBFriendRequestFetcher.this.lambda$createFailureCallback$5$FBFriendRequestFetcher(th);
            }
        };
    }

    private void loadFriendRequests() {
        FBFriendRequestsGraphQL fBFriendRequestsGraphQL = this.mFriendRequestGraphQL;
        if (fBFriendRequestsGraphQL == null) {
            this.mIsErrored = true;
        } else {
            fBFriendRequestsGraphQL.getFriendRequests(NUM_INITIAL_FETCH, new FBFriendRequestsGraphQL.SuccessCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$XjbTt9CaPy3eETd_oP5NNQLdN9Y2 */

                @Override // com.oculus.panelapp.people.graphql.FBFriendRequestsGraphQL.SuccessCallback
                public final void callback(List list) {
                    FBFriendRequestFetcher.this.lambda$loadFriendRequests$3$FBFriendRequestFetcher(list);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$ykMxYW3B1VlanEAmuy3eAjV2cQM2 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBFriendRequestFetcher.this.lambda$createFailureCallback$5$FBFriendRequestFetcher(th);
                }
            });
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

    public /* synthetic */ void lambda$new$0$FBFriendRequestFetcher(String str) throws Exception {
        this.mFriendRequestGraphQL = new FBFriendRequestsGraphQL(this.mContext, this.mOkHttpClient, str);
        loadFriendRequests();
    }

    public /* synthetic */ void lambda$new$1$FBFriendRequestFetcher(Throwable th) throws Exception {
        Log.e(TAG, "Failed to load token for fetching FB friend requests");
        this.mIsErrored = true;
    }

    public void removeUserFriendRequest(SocialUser socialUser) {
        this.mFriendRequests.remove(socialUser);
        notifyPeopleListObservers();
    }

    public void setFriendRequests(List<FBSocialUser> list) {
        this.mFriendRequests = new ArrayList(list);
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public FBFriendRequestFetcher(Context context, OkHttpClient okHttpClient, AbstractC13251zE<String> r6, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        this.mOkHttpClient = okHttpClient;
        this.mTokenDisposable = r6.A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$YUlZbeBggp8VScn6iqLQ5beIp182 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBFriendRequestFetcher.this.lambda$new$0$FBFriendRequestFetcher((String) obj);
            }
        }, new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$_0GrD5fiwrEfVyloZYATmsPwDOs2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBFriendRequestFetcher.this.lambda$new$1$FBFriendRequestFetcher((Throwable) obj);
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mTokenDisposable.dispose();
        this.mFriendRequests.clear();
        FBFriendRequestsGraphQL fBFriendRequestsGraphQL = this.mFriendRequestGraphQL;
        if (fBFriendRequestsGraphQL != null) {
            fBFriendRequestsGraphQL.destroy();
        }
    }

    public /* synthetic */ void lambda$createFailureCallback$5$FBFriendRequestFetcher(Throwable th) {
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$JcABf2dBg1r3E7_HdGZSMqcwCE2 */

            public final void run() {
                FBFriendRequestFetcher.this.mIsErrored = true;
            }
        });
        Log.e(TAG, "Failed to load FB friend requests");
    }

    public /* synthetic */ void lambda$loadFriendRequests$3$FBFriendRequestFetcher(List list) {
        list.size();
        UiThreadExecutor.getInstance().execute(new Runnable(list) {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBFriendRequestFetcher$l1vfgS3lhib1SigI7X6FFAHZ22c2 */
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                FBFriendRequestFetcher.this.lambda$null$2$FBFriendRequestFetcher(this.f$1);
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadFriendRequests();
    }
}
