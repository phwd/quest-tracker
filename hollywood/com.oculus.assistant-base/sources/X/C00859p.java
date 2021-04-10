package X;

import android.os.Bundle;
import com.oculus.assistant.R;
import org.json.JSONObject;

/* renamed from: X.9p  reason: invalid class name and case insensitive filesystem */
public class C00859p extends C1300wu {
    public Integer A00 = AnonymousClass09.A00;
    public String A01 = "exp-attn-system";

    @Override // X.C1300wu
    public final void A0E(String str) {
        C0514bB.A02(str, "<set-?>");
        this.A01 = str;
    }

    @Override // X.X1, X.C1300wu
    public boolean A42(String str, String str2, Bundle bundle) {
        C0514bB.A02(str, "id");
        C0514bB.A02(str2, "action");
        if (!C0514bB.A05(str, str) || str2.hashCode() != 94756344 || !str2.equals("close")) {
            return super.A42(str, str2, bundle);
        }
        if (!(this instanceof C0333Rn)) {
            return true;
        }
        C0112Aq.A00().A01(new C1284we(AnonymousClass09.A00, true));
        return true;
    }

    @Override // X.C1300wu
    public final String A03() {
        return this.A01;
    }

    @Override // X.C1300wu
    public final JSONObject A04() {
        int i;
        int i2;
        JSONObject A04 = super.A04();
        int i3 = C0411Wo.A00[this.A00.intValue()];
        if (i3 == 1) {
            i = R.dimen.attn_system_exp_state_short_width;
            i2 = R.dimen.attn_system_exp_state_tall_height;
        } else if (i3 == 2) {
            i = R.dimen.attn_system_exp_state_short_width;
            i2 = R.dimen.attn_system_exp_state_short_height;
        } else {
            throw new C0465aA();
        }
        if (A04 != null) {
            A04.put("width", X9.A00(i));
            A04.put("height", X9.A00(i2));
        }
        return A04;
    }
}
