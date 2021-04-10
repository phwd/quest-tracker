package defpackage;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* renamed from: D71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class D71 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ImageView f7868a;

    public D71(ImageView imageView) {
        this.f7868a = imageView;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ImageView imageView = this.f7868a;
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null) {
            E71.e(imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
