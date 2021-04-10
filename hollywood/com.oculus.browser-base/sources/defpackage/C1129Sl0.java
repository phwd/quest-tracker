package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Sl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1129Sl0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        AbstractC5127ui1 ui1 = (AbstractC5127ui1) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5977zi1.f11761a;
        if (kh0 == th0) {
            ((TextView) ui1.findViewById(R.id.tile_view_title)).setText((CharSequence) uh0.g(th0));
            return;
        }
        SH0 sh0 = AbstractC5977zi1.b;
        if (kh0 == sh0) {
            ((TextView) ui1.findViewById(R.id.tile_view_title)).setLines(uh0.f(sh0));
            return;
        }
        TH0 th02 = AbstractC5977zi1.c;
        if (kh0 == th02) {
            ((ImageView) ui1.findViewById(R.id.tile_view_icon)).setImageDrawable((Drawable) uh0.g(th02));
            return;
        }
        QH0 qh0 = AbstractC5977zi1.e;
        if (kh0 == qh0) {
            ui1.findViewById(R.id.offline_badge).setVisibility(uh0.h(qh0) ? 0 : 8);
            return;
        }
        QH0 qh02 = AbstractC5977zi1.d;
        if (kh0 == qh02) {
            boolean h = uh0.h(qh02);
            int dimensionPixelSize = ui1.getResources().getDimensionPixelSize(h ? R.dimen.f26160_resource_name_obfuscated_RES_2131166235 : R.dimen.f26170_resource_name_obfuscated_RES_2131166236);
            int dimensionPixelOffset = ui1.getResources().getDimensionPixelOffset(h ? R.dimen.f26120_resource_name_obfuscated_RES_2131166231 : R.dimen.f26140_resource_name_obfuscated_RES_2131166233);
            View findViewById = ui1.findViewById(R.id.tile_view_icon);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
            marginLayoutParams.width = dimensionPixelSize;
            marginLayoutParams.height = dimensionPixelSize;
            marginLayoutParams.topMargin = dimensionPixelOffset;
            findViewById.setLayoutParams(marginLayoutParams);
            return;
        }
        TH0 th03 = AbstractC5977zi1.g;
        if (kh0 == th03) {
            ui1.H = (Runnable) uh0.g(th03);
            return;
        }
        TH0 th04 = AbstractC5977zi1.h;
        if (kh0 == th04) {
            ui1.setOnClickListener((View.OnClickListener) uh0.g(th04));
            return;
        }
        TH0 th05 = AbstractC5977zi1.i;
        if (kh0 == th05) {
            ui1.setOnLongClickListener((View.OnLongClickListener) uh0.g(th05));
            return;
        }
        TH0 th06 = AbstractC5977zi1.j;
        if (kh0 == th06) {
            ui1.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) uh0.g(th06));
            return;
        }
        TH0 th07 = AbstractC5977zi1.f;
        if (kh0 == th07) {
            ui1.setContentDescription((CharSequence) uh0.g(th07));
        }
    }
}
