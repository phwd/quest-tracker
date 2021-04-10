package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Pair;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: v3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5189v3 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View$OnClickListenerC5699y3 y3Var = (View$OnClickListenerC5699y3) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5869z3.f11718a;
        if (kh0.equals(th0)) {
            String str = (String) uh0.g(th0);
            y3Var.L.setText(str);
            y3Var.f11658J.setText(str);
            return;
        }
        TH0 th02 = AbstractC5869z3.b;
        if (kh0.equals(th02)) {
            y3Var.M.setText((String) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC5869z3.e;
        int i = 8;
        if (kh0.equals(th03)) {
            Pair pair = (Pair) uh0.g(th03);
            Bitmap bitmap = (Bitmap) pair.first;
            if (!((Boolean) pair.second).booleanValue() || Build.VERSION.SDK_INT < 26) {
                y3Var.Q.setImageBitmap(bitmap);
            } else {
                y3Var.Q.setImageIcon(Icon.createWithAdaptiveBitmap(bitmap));
            }
            y3Var.P.setVisibility(8);
            y3Var.Q.setVisibility(0);
            return;
        }
        SH0 sh0 = AbstractC5869z3.f;
        if (kh0.equals(sh0)) {
            int f = uh0.f(sh0);
            y3Var.f11658J.setVisibility(f == 2 ? 0 : 8);
            y3Var.K.setVisibility(f != 2 ? 0 : 8);
            y3Var.M.setVisibility(f == 1 ? 0 : 8);
            y3Var.N.setVisibility(f == 0 ? 0 : 8);
            ImageView imageView = y3Var.O;
            if (f == 0) {
                i = 0;
            }
            imageView.setVisibility(i);
            return;
        }
        QH0 qh0 = AbstractC5869z3.g;
        if (kh0.equals(qh0)) {
            y3Var.R = uh0.h(qh0);
            y3Var.a();
            return;
        }
        TH0 th04 = AbstractC5869z3.i;
        if (kh0.equals(th04)) {
            String str2 = (String) uh0.g(th04);
            y3Var.F.m(AbstractC3258jl0.g, str2);
            y3Var.F.m(AbstractC3258jl0.h, ContextUtils.getApplicationContext().getString(R.string.f46930_resource_name_obfuscated_RES_2131952010, str2));
            return;
        }
        RH0 rh0 = AbstractC5869z3.j;
        if (kh0.equals(rh0)) {
            y3Var.N.setRating(uh0.e(rh0));
            y3Var.O.setImageResource(R.drawable.f29370_resource_name_obfuscated_RES_2131230977);
        }
    }
}
