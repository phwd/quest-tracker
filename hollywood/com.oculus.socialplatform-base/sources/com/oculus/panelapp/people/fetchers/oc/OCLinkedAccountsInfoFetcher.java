package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.people.fetchers.BaseFetcher;
import com.oculus.panelapp.people.fetchers.FetcherPriority;
import java.util.List;

public class OCLinkedAccountsInfoFetcher extends BaseFetcher {
    public static final String TAG = LoggingUtil.tag(OCLinkedAccountsInfoFetcher.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchLinkedAccountsInfoAsyncQueryHandle;
    public LinkedAccountsInfo mLinkedAccountsInfo;

    @Override // com.oculus.panelapp.people.fetchers.IFetcher
    public List<SocialUser> getData() {
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchSocialViewerAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchLinkedAccountsInfoAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchLinkedAccountsInfoAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setLinkedAccountsInfo(LinkedAccountsInfo linkedAccountsInfo) {
        this.mLinkedAccountsInfo = linkedAccountsInfo;
        this.mIsErrored = false;
        this.mEnoughDataFetched = true;
        notifyPeopleListObservers();
    }

    public LinkedAccountsInfo getLinkedAccountsInfo() {
        return this.mLinkedAccountsInfo;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public String getLoggingTag() {
        return TAG;
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public FetcherPriority getRefetchPriority() {
        return FetcherPriority.LOW;
    }

    public OCLinkedAccountsInfoFetcher(Context context, SocialLogger socialLogger) {
        super(context, socialLogger);
        this.mContext = context;
    }

    private void fetchLinkedAccountsInfo() {
        clearFetchSocialViewerAsyncQueryHandle();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mFetchLinkedAccountsInfoAsyncQueryHandle = HorizonContentProviderHelper.fetchLinkedAccountsInfoForViewer(this.mContext, new HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCLinkedAccountsInfoFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                OCLinkedAccountsInfoFetcher.this.logFetchFailure(ActionId.FETCH_OCULUS_LINKED_ACCOUNTS, currentTimeMillis);
                OCLinkedAccountsInfoFetcher oCLinkedAccountsInfoFetcher = OCLinkedAccountsInfoFetcher.this;
                oCLinkedAccountsInfoFetcher.mIsErrored = true;
                oCLinkedAccountsInfoFetcher.clearFetchSocialViewerAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback
            public void onSuccess(LinkedAccountsInfo linkedAccountsInfo) {
                OCLinkedAccountsInfoFetcher.this.logFetchSuccess(ActionId.FETCH_OCULUS_LINKED_ACCOUNTS, currentTimeMillis);
                OCLinkedAccountsInfoFetcher.this.setLinkedAccountsInfo(linkedAccountsInfo);
                OCLinkedAccountsInfoFetcher.this.clearFetchSocialViewerAsyncQueryHandle();
            }
        });
    }

    @Override // com.oculus.panelapp.people.fetchers.IFetcher, com.oculus.panelapp.people.fetchers.BaseFetcher
    public void destroy() {
        super.destroy();
        this.mContext = null;
        this.mLinkedAccountsInfo = null;
        clearFetchSocialViewerAsyncQueryHandle();
    }

    @Override // com.oculus.panelapp.people.fetchers.BaseFetcher
    public void refetchData() {
        fetchLinkedAccountsInfo();
    }
}
