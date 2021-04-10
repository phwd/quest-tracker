package com.oculus.ocui;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class OCSideNavItem extends AnonymousClass1uc {
    public int mBadgeCount;
    public boolean mEnabled;
    public final String mTitle;
    public final String mUri;
    public final Integer mViewId;

    @Bindable
    public int getBadgeCount() {
        return this.mBadgeCount;
    }

    @Bindable
    public boolean getEnabled() {
        return this.mEnabled;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getUri() {
        return this.mUri;
    }

    public Integer getViewId() {
        return this.mViewId;
    }

    public void setBadgeCount(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mBadgeCount = i;
        notifyPropertyChanged(172);
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        notifyPropertyChanged(174);
    }

    public OCSideNavItem(String str) {
        this.mTitle = str;
        this.mUri = null;
        this.mViewId = null;
        this.mBadgeCount = 0;
        this.mEnabled = true;
    }

    public OCSideNavItem(String str, String str2, int i) {
        this.mTitle = str;
        this.mUri = str2;
        this.mViewId = Integer.valueOf(i);
        this.mBadgeCount = 0;
        this.mEnabled = true;
    }
}
