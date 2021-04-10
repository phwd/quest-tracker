package com.oculus.panelapp.social;

import android.content.Context;
import com.oculus.socialplatform.R;

public class SocialErrors {
    public static final int KICKED_FROM_PARTY_ERROR_CODE = 1891503;

    public static void handlePartyError(int i, String str, Context context) {
        String string;
        String str2;
        if (i == 1891503) {
            string = context.getResources().getString(R.string.anytime_tablet_social_party_join_as_kicked_user_failed);
            str2 = "oculus_mobile_social_party_join_kicked_error";
        } else {
            string = context.getResources().getString(R.string.anytime_tablet_social_party_join_failed);
            str2 = "oculus_mobile_social_party_join_error";
        }
        SocialViewWarningToaster.showToast(context, str2, string, str);
    }
}
