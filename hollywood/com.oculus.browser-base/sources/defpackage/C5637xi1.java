package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import com.oculus.browser.R;

/* renamed from: xi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5637xi1 extends XK0 {
    public C5637xi1(View view) {
        super(view);
    }

    public final void x(Bitmap bitmap) {
        ImageView imageView = (ImageView) this.G.findViewById(R.id.thumbnail);
        ImageView imageView2 = (ImageView) this.G.findViewById(R.id.gradient_overlay);
        if (bitmap == null) {
            imageView.setImageDrawable(new ColorDrawable(imageView.getResources().getColor(R.color.f12760_resource_name_obfuscated_RES_2131099966)));
        } else {
            imageView.setImageBitmap(bitmap);
        }
        imageView2.setVisibility(bitmap == null ? 8 : 0);
    }
}
