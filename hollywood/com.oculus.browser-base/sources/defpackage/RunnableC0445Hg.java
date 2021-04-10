package defpackage;

import android.text.TextUtils;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: Hg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0445Hg implements Runnable {
    public final AbstractC0749Mg F;
    public final AutocompleteMatch G;

    public RunnableC0445Hg(AbstractC0749Mg mg, AutocompleteMatch autocompleteMatch) {
        this.F = mg;
        this.G = autocompleteMatch;
    }

    public void run() {
        AbstractC0749Mg mg = this.F;
        AutocompleteMatch autocompleteMatch = this.G;
        C2379ed edVar = (C2379ed) mg.b;
        edVar.r(false);
        boolean z = autocompleteMatch.c;
        String str = autocompleteMatch.i;
        if (z) {
            str = TextUtils.concat(str, " ").toString();
        }
        ((C3909na0) edVar.G).h(str);
        String h = ((Oq1) edVar.H).h();
        ((Oq1) edVar.H).g();
        edVar.o(h);
        if (z) {
            AbstractC3535lK0.a("MobileOmniboxRefineSuggestion.Search");
        } else {
            AbstractC3535lK0.a("MobileOmniboxRefineSuggestion.Url");
        }
    }
}
