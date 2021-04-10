package com.oculus.panelapp.people.views;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.oculus.socialplatform.R;

/* JADX WARN: Init of enum EMPTY_SEARCH_LANDING_PAGE can be incorrect */
/* JADX WARN: Init of enum EMPTY_SEARCH_LANDING_PAGE_FB can be incorrect */
/* JADX WARN: Init of enum ERROR can be incorrect */
/* JADX WARN: Init of enum NO_FB_FRIENDS_ALL_CONNECTIONS can be incorrect */
/* JADX WARN: Init of enum NO_OC_FRIENDS can be incorrect */
/* JADX WARN: Init of enum NO_OC_FRIENDS_ALL_CONNECTIONS can be incorrect */
/* JADX WARN: Init of enum NO_OC_PEOPLE_NEARBY can be incorrect */
/* JADX WARN: Init of enum NO_OC_ALL_PEOPLE_NEARBY can be incorrect */
/* JADX WARN: Init of enum NO_REQUESTS can be incorrect */
/* JADX WARN: Init of enum NO_SEARCH_RESULTS can be incorrect */
/* JADX WARN: Init of enum NO_SUGGESTIONS can be incorrect */
public enum PeopleEmptyAdapterItemType {
    EMPTY_SEARCH_LANDING_PAGE(r4, r5, null, r7),
    EMPTY_SEARCH_LANDING_PAGE_FB(Integer.valueOf((int) R.string.people_tablet_fb_empty_search_title), r5, null, r7),
    ERROR(r13, r14, null, r16),
    NO_FB_FRIENDS(Integer.valueOf((int) R.string.people_tablet_no_fb_friends_title), Integer.valueOf((int) R.string.people_tablet_no_fb_friends_subtitle), null, Integer.valueOf((int) R.drawable.no_fb_friends)),
    NO_FB_FRIENDS_ALL_CONNECTIONS(r11, null, null, r14),
    NO_INTERNET(Integer.valueOf((int) R.string.people_tablet_offline_title), Integer.valueOf((int) R.string.people_tablet_offline_subtitle), Integer.valueOf((int) R.string.people_tablet_offline_cta), Integer.valueOf((int) R.drawable.offline)),
    NO_OC_FRIENDS(r20, r21, r22, Integer.valueOf((int) R.drawable.no_oc_friends)),
    NO_OC_FRIENDS_ALL_CONNECTIONS(r11, null, r22, r14),
    NO_OC_PEOPLE_NEARBY(Integer.valueOf((int) R.string.people_tablet_no_oc_people_nearby_title), null, null, r14),
    NO_OC_ALL_PEOPLE_NEARBY(Integer.valueOf((int) R.string.people_tablet_no_oc_all_people_nearby_title), null, null, r14),
    NO_REQUESTS(Integer.valueOf((int) R.string.people_tablet_no_friend_requests), null, null, r14),
    NO_SEARCH_RESULTS(Integer.valueOf((int) R.string.people_tablet_no_search_results_title), Integer.valueOf((int) R.string.people_tablet_no_search_results_subtitle), null, r16),
    NO_SUGGESTIONS(Integer.valueOf((int) R.string.people_tablet_no_suggestions_title), null, null, r14),
    NOT_FB_LINKED(Integer.valueOf((int) R.string.people_tablet_unlinked_title), Integer.valueOf((int) R.string.people_tablet_unlinked_subtitle), Integer.valueOf((int) R.string.people_tablet_unlinked_cta), Integer.valueOf((int) R.drawable.not_fb_linked_image));
    
    @Nullable
    public final Integer mCtaStringID;
    @Nullable
    public final Integer mImageID;
    @Nullable
    public final Integer mSubtitleStringID;
    public final Integer mTitleStringID;

    @Nullable
    public String getCTAString(Resources resources) {
        Integer num = this.mCtaStringID;
        if (num == null) {
            return null;
        }
        return resources.getString(num.intValue());
    }

    @Nullable
    public Drawable getImage(Resources resources) {
        Integer num = this.mImageID;
        if (num == null) {
            return null;
        }
        return resources.getDrawable(num.intValue());
    }

    @Nullable
    public String getSubtitleString(Resources resources) {
        Integer num = this.mSubtitleStringID;
        if (num == null) {
            return null;
        }
        return resources.getString(num.intValue());
    }

    public String getTitleString(Resources resources) {
        return resources.getString(this.mTitleStringID.intValue());
    }

    /* access modifiers changed from: public */
    static {
        Integer.valueOf((int) R.string.people_tablet_empty_search_title);
        Integer.valueOf((int) R.string.people_tablet_empty_search_subtitle);
        Integer.valueOf((int) R.drawable.empty_search);
        Integer.valueOf((int) R.string.people_tablet_error_state_title);
        Integer.valueOf((int) R.string.people_tablet_error_state_subtitle);
        Integer.valueOf((int) R.drawable.error_image);
        Integer.valueOf((int) R.string.people_tablet_no_friends_all_connections_title);
        Integer.valueOf((int) R.drawable.empty_users);
        Integer.valueOf((int) R.string.people_tablet_no_oc_friends_title);
        Integer.valueOf((int) R.string.people_tablet_no_oc_friends_subtitle);
        Integer.valueOf((int) R.string.people_tablet_no_friends_cta);
    }

    /* access modifiers changed from: public */
    PeopleEmptyAdapterItemType(@StringRes Integer num, @Nullable @StringRes Integer num2, @Nullable @StringRes Integer num3, @Nullable @DrawableRes Integer num4) {
        this.mTitleStringID = num;
        this.mSubtitleStringID = num2;
        this.mCtaStringID = num3;
        this.mImageID = num4;
    }
}
