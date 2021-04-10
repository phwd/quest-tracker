package com.oculus.panelapp.parties.views.actions;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import com.oculus.socialplatform.R;

/* JADX WARN: Init of enum SELF_UNMUTE can be incorrect */
/* JADX WARN: Init of enum MANAGE_VOICE can be incorrect */
public enum PartyActionType {
    BLOCK(Integer.valueOf((int) R.string.parties_tablet_block), null),
    CANCEL_PARTY_INVITE(Integer.valueOf((int) R.string.parties_tablet_cancel_party_invite), Integer.valueOf((int) R.drawable.oc_icon_party_invite_cancel_filled_24_d2d2d2)),
    KICK_FROM_PARTY(Integer.valueOf((int) R.string.parties_tablet_kick_from_party), null),
    SELF_MUTE(Integer.valueOf((int) R.string.parties_tablet_self_mute), Integer.valueOf((int) R.drawable.oc_icon_microphone_off_filled_24_d2d2d2)),
    SELF_UNMUTE(r2, r5),
    SWITCH_AUDIO_APP(Integer.valueOf((int) R.string.parties_tablet_microphone_send_to_app), null),
    SWITCH_AUDIO_MUTE(Integer.valueOf((int) R.string.parties_tablet_microphone_mute), null),
    SWITCH_AUDIO_PARTY(Integer.valueOf((int) R.string.parties_tablet_microphone_send_to_party), null),
    INVITE_TO_PARTY(Integer.valueOf((int) R.string.parties_tablet_invite_to_party_action_button), Integer.valueOf((int) R.drawable.oc_icon_friends_add_filled_24_d2d2d2)),
    REPORT(Integer.valueOf((int) R.string.parties_tablet_report_cta), null),
    TRAVEL_TO(Integer.valueOf((int) R.string.parties_tablet_travel_to_party_member), Integer.valueOf((int) R.drawable.oc_icon_destination_filled_24_d2d2d2)),
    MANAGE_VOICE(Integer.valueOf((int) R.string.parties_tablet_viewer_manage_voice), r5);
    
    public final Integer mActionIconID;
    public final Integer mActionStringID;

    /* renamed from: com.oculus.panelapp.parties.views.actions.PartyActionType$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$parties$views$actions$PartyActionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.panelapp.parties.views.actions.PartyActionType[] r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.parties.views.actions.PartyActionType.AnonymousClass1.$SwitchMap$com$oculus$panelapp$parties$views$actions$PartyActionType = r2
                com.oculus.panelapp.parties.views.actions.PartyActionType r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.BLOCK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.parties.views.actions.PartyActionType r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.SELF_MUTE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.parties.views.actions.PartyActionType r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.SELF_UNMUTE     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.parties.views.actions.PartyActionType r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.CANCEL_PARTY_INVITE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.panelapp.parties.views.actions.PartyActionType r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.KICK_FROM_PARTY     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.panelapp.parties.views.actions.PartyActionType r0 = com.oculus.panelapp.parties.views.actions.PartyActionType.INVITE_TO_PARTY     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.actions.PartyActionType.AnonymousClass1.<clinit>():void");
        }
    }

    public Drawable getActionIcon(Resources resources) {
        return resources.getDrawable(this.mActionIconID.intValue());
    }

    public String getActionString(Resources resources) {
        return resources.getString(this.mActionStringID.intValue());
    }

    public int getActionStringID() {
        return this.mActionStringID.intValue();
    }

    public boolean hasActionIcon() {
        if (this.mActionIconID != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    static {
        Integer.valueOf((int) R.string.parties_tablet_self_unmute);
        Integer.valueOf((int) R.drawable.oc_icon_microphone_on_filled_24_d2d2d2);
    }

    /* access modifiers changed from: public */
    PartyActionType(@StringRes Integer num, @DrawableRes Integer num2) {
        this.mActionStringID = num;
        this.mActionIconID = num2;
    }

    public boolean isAsyncAction() {
        switch (ordinal()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
                return true;
            case 5:
            case 6:
            case 7:
            default:
                return false;
        }
    }
}
