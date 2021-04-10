package defpackage;

import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: Ig  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0506Ig implements Runnable {
    public final AbstractC0749Mg F;
    public final AutocompleteMatch G;
    public final int H;

    public RunnableC0506Ig(AbstractC0749Mg mg, AutocompleteMatch autocompleteMatch, int i) {
        this.F = mg;
        this.G = autocompleteMatch;
        this.H = i;
    }

    public void run() {
        this.F.l(this.G, this.H);
    }
}
