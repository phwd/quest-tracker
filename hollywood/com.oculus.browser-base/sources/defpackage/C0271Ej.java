package defpackage;

import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: Ej  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0271Ej extends AbstractC2059ck {
    public final /* synthetic */ C5638xj F;
    public final /* synthetic */ C0515Ij G;

    public C0271Ej(C0515Ij ij, C5638xj xjVar) {
        this.G = ij;
        this.F = xjVar;
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        C5638xj xjVar = this.F;
        float f = ((C1551Zj) this.G.P).W;
        BottomSheet bottomSheet = xjVar.F;
        if (bottomSheet != null) {
            bottomSheet.i0 = f;
            if (bottomSheet.U != 0 && bottomSheet.T <= bottomSheet.l(1)) {
                bottomSheet.u(bottomSheet.T, 1, true);
            }
        }
    }
}
