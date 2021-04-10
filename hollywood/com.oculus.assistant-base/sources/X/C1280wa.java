package X;

import android.os.Bundle;

/* renamed from: X.wa  reason: case insensitive filesystem */
public final class C1280wa implements X1 {
    public final /* synthetic */ C1281wb A00;

    public C1280wa(C1281wb wbVar) {
        this.A00 = wbVar;
    }

    @Override // X.X1
    public final boolean A42(String str, String str2, Bundle bundle) {
        String str3;
        C0514bB.A02(str, "id");
        C0514bB.A02(str2, "action");
        if (!C0514bB.A05(str, "dlg-voice-selection")) {
            return false;
        }
        int hashCode = str2.hashCode();
        if (hashCode == -817598092) {
            str3 = "secondary";
        } else if (hashCode != -314765822) {
            return false;
        } else {
            str3 = "primary";
        }
        if (!str2.equals(str3)) {
            return false;
        }
        C0112Aq.A00().A01(new C1290wk());
        C0112Aq.A00().A01(new C1289wj());
        return false;
    }
}
