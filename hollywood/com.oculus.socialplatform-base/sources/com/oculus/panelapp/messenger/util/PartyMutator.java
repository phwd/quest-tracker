package com.oculus.panelapp.messenger.util;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;

public class PartyMutator {
    public static final String TAG = LoggingUtil.tag(PartyMutator.class);
    public Context mContext;
    @Nullable
    public AsyncQueryHandle mSendPartyLinkAsyncQueryHandle;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearSendPartyLinkAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mSendPartyLinkAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mSendPartyLinkAsyncQueryHandle = null;
        }
    }

    public boolean isSendPartyLinkLoading() {
        if (this.mSendPartyLinkAsyncQueryHandle != null) {
            return true;
        }
        return false;
    }

    public PartyMutator(Context context) {
        this.mContext = context;
    }

    public void destroy() {
        clearSendPartyLinkAsyncQueryHandle();
    }

    public void sendPartyLinkToThread(final long j, final HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        clearSendPartyLinkAsyncQueryHandle();
        this.mSendPartyLinkAsyncQueryHandle = HorizonContentProviderHelper.createOrUpdateCustomParty(this.mContext, ImmutableMap.A05("message_thread_key", String.valueOf(j)), new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.messenger.util.PartyMutator.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Log.e(PartyMutator.TAG, "Error sending party link to thread");
                PartyMutator.this.clearSendPartyLinkAsyncQueryHandle();
                singleIDCallback.onError();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyMutator.this.clearSendPartyLinkAsyncQueryHandle();
                singleIDCallback.onSuccess(str);
            }
        });
    }
}
