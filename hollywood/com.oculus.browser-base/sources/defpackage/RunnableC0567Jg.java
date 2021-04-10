package defpackage;

import android.content.res.Resources;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: Jg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0567Jg implements Runnable {
    public final AbstractC0749Mg F;
    public final AutocompleteMatch G;
    public final int H;

    public RunnableC0567Jg(AbstractC0749Mg mg, AutocompleteMatch autocompleteMatch, int i) {
        this.F = mg;
        this.G = autocompleteMatch;
        this.H = i;
    }

    public void run() {
        C2746gl0 gl0;
        AbstractC0749Mg mg = this.F;
        AutocompleteMatch autocompleteMatch = this.G;
        int i = this.H;
        C2379ed edVar = (C2379ed) mg.b;
        Objects.requireNonNull(edVar);
        AbstractC3535lK0.a("MobileOmniboxDeleteGesture");
        if (autocompleteMatch.o && (gl0 = (C2746gl0) edVar.e0.get()) != null) {
            C2209dd ddVar = new C2209dd(edVar, i, autocompleteMatch, gl0);
            Resources resources = edVar.F.getResources();
            int i2 = R.string.f56650_resource_name_obfuscated_RES_2131952982;
            int i3 = autocompleteMatch.f10861a;
            if (i3 == 19 || i3 == 26 || i3 == 27) {
                i2 = R.string.f56660_resource_name_obfuscated_RES_2131952983;
            }
            HH0 hh0 = new HH0(AbstractC3258jl0.r);
            hh0.e(AbstractC3258jl0.f10235a, ddVar);
            hh0.e(AbstractC3258jl0.c, autocompleteMatch.d);
            hh0.d(AbstractC3258jl0.e, resources, i2);
            hh0.d(AbstractC3258jl0.g, resources, R.string.f56550_resource_name_obfuscated_RES_2131952972);
            hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
            hh0.b(AbstractC3258jl0.m, true);
            UH0 a2 = hh0.a();
            edVar.r(false);
            gl0.i(a2, 0, false);
        }
    }
}
