package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;
import org.chromium.url.GURL;

/* renamed from: qs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4475qs implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C4985ts f11169a;
    public final Callback b;
    public final Resources c;
    public final String d;

    public C4475qs(C4985ts tsVar, Callback callback, Resources resources, String str) {
        this.f11169a = tsVar;
        this.b = callback;
        this.c = resources;
        this.d = str;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        C4985ts tsVar = this.f11169a;
        Callback callback = this.b;
        Resources resources = this.c;
        String str2 = this.d;
        if (tsVar.e()) {
            callback.onResult(AbstractC2870hT0.c(tsVar.k, R.drawable.f34660_resource_name_obfuscated_RES_2131231506, R.color.f12850_resource_name_obfuscated_RES_2131099975));
        } else if (bitmap != null) {
            callback.onResult(new BitmapDrawable(resources, bitmap));
        } else {
            if (AbstractC5154ur1.b.contains(new GURL(str2).g())) {
                callback.onResult(AbstractC5544x8.a(tsVar.k, R.drawable.f28750_resource_name_obfuscated_RES_2131230915));
            } else {
                callback.onResult(null);
            }
        }
    }
}
