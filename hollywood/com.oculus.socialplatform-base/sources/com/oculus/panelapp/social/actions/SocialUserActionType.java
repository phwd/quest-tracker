package com.oculus.panelapp.social.actions;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.oculus.socialplatform.R;
import javax.annotation.Nullable;

/* JADX WARN: Init of enum JOIN_PARTY can be incorrect */
/* JADX WARN: Init of enum CREATE_PARTY_WITH can be incorrect */
/* JADX WARN: Init of enum ADD_TO_PARTY can be incorrect */
public enum SocialUserActionType {
    PER_PERSON_MUTE(R.string.anytime_tablet_social_per_person_mute, null),
    PER_PERSON_UNMUTE(R.string.anytime_tablet_social_per_person_unmute, null),
    BLOCK(R.string.anytime_tablet_social_block, null),
    ADD_FRIEND(R.string.anytime_tablet_social_add_friend, Integer.valueOf((int) R.drawable.oc_icon_friends_add_filled_24_d2d2d2)),
    UNFRIEND(R.string.anytime_tablet_social_unfriend, null),
    REPORT(R.string.anytime_tablet_social_report, null),
    CHAT(R.string.anytime_tablet_social_chat, Integer.valueOf((int) R.drawable.oc_icon_chat_filled_24_d2d2d2)),
    CANCEL_PARTY_INVITE(R.string.anytime_tablet_social_uninvite_to_party, Integer.valueOf((int) R.drawable.oc_icon_party_invite_cancel_filled_24_d2d2d2)),
    JOIN_PARTY(R.string.anytime_tablet_social_party_join_party, r3),
    CREATE_PARTY_WITH(R.string.anytime_tablet_social_start_party, r3),
    KICK_FROM_PARTY(R.string.anytime_tablet_social_kick_from_party, null),
    ADD_TO_PARTY(R.string.anytime_tablet_social_invite_to_party, r3),
    SELF_MUTE(R.string.anytime_tablet_social_party_self_mute, Integer.valueOf((int) R.drawable.oc_icon_microphone_off_filled_24_d2d2d2)),
    SELF_UNMUTE(R.string.anytime_tablet_social_party_self_unmute, Integer.valueOf((int) R.drawable.oc_icon_microphone_on_filled_24_d2d2d2)),
    GOTO(R.string.anytime_tablet_social_party_member_go_to, Integer.valueOf((int) R.drawable.oc_icon_destination_filled_24_d2d2d2));
    
    public final Integer mActionIconID;
    public final int mActionStringID;

    /* renamed from: com.oculus.panelapp.social.actions.SocialUserActionType$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.panelapp.social.actions.SocialUserActionType[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType = r2
                com.oculus.panelapp.social.actions.SocialUserActionType r0 = com.oculus.panelapp.social.actions.SocialUserActionType.ADD_FRIEND     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.social.actions.SocialUserActionType r0 = com.oculus.panelapp.social.actions.SocialUserActionType.ADD_TO_PARTY     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.social.actions.SocialUserActionType r0 = com.oculus.panelapp.social.actions.SocialUserActionType.CANCEL_PARTY_INVITE     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.social.actions.SocialUserActionType r0 = com.oculus.panelapp.social.actions.SocialUserActionType.SELF_MUTE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.panelapp.social.actions.SocialUserActionType r0 = com.oculus.panelapp.social.actions.SocialUserActionType.SELF_UNMUTE     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.<clinit>():void");
        }
    }

    public Drawable getActionIcon(Resources resources) {
        return resources.getDrawable(this.mActionIconID.intValue());
    }

    public String getActionString(Resources resources) {
        return resources.getString(this.mActionStringID);
    }

    public int getActionStringID() {
        return this.mActionStringID;
    }

    @Nullable
    public Integer getIconID() {
        return this.mActionIconID;
    }

    public boolean hasActionIcon() {
        if (this.mActionIconID != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    static {
        Integer.valueOf((int) R.drawable.oc_icon_phone_filled_24_d2d2d2);
    }

    /* access modifiers changed from: public */
    SocialUserActionType(int i, Integer num) {
        this.mActionStringID = i;
        this.mActionIconID = num;
    }

    public boolean isAsyncAction() {
        switch (ordinal()) {
            case 3:
            case 7:
            case 11:
            case 12:
            case 13:
                return true;
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            default:
                return false;
        }
    }
}
