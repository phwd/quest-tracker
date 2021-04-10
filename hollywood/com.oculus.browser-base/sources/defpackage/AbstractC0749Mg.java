package defpackage;

import J.N;
import android.content.Context;
import android.text.Spannable;
import android.text.style.StyleSpan;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.List;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* renamed from: Mg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0749Mg extends AbstractC2506fJ implements B31 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8494a;
    public final AbstractC5531x31 b;
    public final int c;
    public final int d;
    public int e;
    public int f = 0;

    public AbstractC0749Mg(Context context, AbstractC5531x31 x31) {
        this.f8494a = context;
        this.b = x31;
        this.c = context.getResources().getDimensionPixelSize(R.dimen.f23310_resource_name_obfuscated_RES_2131165950);
        this.d = context.getResources().getDimensionPixelSize(R.dimen.f23300_resource_name_obfuscated_RES_2131165949);
        this.e = context.getResources().getDimensionPixelSize(R.dimen.f23260_resource_name_obfuscated_RES_2131165945);
    }

    public static boolean j(Spannable spannable, List list) {
        int i;
        if (list == null) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            C1347Wc wc = (C1347Wc) list.get(i2);
            if ((wc.b & 2) == 2) {
                int i3 = wc.f9157a;
                if (i2 == list.size() - 1) {
                    i = spannable.length();
                } else {
                    i = ((C1347Wc) list.get(i2 + 1)).f9157a;
                }
                spannable.setSpan(new StyleSpan(1), Math.min(i3, spannable.length()), Math.min(i, spannable.length()), 33);
                z = true;
            }
        }
        return z;
    }

    @Override // defpackage.B31
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i) {
        uh0.m(AbstractC0871Og.e, new RunnableC0506Ig(this, autocompleteMatch, i));
        uh0.m(AbstractC0871Og.f, new RunnableC0567Jg(this, autocompleteMatch, i));
        uh0.m(AbstractC0871Og.c, new RunnableC0628Kg(this, autocompleteMatch));
        uh0.l(AbstractC0871Og.d, this.f);
        uh0.m(AbstractC0871Og.b, null);
    }

    @Override // defpackage.AbstractC2677gJ
    public int h() {
        return this.e;
    }

    @Override // defpackage.AbstractC2677gJ, defpackage.AbstractC2506fJ
    public void i() {
        if (!N.M09VlOh_("OmniboxCompactSuggestions")) {
            return;
        }
        if ("semi-compact".equals(N.MMltG$kc("OmniboxCompactSuggestions", "omnibox_compact_suggestions_variant"))) {
            this.f = 1;
            this.e = this.f8494a.getResources().getDimensionPixelSize(R.dimen.f23370_resource_name_obfuscated_RES_2131165956);
            return;
        }
        this.f = 2;
        this.e = this.f8494a.getResources().getDimensionPixelSize(R.dimen.f23280_resource_name_obfuscated_RES_2131165947);
    }

    public void k(UH0 uh0, GURL gurl, X60 x60, Runnable runnable) {
        if (gurl != null && x60 != null) {
            x60.b(gurl, this.c, new C0689Lg(this, uh0, runnable));
        }
    }

    public void l(AutocompleteMatch autocompleteMatch, int i) {
        ((C2379ed) this.b).m(autocompleteMatch, i, autocompleteMatch.j);
    }

    public void m(UH0 uh0, AutocompleteMatch autocompleteMatch, int i) {
        Runnable runnable;
        String str;
        int i2;
        if (autocompleteMatch.u) {
            i2 = R.drawable.f35080_resource_name_obfuscated_RES_2131231548;
            str = this.f8494a.getResources().getString(R.string.f45670_resource_name_obfuscated_RES_2131951884);
            runnable = new RunnableC0384Gg(this, autocompleteMatch, i);
        } else {
            i2 = R.drawable.f28590_resource_name_obfuscated_RES_2131230899;
            str = this.f8494a.getResources().getString(R.string.f45640_resource_name_obfuscated_RES_2131951881, autocompleteMatch.i);
            runnable = new RunnableC0445Hg(this, autocompleteMatch);
        }
        C5191v31 c2 = C5191v31.c(this.f8494a, i2);
        c2.d = true;
        c2.b = true;
        uh0.m(AbstractC0871Og.b, Arrays.asList(new C0810Ng(c2.a(), str, runnable)));
    }
}
