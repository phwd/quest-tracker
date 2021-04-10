package defpackage;

import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: V81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V81 implements QK0 {
    @Override // defpackage.QK0
    public void a(XK0 xk0) {
        ImageView imageView;
        int i = xk0.L;
        if ((i != 1 && i != 0) || (imageView = (ImageView) ((ViewLookupCachingFrameLayout) xk0.G).d(R.id.tab_thumbnail)) == null) {
            return;
        }
        if (AbstractC4772sd1.d()) {
            imageView.setImageDrawable(null);
        } else if (AbstractC4772sd1.i()) {
            imageView.setMinimumHeight(Math.min(imageView.getHeight(), (int) ((((double) imageView.getWidth()) * 1.0d) / ((double) AbstractC4089od0.b((float) AbstractC4772sd1.c.c(), 0.5f, 2.0f)))));
            imageView.setImageDrawable(null);
        } else {
            imageView.setImageDrawable(null);
            imageView.setMinimumHeight(imageView.getWidth());
        }
    }
}
