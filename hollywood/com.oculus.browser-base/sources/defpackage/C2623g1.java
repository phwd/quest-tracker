package defpackage;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* renamed from: g1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2623g1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C5353w1 w1Var = (C5353w1) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = AbstractC5183v1.f11454a;
        if (kh0 == oh0) {
            w1Var.g.setOnClickListener((View.OnClickListener) uh0.g(oh0));
            return;
        }
        SH0 sh0 = AbstractC5183v1.e;
        if (kh0 == sh0) {
            int f = uh0.f(sh0);
            w1Var.e.setDisplayedChild(f);
            View findViewById = w1Var.e.getChildAt(f).findViewById(C5353w1.f11512a[f]);
            findViewById.setFocusable(true);
            findViewById.sendAccessibilityEvent(8);
            return;
        }
        TH0 th0 = AbstractC5183v1.b;
        if (kh0 == th0) {
            C3522lG lGVar = (C3522lG) uh0.g(th0);
            if (lGVar != null) {
                View childAt = w1Var.e.getChildAt(1);
                C4222pM.b(lGVar, w1Var.g);
                ((ImageView) w1Var.g.findViewById(R.id.account_selection_mark)).setImageResource(R.drawable.f30170_resource_name_obfuscated_RES_2131231057);
                ButtonCompat buttonCompat = (ButtonCompat) childAt.findViewById(R.id.account_picker_continue_as_button);
                Activity activity = w1Var.b;
                Object[] objArr = new Object[1];
                String str = lGVar.d;
                if (str == null) {
                    str = lGVar.a();
                }
                objArr[0] = str;
                buttonCompat.setText(activity.getString(R.string.f62150_resource_name_obfuscated_RES_2131953532, objArr));
                return;
            }
            return;
        }
        OH0 oh02 = AbstractC5183v1.c;
        if (kh0 == oh02) {
            View.OnClickListener onClickListener = (View.OnClickListener) uh0.g(oh02);
            for (int i = 0; i < w1Var.e.getChildCount(); i++) {
                ButtonCompat buttonCompat2 = (ButtonCompat) w1Var.e.getChildAt(i).findViewById(R.id.account_picker_continue_as_button);
                if (buttonCompat2 != null) {
                    buttonCompat2.setOnClickListener(onClickListener);
                }
            }
            return;
        }
        OH0 oh03 = AbstractC5183v1.d;
        if (kh0 == oh03) {
            w1Var.h.setOnClickListener((View.OnClickListener) uh0.g(oh03));
        }
    }
}
