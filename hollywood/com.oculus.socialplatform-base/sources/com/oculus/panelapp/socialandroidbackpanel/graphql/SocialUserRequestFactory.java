package com.oculus.panelapp.socialandroidbackpanel.graphql;

import android.content.Context;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;

public class SocialUserRequestFactory {
    public final Context mContext;

    public AsyncQueryHandle fetchSocialViewer(HorizonContentProviderHelper.FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback) {
        return HorizonContentProviderHelper.fetchSocialViewer(this.mContext, fetchSocialViewerInfoCallback);
    }

    public SocialUserRequestFactory(Context context) {
        this.mContext = context;
    }
}
