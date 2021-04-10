package X;

import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xw  reason: case insensitive filesystem */
public final /* synthetic */ class C1364xw implements AbstractC0096Aa {
    public final /* synthetic */ boolean A00;

    public /* synthetic */ C1364xw(boolean z) {
        this.A00 = z;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        return ((IOculusAssistantAttentionListener) obj).A4C(this.A00);
    }
}
