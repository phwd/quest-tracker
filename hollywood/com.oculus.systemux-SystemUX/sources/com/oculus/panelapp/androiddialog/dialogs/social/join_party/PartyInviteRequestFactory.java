package com.oculus.panelapp.androiddialog.dialogs.social.join_party;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;

public class PartyInviteRequestFactory {
    private static final String TAG = LoggingUtil.tag(PartyInviteRequestFactory.class);
    private final Context mContext;

    public PartyInviteRequestFactory(Context context) {
        this.mContext = context;
    }

    public AsyncQueryHandle joinParty(String str, String str2, String str3, HorizonContentProviderHelper.SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
        try {
            return HorizonContentProviderHelper.joinParty(this.mContext, str, str2, str3, singleIDCallbackWithErrorCode);
        } catch (Exception e) {
            Log.e(TAG, "Error during joinParty: ", e);
            singleIDCallbackWithErrorCode.onError(0);
            return null;
        }
    }

    public AsyncQueryHandle fetchPartyInviteInfoWithNonce(String str, String str2, HorizonContentProviderHelper.FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
        try {
            return HorizonContentProviderHelper.fetchPartyInviteInfoWithNonce(this.mContext, str, str2, fetchPartyInviteInfoCallback);
        } catch (Exception e) {
            Log.e(TAG, "Error during fetchPartyInviteInfoWithNonce: ", e);
            fetchPartyInviteInfoCallback.onError();
            return null;
        }
    }

    public AsyncQueryHandle fetchPartyInviteInfo(String str, HorizonContentProviderHelper.FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
        try {
            return HorizonContentProviderHelper.fetchPartyInviteInfo(this.mContext, str, fetchPartyInviteInfoCallback);
        } catch (Exception e) {
            Log.e(TAG, "Error during fetchPartyInviteInfo: ", e);
            fetchPartyInviteInfoCallback.onError();
            return null;
        }
    }
}
