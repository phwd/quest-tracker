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

public class OCPYMKFetcher extends BaseFetcher {
    public static final String TAG = LoggingUtil.tag(OCPYMKFetcher.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchPYMKsAsyncQueryHandle;
    public List<SocialUser> mPYMK = new ArrayList();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchPYMKsAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchPYMKsAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchPYMKsAsyncQueryHandle = null;
        }
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        return this.mPYMK;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.LOW;
    }

    public void removeUserFromPYMK(SocialUser socialUser) {
        this.mPYMK.remove(socialUser);
        notifyPeopleListObservers();
    }

    @VisibleForTesting(otherwise = 2)
    public void setPYMK(List<SocialUser> list) {
        this.mPYMK = list;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public OCPYMKFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
        loadPYMK();
    }

    private void loadPYMK() {
        clearFetchPYMKsAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchPYMKsAsyncQueryHandle = HorizonContentProviderHelper.fetchPYMKs(this.mContext, new HorizonContentProviderHelper.FetchPYMKsCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCPYMKFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                OCPYMKFetcher.this.logFetchFailure(ActionId.FETCH_OCULUS_PYMK, currentTimeMillis);
                OCPYMKFetcher oCPYMKFetcher = OCPYMKFetcher.this;
                oCPYMKFetcher.mIsErrored = true;
                oCPYMKFetcher.clearFetchPYMKsAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPYMKsCallback
            public void onSuccess(ArrayList<SocialUser> arrayList) {
                OCPYMKFetcher.this.logFetchSuccess(ActionId.FETCH_OCULUS_PYMK, currentTimeMillis);
                OCPYMKFetcher oCPYMKFetcher = OCPYMKFetcher.this;
                oCPYMKFetcher.mIsErrored = false;
                oCPYMKFetcher.setPYMK(arrayList);
                OCPYMKFetcher.this.clearFetchPYMKsAsyncQueryHandle();
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        clearFetchPYMKsAsyncQueryHandle();
        this.mPYMK.clear();
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        loadPYMK();
    }
}
