package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: g61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2640g61 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9978a;

    public C2640g61(Callback callback) {
        this.f9978a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f9978a;
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap != null) {
            TabContentManager.a(1);
        } else {
            TabContentManager.a(2);
        }
        callback.onResult(bitmap);
    }
}
