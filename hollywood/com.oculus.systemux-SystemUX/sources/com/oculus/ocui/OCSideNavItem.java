package com.oculus.ocui;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.oculus.common.ocui.BR;

public class OCSideNavItem extends BaseObservable {
    private int mBadgeCount;
    private boolean mEnabled;
    private final String mTitle;
    private final String mUri;
    private final Integer mViewId;

    public OCSideNavItem(String str, String str2, int i) {
        this.mTitle = str;
        this.mUri = str2;
        this.mViewId = Integer.valueOf(i);
        this.mBadgeCount = 0;
        this.mEnabled = true;
    }

    public OCSideNavItem(String str) {
        this.mTitle = str;
        this.mUri = null;
        this.mViewId = null;
        this.mBadgeCount = 0;
        this.mEnabled = true;
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

    @Bindable
    public int getBadgeCount() {
        return this.mBadgeCount;
    }

    public void setBadgeCount(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mBadgeCount = i;
        notifyPropertyChanged(BR.badgeCount);
    }

    @Bindable
    public boolean getEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        notifyPropertyChanged(BR.enabled);
    }
}
