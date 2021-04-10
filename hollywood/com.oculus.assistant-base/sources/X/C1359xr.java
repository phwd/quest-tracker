package X;

import com.oculus.assistant.service.api.attention.AssistantInteractionState;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xr  reason: case insensitive filesystem */
public final /* synthetic */ class C1359xr implements AbstractC0096Aa {
    public final /* synthetic */ AssistantInteractionState A00;

    public /* synthetic */ C1359xr(AssistantInteractionState assistantInteractionState) {
        this.A00 = assistantInteractionState;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        AssistantInteractionState assistantInteractionState = this.A00;
        IOculusAssistantAttentionListener iOculusAssistantAttentionListener = (IOculusAssistantAttentionListener) obj;
        if (assistantInteractionState == AssistantInteractionState.LISTENING) {
            C00799i.A00.logAttentionSystem("vrshell_v1");
        }
        return iOculusAssistantAttentionListener.A4G(assistantInteractionState);
    }
}
