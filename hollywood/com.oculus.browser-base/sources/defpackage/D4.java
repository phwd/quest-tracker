package defpackage;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* renamed from: D4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class D4 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ImageView f7860a;

    public D4(ImageView imageView) {
        this.f7860a = imageView;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        E4.a(this.f7860a, (Drawable) obj);
    }
}
