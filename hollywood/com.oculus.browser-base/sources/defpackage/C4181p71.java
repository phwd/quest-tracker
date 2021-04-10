package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.TabGridIphDialogView;

/* renamed from: p71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4181p71 implements AbstractC3087il0 {
    public final /* synthetic */ C2746gl0 F;
    public final /* synthetic */ C4352q71 G;

    public C4181p71(C4352q71 q71, C2746gl0 gl0) {
        this.G = q71;
        this.F = gl0;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.F.b(uh0, 1);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        TabGridIphDialogView tabGridIphDialogView = this.G.b;
        L6.d(tabGridIphDialogView.M, tabGridIphDialogView.O);
        tabGridIphDialogView.N.stop();
    }
}
