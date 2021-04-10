package com.oculus.panelapp.androiddialog.dialogs.social;

import android.content.Context;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;

public class SocialPartyPrivacyRequestFactory {
    private final Context mContext;

    public SocialPartyPrivacyRequestFactory(Context context) {
        this.mContext = context;
    }

    public AsyncQueryHandle fetchPartyPrivacyInfo(String str, HorizonContentProviderHelper.FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback) {
        return HorizonContentProviderHelper.fetchPartyPrivacyInfo(this.mContext, str, fetchPartyPrivacyInfoCallback);
    }

    public AsyncQueryHandle activatePartyLinkInvite(String str, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        return HorizonContentProviderHelper.activatePartyLinkInvite(this.mContext, str, singleIDCallback);
    }

    public AsyncQueryHandle deactivatePartyLinkInvite(String str, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        return HorizonContentProviderHelper.deactivatePartyLinkInvite(this.mContext, str, singleIDCallback);
    }

    public AsyncQueryHandle setPartyType(String str, SocialPartyType socialPartyType, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        return HorizonContentProviderHelper.setPartyType(this.mContext, str, socialPartyType.toTypeForMutation(), singleIDCallback);
    }
}
