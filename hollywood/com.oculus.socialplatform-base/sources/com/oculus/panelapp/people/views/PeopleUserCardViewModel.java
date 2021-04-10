package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.profile.ProfilePresenceType;
import com.oculus.panelapp.people.views.holders.ViewHolderUtil;
import com.oculus.socialplatform.R;

public class PeopleUserCardViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleUserCardViewModel.class);
    public final Context mContext;
    public Drawable mCtaIcon;
    public boolean mIsHovered;
    public boolean mIsPresenceIconVisible;
    public String mLastActiveTime;
    public Drawable mLeftCtaIcon;
    public int mOnlineSubtitleColor;
    public Drawable mPresenceIcon;
    public Drawable mRightCtaIcon;
    public String mSubtitleText;
    public String mUsernameText;

    @Bindable
    public Drawable getCtaIcon() {
        return this.mCtaIcon;
    }

    @Bindable
    public String getLastActiveTime() {
        return this.mLastActiveTime;
    }

    @Bindable
    public Drawable getLeftCtaIcon() {
        return this.mLeftCtaIcon;
    }

    @ColorInt
    @Bindable
    public int getOnlineSubtitleColor() {
        return this.mOnlineSubtitleColor;
    }

    @Bindable
    public Drawable getPresenceIcon() {
        return this.mPresenceIcon;
    }

    @Bindable
    public int getPresenceIconVisibility() {
        if (!this.mIsPresenceIconVisible || this.mIsHovered) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public Drawable getRightCtaIcon() {
        return this.mRightCtaIcon;
    }

    @Bindable
    public int getSubtitleRowVisibility() {
        if (this.mIsHovered) {
            return 8;
        }
        return 0;
    }

    @Bindable
    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    @Bindable
    public String getUsernameText() {
        return this.mUsernameText;
    }

    public void setCtaIcon(Drawable drawable) {
        this.mCtaIcon = drawable;
        notifyPropertyChanged(47);
    }

    public void setIsHovered(boolean z) {
        this.mIsHovered = z;
        notifyPropertyChanged(64);
        notifyPropertyChanged(83);
    }

    public void setLastActiveTime(String str) {
        this.mLastActiveTime = str;
        notifyPropertyChanged(77);
    }

    public void setLeftCtaIcon(Drawable drawable) {
        this.mLeftCtaIcon = drawable;
        notifyPropertyChanged(38);
    }

    public void setPresenceIconType(ProfilePresenceType profilePresenceType) {
        ViewHolderUtil.PresenceIconInfo presenceIconInfo = ViewHolderUtil.getPresenceIconInfo(this.mContext, profilePresenceType);
        this.mIsPresenceIconVisible = presenceIconInfo.mIsPresenceIconVisible;
        this.mPresenceIcon = presenceIconInfo.mPresenceIcon;
        notifyPropertyChanged(64);
        notifyPropertyChanged(82);
    }

    public void setRightCtaIcon(Drawable drawable) {
        this.mRightCtaIcon = drawable;
        notifyPropertyChanged(12);
    }

    public void setShowOnlineSubtitle(boolean z) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = this.mContext.getTheme();
        int i = R.attr.ocSecondaryText;
        if (z) {
            i = R.attr.ocPositive;
        }
        theme.resolveAttribute(i, typedValue, true);
        this.mOnlineSubtitleColor = typedValue.data;
        notifyPropertyChanged(69);
    }

    public void setSubtitleText(String str) {
        this.mSubtitleText = str;
        notifyPropertyChanged(31);
    }

    public void setUsernameText(String str) {
        this.mUsernameText = str;
        notifyPropertyChanged(81);
    }

    public PeopleUserCardViewModel(Context context) {
        this.mContext = context;
    }
}
