package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Arrays;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.url.GURL;

/* renamed from: YJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YJ extends AbstractC0749Mg {
    public final Context g;
    public final AbstractC0920Pc h;
    public final Q31 i;
    public final Q31 j;
    public final Q31 k;
    public boolean l;
    public GURL m;
    public String n;

    public YJ(Context context, AbstractC5531x31 x31, AbstractC0920Pc pc, Q31 q31, Q31 q312, Q31 q313) {
        super(context, x31);
        this.g = context;
        this.h = pc;
        this.i = q31;
        this.k = q312;
        this.j = q313;
    }

    @Override // defpackage.B31, defpackage.AbstractC0749Mg
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i2) {
        super.a(autocompleteMatch, uh0, i2);
        if (this.n == null) {
            this.n = ((Tab) this.k.get()).getTitle();
        }
        uh0.m(D31.c, new C31(this.n));
        uh0.m(D31.d, new C31(this.m.h()));
        C5191v31 c = C5191v31.c(this.f8494a, R.drawable.f30310_resource_name_obfuscated_RES_2131231071);
        c.b = true;
        uh0.m(AbstractC0871Og.f8641a, c.a());
        Context context = this.g;
        C5191v31 c2 = C5191v31.c(this.f8494a, R.drawable.f32630_resource_name_obfuscated_RES_2131231303);
        c2.d = true;
        c2.b = true;
        Context context2 = this.g;
        C5191v31 c3 = C5191v31.c(this.f8494a, R.drawable.f29810_resource_name_obfuscated_RES_2131231021);
        c3.d = true;
        c3.b = true;
        Context context3 = this.g;
        C5191v31 c4 = C5191v31.c(this.f8494a, R.drawable.f28290_resource_name_obfuscated_RES_2131230869);
        c4.d = true;
        c4.b = true;
        uh0.m(AbstractC0871Og.b, Arrays.asList(new C0810Ng(context, c2.a(), R.string.f54850_resource_name_obfuscated_RES_2131952802, new VJ(this)), new C0810Ng(context2, c3.a(), R.string.f50210_resource_name_obfuscated_RES_2131952338, new WJ(this)), new C0810Ng(context3, c4.a(), R.string.f48060_resource_name_obfuscated_RES_2131952123, new XJ(this))));
        k(uh0, this.m, (X60) this.i.get(), null);
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void b(boolean z) {
        if (!z) {
            this.n = null;
            this.l = false;
        }
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 1;
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i2) {
        Tab tab;
        boolean z;
        if (i2 != 0 || (tab = (Tab) this.k.get()) == null || !tab.isInitialized() || tab.isNativePage() || C3372kO0.W(tab)) {
            return false;
        }
        if (tab.a() && !N.M09VlOh_("OmniboxSearchReadyIncognito")) {
            return false;
        }
        GURL url = tab.getUrl();
        int i3 = autocompleteMatch.f10861a;
        if (i3 == 0) {
            z = autocompleteMatch.j.equals(url);
        } else if (i3 != 6) {
            z = false;
        } else {
            String str = autocompleteMatch.i;
            TemplateUrlService a2 = AbstractC0444Hf1.a();
            z = TextUtils.equals(str, N.MfK2IDmL(a2.c, a2, url));
        }
        if (!z) {
            return false;
        }
        this.m = autocompleteMatch.j;
        if (!this.l) {
            this.l = true;
            ((C3909na0) this.h).h("");
        }
        return true;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(D31.g);
    }

    @Override // defpackage.AbstractC0749Mg
    public void l(AutocompleteMatch autocompleteMatch, int i2) {
        ((C2379ed) this.b).m(autocompleteMatch, i2, autocompleteMatch.j);
        AbstractC3535lK0.a("Omnibox.EditUrlSuggestion.Tap");
    }
}
