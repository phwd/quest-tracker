package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* renamed from: vL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5241vL extends AbstractC0749Mg {
    public final Map g = new HashMap();
    public final Q31 h;

    public C5241vL(Context context, AbstractC5531x31 x31, Q31 q31) {
        super(context, x31);
        this.h = q31;
    }

    @Override // defpackage.B31, defpackage.AbstractC0749Mg
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i) {
        MZ mz;
        super.a(autocompleteMatch, uh0, i);
        C5191v31 c = C5191v31.c(this.f8494a, R.drawable.f32740_resource_name_obfuscated_RES_2131231314);
        c.b = true;
        C5361w31 a2 = c.a();
        TH0 th0 = AbstractC0871Og.f8641a;
        uh0.m(th0, a2);
        if (SysUtils.a() >= 1572864 || AbstractC1575Zv.e().g("disable-low-end-device-mode")) {
            String str = autocompleteMatch.l;
            if (!TextUtils.isEmpty(str)) {
                try {
                    uh0.m(th0, new C5361w31(new ColorDrawable(Color.parseColor(str)), true, true, false, 0, null));
                } catch (IllegalArgumentException unused) {
                    AbstractC1220Ua0.d("EntitySP", AbstractC2531fV.f("Failed to parse dominant color: ", str), new Object[0]);
                }
            }
            Object obj = ThreadUtils.f10596a;
            GURL gurl = autocompleteMatch.k;
            if (!gurl.j() && (mz = (MZ) this.h.get()) != null) {
                if (this.g.containsKey(gurl)) {
                    ((List) this.g.get(gurl)).add(uh0);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(uh0);
                    this.g.put(gurl, arrayList);
                    String h2 = gurl.h();
                    int i2 = this.d;
                    mz.d(new LZ(h2, "EntitySuggestions", i2, i2, 0), new C5071uL(this, gurl));
                }
            }
        }
        uh0.m(AbstractC5411wL.f11541a, autocompleteMatch.d);
        uh0.m(AbstractC5411wL.b, autocompleteMatch.f);
        m(uh0, autocompleteMatch, i);
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 3;
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i) {
        return autocompleteMatch.f10861a == 9;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(AbstractC5411wL.d);
    }
}
