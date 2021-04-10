package X;

import com.oculus.assistant.R;
import java.util.List;
import org.json.JSONObject;

/* renamed from: X.8d  reason: invalid class name and case insensitive filesystem */
public final class C00538d extends C1305wz {
    public AD A00;
    public boolean A01;
    public final C1307x1 A02;
    public final C1307x1 A03;
    public final C1308x2 A04;

    @Override // X.C1305wz, X.XA
    public final void A01(JSONObject jSONObject) {
        C1308x2 x2Var = this.A04;
        AD ad = this.A00;
        x2Var.A03 = ad.A02;
        C1307x1 x1Var = this.A02;
        String str = ad.A00;
        x1Var.A03 = AnonymousClass08.A04("play-", str);
        this.A03.A03 = AnonymousClass08.A04("select-", str);
        super.A01(jSONObject);
    }

    public final void A02(boolean z) {
        C1307x1 x1Var;
        int i;
        this.A01 = z;
        if (z) {
            x1Var = this.A02;
            i = R.drawable.pause_circle_filled_24;
        } else {
            x1Var = this.A02;
            i = R.drawable.play_circle_filled_24;
        }
        x1Var.A01 = i;
    }

    public final void A03(boolean z) {
        C1307x1 x1Var;
        int i;
        if (z) {
            x1Var = this.A03;
            i = R.drawable.rounded_rect;
        } else {
            x1Var = this.A03;
            i = R.drawable.transparent;
        }
        x1Var.A00 = i;
        x1Var.A01 = R.drawable.rounded_rect_button;
    }

    public C00538d() {
        Integer[] numArr = super.A04.A00;
        numArr[0] = null;
        numArr[4] = 4;
        super.A02 = -1;
        C1307x1 x1Var = new C1307x1();
        this.A03 = x1Var;
        ((XA) x1Var).A02 = -1;
        ((XA) x1Var).A00 = 52;
        x1Var.A00 = R.drawable.transparent;
        x1Var.A01 = R.drawable.rounded_rect_button;
        List list = ((C1305wz) this).A00;
        list.add(new XB(this, x1Var));
        C1308x2 x2Var = new C1308x2();
        this.A04 = x2Var;
        ((XA) x2Var).A02 = -1;
        Integer[] numArr2 = ((XA) x2Var).A04.A00;
        numArr2[0] = null;
        numArr2[1] = 24;
        ((XA) x2Var).A00 = 52;
        x2Var.A00 = 0;
        x2Var.A01 = "center-vertical";
        list.add(new XB(this, x2Var));
        C1307x1 x1Var2 = new C1307x1();
        this.A02 = x1Var2;
        ((XA) x1Var2).A02 = 42;
        ((XA) x1Var2).A00 = 42;
        x1Var2.A05.A00[0] = 12;
        x1Var2.A01 = R.drawable.play_circle_filled_24;
        ((XA) x1Var2).A01 = 21;
        list.add(new XB(this, x1Var2));
    }
}
