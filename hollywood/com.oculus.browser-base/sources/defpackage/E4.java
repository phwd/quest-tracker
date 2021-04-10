package defpackage;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.oculus.browser.R;

/* renamed from: E4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class E4 {
    public static void a(ImageView imageView, Drawable drawable) {
        int dimensionPixelSize = imageView.getContext().getResources().getDimensionPixelSize(R.dimen.f20290_resource_name_obfuscated_RES_2131165648);
        if (drawable != null) {
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        }
        imageView.setImageDrawable(drawable);
    }
}
