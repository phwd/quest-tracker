package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: E31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class E31 {
    public static void a(UH0 uh0, View view, KH0 kh0) {
        TH0 th0 = D31.c;
        if (kh0 == th0) {
            ((TextView) view.findViewById(R.id.line_1)).setText((CharSequence) uh0.g(th0));
        } else if (kh0 == AbstractC4851t31.f11318a) {
            b(view, uh0);
        } else {
            QH0 qh0 = D31.b;
            int i = 0;
            if (kh0 == qh0) {
                b(view, uh0);
                boolean h = uh0.h(qh0);
                TextView textView = (TextView) view.findViewById(R.id.line_2);
                if (!h) {
                    i = 3;
                }
                textView.setTextDirection(i);
                return;
            }
            TH0 th02 = D31.d;
            if (kh0 == th02) {
                TextView textView2 = (TextView) view.findViewById(R.id.line_2);
                C31 c31 = (C31) uh0.g(th02);
                if (!TextUtils.isEmpty(c31)) {
                    textView2.setText(c31);
                    textView2.setVisibility(0);
                    return;
                }
                textView2.setVisibility(8);
                return;
            }
            QH0 qh02 = D31.e;
            if (kh0 == qh02) {
                ((TextView) view.findViewById(R.id.line_1)).setMaxLines(uh0.h(qh02) ? 2 : 1);
            }
        }
    }

    public static void b(View view, UH0 uh0) {
        boolean h = uh0.h(D31.b);
        boolean z = !AbstractC4476qs0.a(uh0.f(AbstractC4851t31.f11318a));
        TextView textView = (TextView) view.findViewById(R.id.line_1);
        TextView textView2 = (TextView) view.findViewById(R.id.line_2);
        int i = R.color.f11570_resource_name_obfuscated_RES_2131099847;
        textView.setTextColor(view.getResources().getColor(z ? R.color.f11470_resource_name_obfuscated_RES_2131099837 : R.color.f11570_resource_name_obfuscated_RES_2131099847));
        if (!h) {
            i = z ? R.color.f15000_resource_name_obfuscated_RES_2131100190 : R.color.f15010_resource_name_obfuscated_RES_2131100191;
        } else if (z) {
            i = R.color.f11670_resource_name_obfuscated_RES_2131099857;
        }
        textView2.setTextColor(view.getResources().getColor(i));
    }
}
