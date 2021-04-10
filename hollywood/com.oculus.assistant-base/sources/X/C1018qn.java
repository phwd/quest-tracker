package X;

import java.io.Serializable;

/* renamed from: X.qn  reason: case insensitive filesystem */
public class C1018qn implements AbstractC0260Ny, Serializable {
    public static final C1018qn A00 = new C1018qn();

    @Override // X.AbstractC0260Ny
    public final boolean A3W() {
        if (!(this instanceof VE)) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0260Ny
    public final void A5q(AbstractC1012qg qgVar, int i) {
        if (this instanceof VE) {
            qgVar.A0O(VE.A01);
            if (i > 0) {
                int i2 = i + i;
                while (i2 > 64) {
                    char[] cArr = VE.A02;
                    qgVar.A0U(cArr, 0, 64);
                    i2 -= cArr.length;
                }
                qgVar.A0U(VE.A02, 0, i2);
            }
        } else if (this instanceof VF) {
            qgVar.A0D(' ');
        }
    }
}
