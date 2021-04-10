package com.oculus.panelapp.people.fetchers.oc;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.ArrayList;

public class OCPeopleNearbyLoader {
    public final Context mContext;
    @Nullable
    public AsyncQueryHandle mFetchPeopleNearbyAsyncQueryHandle;
    @Nullable
    public AsyncQueryHandle mFetchPeopleNearbyAsyncQueryHandleAll;

    public interface PeopleNearbyLoadingCallback {
        void onError();

        void onSuccess(ArrayList<SocialUser> arrayList);
    }

    public void loadPeopleNearby(final String str, @Nullable Integer num, final PeopleNearbyLoadingCallback peopleNearbyLoadingCallback) {
        final boolean z;
        if (num == null) {
            z = true;
            clearFetchPeopleNearbyAsyncQueryHandleAll();
        } else {
            z = false;
            clearFetchPeopleNearbyAsyncQueryHandle();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        AnonymousClass1 r2 = new HorizonContentProviderHelper.FetchPeopleNearbyCallback() {
            /* class com.oculus.panelapp.people.fetchers.oc.OCPeopleNearbyLoader.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                boolean z;
                try {
                    System.currentTimeMillis();
                    peopleNearbyLoadingCallback.onError();
                    if (!z) {
                        OCPeopleNearbyLoader.this.clearFetchPeopleNearbyAsyncQueryHandle();
                    }
                } finally {
                    if (z) {
                        OCPeopleNearbyLoader.this.clearFetchPeopleNearbyAsyncQueryHandleAll();
                        throw th;
                    } else {
                        OCPeopleNearbyLoader.this.clearFetchPeopleNearbyAsyncQueryHandle();
                    }
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchPeopleNearbyCallback
            public void onSuccess(ArrayList<SocialUser> arrayList) {
                boolean z;
                try {
                    System.currentTimeMillis();
                    arrayList.size();
                    peopleNearbyLoadingCallback.onSuccess(arrayList);
                    if (!z) {
                        OCPeopleNearbyLoader.this.clearFetchPeopleNearbyAsyncQueryHandle();
                    }
                } finally {
                    if (z) {
                        OCPeopleNearbyLoader.this.clearFetchPeopleNearbyAsyncQueryHandleAll();
                        throw th;
                    } else {
                        OCPeopleNearbyLoader.this.clearFetchPeopleNearbyAsyncQueryHandle();
                    }
                }
            }
        };
        Context context = this.mContext;
        if (z) {
            this.mFetchPeopleNearbyAsyncQueryHandleAll = HorizonContentProviderHelper.fetchPeopleNearby(context, null, r2);
        } else {
            this.mFetchPeopleNearbyAsyncQueryHandle = HorizonContentProviderHelper.fetchPeopleNearby(context, num, r2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchPeopleNearbyAsyncQueryHandle() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchPeopleNearbyAsyncQueryHandle;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchPeopleNearbyAsyncQueryHandle = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearFetchPeopleNearbyAsyncQueryHandleAll() {
        AsyncQueryHandle asyncQueryHandle = this.mFetchPeopleNearbyAsyncQueryHandleAll;
        if (asyncQueryHandle != null) {
            asyncQueryHandle.destroy();
            this.mFetchPeopleNearbyAsyncQueryHandleAll = null;
        }
    }

    public OCPeopleNearbyLoader(Context context) {
        this.mContext = context;
    }

    public void destroy() {
        clearFetchPeopleNearbyAsyncQueryHandle();
        clearFetchPeopleNearbyAsyncQueryHandleAll();
    }
}
