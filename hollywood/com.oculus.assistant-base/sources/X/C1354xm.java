package X;

import com.oculus.assistant.service.api.attention.AssistantErrorType;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xm  reason: case insensitive filesystem */
public final /* synthetic */ class C1354xm implements AbstractC0096Aa {
    public final /* synthetic */ YB A00;
    public final /* synthetic */ AssistantErrorType A01;

    public /* synthetic */ C1354xm(YB yb, AssistantErrorType assistantErrorType) {
        this.A00 = yb;
        this.A01 = assistantErrorType;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        YB yb = this.A00;
        AssistantErrorType assistantErrorType = this.A01;
        IOculusAssistantAttentionListener iOculusAssistantAttentionListener = (IOculusAssistantAttentionListener) obj;
        if (!"com.oculus.systemux".equals(str) || yb.A0B.A02.size() != 0) {
            return iOculusAssistantAttentionListener.A46(assistantErrorType);
        }
        return false;
    }
}
