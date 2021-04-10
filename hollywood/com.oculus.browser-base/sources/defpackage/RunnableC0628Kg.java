package defpackage;

import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: Kg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0628Kg implements Runnable {
    public final AbstractC0749Mg F;
    public final AutocompleteMatch G;

    public RunnableC0628Kg(AbstractC0749Mg mg, AutocompleteMatch autocompleteMatch) {
        this.F = mg;
        this.G = autocompleteMatch;
    }

    public void run() {
        AbstractC0749Mg mg = this.F;
        AutocompleteMatch autocompleteMatch = this.G;
        AbstractC5531x31 x31 = mg.b;
        String str = autocompleteMatch.i;
        C2379ed edVar = (C2379ed) x31;
        if (!edVar.c0) {
            edVar.c0 = true;
            ((C3909na0) edVar.G).h(str);
        }
    }
}
