package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: ye1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5795ye1 extends AbstractC0749Mg {
    public final boolean g;
    public C2803h4 h;

    public C5795ye1(Context context, AbstractC5531x31 x31) {
        super(context, x31);
        this.g = DeviceFormFactor.a(context);
    }

    @Override // defpackage.B31, defpackage.AbstractC0749Mg
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i) {
        super.a(autocompleteMatch, uh0, i);
        uh0.m(AbstractC0014Ae1.c, this.h);
        uh0.m(AbstractC0014Ae1.b, autocompleteMatch.i);
        C31 c31 = new C31(autocompleteMatch.d);
        AbstractC0749Mg.j(c31, autocompleteMatch.e);
        uh0.m(AbstractC0014Ae1.f7684a, c31);
        C5191v31 c = C5191v31.c(this.f8494a, R.drawable.f32740_resource_name_obfuscated_RES_2131231314);
        c.b = true;
        uh0.m(AbstractC0871Og.f8641a, c.a());
        m(uh0, autocompleteMatch, i);
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void c() {
        this.h = new C2803h4();
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 4;
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i) {
        return this.g && autocompleteMatch.f10861a == 10;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(AbstractC0014Ae1.e);
    }
}
