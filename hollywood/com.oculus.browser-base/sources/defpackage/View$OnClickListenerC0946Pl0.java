package defpackage;

import android.view.View;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* renamed from: Pl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC0946Pl0 implements View.OnClickListener {
    public final C1190Tl0 F;
    public final AutocompleteMatch G;
    public final int H;
    public final GURL I;

    public View$OnClickListenerC0946Pl0(C1190Tl0 tl0, AutocompleteMatch autocompleteMatch, int i, GURL gurl) {
        this.F = tl0;
        this.G = autocompleteMatch;
        this.H = i;
        this.I = gurl;
    }

    public void onClick(View view) {
        C1190Tl0 tl0 = this.F;
        ((C2379ed) tl0.c).m(this.G, this.H, this.I);
    }
}
