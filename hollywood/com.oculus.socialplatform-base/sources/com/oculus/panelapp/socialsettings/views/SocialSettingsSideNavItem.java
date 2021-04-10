package com.oculus.panelapp.socialsettings.views;

import android.content.res.Resources;
import com.oculus.socialplatform.R;

public enum SocialSettingsSideNavItem {
    ACTIVE_STATUS(R.string.social_settings_active_status_header, R.id.social_settings_side_nav_active_status),
    NOTIFICATIONS(R.string.social_settings_notifications_header, R.id.social_settings_side_nav_notifications),
    MESSENGER_ACCOUNT(R.string.social_settings_messenger_account_header, R.id.social_settings_side_nav_messenger_account);
    
    public final int mTitleId;
    public final int mViewId;

    public String getTitle(Resources resources) {
        return resources.getString(this.mTitleId);
    }

    public int getViewID() {
        return this.mViewId;
    }

    /* access modifiers changed from: public */
    SocialSettingsSideNavItem(int i, int i2) {
        this.mTitleId = i;
        this.mViewId = i2;
    }
}
