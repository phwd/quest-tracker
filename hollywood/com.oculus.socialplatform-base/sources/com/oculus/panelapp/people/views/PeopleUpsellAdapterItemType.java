package com.oculus.panelapp.people.views;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import com.oculus.socialplatform.R;

/* JADX WARN: Init of enum FIND_FRIENDS can be incorrect */
/* JADX WARN: Init of enum ALL_FRIENDS can be incorrect */
/* JADX WARN: Init of enum ALL_NEARBY can be incorrect */
public enum PeopleUpsellAdapterItemType {
    CREATE_PARTY(Integer.valueOf((int) R.string.people_tablet_start_party_upsell), Integer.valueOf((int) R.drawable.people_tablet_upsell_icon_start_party)),
    FIND_FRIENDS(r2, r6),
    ALL_FRIENDS(Integer.valueOf((int) R.string.people_tablet_all_friends_upsell), r6),
    ALL_NEARBY(Integer.valueOf((int) R.string.people_tablet_all_people_nearby_upsell), r6);
    
    public final Integer mTitleStringID;
    public final Integer mUpsellIconID;

    public Drawable getActionIcon(Resources resources) {
        return resources.getDrawable(this.mUpsellIconID.intValue());
    }

    public String getTitleString(Resources resources) {
        return resources.getString(this.mTitleStringID.intValue());
    }

    /* access modifiers changed from: public */
    static {
        Integer.valueOf((int) R.string.people_tablet_find_friends_upsell);
        Integer.valueOf((int) R.drawable.people_tablet_upsell_icon_find_friends);
    }

    /* access modifiers changed from: public */
    PeopleUpsellAdapterItemType(@StringRes Integer num, @DrawableRes Integer num2) {
        this.mTitleStringID = num;
        this.mUpsellIconID = num2;
    }
}
