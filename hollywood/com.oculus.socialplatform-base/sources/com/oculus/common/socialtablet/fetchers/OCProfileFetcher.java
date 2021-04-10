package com.oculus.common.socialtablet.fetchers;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialViewerInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.vrshell.sharedprefs.PrefKey;
import com.oculus.vrshell.sharedprefs.SharedKeys;
import com.oculus.vrshell.sharedprefs.SharedPreferencesHelper;
import java.util.concurrent.TimeUnit;

public class OCProfileFetcher {
    public static final long OC_PROFILE_DATA_EXPIRATION_TIME_MS = TimeUnit.MINUTES.toMillis(1);
    public static final PrefKey OC_PROFILE_DATA_LAST_FETCHED_TIME_MS;
    public static final PrefKey OC_PROFILE_PROFILE_PHOTO;
    public static final PrefKey OC_PROFILE_USERNAME;
    public static final String TAG = LoggingUtil.tag(OCProfileFetcher.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchOCProfileAsyncQueryHandle;
    public long mLastFetchedTimeMs = SharedPreferencesHelper.getLong(this.mContext, OC_PROFILE_DATA_LAST_FETCHED_TIME_MS, RecyclerView.FOREVER_NS);
    public String mProfilePicUrl = SharedPreferencesHelper.getString(this.mContext, OC_PROFILE_PROFILE_PHOTO, "");
    public String mProfileUsername;

    public interface FetchCallback {
        void onOCProfileFetched(String str, String str2);
    }

    public void destroy() {
        this.mProfileUsername = null;
        this.mProfilePicUrl = null;
        this.mLastFetchedTimeMs = 0;
        clearFetchSocialViewerAsyncQueryHandle();
    }

    static {
        PrefKey prefKey = SharedKeys.FLAGS;
        OC_PROFILE_DATA_LAST_FETCHED_TIME_MS = new PrefKey(prefKey, "OC_PROFILE_DATA_LAST_FETCHED_TIME_MS");
        OC_PROFILE_USERNAME = new PrefKey(prefKey, "OC_PROFILE_USERNAME");
        OC_PROFILE_PROFILE_PHOTO = new PrefKey(prefKey, "OC_PROFILE_PROFILE_PHOTO");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialViewerAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchOCProfileAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchOCProfileAsyncQueryHandle = null;
        }
    }

    private boolean isCacheExpired() {
        long j = this.mLastFetchedTimeMs;
        if (j < SystemClock.elapsedRealtime() - OC_PROFILE_DATA_EXPIRATION_TIME_MS || j > SystemClock.elapsedRealtime()) {
            return true;
        }
        return false;
    }

    public void fetchOCProfile(final FetchCallback fetchCallback) {
        String str = this.mProfileUsername;
        if (!str.isEmpty()) {
            String str2 = this.mProfilePicUrl;
            if (!str2.isEmpty()) {
                fetchCallback.onOCProfileFetched(str, str2);
            }
        }
        if (isCacheExpired()) {
            clearFetchSocialViewerAsyncQueryHandle();
            this.mFetchOCProfileAsyncQueryHandle = HorizonContentProviderHelper.fetchSocialViewer(this.mContext, new HorizonContentProviderHelper.FetchSocialViewerInfoCallback() {
                /* class com.oculus.common.socialtablet.fetchers.OCProfileFetcher.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    Log.e(OCProfileFetcher.TAG, "fetchOCProfile: Failed to fetch social viewer info");
                    OCProfileFetcher.this.clearFetchSocialViewerAsyncQueryHandle();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchSocialViewerInfoCallback
                public void onSuccess(SocialViewerInfo socialViewerInfo) {
                    OCProfileFetcher oCProfileFetcher = OCProfileFetcher.this;
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    oCProfileFetcher.mLastFetchedTimeMs = elapsedRealtime;
                    SharedPreferencesHelper.putLong(oCProfileFetcher.mContext, OCProfileFetcher.OC_PROFILE_DATA_LAST_FETCHED_TIME_MS, elapsedRealtime);
                    OCProfileFetcher oCProfileFetcher2 = OCProfileFetcher.this;
                    String str = oCProfileFetcher2.mProfileUsername;
                    String str2 = socialViewerInfo.mAlias;
                    if (str != str2 || oCProfileFetcher2.mProfilePicUrl != socialViewerInfo.mProfilePhotoUrl) {
                        oCProfileFetcher2.mProfileUsername = str2;
                        oCProfileFetcher2.mProfilePicUrl = socialViewerInfo.mProfilePhotoUrl;
                        SharedPreferencesHelper.putString(oCProfileFetcher2.mContext, OCProfileFetcher.OC_PROFILE_USERNAME, str2);
                        OCProfileFetcher oCProfileFetcher3 = OCProfileFetcher.this;
                        SharedPreferencesHelper.putString(oCProfileFetcher3.mContext, OCProfileFetcher.OC_PROFILE_PROFILE_PHOTO, oCProfileFetcher3.mProfilePicUrl);
                        FetchCallback fetchCallback = fetchCallback;
                        OCProfileFetcher oCProfileFetcher4 = OCProfileFetcher.this;
                        fetchCallback.onOCProfileFetched(oCProfileFetcher4.mProfileUsername, oCProfileFetcher4.mProfilePicUrl);
                        OCProfileFetcher.this.clearFetchSocialViewerAsyncQueryHandle();
                    }
                }
            });
        }
    }

    public OCProfileFetcher(Context context) {
        this.mContext = context;
        this.mProfileUsername = SharedPreferencesHelper.getString(context, OC_PROFILE_USERNAME, "");
    }
}
