package com.oculus.panelapp.socialandroidbackpanel.views.create_party;

import android.content.res.Resources;
import com.oculus.socialplatform.R;

public final class ApplicationStringsUtil {
    public static String getApplicationSubtitleAvailability(Resources resources, int i, int i2) {
        int i3;
        if (i >= i2) {
            i3 = R.string.social_create_vr_invite_destination_availability_all;
        } else if (i != 1) {
            return resources.getString(R.string.social_create_vr_invite_destination_availability_some, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            i3 = R.string.social_create_vr_invite_destination_availability_only_you;
        }
        return resources.getString(i3);
    }

    public static String getApplicationSubtitle(Resources resources, int i, int i2, int i3) {
        return String.format("%s  %s  %s", resources.getString(R.string.social_create_vr_invite_destination_subtitle, String.valueOf(i)), resources.getString(R.string.social_create_vr_invite_destination_concat_symbol), getApplicationSubtitleAvailability(resources, i2, i3));
    }
}
