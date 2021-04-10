package com.oculus.panelapp.social.actions;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.oculus.panelapp.social.R;
import javax.annotation.Nullable;

public enum SocialUserActionType {
    PER_PERSON_MUTE(R.string.anytime_tablet_social_per_person_mute, null),
    PER_PERSON_UNMUTE(R.string.anytime_tablet_social_per_person_unmute, null),
    BLOCK(R.string.anytime_tablet_social_block, null),
    ADD_FRIEND(R.string.anytime_tablet_social_add_friend, Integer.valueOf(R.drawable.oc_icon_friends_add_filled_24_d2d2d2)),
    UNFRIEND(R.string.anytime_tablet_social_unfriend, null),
    REPORT(R.string.anytime_tablet_social_report, null),
    CHAT(R.string.anytime_tablet_social_chat, Integer.valueOf(R.drawable.oc_icon_chat_filled_24_d2d2d2)),
    CANCEL_PARTY_INVITE(R.string.anytime_tablet_social_uninvite_to_party, Integer.valueOf(R.drawable.oc_icon_party_invite_cancel_filled_24_d2d2d2)),
    JOIN_PARTY(R.string.anytime_tablet_social_party_join_party, Integer.valueOf(R.drawable.oc_icon_phone_filled_24_d2d2d2)),
    CREATE_PARTY_WITH(R.string.anytime_tablet_social_start_party, Integer.valueOf(R.drawable.oc_icon_phone_filled_24_d2d2d2)),
    KICK_FROM_PARTY(R.string.anytime_tablet_social_kick_from_party, null),
    ADD_TO_PARTY(R.string.anytime_tablet_social_invite_to_party, Integer.valueOf(R.drawable.oc_icon_phone_filled_24_d2d2d2)),
    SELF_MUTE(R.string.anytime_tablet_social_party_self_mute, Integer.valueOf(R.drawable.oc_icon_microphone_off_filled_24_d2d2d2)),
    SELF_UNMUTE(R.string.anytime_tablet_social_party_self_unmute, Integer.valueOf(R.drawable.oc_icon_microphone_on_filled_24_d2d2d2)),
    GOTO(R.string.anytime_tablet_social_party_member_go_to, Integer.valueOf(R.drawable.oc_icon_destination_filled_24_d2d2d2));
    
    private final Integer mActionIconID;
    private final int mActionStringID;

    private SocialUserActionType(int i, Integer num) {
        this.mActionStringID = i;
        this.mActionIconID = num;
    }

    public String getActionString(Resources resources) {
        return resources.getString(this.mActionStringID);
    }

    public Drawable getActionIcon(Resources resources) {
        return resources.getDrawable(this.mActionIconID.intValue());
    }

    public boolean hasActionIcon() {
        return this.mActionIconID != null;
    }

    public int getActionStringID() {
        return this.mActionStringID;
    }

    @Nullable
    public Integer getIconID() {
        return this.mActionIconID;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.social.actions.SocialUserActionType$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType = new int[SocialUserActionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.panelapp.social.actions.SocialUserActionType[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType = r0
                int[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.social.actions.SocialUserActionType r1 = com.oculus.panelapp.social.actions.SocialUserActionType.ADD_FRIEND     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.social.actions.SocialUserActionType r1 = com.oculus.panelapp.social.actions.SocialUserActionType.ADD_TO_PARTY     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.social.actions.SocialUserActionType r1 = com.oculus.panelapp.social.actions.SocialUserActionType.CANCEL_PARTY_INVITE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.panelapp.social.actions.SocialUserActionType r1 = com.oculus.panelapp.social.actions.SocialUserActionType.SELF_MUTE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.panelapp.social.actions.SocialUserActionType r1 = com.oculus.panelapp.social.actions.SocialUserActionType.SELF_UNMUTE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.actions.SocialUserActionType.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean isAsyncAction() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$social$actions$SocialUserActionType[ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5;
    }
}
