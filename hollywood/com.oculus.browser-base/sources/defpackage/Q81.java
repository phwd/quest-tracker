package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;

/* renamed from: Q81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q81 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        ColorStateList colorStateList;
        UH0 uh0 = (UH0) obj;
        ViewGroup viewGroup = (ViewGroup) obj2;
        KH0 kh0 = (KH0) obj3;
        V91.a(uh0, viewGroup, kh0);
        int f = uh0.f(AbstractC5106ub1.f11420a);
        int integer = viewGroup.getResources().getInteger(R.integer.f35900_resource_name_obfuscated_RES_2131492887);
        int integer2 = viewGroup.getResources().getInteger(R.integer.f35920_resource_name_obfuscated_RES_2131492889);
        SelectableTabGridView selectableTabGridView = (SelectableTabGridView) viewGroup.findViewById(R.id.content_view);
        if (AbstractC5106ub1.l == kh0) {
            T91 t91 = new T91(uh0, f, selectableTabGridView);
            U91 u91 = new U91(uh0, f, selectableTabGridView);
            selectableTabGridView.setOnClickListener(t91);
            selectableTabGridView.setOnLongClickListener(u91);
            ImageView imageView = (ImageView) selectableTabGridView.findViewById(R.id.end_button);
            imageView.setOnClickListener(t91);
            imageView.setOnLongClickListener(u91);
            return;
        }
        TH0 th0 = AbstractC5106ub1.m;
        if (th0 == kh0) {
            selectableTabGridView.h((C3209jS0) uh0.g(th0));
            selectableTabGridView.g(Integer.valueOf(f));
            return;
        }
        QH0 qh0 = AbstractC5106ub1.h;
        if (qh0 == kh0) {
            boolean h = uh0.h(qh0);
            ImageView imageView2 = (ImageView) viewGroup.findViewById(R.id.end_button);
            Drawable background = imageView2.getBackground();
            if (h) {
                integer = integer2;
            }
            background.setLevel(integer);
            Drawable mutate = imageView2.getBackground().mutate();
            if (h) {
                colorStateList = (ColorStateList) uh0.g(AbstractC5106ub1.r);
            } else {
                colorStateList = (ColorStateList) uh0.g(AbstractC5106ub1.q);
            }
            mutate.setTintList(colorStateList);
            imageView2.getDrawable().setAlpha(h ? 255 : 0);
            imageView2.setImageTintList(h ? (ColorStateList) uh0.g(AbstractC5106ub1.i) : null);
            if (h) {
                ((L6) imageView2.getDrawable()).start();
            }
        }
    }
}
