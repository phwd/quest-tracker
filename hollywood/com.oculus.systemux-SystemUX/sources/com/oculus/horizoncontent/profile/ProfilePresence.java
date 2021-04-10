package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.common.horizoncontent.R;

public class ProfilePresence {
    private String mPresenceString;
    private ProfilePresenceType mPresenceType;

    public ProfilePresence(ProfilePresenceType profilePresenceType, String str) {
        this.mPresenceType = profilePresenceType;
        this.mPresenceString = str;
    }

    public ProfilePresenceType getPresenceType() {
        return this.mPresenceType;
    }

    public String getPresenceString() {
        return this.mPresenceString;
    }

    @Nullable
    protected static String getLastActiveInVrString(Resources resources, Long l, String str) {
        boolean z = str.length() > 0;
        int timeAgoMinutes = getTimeAgoMinutes(l);
        if (timeAgoMinutes >= 60) {
            int i = timeAgoMinutes / 60;
            if (i >= 24) {
                int i2 = i / 24;
                if (i2 >= 3) {
                    return null;
                }
                if (z) {
                    return resources.getString(R.string.profile_tablet_last_active_time_in_vr_in_days, str, Integer.valueOf(i2));
                }
                return resources.getString(R.string.profile_tablet_last_active_time_in_oc_in_days, Integer.valueOf(i2));
            } else if (z) {
                return resources.getString(R.string.profile_tablet_last_active_time_in_vr_in_hours, str, Integer.valueOf(i));
            } else {
                return resources.getString(R.string.profile_tablet_last_active_time_in_oc_in_hours, Integer.valueOf(i));
            }
        } else if (z) {
            return resources.getString(R.string.profile_tablet_last_active_time_in_vr_in_minutes, str, Integer.valueOf(timeAgoMinutes));
        } else {
            return resources.getString(R.string.profile_tablet_last_active_time_in_oc_in_minutes, Integer.valueOf(timeAgoMinutes));
        }
    }

    protected static int getTimeAgoMinutes(Long l) {
        int currentTimeMillis = ((int) ((System.currentTimeMillis() / 1000) - l.longValue())) / 60;
        if (currentTimeMillis == 0) {
            return 1;
        }
        return currentTimeMillis;
    }
}
