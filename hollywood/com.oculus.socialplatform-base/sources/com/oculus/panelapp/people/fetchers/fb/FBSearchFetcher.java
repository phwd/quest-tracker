package com.oculus.panelapp.people.fetchers.fb;

import X.AbstractC12271xB;
import X.AbstractC12851yS;
import X.AbstractC13251zE;
import X.AnonymousClass1y2;
import android.annotation.SuppressLint;
import android.content.Context;
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
import com.oculus.panelapp.people.graphql.FBSearchGraphQL;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.List;
import okhttp3.OkHttpClient;

public class FBSearchFetcher extends BaseFetcher implements ISearchFetcher {
    public static final String TAG = LoggingUtil.tag(FBSearchFetcher.class);
    public Context mContext;
    public List<SocialUser> mInitialUserList;
    public boolean mIsFBSearchEnabled;
    public OkHttpClient mOkHttpClient;
    public FBSearchGraphQL mSearchGraphQL;
    public ImmutableList<SocialUser> mSearchResults;
    public String mSearchString;
    public AbstractC12271xB mTokenDisposable;

    public /* synthetic */ void lambda$new$1$FBSearchFetcher(Throwable th) throws Exception {
        this.mIsErrored = true;
    }

    public /* synthetic */ void lambda$null$2$FBSearchFetcher(List list) {
        this.mIsErrored = false;
        setSearchResults(list);
    }

    public /* synthetic */ void lambda$null$4$FBSearchFetcher() {
        this.mIsErrored = true;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
    }

    private FacebookGraphQLUtil.FailureCallback createFailureCallback() {
        return new FacebookGraphQLUtil.FailureCallback() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$00dLd6iCK1tevnOuFUH0YhvbLnM2 */

            @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
            public final void callback(Throwable th) {
                FBSearchFetcher.this.lambda$createFailureCallback$5$FBSearchFetcher(th);
            }
        };
    }

    private void setInitialData(List<SocialUser> list) {
        this.mInitialUserList = list;
        if (TextUtils.isEmpty(this.mSearchString)) {
            this.mEnoughDataFetched = true;
        }
        notifyPeopleListObservers();
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public int getCount() {
        if (TextUtils.isEmpty(this.mSearchString)) {
            return this.mInitialUserList.size();
        }
        return this.mSearchResults.size();
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
        return FetcherPriority.HIGH;
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public String getSearchString() {
        return this.mSearchString;
    }

    public /* synthetic */ void lambda$new$0$FBSearchFetcher(String str) throws Exception {
        if (this.mIsFBSearchEnabled) {
            this.mSearchGraphQL = new FBSearchGraphQL(this.mContext, this.mOkHttpClient, str);
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.ISearchFetcher
    public void searchForUser(String str) {
        this.mSearchString = str;
        this.mEnoughDataFetched = false;
        if (TextUtils.isEmpty(str) || !this.mIsFBSearchEnabled) {
            setSearchResults(ImmutableList.of());
            return;
        }
        FBSearchGraphQL fBSearchGraphQL = this.mSearchGraphQL;
        if (fBSearchGraphQL == null) {
            this.mIsErrored = true;
        } else {
            fBSearchGraphQL.searchUsers(str, new FBSearchGraphQL.SuccessCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$Y7msI3OdJehsJsKshOL_A62WioY2 */

                @Override // com.oculus.panelapp.people.graphql.FBSearchGraphQL.SuccessCallback
                public final void callback(List list) {
                    FBSearchFetcher.this.lambda$searchForUser$3$FBSearchFetcher(list);
                }
            }, new FacebookGraphQLUtil.FailureCallback() {
                /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$00dLd6iCK1tevnOuFUH0YhvbLnM2 */

                @Override // com.oculus.graphql.fb.FacebookGraphQLUtil.FailureCallback
                public final void callback(Throwable th) {
                    FBSearchFetcher.this.lambda$createFailureCallback$5$FBSearchFetcher(th);
                }
            });
        }
    }

    @SuppressLint({"CheckResult"})
    public FBSearchFetcher(Context context, OkHttpClient okHttpClient, AbstractC13251zE<String> r6, boolean z, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        ImmutableList<SocialUser> of = ImmutableList.of();
        this.mInitialUserList = of;
        this.mSearchResults = of;
        this.mOkHttpClient = okHttpClient;
        this.mIsFBSearchEnabled = z;
        this.mTokenDisposable = r6.A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$hJKikbHcfjCRFFEIefgFo22iyqI2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBSearchFetcher.this.lambda$new$0$FBSearchFetcher((String) obj);
            }
        }, new AbstractC12851yS() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$IGSA70YgXs4Uw3mfYTHlME7zBPA2 */

            @Override // X.AbstractC12851yS
            public final void accept(Object obj) {
                FBSearchFetcher.this.mIsErrored = true;
            }
        });
        loadInitialData();
    }

    private void loadInitialData() {
        setInitialData(ImmutableList.of());
    }

    private void setSearchResults(List<FBSocialUser> list) {
        this.mSearchResults = ImmutableList.A0A(list);
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mTokenDisposable.dispose();
        FBSearchGraphQL fBSearchGraphQL = this.mSearchGraphQL;
        if (fBSearchGraphQL != null) {
            fBSearchGraphQL.destroy();
        }
        ImmutableList<SocialUser> of = ImmutableList.of();
        this.mInitialUserList = of;
        this.mSearchResults = of;
    }

    public /* synthetic */ void lambda$createFailureCallback$5$FBSearchFetcher(Throwable th) {
        UiThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$VsV7BGb07oXATigA8fWPlUjvIg2 */

            public final void run() {
                FBSearchFetcher.this.mIsErrored = true;
            }
        });
        Log.e(TAG, "Failed to load friends");
    }

    public /* synthetic */ void lambda$searchForUser$3$FBSearchFetcher(List list) {
        list.size();
        UiThreadExecutor.getInstance().execute(new Runnable(list) {
            /* class com.oculus.panelapp.people.fetchers.fb.$$Lambda$FBSearchFetcher$NX00HsaxaUIVKKeesh2LHRYtHgw2 */
            public final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                FBSearchFetcher.this.lambda$null$2$FBSearchFetcher(this.f$1);
            }
        });
    }
}
