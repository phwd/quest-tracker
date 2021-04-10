package X;

import java.util.List;

/* renamed from: X.wM  reason: case insensitive filesystem */
public final class C1266wM implements AbstractC0105Aj {
    public final /* synthetic */ WV A00;

    public C1266wM(WV wv) {
        this.A00 = wv;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0514bB.A02(ai, "<anonymous parameter 0>");
        C0514bB.A02(ak, "<anonymous parameter 1>");
        WV wv = this.A00;
        WX wx = wv.A03;
        int i = wx.A00;
        List list = wx.A02;
        if (i == list.size() - 1) {
            WV.A00(wv);
            HandlerC0422Wz.A02();
            wx.A01 = null;
            wx.A00 = -1;
            return;
        }
        if (wx.A00 != list.size() - 1) {
            AbstractC0400Wb wb = wx.A01;
            if (wb != null) {
                wb.A02();
            }
            HandlerC0422Wz.A02();
            int i2 = wx.A00 + 1;
            wx.A00 = i2;
            wx.A01 = (AbstractC0400Wb) list.get(i2);
        }
        AbstractC0400Wb wb2 = wx.A01;
        if (wb2 != null) {
            C1300wu A02 = wb2.A02();
            if (A02 != null) {
                HandlerC0422Wz.A06.A09(A02, true);
            }
            wb2.A06();
        }
    }
}
