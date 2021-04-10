package defpackage;

import android.content.res.Resources;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.SearchView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.url.GURL;

/* renamed from: l4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3486l4 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C5362w4 w4Var = (C5362w4) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = AbstractC4852t4.b;
        if (kh0 == oh0) {
            w4Var.b = (Callback) uh0.g(oh0);
            return;
        }
        QH0 qh0 = AbstractC4852t4.f11319a;
        if (kh0 != qh0) {
            OH0 oh02 = AbstractC4852t4.d;
            if (kh0 == oh02) {
                Resources resources = w4Var.d.getResources();
                String c = AbstractC1911br1.c(new GURL((String) uh0.g(oh02)), 2);
                String format = String.format(resources.getString(R.string.f46700_resource_name_obfuscated_RES_2131951987), c);
                int indexOf = format.indexOf(c);
                SpannableString spannableString = new SpannableString(format);
                spannableString.setSpan(new StyleSpan(1), indexOf, c.length() + indexOf, 33);
                ((TextView) w4Var.d.findViewById(R.id.sheet_warning)).setText(spannableString);
                return;
            }
            OH0 oh03 = AbstractC4852t4.e;
            if (kh0 == oh03) {
                ((SearchView) w4Var.d.findViewById(R.id.all_passwords_search_view)).setOnQueryTextListener(new C5192v4(w4Var, (Callback) uh0.g(oh03)));
                return;
            }
            OH0 oh04 = AbstractC4852t4.c;
            if (kh0 == oh04) {
                w4Var.c.q0(new C2000cL0(new MW0((C1794b90) uh0.g(oh04), new C5532x4(), new C5702y4()), new C5872z4()));
            }
        } else if (uh0.h(qh0)) {
            ((C5638xj) w4Var.f11516a).j(w4Var.e);
            if (!((C5638xj) w4Var.f11516a).u(w4Var, true)) {
                w4Var.b.onResult(0);
                ((C5638xj) w4Var.f11516a).r(w4Var.e);
            }
        } else {
            ((C5638xj) w4Var.f11516a).p(w4Var, true, 0);
        }
    }
}
