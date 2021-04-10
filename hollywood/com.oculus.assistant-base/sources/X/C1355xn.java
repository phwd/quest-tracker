package X;

import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xn  reason: case insensitive filesystem */
public final /* synthetic */ class C1355xn implements AbstractC0096Aa {
    public final /* synthetic */ String A00;

    public /* synthetic */ C1355xn(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        return ((IOculusAssistantAttentionListener) obj).A3r(this.A00);
    }
}
