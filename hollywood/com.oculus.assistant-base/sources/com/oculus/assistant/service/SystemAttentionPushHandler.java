package com.oculus.assistant.service;

import X.HandlerC0422Wz;
import X.Vu;
import X.W0;
import X.X0;
import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.acra.ErrorReporter;
import com.facebook.assistant.oacr.OacrConstants;
import com.oculus.assistant.service.api.attention.AssistantErrorType;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

public final class SystemAttentionPushHandler extends IOculusAssistantAttentionListener.Stub {
    public SharedPreferences A00;
    public boolean A01;

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A46(AssistantErrorType assistantErrorType) {
        return false;
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4C(boolean z) {
        return false;
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4D(String str) {
        return false;
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4E(float f) {
        return false;
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A3r(String str) {
        HandlerC0422Wz wz = HandlerC0422Wz.A06;
        if (!wz.A0C()) {
            return false;
        }
        X0 x0 = new X0();
        x0.A04 = 1;
        x0.A03("IDLE");
        x0.A03 = Integer.valueOf((int) ErrorReporter.MAX_ANR_TRACES_TIME_DELTA_MS);
        x0.A05 = str;
        if (W0.A00().A00.getBoolean("enable_dynamic_attn_system_time", false)) {
            x0.A03 = Integer.valueOf(Vu.A00(str, OacrConstants.AUTO_SPEECH_DOMAIN));
        }
        wz.A0A(x0);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007b  */
    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A4G(com.oculus.assistant.service.api.attention.AssistantInteractionState r13) {
        /*
        // Method dump skipped, instructions count: 163
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.assistant.service.SystemAttentionPushHandler.A4G(com.oculus.assistant.service.api.attention.AssistantInteractionState):boolean");
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4O(String str, boolean z) {
        HandlerC0422Wz wz = HandlerC0422Wz.A06;
        if (!wz.A0C()) {
            return false;
        }
        X0 x0 = new X0();
        x0.A05 = str;
        x0.A03("TRANSCRIPTION");
        x0.A02 = Boolean.valueOf(this.A01);
        x0.A03 = 30000;
        wz.A0A(x0);
        this.A01 = false;
        return true;
    }

    public SystemAttentionPushHandler(Context context) {
        this.A00 = context.getSharedPreferences("first_time_user", 0);
    }
}
