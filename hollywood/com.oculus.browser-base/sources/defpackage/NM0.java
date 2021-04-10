package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: NM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NM0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        View findViewById = view.findViewById(R.id.menu_row_text);
        TH0 th0 = YM0.f9269a;
        if (kh0 == th0) {
            ((TextView) findViewById).setText((CharSequence) uh0.g(th0));
        }
        TH0 th02 = ZM0.d;
        if (kh0 == th02) {
            Drawable drawable = (Drawable) uh0.g(th02);
            ImageView imageView = (ImageView) view.findViewById(R.id.menu_row_share_icon);
            imageView.setImageDrawable(drawable);
            imageView.setVisibility(drawable != null ? 0 : 8);
            int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.f24780_resource_name_obfuscated_RES_2131166097);
            view.findViewById(R.id.menu_row_text).setPaddingRelative(dimensionPixelSize, 0, drawable != null ? 0 : dimensionPixelSize, 0);
            return;
        }
        TH0 th03 = ZM0.e;
        if (kh0 == th03) {
            ((ImageView) view.findViewById(R.id.menu_row_share_icon)).setContentDescription(view.getContext().getString(R.string.f45560_resource_name_obfuscated_RES_2131951873, uh0.g(th03)));
            return;
        }
        TH0 th04 = ZM0.g;
        if (kh0 == th04) {
            view.findViewById(R.id.menu_row_share_icon).setOnClickListener((View.OnClickListener) uh0.g(th04));
        }
    }
}
