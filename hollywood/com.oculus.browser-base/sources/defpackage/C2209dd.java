package defpackage;

import J.N;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: dd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2209dd implements AbstractC3087il0 {
    public final /* synthetic */ int F;
    public final /* synthetic */ AutocompleteMatch G;
    public final /* synthetic */ C2746gl0 H;
    public final /* synthetic */ C2379ed I;

    public C2209dd(C2379ed edVar, int i, AutocompleteMatch autocompleteMatch, C2746gl0 gl0) {
        this.I = edVar;
        this.F = i;
        this.G = autocompleteMatch;
        this.H = gl0;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            AbstractC3535lK0.a("MobileOmniboxDeleteRequested");
            AutocompleteController autocompleteController = this.I.P;
            int i2 = this.F;
            int hashCode = this.G.hashCode();
            long j = autocompleteController.f10726a;
            if (j != 0) {
                N.MIy8Zrdo(j, autocompleteController, i2, hashCode);
            }
            this.H.b(uh0, 1);
        } else if (i == 1) {
            this.H.b(uh0, 2);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
    }
}
