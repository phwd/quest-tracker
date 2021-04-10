package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: an0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1718an0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC2752gn0.f10021a;
        if (th0 == kh0) {
            ((ImageView) view.findViewById(R.id.favicon_img)).setImageDrawable((Drawable) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC2752gn0.b;
        if (th02 == kh0) {
            ((TextView) view.findViewById(R.id.entry_title)).setText((CharSequence) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC2752gn0.c;
        if (th03 == kh0) {
            view.setOnClickListener((View.OnClickListener) uh0.g(th03));
        }
    }
}
