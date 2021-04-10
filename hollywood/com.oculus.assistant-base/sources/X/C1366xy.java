package X;

import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

/* renamed from: X.xy  reason: case insensitive filesystem */
public final /* synthetic */ class C1366xy implements AbstractC0096Aa {
    public final /* synthetic */ String A00;
    public final /* synthetic */ boolean A01;

    public /* synthetic */ C1366xy(String str, boolean z) {
        this.A00 = str;
        this.A01 = z;
    }

    @Override // X.AbstractC0096Aa
    public final boolean A4w(String str, Object obj) {
        return ((IOculusAssistantAttentionListener) obj).A4O(this.A00, this.A01);
    }
}
