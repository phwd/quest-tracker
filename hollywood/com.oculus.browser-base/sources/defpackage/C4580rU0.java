package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: rU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4580rU0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        ViewGroup viewGroup = (ViewGroup) obj2;
        KH0 kh0 = (KH0) obj3;
        C5090uU0.w((UH0) obj, viewGroup, kh0);
        if (AU0.f7673a.equals(kh0)) {
            ImageView imageView = (ImageView) viewGroup.findViewById(R.id.icon);
            View findViewById = viewGroup.findViewById(R.id.layout);
            int dimensionPixelSize = ContextUtils.getApplicationContext().getResources().getDimensionPixelSize(R.dimen.f24990_resource_name_obfuscated_RES_2131166118);
            int dimensionPixelSize2 = ContextUtils.getApplicationContext().getResources().getDimensionPixelSize(R.dimen.f24980_resource_name_obfuscated_RES_2131166117);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = dimensionPixelSize;
            layoutParams.width = dimensionPixelSize;
            imageView.requestLayout();
            findViewById.setPadding(0, dimensionPixelSize2, 0, 0);
        }
    }
}
