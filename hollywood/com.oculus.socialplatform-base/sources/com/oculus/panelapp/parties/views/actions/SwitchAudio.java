package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.PartyMicrophoneState;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.views.PartyUserCardAdapterItem;

public class SwitchAudio extends PartyAction {
    public static final String TAG = LoggingUtil.tag(SwitchAudio.class);
    public PartyMicrophoneState mDesiredMicrophoneState;
    public PartyUserCardAdapterItem mPartyUserCardAdapterItem;

    @Nullable
    public static PartyActionType getTypeFromPartyMicrophoneState(PartyMicrophoneState partyMicrophoneState) {
        if (partyMicrophoneState != null) {
            switch (partyMicrophoneState.ordinal()) {
                case 1:
                    return PartyActionType.SWITCH_AUDIO_MUTE;
                case 2:
                    return PartyActionType.SWITCH_AUDIO_PARTY;
                case 3:
                    return PartyActionType.SWITCH_AUDIO_APP;
            }
        }
        return null;
    }

    /* renamed from: com.oculus.panelapp.parties.views.actions.SwitchAudio$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneState;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.horizoncontent.social.PartyMicrophoneState[] r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.parties.views.actions.SwitchAudio.AnonymousClass2.$SwitchMap$com$oculus$horizoncontent$social$PartyMicrophoneState = r2
                com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.MUTE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.APP     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizoncontent.social.PartyMicrophoneState r0 = com.oculus.horizoncontent.social.PartyMicrophoneState.PARTY     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.actions.SwitchAudio.AnonymousClass2.<clinit>():void");
        }
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        PartyMicrophoneState partyMicrophoneState = this.mDesiredMicrophoneState;
        PartyActionType typeFromPartyMicrophoneState = getTypeFromPartyMicrophoneState(partyMicrophoneState);
        if (typeFromPartyMicrophoneState != null) {
            return typeFromPartyMicrophoneState;
        }
        StringBuilder sb = new StringBuilder("Unexpected MicrophoneState in SwitchAudio getType: ");
        sb.append(partyMicrophoneState);
        throw new RuntimeException(sb.toString());
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        SocialUser socialUser = this.mPartyUserCardAdapterItem.mUser;
        if (socialUser == null || socialUser.mUserType != SocialUser.UserRowType.PARTY_CONTROLS) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, @Nullable final PartyActionHandler partyActionHandler) {
        PartyMicrophoneState partyMicrophoneState = this.mDesiredMicrophoneState;
        partyMicrophoneState.toString();
        HorizonContentProviderHelper.setPartyMicrophoneState(context, partyMicrophoneState, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.views.actions.SwitchAudio.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onError();
                }
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                }
            }
        });
    }

    public SwitchAudio(@NonNull PartyUserCardAdapterItem partyUserCardAdapterItem, @NonNull PartyMicrophoneState partyMicrophoneState) {
        this.mPartyUserCardAdapterItem = partyUserCardAdapterItem;
        this.mDesiredMicrophoneState = partyMicrophoneState;
    }
}
