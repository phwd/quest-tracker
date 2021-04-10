package com.oculus.panelapp.social;

import android.content.Context;

public class SocialErrors {
    private static final int KICKED_FROM_PARTY_ERROR_CODE = 1891503;

    public static void handlePartyError(int i, String str, Context context) {
        if (i == KICKED_FROM_PARTY_ERROR_CODE) {
            SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_join_kicked_error", context.getResources().getString(R.string.anytime_tablet_social_party_join_as_kicked_user_failed), str);
        } else {
            SocialViewWarningToaster.showToast(context, "oculus_mobile_social_party_join_error", context.getResources().getString(R.string.anytime_tablet_social_party_join_failed), str);
        }
    }
}
