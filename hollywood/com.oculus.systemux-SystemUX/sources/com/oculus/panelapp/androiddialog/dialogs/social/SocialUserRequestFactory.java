package com.oculus.panelapp.androiddialog.dialogs.social;

import android.content.Context;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;

public class SocialUserRequestFactory {
    private final Context mContext;

    public SocialUserRequestFactory(Context context) {
        this.mContext = context;
    }

    public AsyncQueryHandle fetchSocialViewer(HorizonContentProviderHelper.FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback) {
        return HorizonContentProviderHelper.fetchSocialViewer(this.mContext, fetchSocialViewerInfoCallback);
    }
}
