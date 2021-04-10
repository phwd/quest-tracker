package com.oculus.panelapp.socialandroidbackpanel.views.join_party;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;

public class JoinPartyRequestFactory {
    public static final String TAG = LoggingUtil.tag(JoinPartyRequestFactory.class);
    public final Context mContext;

    public AsyncQueryHandle joinParty(String str, String str2, String str3, HorizonContentProviderHelper.SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
        try {
            return HorizonContentProviderHelper.joinParty(this.mContext, str, str2, str3, singleIDCallbackWithErrorCode);
        } catch (Exception e) {
            Log.e(TAG, "Error during joinParty: ", e);
            singleIDCallbackWithErrorCode.onError(0);
            return null;
        }
    }

    public JoinPartyRequestFactory(Context context) {
        this.mContext = context;
    }
}
