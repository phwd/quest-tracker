package X;

import android.app.Application;
import com.oculus.assistant.R;

/* renamed from: X.wZ  reason: case insensitive filesystem */
public abstract class AbstractC1279wZ extends AbstractC0400Wb {
    public static final C0404Wg A02 = new C0404Wg();
    public int A00 = 1;
    public final Application A01 = BX.A00();

    public static final C00859p A00(AbstractC1279wZ wZVar, XA xa) {
        int i;
        String str;
        C1306x0 x0Var = new C1306x0();
        x0Var.A02 = -1;
        ((XA) x0Var).A00 = -1;
        boolean z = wZVar instanceof A1;
        if (!z) {
            i = R.string.assistant_nux_take_screenshot_description;
        } else {
            i = R.string.assistant_nux_what_time_is_it_description;
        }
        C1308x2 x2Var = new C1308x2(i);
        ((XA) x2Var).A00 = -1;
        x2Var.A01 = null;
        x2Var.A00 = 19;
        ((XA) x2Var).A01 = 19;
        x0Var.A02(x2Var);
        C0514bB.A02(xa, "component");
        x0Var.A00.add(new XC(x0Var, xa));
        AnonymousClass1i r2 = new AnonymousClass1i(wZVar);
        if (!z) {
            str = "NUX_TAKE_A_PHOTO_STEP_DIALOG";
        } else {
            str = "NUX_WHAT_TIME_IS_IT_STEP_DIALOG";
        }
        r2.A0G(str);
        Integer num = AnonymousClass09.A01;
        C0514bB.A02(num, "style");
        ((C00859p) r2).A00 = num;
        r2.A0D(x0Var);
        return r2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    @Override // X.AbstractC0400Wb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.res.AssetFileDescriptor A08(android.content.res.Resources r4, java.lang.Integer r5) {
        /*
            r3 = this;
            java.lang.String r0 = "resources"
            X.C0514bB.A02(r4, r0)
            java.lang.String r0 = "audioType"
            X.C0514bB.A02(r5, r0)
            int[] r1 = X.C0405Wh.A00
            int r0 = r5.intValue()
            r2 = r1[r0]
            r0 = 1
            r1 = 0
            if (r2 == r0) goto L_0x0032
            r0 = 2
            if (r2 == r0) goto L_0x0026
            r0 = 3
            if (r2 == r0) goto L_0x003a
            r0 = 4
            if (r2 == r0) goto L_0x0050
            android.content.res.AssetFileDescriptor r1 = super.A08(r4, r5)
        L_0x0023:
            r3.A00 = r1
            return r1
        L_0x0026:
            boolean r0 = r3 instanceof X.A1
            if (r0 != 0) goto L_0x002e
            r0 = 2131427353(0x7f0b0019, float:1.847632E38)
            goto L_0x0041
        L_0x002e:
            r0 = 2131427361(0x7f0b0021, float:1.8476336E38)
            goto L_0x0041
        L_0x0032:
            boolean r0 = r3 instanceof X.A1
            if (r0 != 0) goto L_0x0023
            r0 = 2131427354(0x7f0b001a, float:1.8476322E38)
            goto L_0x0041
        L_0x003a:
            boolean r0 = r3 instanceof X.A1
            if (r0 != 0) goto L_0x004c
            r0 = 2131427351(0x7f0b0017, float:1.8476316E38)
        L_0x0041:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            if (r0 == 0) goto L_0x0023
            int r0 = r0.intValue()
            goto L_0x0053
        L_0x004c:
            r0 = 2131427359(0x7f0b001f, float:1.8476332E38)
            goto L_0x0041
        L_0x0050:
            r0 = 2131427355(0x7f0b001b, float:1.8476324E38)
        L_0x0053:
            android.content.res.AssetFileDescriptor r1 = r4.openRawResourceFd(r0)
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1279wZ.A08(android.content.res.Resources, java.lang.Integer):android.content.res.AssetFileDescriptor");
    }

    public final C00859p A09() {
        C1307x1 x1Var = new C1307x1();
        ((XA) x1Var).A01 = 16;
        x1Var.A01 = R.drawable.ic_nux_close;
        ((XA) x1Var).A02 = 24;
        ((XA) x1Var).A00 = 24;
        Integer[] numArr = x1Var.A04.A00;
        numArr[0] = null;
        numArr[4] = 8;
        numArr[0] = null;
        numArr[2] = 8;
        return A00(this, x1Var);
    }

    public final C00859p A0A() {
        C1307x1 x1Var = new C1307x1();
        ((XA) x1Var).A01 = 16;
        x1Var.A01 = R.drawable.ic_nux_check;
        ((XA) x1Var).A02 = 24;
        ((XA) x1Var).A00 = 24;
        Integer[] numArr = x1Var.A04.A00;
        numArr[0] = null;
        numArr[4] = 8;
        numArr[0] = null;
        numArr[2] = 8;
        return A00(this, x1Var);
    }
}
