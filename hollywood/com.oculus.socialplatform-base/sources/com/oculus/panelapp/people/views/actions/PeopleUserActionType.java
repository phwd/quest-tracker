package com.oculus.panelapp.people.views.actions;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.socialplatform.R;

/* JADX WARN: Init of enum FB_SHARE_PARTY can be incorrect */
/* JADX WARN: Init of enum INVITE_TO_PARTY can be incorrect */
public enum PeopleUserActionType {
    ACCEPT_FRIEND_REQUEST(Integer.valueOf((int) R.string.people_tablet_accept_friend_request_cta), null, null, ActionId.ACCEPT_FRIEND_REQUEST),
    ADD_FRIEND(Integer.valueOf((int) R.string.people_tablet_add_friend_cta), Integer.valueOf((int) R.drawable.oc_icon_friends_add_filled_24_d2d2d2), Integer.valueOf((int) R.drawable.people_tablet_add_friends_button_with_circular_background_on_hover), ActionId.ADD_FRIEND),
    BLOCK(Integer.valueOf((int) R.string.people_tablet_block_cta), null, null, ActionId.BLOCK_USER),
    CANCEL_FRIEND_REQUEST(Integer.valueOf((int) R.string.people_tablet_cancel_friend_request_cta), Integer.valueOf((int) R.drawable.oc_icon_friends_remove_filled_16_d2d2d2), Integer.valueOf((int) R.drawable.people_tablet_cancel_friend_request_button_with_circular_background_on_hover), ActionId.CANCEL_FRIEND_REQUEST),
    CHAT(Integer.valueOf((int) R.string.people_tablet_chat_cta), Integer.valueOf((int) R.drawable.oc_icon_chat_filled_16_d2d2d2), Integer.valueOf((int) R.drawable.people_tablet_chat_button_with_circular_background_on_hover), null),
    CREATE_PARTY_WITH(Integer.valueOf((int) R.string.people_tablet_create_party_cta), Integer.valueOf((int) R.drawable.oc_icon_parties_filled_16_d2d2d2), Integer.valueOf((int) R.drawable.people_tablet_create_party_button_with_circular_background_on_hover), ActionId.PARTY_CREATE),
    DENY_FRIEND_REQUEST(Integer.valueOf((int) R.string.people_tablet_reject_friend_request_cta), null, null, ActionId.DENY_FRIEND_REQUEST),
    FB_SHARE_PARTY(r15, r16, null, ActionId.PARTY_SEND_LINK_TO_THREAD),
    HIDE_PYMK_USER(null, Integer.valueOf((int) R.drawable.people_tablet_hide_suggestion_background), null, ActionId.HIDE_PYMK_USER),
    INVITE_TO_PARTY(r15, r16, null, ActionId.PARTY_SEND_INVITE),
    JOIN_PARTY(Integer.valueOf((int) R.string.people_tablet_join_party_cta), Integer.valueOf((int) R.drawable.oc_icon_party_join_filled_16_d2d2d2), Integer.valueOf((int) R.drawable.people_tablet_join_party_button_with_circular_background_on_hover), ActionId.PARTY_JOIN),
    REPORT(Integer.valueOf((int) R.string.people_tablet_report_cta), null, null, null),
    UNFRIEND(Integer.valueOf((int) R.string.people_tablet_unfriend_cta), null, null, ActionId.UNFRIEND),
    VIEW_PROFILE(Integer.valueOf((int) R.string.people_tablet_view_profile_cta), null, null, null);
    
    public final Integer mActionIconID;
    public final Integer mActionIconWithCircularBackgroundOnHoverID;
    @Nullable
    public final ActionId mActionLoggingId;
    public final Integer mActionStringID;

    public Drawable getActionIcon(Resources resources) {
        Integer num = this.mActionIconID;
        if (num == null) {
            return null;
        }
        return resources.getDrawable(num.intValue());
    }

    public Drawable getActionIconWithCircularBackgroundOnHoverID(Resources resources) {
        return resources.getDrawable(this.mActionIconWithCircularBackgroundOnHoverID.intValue());
    }

    public ActionId getActionLoggingId() {
        return this.mActionLoggingId;
    }

    public String getActionString(Resources resources) {
        return resources.getString(this.mActionStringID.intValue());
    }

    public int getActionStringID() {
        return this.mActionStringID.intValue();
    }

    public boolean hasActionIconWithCircularBackgroundOnHoverID() {
        if (this.mActionIconWithCircularBackgroundOnHoverID != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    static {
        Integer.valueOf((int) R.string.people_tablet_invite_to_party_cta);
        Integer.valueOf((int) R.drawable.oc_icon_party_add_filled_16_d2d2d2);
    }

    /* access modifiers changed from: public */
    PeopleUserActionType(@StringRes Integer num, @DrawableRes Integer num2, @DrawableRes Integer num3, @Nullable ActionId actionId) {
        this.mActionStringID = num;
        this.mActionIconID = num2;
        this.mActionIconWithCircularBackgroundOnHoverID = num3;
        this.mActionLoggingId = actionId;
    }
}
