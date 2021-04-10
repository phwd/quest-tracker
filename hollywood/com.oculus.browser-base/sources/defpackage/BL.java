package defpackage;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: BL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BL implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final CL f7731a;
    public final Callback b;

    public BL(CL cl, Callback callback) {
        this.f7731a = cl;
        this.b = callback;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        Object obj;
        CL cl = this.f7731a;
        Callback callback = this.b;
        if (bitmap != null) {
            obj = AbstractC4055oO.b(cl.f7803a.getResources(), bitmap);
        } else {
            obj = AbstractC2417ep1.f(cl.f7803a, R.drawable.f30310_resource_name_obfuscated_RES_2131231071, R.color.f11390_resource_name_obfuscated_RES_2131099829);
        }
        callback.onResult(obj);
    }
}
