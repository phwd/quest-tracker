package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.FBSocialUser;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.panelapp.people.views.holders.ViewHolderUtil;
import com.oculus.socialplatform.R;

public class UserViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(UserViewModel.class);
    public Context mContext;
    public String mLastActiveTime;
    public String mNameText;
    public Drawable mPresenceIcon;
    public int mPresenceIconVisibility;
    public boolean mShowOnlineColor;
    public String mSubtitleText;

    private void bindUserLastActiveTime(SocialUser socialUser) {
        this.mLastActiveTime = null;
        if ((socialUser instanceof FBSocialUser) && !socialUser.getIsActive(this.mContext.getResources())) {
            this.mLastActiveTime = ((FBSocialUser) socialUser).mLastActiveTime;
        }
        notifyPropertyChanged(77);
    }

    /* renamed from: com.oculus.panelapp.people.views.UserViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialUserFriendshipStatus;

        static {
            int[] iArr = new int[SocialUser.UserRowType.values().length];
            $SwitchMap$com$oculus$horizoncontent$social$SocialUser$UserRowType = iArr;
            try {
                iArr[SocialUser.UserRowType.FRIEND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[SocialUserFriendshipStatus.values().length];
            $SwitchMap$com$oculus$horizoncontent$social$SocialUserFriendshipStatus = iArr2;
            try {
                iArr2[SocialUserFriendshipStatus.ARE_FRIENDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void bindActiveStatus(SocialUser socialUser) {
        this.mShowOnlineColor = socialUser.getIsActive(this.mContext.getResources());
        notifyPropertyChanged(65);
    }

    private void bindName(SocialUser socialUser) {
        String str;
        String str2 = socialUser.mName;
        String str3 = socialUser.mAlias;
        if (str3 == null || str3.length() <= 0) {
            if (str2 == null || str2.length() <= 0) {
                str = "";
            } else {
                setNameText(str2);
                return;
            }
        } else if (str2 == null || str2.length() <= 0) {
            setNameText(str3);
            return;
        } else {
            str = this.mContext.getString(R.string.people_tablet_search_result_alias_and_name, str3, str2);
        }
        setNameText(str);
    }

    private void bindPresenceIcon(SocialUser socialUser) {
        ViewHolderUtil.PresenceIconInfo presenceIconInfo = ViewHolderUtil.getPresenceIconInfo(this.mContext, socialUser.getPresenceIconType());
        int i = 8;
        if (presenceIconInfo.mIsPresenceIconVisible) {
            i = 0;
        }
        this.mPresenceIconVisibility = i;
        this.mPresenceIcon = presenceIconInfo.mPresenceIcon;
        notifyPropertyChanged(64);
        notifyPropertyChanged(82);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        if (r6.mUserType.ordinal() != 2) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void bindSubtitle(com.oculus.horizoncontent.social.SocialUser r6, com.oculus.panelapp.people.PeopleTabletType r7) {
        /*
            r5 = this;
            com.oculus.panelapp.people.PeopleTabletType r0 = com.oculus.panelapp.people.PeopleTabletType.FACEBOOK
            java.lang.String r2 = ""
            if (r7 != r0) goto L_0x0034
            com.oculus.horizoncontent.social.SocialUserFriendshipStatus r0 = r6.getFriendship()
            int r1 = r0.ordinal()
            r0 = 0
            if (r1 == r0) goto L_0x0041
            java.lang.Integer r4 = r6.mNumMutualFriends
            if (r4 == 0) goto L_0x003d
            int r0 = r4.intValue()
            if (r0 == 0) goto L_0x003d
            android.content.Context r0 = r5.mContext
            android.content.res.Resources r3 = r0.getResources()
            r2 = 2131558409(0x7f0d0009, float:1.8742133E38)
            int r1 = r4.intValue()
            java.lang.Object[] r0 = new java.lang.Object[]{r4}
            java.lang.String r0 = r3.getQuantityString(r2, r1, r0)
        L_0x0030:
            r5.setSubtitleText(r0)
            return
        L_0x0034:
            com.oculus.horizoncontent.social.SocialUser$UserRowType r0 = r6.mUserType
            int r1 = r0.ordinal()
            r0 = 2
            if (r1 == r0) goto L_0x0041
        L_0x003d:
            r5.setSubtitleText(r2)
            return
        L_0x0041:
            android.content.Context r0 = r5.mContext
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r0 = com.oculus.panelapp.people.views.holders.ViewHolderUtil.getPresenceSubtitle(r6, r0)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.people.views.UserViewModel.bindSubtitle(com.oculus.horizoncontent.social.SocialUser, com.oculus.panelapp.people.PeopleTabletType):void");
    }

    private void setNameText(String str) {
        this.mNameText = str;
        notifyPropertyChanged(78);
    }

    private void setSubtitleText(String str) {
        this.mSubtitleText = str;
        notifyPropertyChanged(31);
    }

    @Bindable
    public boolean getIsSubtitleVisible() {
        String str = this.mSubtitleText;
        if (str == null || str.length() <= 0) {
            return false;
        }
        return true;
    }

    @Bindable
    public String getLastActiveTime() {
        return this.mLastActiveTime;
    }

    @Bindable
    public String getNameText() {
        return this.mNameText;
    }

    @Bindable
    public Drawable getPresenceIcon() {
        return this.mPresenceIcon;
    }

    @Bindable
    public int getPresenceIconVisibility() {
        return this.mPresenceIconVisibility;
    }

    @Bindable
    public boolean getShowOnlineColor() {
        return this.mShowOnlineColor;
    }

    @Bindable
    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    public UserViewModel(Context context) {
        this.mContext = context;
    }

    public void setUser(SocialUser socialUser, PeopleTabletType peopleTabletType) {
        bindSubtitle(socialUser, peopleTabletType);
        bindName(socialUser);
        bindPresenceIcon(socialUser);
        bindActiveStatus(socialUser);
        bindUserLastActiveTime(socialUser);
    }
}
