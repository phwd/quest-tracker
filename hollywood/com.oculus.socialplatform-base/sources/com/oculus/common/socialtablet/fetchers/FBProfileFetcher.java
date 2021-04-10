package com.oculus.common.socialtablet.fetchers;

import X.AbstractC12271xB;
import X.AbstractC12361xL;
import X.AbstractC12851yS;
import X.AbstractC13251zE;
import X.AnonymousClass1y2;
import X.AnonymousClass219;
import X.C13011yj;
import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountResult;
import com.oculus.common.fbaccountsmanager.RxMessengerVrAccountsContentProviderClient;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.fetchers.FBProfileFetcher;
import com.oculus.common.socialtablet.util.ExecutorUtil;
import com.oculus.vrshell.sharedprefs.PrefKey;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class FBProfileFetcher {
    public static final long FB_PROFILE_DATA_EXPIRATION_TIME_MS = TimeUnit.MINUTES.toMillis(1);
    public static final PrefKey FB_PROFILE_DATA_LAST_FETCHED_TIME_MS;
    public static final PrefKey FB_PROFILE_PROFILE_PHOTO;
    public static final PrefKey FB_PROFILE_USERNAME;
    public static final String TAG = LoggingUtil.tag(FBProfileFetcher.class);
    public Context mContext;
    @Nullable
    public AbstractC12271xB mFetchFBUserQueryDisposable;
    public long mLastFetchedTimeMs = SharedPreferencesHelper.getLong(this.mContext, FB_PROFILE_DATA_LAST_FETCHED_TIME_MS, RecyclerView.FOREVER_NS);
    public String mProfilePicUrl = SharedPreferencesHelper.getString(this.mContext, FB_PROFILE_PROFILE_PHOTO, "");
    public String mProfileUsername;

    public interface FetchCallback {
        void onFBProfileFetched(String str, String str2);
    }

    public void destroy() {
        this.mProfileUsername = null;
        this.mProfilePicUrl = null;
        this.mLastFetchedTimeMs = 0;
        AbstractC12271xB r0 = this.mFetchFBUserQueryDisposable;
        if (r0 != null) {
            r0.dispose();
            this.mFetchFBUserQueryDisposable = null;
        }
    }

    static {
        PrefKey prefKey = SharedKeys.FLAGS;
        FB_PROFILE_DATA_LAST_FETCHED_TIME_MS = new PrefKey(prefKey, "FB_PROFILE_DATA_LAST_FETCHED_TIME_MS");
        FB_PROFILE_USERNAME = new PrefKey(prefKey, "FB_PROFILE_USERNAME");
        FB_PROFILE_PROFILE_PHOTO = new PrefKey(prefKey, "FB_PROFILE_PROFILE_PHOTO");
    }

    private boolean isCacheExpired() {
        long j = this.mLastFetchedTimeMs;
        if (j < SystemClock.elapsedRealtime() - FB_PROFILE_DATA_EXPIRATION_TIME_MS || j > SystemClock.elapsedRealtime()) {
            return true;
        }
        return false;
    }

    public void fetchFBProfile(FetchCallback fetchCallback) {
        String str = this.mProfileUsername;
        if (!str.isEmpty()) {
            String str2 = this.mProfilePicUrl;
            if (!str2.isEmpty()) {
                fetchCallback.onFBProfileFetched(str, str2);
            }
        }
        if (isCacheExpired()) {
            AbstractC13251zE<Optional<MessengerVrAccountResult>> accountData = RxMessengerVrAccountsContentProviderClient.getAccountData(this.mContext);
            AbstractC12361xL r2 = ExecutorUtil.SCHEDULER;
            AnonymousClass219.A01(r2, "scheduler is null");
            this.mFetchFBUserQueryDisposable = new C13011yj(accountData, r2).A03(AnonymousClass1y2.A00()).A05(new AbstractC12851yS(fetchCallback) {
                /* class com.oculus.common.socialtablet.fetchers.$$Lambda$FBProfileFetcher$QwwZWZPr5W2doZ54khz66MacE2 */
                public final /* synthetic */ FBProfileFetcher.FetchCallback f$1;

                {
                    this.f$1 = r2;
                }

                @Override // X.AbstractC12851yS
                public final void accept(Object obj) {
                    FBProfileFetcher.this.lambda$fetchFBProfile$0$FBProfileFetcher(this.f$1, (Optional) obj);
                }
            }, $$Lambda$FBProfileFetcher$GS0B2xymD1M4BM1ErpGN3ANoeqw2.INSTANCE);
        }
    }

    public /* synthetic */ void lambda$fetchFBProfile$0$FBProfileFetcher(FetchCallback fetchCallback, Optional optional) throws Exception {
        String str = (String) optional.flatMap($$Lambda$asJG_p0aLA2txl4eiK8lDj7RaE2.INSTANCE).orElse("");
        String str2 = (String) optional.flatMap($$Lambda$5UYnGeIheEhIMNozIKLF9I5vmRY2.INSTANCE).orElse("");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mLastFetchedTimeMs = elapsedRealtime;
        SharedPreferencesHelper.putLong(this.mContext, FB_PROFILE_DATA_LAST_FETCHED_TIME_MS, elapsedRealtime);
        if (!str.equals(this.mProfileUsername) || !str2.equals(this.mProfilePicUrl)) {
            this.mProfileUsername = str;
            this.mProfilePicUrl = str2;
            SharedPreferencesHelper.putString(this.mContext, FB_PROFILE_USERNAME, str);
            SharedPreferencesHelper.putString(this.mContext, FB_PROFILE_PROFILE_PHOTO, this.mProfilePicUrl);
            fetchCallback.onFBProfileFetched(this.mProfileUsername, this.mProfilePicUrl);
        }
    }

    public FBProfileFetcher(Context context) {
        this.mContext = context;
        this.mProfileUsername = SharedPreferencesHelper.getString(context, FB_PROFILE_USERNAME, "");
    }
}
