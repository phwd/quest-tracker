package X;

import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xq  reason: case insensitive filesystem */
public final /* synthetic */ class C1358xq implements AbstractC0096Aa {
    public final /* synthetic */ String A00;

    public /* synthetic */ C1358xq(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        return ((IOculusAssistantAttentionListener) obj).A3r(this.A00);
    }
}
