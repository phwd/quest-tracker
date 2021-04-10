package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.SuggestionAnswer;

/* renamed from: U6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U6 extends AbstractC0749Mg {
    public final Map g = new HashMap();
    public final Qq1 h;
    public final Q31 i;

    public U6(Context context, AbstractC5531x31 x31, Qq1 qq1, Q31 q31) {
        super(context, x31);
        this.h = qq1;
        this.i = q31;
    }

    @Override // defpackage.B31, defpackage.AbstractC0749Mg
    public void a(AutocompleteMatch autocompleteMatch, UH0 uh0, int i2) {
        int i3;
        String str;
        super.a(autocompleteMatch, uh0, i2);
        Context context = this.f8494a;
        String h2 = ((Oq1) this.h).h();
        W6[] w6Arr = new W6[2];
        SuggestionAnswer suggestionAnswer = autocompleteMatch.h;
        if (suggestionAnswer == null) {
            w6Arr[0] = new X6(context, h2, true);
            w6Arr[1] = new X6(context, autocompleteMatch.d, false);
        } else {
            int i4 = suggestionAnswer.f10864a;
            if (i4 == 1) {
                w6Arr[0] = new X6(context, i4, suggestionAnswer.b, true);
                w6Arr[1] = new X6(context, suggestionAnswer.f10864a, suggestionAnswer.c, false);
                w6Arr[0].f = 1;
            } else {
                w6Arr[0] = new X6(context, i4, suggestionAnswer.c, true);
                w6Arr[1] = new X6(context, suggestionAnswer.f10864a, suggestionAnswer.b, false);
                w6Arr[1].f = 1;
                String str2 = w6Arr[1].d;
                w6Arr[1].d = w6Arr[0].d;
                w6Arr[0].d = str2;
            }
        }
        uh0.m(V6.b, w6Arr[0].c);
        uh0.m(V6.e, w6Arr[1].c);
        uh0.m(V6.c, w6Arr[0].d);
        uh0.m(V6.f, w6Arr[1].d);
        uh0.l(V6.f9064a, w6Arr[0].f);
        uh0.l(V6.d, w6Arr[1].f);
        Context context2 = this.f8494a;
        SuggestionAnswer suggestionAnswer2 = autocompleteMatch.h;
        if (suggestionAnswer2 != null) {
            int i5 = suggestionAnswer2.f10864a;
            i3 = R.drawable.f30320_resource_name_obfuscated_RES_2131231072;
            if (i5 == 1) {
                i3 = R.drawable.f29640_resource_name_obfuscated_RES_2131231004;
            } else if (i5 != 2) {
                switch (i5) {
                    case 6:
                        i3 = R.drawable.f33120_resource_name_obfuscated_RES_2131231352;
                        break;
                    case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                        i3 = R.drawable.f33560_resource_name_obfuscated_RES_2131231396;
                        break;
                    case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                        i3 = R.drawable.f33550_resource_name_obfuscated_RES_2131231395;
                        break;
                    case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                        i3 = R.drawable.f30120_resource_name_obfuscated_RES_2131231052;
                        break;
                    case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                        i3 = R.drawable.f30820_resource_name_obfuscated_RES_2131231122;
                        break;
                }
            } else {
                i3 = R.drawable.f32750_resource_name_obfuscated_RES_2131231315;
            }
        } else {
            i3 = R.drawable.f30030_resource_name_obfuscated_RES_2131231043;
        }
        C5191v31 c = C5191v31.c(context2, i3);
        c.d = true;
        uh0.m(AbstractC0871Og.f8641a, c.a());
        m(uh0, autocompleteMatch, i2);
        Object obj = ThreadUtils.f10596a;
        MZ mz = (MZ) this.i.get();
        if (mz != null && autocompleteMatch.a() && (str = autocompleteMatch.h.c.d) != null) {
            if (this.g.containsKey(str)) {
                ((List) this.g.get(str)).add(uh0);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(uh0);
            this.g.put(str, arrayList);
            mz.d(new LZ(str, "AnswerSuggestions", 0, 0, 0), new T6(this, str));
        }
    }

    @Override // defpackage.AbstractC2677gJ
    public int d() {
        return 2;
    }

    @Override // defpackage.B31
    public boolean f(AutocompleteMatch autocompleteMatch, int i2) {
        return autocompleteMatch.a() || autocompleteMatch.f10861a == 18;
    }

    @Override // defpackage.AbstractC2677gJ
    public UH0 g() {
        return new UH0(V6.h);
    }
}
