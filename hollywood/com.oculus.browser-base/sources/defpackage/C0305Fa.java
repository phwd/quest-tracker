package defpackage;

import android.graphics.drawable.Drawable;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;

/* renamed from: Fa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0305Fa extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AsyncImageView f8023a;
    public final Object b;

    public C0305Fa(AsyncImageView asyncImageView, Object obj) {
        this.f8023a = asyncImageView;
        this.b = obj;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AsyncImageView asyncImageView = this.f8023a;
        Object obj2 = this.b;
        Drawable drawable = (Drawable) obj;
        if (asyncImageView.V == obj2 && asyncImageView.U) {
            Drawable drawable2 = null;
            asyncImageView.T = null;
            asyncImageView.U = false;
            asyncImageView.setImageDrawable(drawable);
            asyncImageView.V = obj2;
            if (drawable == null) {
                drawable2 = asyncImageView.Q;
            }
            asyncImageView.P.d(drawable2);
        }
    }
}
