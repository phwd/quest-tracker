package defpackage;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.base.Callback;

/* renamed from: w21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5358w21 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5698y21 f11514a;
    public final Callback b;

    public C5358w21(C5698y21 y21, Callback callback) {
        this.f11514a = y21;
        this.b = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5698y21 y21 = this.f11514a;
        Callback callback = this.b;
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null || y21.P) {
            callback.onResult(new C5868z21(R.drawable.f32580_resource_name_obfuscated_RES_2131231298, y21.a(R.drawable.f32580_resource_name_obfuscated_RES_2131231298)));
        } else {
            callback.onResult(new C5868z21(y21.f0, bitmap, 0));
        }
    }
}
