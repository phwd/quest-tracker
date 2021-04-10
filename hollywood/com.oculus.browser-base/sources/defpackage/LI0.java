package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: LI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LI0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        RI0 ri0 = (RI0) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5869z3.f11718a;
        if (kh0.equals(th0)) {
            ((TextView) ri0.b.findViewById(R.id.app_name)).setText((String) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC5869z3.b;
        if (kh0.equals(th02)) {
            ((TextView) ri0.b.findViewById(R.id.app_origin)).setText((String) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC5869z3.c;
        int i = 8;
        if (kh0.equals(th03)) {
            String str = (String) uh0.g(th03);
            TextView textView = (TextView) ri0.c.findViewById(R.id.categories);
            textView.setText(ri0.f8825a.getString(R.string.f59640_resource_name_obfuscated_RES_2131953281, str));
            if (!str.isEmpty()) {
                i = 0;
            }
            textView.setVisibility(i);
            return;
        }
        TH0 th04 = AbstractC5869z3.d;
        if (kh0.equals(th04)) {
            String str2 = (String) uh0.g(th04);
            TextView textView2 = (TextView) ri0.c.findViewById(R.id.description);
            textView2.setText(str2);
            if (!str2.isEmpty()) {
                i = 0;
            }
            textView2.setVisibility(i);
            return;
        }
        TH0 th05 = AbstractC5869z3.e;
        if (kh0.equals(th05)) {
            Pair pair = (Pair) uh0.g(th05);
            Bitmap bitmap = (Bitmap) pair.first;
            boolean booleanValue = ((Boolean) pair.second).booleanValue();
            ImageView imageView = (ImageView) ri0.b.findViewById(R.id.app_icon);
            if (!booleanValue || Build.VERSION.SDK_INT < 26) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageIcon(Icon.createWithAdaptiveBitmap(bitmap));
            }
            imageView.setVisibility(0);
            return;
        }
        QH0 qh0 = AbstractC5869z3.g;
        if (kh0.equals(qh0)) {
            ri0.b.findViewById(R.id.button_install).setEnabled(uh0.h(qh0));
            return;
        }
        TH0 th06 = AbstractC5869z3.h;
        if (kh0.equals(th06)) {
            View.OnClickListener onClickListener = (View.OnClickListener) uh0.g(th06);
            ri0.b.findViewById(R.id.button_install).setOnClickListener(onClickListener);
            ri0.b.findViewById(R.id.drag_handlebar).setOnClickListener(onClickListener);
        }
    }
}
