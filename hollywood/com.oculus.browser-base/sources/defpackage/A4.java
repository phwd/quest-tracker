package defpackage;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;
import org.chromium.url.GURL;

/* renamed from: A4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class A4 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        String str;
        String str2;
        String str3;
        C4 c4;
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = AbstractC4682s4.f11249a;
        C2824hB hBVar = (C2824hB) uh0.g(oh0);
        boolean z = true;
        if (kh0 == AbstractC4682s4.b) {
            boolean h = uh0.h(AbstractC4682s4.c);
            ChipView chipView = (ChipView) view.findViewById(R.id.suggestion_text);
            ChipView chipView2 = (ChipView) view.findViewById(R.id.password_text);
            if (h) {
                chipView2.setOnClickListener(new B4(uh0, hBVar));
                chipView.setOnClickListener(null);
                chipView.setClickable(false);
                return;
            }
            String str4 = hBVar.f10051a;
            if (str4.isEmpty()) {
                c4 = null;
            } else {
                c4 = new C4(uh0, hBVar);
            }
            chipView.setOnClickListener(c4);
            chipView.setClickable(!str4.isEmpty());
            chipView2.setOnClickListener(null);
            chipView2.setClickable(false);
            return;
        }
        MH0 mh0 = AbstractC4682s4.c;
        if (kh0 == mh0) {
            boolean h2 = uh0.h(mh0);
            ChipView chipView3 = (ChipView) view.findViewById(R.id.suggestion_text);
            String str5 = hBVar.f10051a;
            chipView3.setEnabled(!h2 && !str5.isEmpty());
            if (h2 || str5.isEmpty()) {
                z = false;
            }
            chipView3.setClickable(z);
            ChipView chipView4 = (ChipView) view.findViewById(R.id.password_text);
            chipView4.setEnabled(h2);
            chipView4.setClickable(h2);
        } else if (kh0 == oh0) {
            TextView textView = (TextView) view.findViewById(R.id.password_info_title);
            if (hBVar.e) {
                str = hBVar.f;
            } else {
                str = AbstractC1911br1.c(new GURL(hBVar.d), 2);
            }
            textView.setText(str);
            ((ChipView) view.findViewById(R.id.suggestion_text)).F.setText(hBVar.c);
            ChipView chipView5 = (ChipView) view.findViewById(R.id.password_text);
            boolean isEmpty = hBVar.b.isEmpty();
            if (!isEmpty) {
                chipView5.F.setTransformationMethod(new PasswordTransformationMethod());
            }
            TextView textView2 = chipView5.F;
            if (isEmpty) {
                str2 = view.getContext().getString(R.string.f46670_resource_name_obfuscated_RES_2131951984);
            } else {
                str2 = hBVar.b;
            }
            textView2.setText(str2);
            C3884nO nOVar = new C3884nO(view.getContext());
            ImageView imageView = (ImageView) view.findViewById(R.id.favicon);
            if (hBVar.e) {
                str3 = hBVar.f;
            } else {
                str3 = hBVar.d;
            }
            E4.a(imageView, nOVar.b(str3));
            if (!hBVar.e) {
                nOVar.a(hBVar.d, new D4(imageView));
            }
        }
    }
}
