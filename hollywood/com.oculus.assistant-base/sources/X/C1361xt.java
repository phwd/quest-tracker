package X;

import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xt  reason: case insensitive filesystem */
public final /* synthetic */ class C1361xt implements AbstractC0096Aa {
    public final /* synthetic */ float A00;

    public /* synthetic */ C1361xt(float f) {
        this.A00 = f;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        return ((IOculusAssistantAttentionListener) obj).A4E(this.A00);
    }
}
