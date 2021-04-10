package X;

import com.oculus.assistant.assistantutils.SystemUXUtil;

/* renamed from: X.ys  reason: case insensitive filesystem */
public final class C1406ys implements AbstractC0437Yg {
    @Override // X.AbstractC0437Yg
    public final boolean A1q(AnonymousClass8F r5, AnonymousClass8H r6) {
        BX.A00().sendBroadcast(SystemUXUtil.A03(ZG.GUARDIAN_PASSTHROUGH_ON_DEMAND, false, new String[0]));
        return true;
    }

    @Override // X.AbstractC0437Yg
    public final boolean A54(AnonymousClass8F r3) {
        if (C0398Vv.A02()) {
            String str = r3.A03;
            if (str.equals("b2fbc5d4-7f9c-4493-8832-edb6e8a7e017") || str.equals("e524f2a3-e633-4af7-af99-1e13cbe03cd3") || str.equals("fcefce43-d121-47de-939d-1f1f624c761d")) {
                return false;
            }
            return true;
        }
        return false;
    }
}
