package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Sg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1115Sg implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        Drawable drawable;
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TextView textView = (TextView) view.findViewById(R.id.menu_item_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.menu_item_icon);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.menu_item_end_icon);
        SH0 sh0 = Y80.f9255a;
        if (kh0 == sh0) {
            textView.setText(uh0.f(sh0));
            return;
        }
        TH0 th0 = Y80.b;
        if (kh0 == th0) {
            textView.setText((CharSequence) uh0.g(th0));
            return;
        }
        SH0 sh02 = Y80.c;
        if (kh0 == sh02 || kh0 == Y80.d) {
            int f = uh0.f((NH0) kh0);
            if (f == 0) {
                drawable = null;
            } else {
                drawable = AbstractC5544x8.a(view.getContext(), f);
            }
            if (drawable == null) {
                return;
            }
            if (kh0 == sh02) {
                imageView.setImageDrawable(drawable);
                textView.setPaddingRelative(view.getResources().getDimensionPixelOffset(R.dimen.f20670_resource_name_obfuscated_RES_2131165686), textView.getPaddingTop(), textView.getPaddingEnd(), textView.getPaddingBottom());
                imageView.setVisibility(0);
                imageView2.setVisibility(8);
                return;
            }
            imageView2.setImageDrawable(drawable);
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
            return;
        }
        SH0 sh03 = Y80.e;
        if (kh0 == sh03) {
            Context context = view.getContext();
            int f2 = uh0.f(sh03);
            Object obj4 = K2.f8337a;
            imageView.setImageTintList(context.getColorStateList(f2));
            imageView2.setImageTintList(view.getContext().getColorStateList(uh0.f(sh03)));
            return;
        }
        QH0 qh0 = Y80.g;
        if (kh0 == qh0) {
            textView.setEnabled(uh0.h(qh0));
            imageView.setEnabled(uh0.h(qh0));
            imageView2.setEnabled(uh0.h(qh0));
        }
    }
}
