package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: KM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class KM0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = XM0.f9204a;
        int i = 8;
        int i2 = 0;
        if (kh0 == th0) {
            TextView textView = (TextView) view.findViewById(R.id.menu_header_title);
            textView.setText((CharSequence) uh0.g(th0));
            if (!TextUtils.isEmpty((CharSequence) uh0.g(th0))) {
                i = 0;
            }
            textView.setVisibility(i);
            return;
        }
        SH0 sh0 = XM0.b;
        if (kh0 == sh0) {
            int f = uh0.f(sh0);
            TextView textView2 = (TextView) view.findViewById(R.id.menu_header_title);
            textView2.setMaxLines(f);
            if (f == Integer.MAX_VALUE) {
                textView2.setEllipsize(null);
            } else {
                textView2.setEllipsize(TextUtils.TruncateAt.END);
            }
        } else {
            TH0 th02 = XM0.c;
            if (kh0 == th02) {
                TextView textView3 = (TextView) view.findViewById(R.id.menu_header_url);
                textView3.setText((CharSequence) uh0.g(th02));
                if (!TextUtils.isEmpty((CharSequence) uh0.g(th02))) {
                    i = 0;
                }
                textView3.setVisibility(i);
                return;
            }
            TH0 th03 = XM0.d;
            if (kh0 == th03) {
                view.findViewById(R.id.title_and_url).setOnClickListener((View.OnClickListener) uh0.g(th03));
                return;
            }
            SH0 sh02 = XM0.e;
            if (kh0 == sh02) {
                int f2 = uh0.f(sh02);
                TextView textView4 = (TextView) view.findViewById(R.id.menu_header_url);
                textView4.setMaxLines(f2);
                if (f2 == Integer.MAX_VALUE) {
                    textView4.setEllipsize(null);
                } else {
                    textView4.setEllipsize(TextUtils.TruncateAt.END);
                }
            } else {
                TH0 th04 = XM0.f;
                if (kh0 == th04) {
                    Bitmap bitmap = (Bitmap) uh0.g(th04);
                    if (bitmap != null) {
                        ((ImageView) view.findViewById(R.id.menu_header_image)).setImageBitmap(bitmap);
                        return;
                    }
                    return;
                }
                QH0 qh0 = XM0.g;
                if (kh0 == qh0) {
                    boolean h = uh0.h(qh0);
                    View findViewById = view.findViewById(R.id.circle_background);
                    if (!h) {
                        i2 = 4;
                    }
                    findViewById.setVisibility(i2);
                    return;
                }
                SH0 sh03 = XM0.h;
                if (kh0 == sh03) {
                    int f3 = uh0.f(sh03);
                    View findViewById2 = view.findViewById(R.id.menu_header_performance_info);
                    if (f3 == 2) {
                        i = 0;
                    }
                    findViewById2.setVisibility(i);
                }
            }
        }
    }
}
