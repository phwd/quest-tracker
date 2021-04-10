package com.oculus.horizoncontent.profile;

import android.content.res.Resources;
import androidx.annotation.Nullable;
import com.oculus.socialplatform.R;

public class ProfilePresence {
    public String mPresenceString;
    public ProfilePresenceType mPresenceType;

    public String getPresenceString() {
        return this.mPresenceString;
    }

    public ProfilePresenceType getPresenceType() {
        return this.mPresenceType;
    }

    public ProfilePresence(ProfilePresenceType profilePresenceType, String str) {
        this.mPresenceType = profilePresenceType;
        this.mPresenceString = str;
    }

    @Nullable
    public static String getLastActiveInVrString(Resources resources, Long l, String str) {
        int i;
        Object[] objArr;
        boolean z = false;
        if (str.length() > 0) {
            z = true;
        }
        int timeAgoMinutes = getTimeAgoMinutes(l);
        if (timeAgoMinutes >= 60) {
            timeAgoMinutes /= 60;
            if (timeAgoMinutes >= 24) {
                timeAgoMinutes /= 24;
                if (timeAgoMinutes >= 3) {
                    return null;
                }
                if (z) {
                    i = R.string.profile_tablet_last_active_time_in_vr_in_days;
                } else {
                    i = R.string.profile_tablet_last_active_time_in_oc_in_days;
                    objArr = new Object[]{Integer.valueOf(timeAgoMinutes)};
                    return resources.getString(i, objArr);
                }
            } else if (z) {
                i = R.string.profile_tablet_last_active_time_in_vr_in_hours;
            } else {
                i = R.string.profile_tablet_last_active_time_in_oc_in_hours;
                objArr = new Object[]{Integer.valueOf(timeAgoMinutes)};
                return resources.getString(i, objArr);
            }
        } else if (z) {
            i = R.string.profile_tablet_last_active_time_in_vr_in_minutes;
        } else {
            i = R.string.profile_tablet_last_active_time_in_oc_in_minutes;
            objArr = new Object[]{Integer.valueOf(timeAgoMinutes)};
            return resources.getString(i, objArr);
        }
        objArr = new Object[]{str, Integer.valueOf(timeAgoMinutes)};
        return resources.getString(i, objArr);
    }

    public static int getTimeAgoMinutes(Long l) {
        int currentTimeMillis = ((int) ((System.currentTimeMillis() / 1000) - l.longValue())) / 60;
        if (currentTimeMillis == 0) {
            return 1;
        }
        return currentTimeMillis;
    }
}
