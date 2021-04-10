package com.oculus.assistant.service;

import X.HandlerC0422Wz;
import com.oculus.assistant.service.api.attention.AssistantErrorType;
import com.oculus.assistant.service.api.attention.AssistantInteractionState;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

public final class SystemAttentionPrePushHandler extends IOculusAssistantAttentionListener.Stub {
    public AssistantService A00;
    public IOculusAssistantAttentionListener A01;

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A3r(String str) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A3r(str);
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A46(AssistantErrorType assistantErrorType) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A46(assistantErrorType);
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4C(boolean z) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A4C(z);
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4D(String str) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A4D(str);
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4E(float f) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A4E(f);
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4G(AssistantInteractionState assistantInteractionState) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A4G(assistantInteractionState);
    }

    @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
    public final boolean A4O(String str, boolean z) {
        if (this.A00.A0X.A00 || !HandlerC0422Wz.A06.A0C()) {
            return false;
        }
        return this.A01.A4O(str, z);
    }

    public SystemAttentionPrePushHandler(AssistantService assistantService, IOculusAssistantAttentionListener iOculusAssistantAttentionListener) {
        this.A01 = iOculusAssistantAttentionListener;
        this.A00 = assistantService;
    }
}
