package com.oculus.common.socialtablet.fetchers;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FBLinkingFetcher {
    public static final String TAG = LoggingUtil.tag(FBLinkingFetcher.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchLinkedAccountsInfoAsyncQueryHandle;
    public Optional<Boolean> mIsLinked = Optional.empty();
    public List<FetchLinkedStatusListener> mListeners = new ArrayList();

    public interface FetchLinkedStatusListener {
        void onLinkedStatusFetched(boolean z);
    }

    public void destroy() {
        this.mIsLinked = null;
        this.mListeners.clear();
        this.mListeners = null;
        clearFetchLinkedAccountsInfoAsyncQueryHandle();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchLinkedAccountsInfoAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchLinkedAccountsInfoAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchLinkedAccountsInfoAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyListeners() {
        List<FetchLinkedStatusListener> list = this.mListeners;
        if (list != null) {
            list.forEach(new Consumer() {
                /* class com.oculus.common.socialtablet.fetchers.$$Lambda$FBLinkingFetcher$NFm8eZtAoAvb8UTaN_MweNQlVP02 */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FBLinkingFetcher.this.lambda$notifyListeners$0$FBLinkingFetcher((FBLinkingFetcher.FetchLinkedStatusListener) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$notifyListeners$0$FBLinkingFetcher(FetchLinkedStatusListener fetchLinkedStatusListener) {
        fetchLinkedStatusListener.onLinkedStatusFetched(this.mIsLinked.get().booleanValue());
    }

    public void registerFBLinkedStatusListener(FetchLinkedStatusListener fetchLinkedStatusListener) {
        if (this.mIsLinked.isPresent()) {
            fetchLinkedStatusListener.onLinkedStatusFetched(this.mIsLinked.get().booleanValue());
        } else if (!this.mListeners.contains(fetchLinkedStatusListener)) {
            this.mListeners.add(fetchLinkedStatusListener);
        }
    }

    public void unregisterFBLinkedStatusListener(FetchLinkedStatusListener fetchLinkedStatusListener) {
        if (this.mListeners.contains(fetchLinkedStatusListener)) {
            this.mListeners.remove(fetchLinkedStatusListener);
        }
    }

    public FBLinkingFetcher(Context context) {
        this.mContext = context;
        fetchFBLinkedStatus();
    }

    private void fetchFBLinkedStatus() {
        clearFetchLinkedAccountsInfoAsyncQueryHandle();
        this.mFetchLinkedAccountsInfoAsyncQueryHandle = HorizonContentProviderHelper.fetchLinkedAccountsInfoForViewer(this.mContext, new HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback() {
            /* class com.oculus.common.socialtablet.fetchers.FBLinkingFetcher.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(FBLinkingFetcher.TAG, "fetchFBLinkedStatus: Failed to fetch FB-linked status.");
                FBLinkingFetcher.this.mIsLinked = Optional.of(false);
                FBLinkingFetcher.this.notifyListeners();
                FBLinkingFetcher.this.clearFetchLinkedAccountsInfoAsyncQueryHandle();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchLinkedAccountsInfoForViewerCallback
            public void onSuccess(LinkedAccountsInfo linkedAccountsInfo) {
                boolean isFbLinked = linkedAccountsInfo.isFbLinked();
                FBLinkingFetcher.this.mIsLinked = Optional.of(Boolean.valueOf(isFbLinked));
                FBLinkingFetcher.this.notifyListeners();
                FBLinkingFetcher.this.clearFetchLinkedAccountsInfoAsyncQueryHandle();
            }
        });
    }
}
